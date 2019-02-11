package com.zc.mall.core.sys.model;

import com.zc.mall.core.sys.entity.Config;
import com.zc.sys.common.model.jpa.Page;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年7月27日
 */
public class ConfigModel extends Config {

    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    private int pageNo;
    /**
     * 每页数据条数
     */
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     */
    private String searchName;
    /**
     * 系统配置表nid数组
     */
    private String[] configNid;
    /**
     * 参数配置map
     **/
    private Map<String, Config> map;

    public ConfigModel() {
        super();
    }

    public ConfigModel(int size) {
        map = Collections.synchronizedMap(new HashMap<String, Config>());
        configNid = new String[size];
    }

    public void addConfig(Config sys) {
        map.put(sys.getNid().replace("con_", ""), sys);
    }

    public void setConfigNid(int length, String nid) {
        configNid[length] = nid.replace("con_", "");
    }

    private Config getConfig(String key) {
        Config sys = (Config) map.get(key);
        return sys;
    }

    public String getValue(String key) {
        Config c = getConfig(key);
        if (c == null)
            return null;
        return c.getValue();
    }

    /**
     * 实体转换model
     *
     * @param user 实体
     * @return model
     */
    public static ConfigModel instance(Config config) {
        if (config == null || config.getId() <= 0) {
            return null;
        }
        ConfigModel configModel = new ConfigModel();
        BeanUtils.copyProperties(config, configModel);
        return configModel;
    }

    /**
     * model转换实体
     *
     * @return 实体
     */
    public Config prototype() {
        Config config = new Config();
        BeanUtils.copyProperties(this, config);
        return config;
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
     * 获取【系统配置表nid数组】
     **/
    public String[] getConfigNid() {
        return configNid;
    }

    /**
     * 设置【系统配置表nid数组】
     **/
    public void setConfigNid(String[] configNid) {
        this.configNid = configNid;
    }

    /**
     * 获取【参数配置map】
     **/
    public Map<String, Config> getMap() {
        return map;
    }

    /**
     * 设置【参数配置map】
     **/
    public void setMap(Map<String, Config> map) {
        this.map = map;
    }

}
