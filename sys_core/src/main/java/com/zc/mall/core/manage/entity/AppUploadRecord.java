package com.zc.mall.core.manage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 安卓包上传记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_M + "_app_upload_record")
public class AppUploadRecord extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 版本号
     **/
    private String versionNo;
    /**
     * 版本id
     **/
    private String versionId;
    /**
     * 内容
     **/
    private String content;
    /**
     * 下载链接
     **/
    private String url;
    /**
     * 类型
     **/
    private int type;
    /**
     * 添加时间
     **/
    private Date addTime;
    /**
     * 管理员
     **/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operator operator;

    /**
     * 获取【版本号】
     **/
    public String getVersionNo() {
        return versionNo;
    }

    /**
     * 设置【版本号】
     **/
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * 获取【版本id】
     **/
    public String getVersionId() {
        return versionId;
    }

    /**
     * 设置【版本id】
     **/
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    /**
     * 获取【内容】
     **/
    public String getContent() {
        return content;
    }

    /**
     * 设置【内容】
     **/
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取【下载链接】
     **/
    public String getUrl() {
        return url;
    }

    /**
     * 设置【下载链接】
     **/
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取【类型】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【类型】
     **/
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取【添加时间】
     **/
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置【添加时间】
     **/
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取【管理员】
     **/
    public Operator getOperator() {
        return operator;
    }

    /**
     * 设置【管理员】
     **/
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

}