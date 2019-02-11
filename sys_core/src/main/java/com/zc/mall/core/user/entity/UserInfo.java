
package com.zc.mall.core.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = BaseConstant.DB_PREFIX + BaseConstant.MODEL_U + "_user_info")
public class UserInfo extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 用户
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 邀请用户
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invite_user_id")
    private User inviteUser;
    /**
     * 邀请码
     **/
    private String inviteCode;
    /**
     * 用户类型
     **/
    private int type;
    /**
     * 用户类别
     **/
    private int userNature;
    /**
     * 证件类型
     **/
    private int cardType;
    /**
     * 注册渠道
     **/
    private int route;
    /**
     * 推广途径
     **/
    private String channel;
    /**
     * 性别
     **/
    private int sex;
    /**
     * 头像地址
     **/
    private String headImg;
    /**
     * E签宝账户唯一标识
     **/
    private String eSignAccountId;
    /**
     * E签宝电子签章数据
     **/
    private String eSignSealData;
    /**
     * 云合同id
     **/
    private String cloudContractId;
    /**
     * 云合同签章id
     **/
    private String cloudMoulageId;
    /**
     * 芝麻信用授权标识
     **/
    private String zmxyOpenId;
    /**
     * 民泰电子账号
     **/
    private String elecAcct;
    /**
     * 设备标识符
     **/
    private String adIdentifier;
    /**
     * 身份证国徽面
     **/
    private String cardFg;
    /**
     * 身份证头像面
     **/
    private String cardBg;
    /**
     * 锁定备注
     **/
    private String loginLockRemark;
    /**
     * 省
     **/
    private String province;
    /**
     * 市
     **/
    private String city;
    /**
     * 区
     **/
    private String area;
    /**
     * 居住地址
     **/
    private String address;
    /**
     * 公司名称
     **/
    private String companyName;
    /**
     * 公司类型
     **/
    private int companyType;
    /**
     * 公司证件号(企业征信代码)
     **/
    private String companyCardNo;
    /**
     * 公司描述
     **/
    private String companyDes;
    /**
     * 企业法人姓名
     **/
    private String legalName;
    /**
     * 企业法人证件号
     **/
    private String legalCardNo;
    /**
     * 公司产品介绍
     **/
    private String companyProjectDes;
    /**
     * 公司产品类型
     **/
    private String companyProjectType;
    /**
     * logo图片地址
     **/
    private String companyLogoPic;
    /**
     * 注册资本
     **/
    private String companyRegisteredCapital;
    /**
     * 实缴注册资本
     **/
    private String companyRealRegisteredCapital;
    /**
     * 公司经营地址
     **/
    private String companyBusinessAddress;
    /**
     * 公司经营期限
     **/
    private String companyBusinessPeriod;
    /**
     * 公司经营状态
     **/
    private String companyBusinessState;
    /**
     * 公司电话
     **/
    private String companyTelephone;
    /**
     * 公司推荐信息
     **/
    private String companyRecommendDec;
    /**
     * 营业执照图片
     **/
    private String companyBusinessLicensePic;
    /**
     * 公司章程
     **/
    private String companyRulesPic;
    /**
     * 公司其他图片
     **/
    private String companyOtherPic;
    /**
     * 提现规则
     **/
    private String companyCashRule;
    /**
     * 公司安全保障
     **/
    private String companySafety;
    /**
     * 公司安全保障方式
     **/
    private String companySafetyType;
    /**
     * 公司成立时间
     **/
    private Date companyAddTime;
    /**
     * 最高年化
     **/
    private String companyRateMost;
    /**
     * 最低年化
     **/
    private String companyRateLowest;
    /**
     * 最大期限
     **/
    private String companyPeriodMost;
    /**
     * 最小期限
     **/
    private String companyPeriodLowest;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 注册ip
     **/
    private String addIp;

    /**
     * 生日
     */
    private Date birthday;
    /**
     * 需求
     */
    private String demands;

    public UserInfo() {
        super();
    }

    public UserInfo(User user) {
        super();
        this.user = user;
    }

    /**
     * 获取【用户】
     **/
    public User getUser() {
        return user;
    }

    /**
     * 设置【用户】
     **/
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取【邀请用户】
     **/
    public User getInviteUser() {
        return inviteUser;
    }

    /**
     * 设置【邀请用户】
     **/
    public void setInviteUser(User inviteUser) {
        this.inviteUser = inviteUser;
    }

    /**
     * 获取【邀请码】
     **/
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * 设置【邀请码】
     **/
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /**
     * 获取【用户类型】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【用户类型】
     **/
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取【用户类别】
     **/
    public int getUserNature() {
        return userNature;
    }

    /**
     * 设置【用户类别】
     **/
    public void setUserNature(int userNature) {
        this.userNature = userNature;
    }

    /**
     * 获取【证件类型】
     **/
    public int getCardType() {
        return cardType;
    }

    /**
     * 设置【证件类型】
     **/
    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    /**
     * 获取【注册渠道】
     **/
    public int getRoute() {
        return route;
    }

    /**
     * 设置【注册渠道】
     **/
    public void setRoute(int route) {
        this.route = route;
    }

    /**
     * 获取【推广途径】
     **/
    public String getChannel() {
        return channel;
    }

    /**
     * 设置【推广途径】
     **/
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 获取【性别】
     **/
    public int getSex() {
        return sex;
    }

    /**
     * 设置【性别】
     **/
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * 获取【头像地址】
     **/
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置【头像地址】
     **/
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 获取【E签宝账户唯一标识】
     **/
    public String geteSignAccountId() {
        return eSignAccountId;
    }

    /**
     * 设置【E签宝账户唯一标识】
     **/
    public void seteSignAccountId(String eSignAccountId) {
        this.eSignAccountId = eSignAccountId;
    }

    /**
     * 获取【民泰电子账号】
     **/
    public String getElecAcct() {
        return elecAcct;
    }

    /**
     * 设置【民泰电子账号】
     **/
    public void setElecAcct(String elecAcct) {
        this.elecAcct = elecAcct;
    }

    /**
     * 获取【设备标识符】
     **/
    public String getAdIdentifier() {
        return adIdentifier;
    }

    /**
     * 设置【设备标识符】
     **/
    public void setAdIdentifier(String adIdentifier) {
        this.adIdentifier = adIdentifier;
    }

    /**
     * 获取【身份证国徽面】
     **/
    public String getCardFg() {
        return cardFg;
    }

    /**
     * 设置【身份证国徽面】
     **/
    public void setCardFg(String cardFg) {
        this.cardFg = cardFg;
    }

    /**
     * 获取【身份证头像面】
     **/
    public String getCardBg() {
        return cardBg;
    }

    /**
     * 设置【身份证头像面】
     **/
    public void setCardBg(String cardBg) {
        this.cardBg = cardBg;
    }

    /**
     * 获取【省】
     **/
    public String getProvince() {
        return province;
    }

    /**
     * 设置【省】
     **/
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取【市】
     **/
    public String getCity() {
        return city;
    }

    /**
     * 设置【市】
     **/
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取【区】
     **/
    public String getArea() {
        return area;
    }

    /**
     * 设置【区】
     **/
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取【居住地址】
     **/
    public String getAddress() {
        return address;
    }

    /**
     * 设置【居住地址】
     **/
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取【公司名称】
     **/
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置【公司名称】
     **/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取【公司类型】
     **/
    public int getCompanyType() {
        return companyType;
    }

    /**
     * 设置【公司类型】
     **/
    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    /**
     * 获取【公司证件号(企业征信代码)】
     **/
    public String getCompanyCardNo() {
        return companyCardNo;
    }

    /**
     * 设置【公司证件号(企业征信代码)】
     **/
    public void setCompanyCardNo(String companyCardNo) {
        this.companyCardNo = companyCardNo;
    }

    /**
     * 获取【公司描述】
     **/
    public String getCompanyDes() {
        return companyDes;
    }

    /**
     * 设置【公司描述】
     **/
    public void setCompanyDes(String companyDes) {
        this.companyDes = companyDes;
    }

    /**
     * 获取【企业法人姓名】
     **/
    public String getLegalName() {
        return legalName;
    }

    /**
     * 设置【企业法人姓名】
     **/
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    /**
     * 获取【企业法人证件号】
     **/
    public String getLegalCardNo() {
        return legalCardNo;
    }

    /**
     * 设置【企业法人证件号】
     **/
    public void setLegalCardNo(String legalCardNo) {
        this.legalCardNo = legalCardNo;
    }

    /**
     * 获取【注册ip】
     **/
    public String getAddIp() {
        return addIp;
    }

    /**
     * 设置【注册ip】
     **/
    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    /**
     * 获取【E签宝电子签章数据】
     **/
    public String geteSignSealData() {
        return eSignSealData;
    }

    /**
     * 设置【E签宝电子签章数据】
     **/
    public void seteSignSealData(String eSignSealData) {
        this.eSignSealData = eSignSealData;
    }

    /**
     * 获取【锁定备注】
     **/
    public String getLoginLockRemark() {
        return loginLockRemark;
    }

    /**
     * 设置【锁定备注】
     **/
    public void setLoginLockRemark(String loginLockRemark) {
        this.loginLockRemark = loginLockRemark;
    }

    /**
     * 获取【芝麻信用授权标识】
     **/
    public String getZmxyOpenId() {
        return zmxyOpenId;
    }

    /**
     * 设置【芝麻信用授权标识】
     **/
    public void setZmxyOpenId(String zmxyOpenId) {
        this.zmxyOpenId = zmxyOpenId;
    }

    /**
     * 获取【公司产品介绍】
     **/
    public String getCompanyProjectDes() {
        return companyProjectDes;
    }

    /**
     * 设置【公司产品介绍】
     **/
    public void setCompanyProjectDes(String companyProjectDes) {
        this.companyProjectDes = companyProjectDes;
    }

    /**
     * 获取【公司产品类型】
     **/
    public String getCompanyProjectType() {
        return companyProjectType;
    }

    /**
     * 设置【公司产品类型】
     **/
    public void setCompanyProjectType(String companyProjectType) {
        this.companyProjectType = companyProjectType;
    }


    /**
     * 获取【logo图片地址】
     **/
    public String getCompanyLogoPic() {
        return companyLogoPic;
    }

    /**
     * 设置【logo图片地址】
     **/
    public void setCompanyLogoPic(String companyLogoPic) {
        this.companyLogoPic = companyLogoPic;
    }

    /**
     * 获取【注册资本】
     **/
    public String getCompanyRegisteredCapital() {
        return companyRegisteredCapital;
    }

    /**
     * 设置【注册资本】
     **/
    public void setCompanyRegisteredCapital(String companyRegisteredCapital) {
        this.companyRegisteredCapital = companyRegisteredCapital;
    }

    /**
     * 获取【实缴注册资本】
     **/
    public String getCompanyRealRegisteredCapital() {
        return companyRealRegisteredCapital;
    }

    /**
     * 设置【实缴注册资本】
     **/
    public void setCompanyRealRegisteredCapital(String companyRealRegisteredCapital) {
        this.companyRealRegisteredCapital = companyRealRegisteredCapital;
    }

    /**
     * 获取【公司经营地址】
     **/
    public String getCompanyBusinessAddress() {
        return companyBusinessAddress;
    }

    /**
     * 设置【公司经营地址】
     **/
    public void setCompanyBusinessAddress(String companyBusinessAddress) {
        this.companyBusinessAddress = companyBusinessAddress;
    }

    /**
     * 获取【公司经营期限】
     **/
    public String getCompanyBusinessPeriod() {
        return companyBusinessPeriod;
    }

    /**
     * 设置【公司经营期限】
     **/
    public void setCompanyBusinessPeriod(String companyBusinessPeriod) {
        this.companyBusinessPeriod = companyBusinessPeriod;
    }

    /**
     * 获取【公司经营状态】
     **/
    public String getCompanyBusinessState() {
        return companyBusinessState;
    }

    /**
     * 设置【公司经营状态】
     **/
    public void setCompanyBusinessState(String companyBusinessState) {
        this.companyBusinessState = companyBusinessState;
    }

    /**
     * 获取【公司电话】
     **/
    public String getCompanyTelephone() {
        return companyTelephone;
    }

    /**
     * 设置【公司电话】
     **/
    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    /**
     * 获取【公司推荐信息】
     **/
    public String getCompanyRecommendDec() {
        return companyRecommendDec;
    }

    /**
     * 设置【公司推荐信息】
     **/
    public void setCompanyRecommendDec(String companyRecommendDec) {
        this.companyRecommendDec = companyRecommendDec;
    }

    /**
     * 获取【营业执照图片】
     **/
    public String getCompanyBusinessLicensePic() {
        return companyBusinessLicensePic;
    }

    /**
     * 设置【营业执照图片】
     **/
    public void setCompanyBusinessLicensePic(String companyBusinessLicensePic) {
        this.companyBusinessLicensePic = companyBusinessLicensePic;
    }

    /**
     * 获取【公司章程】
     **/
    public String getCompanyRulesPic() {
        return companyRulesPic;
    }

    /**
     * 设置【公司章程】
     **/
    public void setCompanyRulesPic(String companyRulesPic) {
        this.companyRulesPic = companyRulesPic;
    }

    /**
     * 获取【公司其他图片】
     **/
    public String getCompanyOtherPic() {
        return companyOtherPic;
    }

    /**
     * 设置【公司其他图片】
     **/
    public void setCompanyOtherPic(String companyOtherPic) {
        this.companyOtherPic = companyOtherPic;
    }

    /**
     * 获取【备注】
     **/
    public String getRemark() {
        return remark;
    }

    /**
     * 设置【备注】
     **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取【提现规则】
     **/
    public String getCompanyCashRule() {
        return companyCashRule;
    }

    /**
     * 设置【提现规则】
     **/
    public void setCompanyCashRule(String companyCashRule) {
        this.companyCashRule = companyCashRule;
    }

    /**
     * 获取【公司安全保障】
     **/
    public String getCompanySafety() {
        return companySafety;
    }

    /**
     * 设置【公司安全保障】
     **/
    public void setCompanySafety(String companySafety) {
        this.companySafety = companySafety;
    }

    /**
     * 获取【公司安全保障方式】
     **/
    public String getCompanySafetyType() {
        return companySafetyType;
    }

    /**
     * 设置【公司安全保障方式】
     **/
    public void setCompanySafetyType(String companySafetyType) {
        this.companySafetyType = companySafetyType;
    }

    /**
     * 获取【公司成立时间】
     **/
    public Date getCompanyAddTime() {
        return companyAddTime;
    }

    /**
     * 设置【公司成立时间】
     **/
    public void setCompanyAddTime(Date companyAddTime) {
        this.companyAddTime = companyAddTime;
    }

    /**
     * 获取【最高年化】
     **/
    public String getCompanyRateMost() {
        return companyRateMost;
    }

    /**
     * 设置【最高年化】
     **/
    public void setCompanyRateMost(String companyRateMost) {
        this.companyRateMost = companyRateMost;
    }

    /**
     * 获取【最低年化】
     **/
    public String getCompanyRateLowest() {
        return companyRateLowest;
    }

    /**
     * 设置【最低年化】
     **/
    public void setCompanyRateLowest(String companyRateLowest) {
        this.companyRateLowest = companyRateLowest;
    }

    /**
     * 获取【最大期限】
     **/
    public String getCompanyPeriodMost() {
        return companyPeriodMost;
    }

    /**
     * 设置【最大期限】
     **/
    public void setCompanyPeriodMost(String companyPeriodMost) {
        this.companyPeriodMost = companyPeriodMost;
    }

    /**
     * 获取【最小期限】
     **/
    public String getCompanyPeriodLowest() {
        return companyPeriodLowest;
    }

    /**
     * 设置【最小期限】
     **/
    public void setCompanyPeriodLowest(String companyPeriodLowest) {
        this.companyPeriodLowest = companyPeriodLowest;
    }

    /**
     * 获取【云合同id】
     **/
    public String getCloudContractId() {
        return cloudContractId;
    }

    /**
     * 设置【云合同id】
     **/
    public void setCloudContractId(String cloudContractId) {
        this.cloudContractId = cloudContractId;
    }

    /**
     * 获取【云合同签章id】
     **/
    public String getCloudMoulageId() {
        return cloudMoulageId;
    }

    /**
     * 设置【云合同签章id】
     **/
    public void setCloudMoulageId(String cloudMoulageId) {
        this.cloudMoulageId = cloudMoulageId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDemands() {
        return demands;
    }

    public void setDemands(String demands) {
        this.demands = demands;
    }
}