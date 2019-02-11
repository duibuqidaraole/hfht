package com.zc.mall.core.sys.model;

import com.zc.mall.core.sys.entity.Dict;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

/**
 * 字典
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月7日
 */
public class DictModel extends Dict {
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
     * 实体转换model
     */
    public static DictModel instance(Dict dict) {
        if (dict == null || dict.getId() <= 0) {
            return null;
        }
        DictModel dictModel = new DictModel();
        BeanUtils.copyProperties(dict, dictModel);
        return dictModel;
    }

    /**
     * model转换实体
     */
    public Dict prototype() {
        Dict dict = new Dict();
        BeanUtils.copyProperties(this, dict);
        return dict;
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

}
