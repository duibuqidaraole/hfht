package com.zc.mall.core.manage.service.impl;

import com.zc.mall.core.manage.dao.AppUploadRecordDao;
import com.zc.mall.core.manage.entity.AppUploadRecord;
import com.zc.mall.core.manage.model.AppUploadRecordModel;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.manage.service.AppUploadRecordService;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.model.jpa.PageDataList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 安卓包上传记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Service
public class AppUploadRecordServiceImpl implements AppUploadRecordService {

    @Resource
    private AppUploadRecordDao appUploadRecordDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(AppUploadRecordModel model) {
        PageDataList<AppUploadRecord> pageDataList = appUploadRecordDao
                .list(model);
        PageDataList<AppUploadRecordModel> pageDataList_ = new PageDataList<AppUploadRecordModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<AppUploadRecordModel> list = new ArrayList<AppUploadRecordModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (AppUploadRecord appUploadRecord : pageDataList.getList()) {
                AppUploadRecordModel model_ = AppUploadRecordModel
                        .instance(appUploadRecord);
                model_.setOperatorModel(OperatorModel.instance(appUploadRecord
                        .getOperator()));
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result add(AppUploadRecordModel model) {

        return null;
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(AppUploadRecordModel model) {
        return null;
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(AppUploadRecordModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        AppUploadRecord appUploadRecord = appUploadRecordDao
                .find(model.getId());
        AppUploadRecordModel model_ = AppUploadRecordModel
                .instance(appUploadRecord);
        model_.setOperatorModel(OperatorModel.instance(appUploadRecord
                .getOperator()));
        return Result.success().setData(model_);
    }

}