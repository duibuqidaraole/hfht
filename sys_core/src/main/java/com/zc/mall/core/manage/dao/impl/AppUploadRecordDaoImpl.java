package com.zc.mall.core.manage.dao.impl;

import com.zc.mall.core.manage.dao.AppUploadRecordDao;
import com.zc.mall.core.manage.entity.AppUploadRecord;
import com.zc.mall.core.manage.model.AppUploadRecordModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

/**
 * 安卓包上传记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class AppUploadRecordDaoImpl extends BaseDaoImpl<AppUploadRecord> implements AppUploadRecordDao {

    @Override
    public PageDataList<AppUploadRecord> list(AppUploadRecordModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter2 = new SearchFilter("name", Operators.LIKE,
                    model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

}