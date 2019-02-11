package com.zc.mall.core.sys.dao.impl;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.sys.constant.DictNidEnum;
import com.zc.mall.core.sys.dao.DictDao;
import com.zc.mall.core.sys.entity.Dict;
import com.zc.mall.core.sys.model.DictModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.model.jpa.SearchFilter.Operators;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年7月29日
 */
@Repository
public class DictDaoImpl extends BaseDaoImpl<Dict> implements DictDao {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public PageDataList<Dict> list(DictModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter orFilter1 = new SearchFilter("nid", Operators.LIKE, StringUtil.isNull(model.getSearchName()));
            SearchFilter orFilter2 = new SearchFilter("name", Operators.LIKE, StringUtil.isNull(model.getSearchName()));
            param.addOrFilter(orFilter1, orFilter2);
        } else {
            if (StringUtil.isNotBlank(model.getNid())) {
                param.addParam("nid", Operators.LIKE, model.getNid());
            }
            if (StringUtil.isNotBlank(model.getName())) {
                param.addParam("name", Operators.LIKE, StringUtil.isNull(model.getName()));
            }
            if (model.getState() != 0) {
                param.addParam("state", model.getState());
            }
        }
        param.addOrder(OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 初始化加载
     *
     * @return
     */
    @Override
    public Map<String, Object> initDict() {
        Map<String, Object> dictMap = new HashMap<String, Object>();
        for (DictNidEnum dictNidEnum : DictNidEnum.values()) {
            List<Dict> list = this.findByNid(dictNidEnum.getNid());
            if (list.size() > 0) {
                dictMap.put(dictNidEnum.getNid(), list);
            }
        }
        return dictMap;
    }

    /**
     * nid查询
     *
     * @param nid
     * @return
     */
    @Override
    public List<Dict> findByNid(String nid) {
        QueryParam param = QueryParam.getInstance();
        param.addParam("nid", nid);
        param.addParam("state", BaseConstant.INFO_STATE_YES);
        return this.findByCriteria(param);
    }

}
