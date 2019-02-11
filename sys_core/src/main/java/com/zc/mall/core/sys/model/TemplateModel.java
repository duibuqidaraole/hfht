package com.zc.mall.core.sys.model;

import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.sys.entity.Template;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.FreemarkerUtil;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月08日
 */
public class TemplateModel extends Template {
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
     * 获取解析模版
     *
     * @param nid     标识
     * @param type    类型1-资金模版，2-通知信息模版，3-操作日志模版，4-积分日志模版，5-协议模版，6-信用日志模板
     * @param typeSub 子类型(例：1短信，2邮件，3站内信)
     * @return
     */
    public TemplateModel instance(String nid, int type, int typeSub) {
        Template template = Global.getTemplate(nid, type, typeSub);
        if (template == null) {
            throw new BusinessException("模版不存在[nid=" + nid + " , type=" + type
                    + " ,typeSub:" + typeSub + " ]");
        }
        TemplateModel model = instance(template);
        try {
            model.setContent(FreemarkerUtil.renderTemplate(model.getContent(),
                    Global.getTransfer()));
            model.setTitle(FreemarkerUtil.renderTemplate(model.getTitle(),
                    Global.getTransfer()));
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.info("模版解析报错");
        }
        return model;
    }

    /**
     * 实体转换model
     */
    public static TemplateModel instance(Template template) {
        if (template == null || template.getId() <= 0) {
            return null;
        }
        TemplateModel templateModel = new TemplateModel();
        BeanUtils.copyProperties(template, templateModel);
        return templateModel;
    }

    /**
     * model转换实体
     */
    public Template prototype() {
        Template template = new Template();
        BeanUtils.copyProperties(this, template);
        return template;
    }

    /**
     * 参数校验
     */
    public void validParam() {
        if (StringUtil.isBlank(this.getName())) {
            throw new BusinessException("名称不能为空！");
        }
        if (StringUtil.isBlank(this.getNid())) {
            throw new BusinessException("标识不能为空！");
        }
        if (this.getType() <= 0) {
            throw new BusinessException("请选择类型！");
        }
        if (StringUtil.isBlank(this.getTitle())) {
            throw new BusinessException("模版标题不能为空！");
        }
        if (StringUtil.isBlank(this.getContent())) {
            throw new BusinessException("模版内容不能为空！");
        }
    }

    /**
     * 设置修改参数
     *
     * @param template
     */
    public void setUpdateParam(Template template) {
        template.setPaymentsType(this.getPaymentsType());
        template.setName(this.getName());
        template.setNid(this.getNid());
        template.setRemark(this.getRemark());
        template.setRoute(this.getRoute());
        template.setState(this.getState());
        template.setContent(this.getContent());
        template.setTitle(this.getTitle());
        template.setType(this.getType());
        template.setTypeSub(this.getTypeSub());
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