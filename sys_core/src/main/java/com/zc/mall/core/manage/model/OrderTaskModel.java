package com.zc.mall.core.manage.model;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.service.OrderTaskService;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class OrderTaskModel extends OrderTask {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;
    /**
     * 用户Model
     **/
    private UserModel userModel;
    /**
     * queue类型
     **/
    private String queueType;

    /**
     * 实体转换model
     */
    public static OrderTaskModel instance(OrderTask orderTask) {
        if (orderTask == null || orderTask.getId() <= 0) {
            return null;
        }
        OrderTaskModel orderTaskModel = new OrderTaskModel();
        BeanUtils.copyProperties(orderTask, orderTaskModel);
        return orderTaskModel;
    }

    /**
     * model转换实体
     */
    public OrderTask prototype() {
        OrderTask orderTask = new OrderTask();
        BeanUtils.copyProperties(this, orderTask);
        return orderTask;
    }

    /**
     * 处理订单任务
     *
     * @param orderNo
     * @param state
     * @param result
     */
    public static void dealOrder(String orderNo, int state, String result) {
        if (StringUtil.isBlank(orderNo)) {
            return;
        }
        OrderTaskService orderTaskService = BeanUtil
                .getBean(OrderTaskService.class);
        orderTaskService.dealOrder(orderNo, state, result);
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * 获取【queue类型】
     **/
    public String getQueueType() {
        return queueType;
    }

    /**
     * 设置【queue类型】
     **/
    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    /**
     * 获取【用户Model】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置【用户Model】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}