package com.zc.mall.core.common.protocol.sign;

import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;

/**
 * 协议签名抽象类
 *
 * @author zp
 */
public abstract class AbstractProtocolSign implements ProtocolSign {

    /**
     * 投资用户
     **/
    protected UserModel tenderUserModel;
    /**
     * 借款用户
     **/
    protected UserModel borrowUserModel;
    /**
     * 投资用户
     **/
    protected UserInfoModel tenderUserInfoModel;
    /**
     * 借款用户
     **/
    protected UserInfoModel borrowUserInfoModel;
    /**
     * 投资id
     **/
    protected long tenderId;
    /**
     * 协议地址
     **/
    protected String path;
    /**
     * 协议名称
     **/
    protected String name;


    @Override
    public String executer(UserModel tenderUserModel, UserModel borrowUserModel, long tenderId, String path, String name) {
        this.tenderUserModel = tenderUserModel;
        this.borrowUserModel = borrowUserModel;
        tenderUserInfoModel = UserInfoModel.instance(this.tenderUserModel.getUserInfo());
        borrowUserInfoModel = UserInfoModel.instance(this.borrowUserModel.getUserInfo());
        this.tenderId = tenderId;
        this.path = path;
        this.name = name;
        this.init();
        this.doSign();
        return this.name;
    }

    /**
     * 初始化
     */
    public abstract void init();

    /**
     * 签名
     */
    public abstract void doSign();

    /**
     * 获取【投资用户】
     **/
    public UserModel getTenderUserModel() {
        return tenderUserModel;
    }

    /**
     * 设置【投资用户】
     **/
    public void setTenderUserModel(UserModel tenderUserModel) {
        this.tenderUserModel = tenderUserModel;
    }

    /**
     * 获取【借款用户】
     **/
    public UserModel getBorrowUserModel() {
        return borrowUserModel;
    }

    /**
     * 设置【借款用户】
     **/
    public void setBorrowUserModel(UserModel borrowUserModel) {
        this.borrowUserModel = borrowUserModel;
    }

    /**
     * 获取【投资id】
     **/
    public long getTenderId() {
        return tenderId;
    }

    /**
     * 设置【投资id】
     **/
    public void setTenderId(long tenderId) {
        this.tenderId = tenderId;
    }

    /**
     * 获取【协议地址】
     **/
    public String getPath() {
        return path;
    }

    /**
     * 设置【协议地址】
     **/
    public void setPath(String path) {
        this.path = path;
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

}
