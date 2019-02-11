package com.zc.mall.mall.service.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.mall.constant.BaseMallConstant;
import com.zc.mall.mall.constant.OrderInfoLogTypeEnum;
import com.zc.mall.mall.dao.*;
import com.zc.mall.mall.entity.*;
import com.zc.mall.mall.model.GoodsSkuModel;
import com.zc.mall.mall.model.OrderGoodsCommentModel;
import com.zc.mall.mall.model.OrderGoodsModel;
import com.zc.mall.mall.service.OrderGoodsCommentService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单评论
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@Service
public class OrderGoodsCommentServiceImpl implements OrderGoodsCommentService {

    @Resource
    private OrderGoodsCommentDao orderGoodsCommentDao;
    @Resource
    private UserDao userDao;
    @Resource
    private OrderGoodsDao orderGoodsDao;
    @Resource
    private OperatorDao operatorDao;
    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private OrderInfoLogDao orderInfoLogDao;
    @Resource
    private GoodsSkuSpecValueDao goodsSkuSpecValueDao;

    /**
     * 自动默认评价
     */
    @Override
    @Transactional
    public void autoDefaultComment() {
        List<OrderInfo> orderInfoList = orderInfoDao.findWaitComment();
        // 循环订单
        for (OrderInfo orderInfo : orderInfoList) {
            OrderInfoLog orderInfoLog = orderInfoLogDao.findOrderInfoLog(OrderInfoLogTypeEnum.ORDER_INFO_TYPE_RECEIVE.getOrderInfoLogType(), orderInfo.getId());
            if (orderInfoLog == null) {
                continue;
            }
            int betweenDays = DateUtil.daysBetween(DateUtil.getDayStartTime(orderInfoLog.getAddTime().getTime() / 1000), DateUtil.getDayStartTime(0));
            // 订单收货时间判断
            if (betweenDays <= 7) {
                continue;
            }
            List<OrderGoods> orderGoodsList = orderInfo.getOrderGoodsList();
            // 循环订单商品
            for (OrderGoods orderGoods : orderGoodsList) {
                List<OrderGoodsComment> orderGoodsCommentList = orderGoods.getOrderGoodsCommentList();
                if (orderGoodsCommentList.size() <= 0) {
                    OrderGoodsCommentModel model = new OrderGoodsCommentModel();
                    model.setContent("此用户默认好评");
                    model.setGrade(5);
                    model.setOrderGoods(orderGoods);
                    model.setType(BaseMallConstant.ORDERGOODSCOMMENTTYPE_USER);
                    model.setUser(orderInfo.getUser());
                    add(model);
                }
            }
        }
    }

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result list(OrderGoodsCommentModel model) {
        PageDataList<OrderGoodsComment> pageDataList = orderGoodsCommentDao.list(model);
        PageDataList<OrderGoodsCommentModel> pageDataList_ = new PageDataList<>();
        pageDataList_.setPage(pageDataList.getPage());
        List<OrderGoodsCommentModel> list = new ArrayList<>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (OrderGoodsComment orderGoodsComment : pageDataList.getList()) {
                OrderGoodsCommentModel model_ = OrderGoodsCommentModel.instance(orderGoodsComment);
                OrderGoodsModel orderGoodsModel = OrderGoodsModel.instance(model_.getOrderGoods());
                GoodsSkuModel goodsSkuModel = GoodsSkuModel.instance(orderGoodsModel.getGoodsSku());
                GoodsSkuSpecValue capacityValue = goodsSkuSpecValueDao.findBySku(goodsSkuModel.getId(), "spec_1_capacity_1812010048954322");
                goodsSkuModel.setCapacityValue(capacityValue==null?"":capacityValue.getGoodsSpecValue().getValue());
                orderGoodsModel.setGoodsSkuModel(goodsSkuModel);
                model_.setOrderGoodsModel(orderGoodsModel);
                UserModel userModel = UserModel.instance(model_.getUser());
                if (orderGoodsComment.getUser() != null && orderGoodsComment.getUser().getUserInfo() != null) {
                    userModel.setInfoModel(UserInfoModel.instance(orderGoodsComment.getUser().getUserInfo()));
                }
                OrderGoodsCommentModel replyComment = getReplyComment(model_);
                if (replyComment != null) {
                    replyComment.setOperatorModel(OperatorModel.instance(replyComment.getOperator()));
                }
                model_.setReplyRecomment(replyComment);
                model_.setUserModel(userModel);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 获取评论的回复
     *
     * @param model_
     * @return
     */
    private OrderGoodsCommentModel getReplyComment(OrderGoodsCommentModel model_) {
        return orderGoodsCommentDao.getReplyComment(model_);
    }

    /**
     * 评论权限查询
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public boolean isComment(OrderGoodsCommentModel model) {
        if (model.getOrderGoods() == null || model.getOrderGoods().getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        OrderGoods orderGoods = orderGoodsDao.find(model.getOrderGoods().getId());
        if (orderGoods == null) {
            throw new BusinessException("参数错误！");
        }
        OrderInfo orderInfo = orderGoods.getOrderInfo();
        model.setOrderGoods(orderGoods);

        boolean flag = false;
        // 评价次数
        int count = -1;
        switch (model.getType()) {
            // 用户评价
            case BaseMallConstant.ORDERGOODSCOMMENTTYPE_USER:
                if (model.getUser() == null || model.getUser().getId() <= 0) {
                    throw new BusinessException("参数错误！");
                }
                User user = userDao.find(model.getUser().getId());
                if (user == null) {
                    throw new BusinessException("参数错误！");
                }
                if (orderInfo.getUser().getId() != model.getUser().getId()) {
                    throw new BusinessException("参数错误！");
                }
                model.setUser(user);
                count = orderGoodsCommentDao.countByModel(model);
                if (count == 0) {
                    flag = true;
                }
                break;
            // 平台回复
            case BaseMallConstant.ORDERGOODSCOMMENTTYPE_OPERATOR:
                if (model.getOperator() == null || model.getOperator().getId() <= 0) {
                    throw new BusinessException("参数错误！");
                }
                Operator operator = operatorDao.find(model.getOperator().getId());
                if (operator == null) {
                    throw new BusinessException("参数错误！");
                }
                if (model.getPid() <= 0) {
                    throw new BusinessException("参数错误！");
                }
                count = orderGoodsCommentDao.countByModel(model);
                if (count == 0) {
                    flag = true;
                }
                break;
            default:
                break;
        }
        return flag;
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(OrderGoodsCommentModel model) {
        this.checkAdd(model);
        this.initAdd(model);
        orderGoodsCommentDao.save(model.prototype());
        return Result.success();
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(OrderGoodsCommentModel model) {
        OrderGoodsComment orderGoodsComment = this.checkUpdate(model);
        this.initUpdate(model, orderGoodsComment);
        orderGoodsCommentDao.update(orderGoodsComment);
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(OrderGoodsCommentModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        OrderGoodsComment orderGoodsComment = orderGoodsCommentDao.find(model.getId());
        if (orderGoodsComment == null) {
            return Result.error("参数错误！");
        }
        return Result.success().setData(OrderGoodsCommentModel.instance(orderGoodsComment));
    }

    /**
     * 校验
     *
     * @param model
     */
    private void checkAdd(OrderGoodsCommentModel model) {
        // 判断是否可以评价
        if (!isComment(model)) {
            throw new BusinessException("当前无法评价！");
        }
        if (StringUtil.isBlank(model.getContent())) {
            throw new BusinessException("请填写内容！");
        }
        if (model.getType() == BaseMallConstant.ORDERGOODSCOMMENTTYPE_USER && model.getGrade() <= 0) {
            throw new BusinessException("请填写评分！");
        }
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initAdd(OrderGoodsCommentModel model) {
        model.setState(BaseConstant.BUSINESS_STATE_WAIT);
        if (model.getOperator() != null && model.getOperator().getId() > 0) {
            model.setState(BaseConstant.BUSINESS_STATE_YES);
        }
        model.setAddTime(DateUtil.getNow());
        try {
            String encode = URLEncoder.encode(model.getContent(), "utf-8");
            model.setContent(encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (model.getType() == BaseMallConstant.ORDERGOODSCOMMENTTYPE_OPERATOR) {
            OrderGoodsComment orderGoodsComment = orderGoodsCommentDao.find((long)model.getPid());
            orderGoodsComment.setState(BaseConstant.BUSINESS_STATE_FINASH);
        }
        // 检查更新订单是否全部评论
        initOrderInfoIsComment(model);
    }

    /**
     * 检查更新订单是否全部评论
     *
     * @param model
     */
    private void initOrderInfoIsComment(OrderGoodsCommentModel model) {
        int isComment = 1;
        OrderInfo orderInfo = model.getOrderGoods().getOrderInfo();
        for (OrderGoods orderGoods : orderInfo.getOrderGoodsList()) {
            if (orderGoods.getId() != model.getOrderGoods().getId()) {
                if (orderGoods.getOrderGoodsCommentList() == null || orderGoods.getOrderGoodsCommentList().size() == 0) {
                    isComment = -1;
                }
            }
        }
        orderInfo.setIsComment(isComment);
    }

    /**
     * 校验
     *
     * @param model
     * @return
     */
    private OrderGoodsComment checkUpdate(OrderGoodsCommentModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        OrderGoodsComment orderGoodsComment = orderGoodsCommentDao.find(model.getId());
        if (orderGoodsComment == null) {
            throw new BusinessException("参数错误！");
        }
        return orderGoodsComment;
    }

    /**
     * 初始化
     *
     * @param model
     */
    private void initUpdate(OrderGoodsCommentModel model, OrderGoodsComment orderGoodsComment) {

    }

    /**
     * 删除评论
     *
     * @param model
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public Object deleteComment(OrderGoodsCommentModel model) throws BusinessException {
        if (StringUtil.isBlank(model.getId())) {
            throw new BusinessException("参数错误！");
        }
        OrderGoodsComment orderGoodsComment = orderGoodsCommentDao.find(model.getId());
        if (orderGoodsComment != null) {
            orderGoodsComment.setState(BaseConstant.BUSINESS_STATE_NO);
            orderGoodsCommentDao.update(orderGoodsComment);
        }
        return Result.success();
    }

    /**
     * 审核订单
     *
     * @param model
     * @return
     */
    @Override
    public Object passComment(OrderGoodsCommentModel model) {
        if (model == null || model.getId() <= 0 || model.getState() == 0) {
            throw new BusinessException("请传入正确评论");
        }
        OrderGoodsComment orderGoodsComment = orderGoodsCommentDao.find(model.getId());
        if (orderGoodsComment == null) {
            throw new BusinessException("订单评论不存在");
        }
        if (orderGoodsComment.getState() != BaseConstant.BUSINESS_STATE_WAIT) {
            throw new BusinessException("订单评论状态有误。不能审核");
        }
        if (model.getState() == BaseConstant.BUSINESS_STATE_YES) {
            orderGoodsComment.setState(BaseConstant.BUSINESS_STATE_YES);
        }
        if (model.getState() == BaseConstant.BUSINESS_STATE_NO) {
            orderGoodsComment.setState(BaseConstant.BUSINESS_STATE_FAIL);
        }
        return Result.success().setData(orderGoodsCommentDao.update(orderGoodsComment));
    }
}