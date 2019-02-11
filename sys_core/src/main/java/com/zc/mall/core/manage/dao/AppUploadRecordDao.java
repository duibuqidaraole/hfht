package com.zc.mall.core.manage.dao;

import com.zc.mall.core.manage.entity.AppUploadRecord;
import com.zc.mall.core.manage.model.AppUploadRecordModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 安卓包上传记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public interface AppUploadRecordDao extends BaseDao<AppUploadRecord> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<AppUploadRecord> list(AppUploadRecordModel model);
}