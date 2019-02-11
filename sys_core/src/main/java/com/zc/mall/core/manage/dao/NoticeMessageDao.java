package com.zc.mall.core.manage.dao;

import com.zc.mall.core.manage.entity.NoticeMessage;
import com.zc.mall.core.manage.model.NoticeMessageModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

/**
 * 通知消息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
public interface NoticeMessageDao extends BaseDao<NoticeMessage> {
    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<NoticeMessage> list(NoticeMessageModel model);
}