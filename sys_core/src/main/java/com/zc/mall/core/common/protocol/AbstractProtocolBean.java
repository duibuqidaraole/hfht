package com.zc.mall.core.common.protocol;

import com.lowagie.text.pdf.BaseFont;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.sys.common.exception.BusinessException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 协议抽象类
 *
 * @author zp
 */
public abstract class AbstractProtocolBean implements ProtocolBean {

    /**
     * 标识
     **/
    protected String nid;
    /**
     * 模板
     **/
    protected TemplateModel templateModel;
    /**
     * 协议名称
     **/
    protected String name;
    /**
     * model
     **/
    protected Object model;
    /**
     * 合同地址
     **/
    protected String path;

    public static AbstractProtocolBean instance(String key) {
        return BeanUtil.getBean(key);
    }

    @Override
    public void executer(String nid, Object model) {
        this.nid = nid;
        this.model = model;
        this.initData();
        this.initTemplate();
        this.initName();
        this.buildProtocol();
        this.sign();
        this.afterHandle();
    }

    @Override
    public void initTemplate() {
        this.templateModel = new TemplateModel().instance(this.nid, BaseConstant.TEMPLATE_TYPE_PROTOCOL, 0);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initName() {

    }

    @Override
    public void buildProtocol() {
        String protocolPic = Global.getValue(ConfigParamConstant.SYS_PARAM_PROTOCOL_PIC);
        try {
            this.path = protocolPic + this.name + ".pdf";
            OutputStream os = new FileOutputStream(this.path);
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont(protocolPic + ProtocolConstant.PROTOCOL_FRONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(this.templateModel.getContent());
            renderer.layout();
            renderer.createPDF(os);
            os.close();
        } catch (Exception e) {
            throw new BusinessException("创建协议失败：" + e.getMessage());
        }
    }

    @Override
    public void sign() {

    }

    @Override
    public void afterHandle() {

    }

    @Override
    public void downloadContract(String contractId) {

    }

    /**
     * 获取【标识】
     **/
    public String getNid() {
        return nid;
    }

    /**
     * 设置【标识】
     **/
    public void setNid(String nid) {
        this.nid = nid;
    }

    /**
     * 获取【协议名称】
     **/
    public String getName() {
        return name;
    }

    /**
     * 设置【协议名称】
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取【模板】
     **/
    public TemplateModel getTemplateModel() {
        return templateModel;
    }

    /**
     * 设置【模板】
     **/
    public void setTemplateModel(TemplateModel templateModel) {
        this.templateModel = templateModel;
    }

    /**
     * 获取【model】
     **/
    public Object getModel() {
        return model;
    }

    /**
     * 设置【model】
     **/
    public void setModel(Object model) {
        this.model = model;
    }

    /**
     * 获取【合同地址】
     **/
    public String getPath() {
        return path;
    }

    /**
     * 设置【合同地址】
     **/
    public void setPath(String path) {
        this.path = path;
    }

}
