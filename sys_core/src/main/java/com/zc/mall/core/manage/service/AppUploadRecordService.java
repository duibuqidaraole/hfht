package com.zc.mall.core.manage.service;

import com.zc.mall.core.manage.model.AppUploadRecordModel;
import com.zc.sys.common.form.Result;

/**
 * 安卓包上传记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface AppUploadRecordService {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    public Result list(AppUploadRecordModel model);

    /**
     * 添加
     *
     * @param model
     * @return
     */
    public Result add(AppUploadRecordModel model);

    /**
     * 修改
     *
     * @param model
     * @return
     */
    public Result update(AppUploadRecordModel model);

    /**
     * 获取
     *
     * @param model
     * @return
     */
    public Result getById(AppUploadRecordModel model);

}