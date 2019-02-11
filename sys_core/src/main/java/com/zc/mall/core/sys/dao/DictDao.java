package com.zc.mall.core.sys.dao;

import com.zc.mall.core.sys.entity.Dict;
import com.zc.mall.core.sys.model.DictModel;
import com.zc.sys.common.dao.BaseDao;
import com.zc.sys.common.model.jpa.PageDataList;

import java.util.List;
import java.util.Map;

/**
 * 字典
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年7月29日
 */
public interface DictDao extends BaseDao<Dict> {

    /**
     * 列表
     *
     * @param model
     * @return
     */
    PageDataList<Dict> list(DictModel model);

    /**
     * 初始化加载
     *
     * @return
     */
    Map<String, Object> initDict();

    /**
     * nid查询
     *
     * @param nid
     * @return
     */
    List<Dict> findByNid(String nid);

}
