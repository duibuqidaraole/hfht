package com.zc.mall.core.common.protocol.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.http.UtilHttp;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

/**
 * 云合同签名
 *
 * @author zp
 */
@Component("cloudContractSign")
public class CloudContractSign extends AbstractProtocolSign {
    /** SYS-PARAM=========================================================start **/
    /**
     * 系统参数配置-云合同appId
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_APP_ID = "cloud_contract_app_id";
    /**
     * 系统参数配置-云合同密码
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_PASSWORD = "cloud_contract_password";
    /**
     * 系统参数配置-云合同平台id
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_ADMIN_ID = "cloud_contract_admin_id";
    /**
     * 系统参数配置-云合同平台手机号
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_ADMIN_MOBILE = "cloud_contract_admin_mobile";
    /**
     * 系统参数配置-云合同平台企业名称
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_ADMIN_REAL_NAME = "cloud_contract_admin_real_Name";
    /**
     * 系统参数配置-云合同平台社会统一信用代码
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_ADMIN_CARD_NO = "cloud_contract_admin_card_no";
    /**
     * 系统参数配置-云合同借款人签名关键字
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_BORROW_KEY_WORD = "borrow_key_word";
    /**
     * 系统参数配置-云合同投资人签名关键字
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_TENDER_KEY_WORD = "tender_key_word";
    /**
     * 系统参数配置-云合同平台签名关键字
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_admin_KEY_WORD = "admin_key_word";
    /** SYS-PARAM=========================================================end **/

    /**
     * 用户类型-个人
     **/
    public static final String CLOUD_CONTRACT_SIGN_USER_TYPE_GENERAL = "0";
    /**
     * 用户类型-企业
     **/
    public static final String CLOUD_CONTRACT_SIGN_USER_TYPE_ARTIF = "1";
    /**
     * 用户类型-平台自身
     **/
    public static final String CLOUD_CONTRACT_SIGN_USER_TYPE_ADMIN = "4";

    /**
     * 商户号
     **/
    private String appId;
    /**
     * 密码
     **/
    private String password;
    /**
     * 平台云合同id
     **/
    private String adminId;
    /**
     * 借款人云合同id
     **/
    private String borrowCId;
    /**
     * 投资人云合同id
     **/
    private String tenderCId;
    /**
     * token
     **/
    private String token;
    /**
     * adminToken
     **/
    private String adminToken;

    @Override
    public void init() {
        appId = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_APP_ID);
        password = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_PASSWORD);
        adminId = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_ID);
    }

    @Override
    public void doSign() {
        // 校验用户添加
        this.checkAddUser();
        // adminToken
        this.adminToken = this.getToken(this.adminId);
        // token
        this.token = this.getToken(this.tenderCId);
        // 生成合同
        this.cloudSign(this.token, this.path, this.name);
        // 添加参与者
        this.addPatner(this.name, this.token);
        // 签署合同
        this.signContract(this.name, this.adminToken);
    }

    /**
     * 签署合同
     *
     * @param contractId 合同id
     * @param token      平台token
     */
    private void signContract(String contractId, String token) {
        JSONArray jsonArray = new JSONArray();
        String signer = "";
        jsonArray.add(this.borrowCId);
        jsonArray.add(this.tenderCId);
        jsonArray.add(this.adminId);
        signer += jsonArray;
        String param = "&contractId=" + contractId + "&signer=" + signer;
        String r = UtilHttp.sendCloudContractPost("https://sdk.yunhetong.com/sdk/contract/signContract?token=" + token, param);
        JSONObject parseObject = JSONObject.parseObject(r);
        if (!"200".equals(StringUtil.isNull(parseObject.getString("subCode")))) {
            throw new BusinessException("云合同签署返回错误：" + parseObject.getString("message"));
        }
    }

    /**
     * 添加参与者,判断输入用户是否已经验证
     *
     * @param recivePartners 借款方
     * @param putPartners    出借方
     * @param contractId     合同id
     * @param token
     */
    public void addPatner(String contractId, String token) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectr = new JSONObject();// 借款人
        JSONObject jsonObjectp = new JSONObject();// 出借方
        JSONObject jsonObjectc = new JSONObject();// 平台（居间方）
        // 借款人
        jsonObjectr.put("appUserId", this.borrowCId);
        jsonObjectr.put("keyWord", Global.getValue(SYS_PARAM_CLOUD_CONTRACT_BORROW_KEY_WORD));
        // 出借方
        jsonObjectp.put("appUserId", this.tenderCId);
        jsonObjectp.put("keyWord", Global.getValue(SYS_PARAM_CLOUD_CONTRACT_TENDER_KEY_WORD));
        // 平台
        jsonObjectc.put("appUserId", this.adminId);
        jsonObjectc.put("keyWord", Global.getValue(SYS_PARAM_CLOUD_CONTRACT_admin_KEY_WORD));

        jsonArray.add(jsonObjectr);
        jsonArray.add(jsonObjectc);
        jsonArray.add(jsonObjectp);
        String param = "&contractId=" + contractId + "&partners=" + jsonArray;
        String r = UtilHttp.sendCloudContractPost("https://sdk.yunhetong.com/sdk/contract/addPartner?token=" + token, param);
        JSONObject parseObject = JSONObject.parseObject(r);
        if (!"200".equals(StringUtil.isNull(parseObject.getString("subCode")))) {
            throw new BusinessException("云合同添加参与者返回错误：" + parseObject.getString("message"));
        }
    }

    /**
     * 生成合同
     *
     * @param token
     * @param srcPdfFile 文件地址
     * @param proName    文件名 （不含格式）
     * @return
     * @throws UtilException
     */
    public void cloudSign(String token, String srcPdfFile, String proName) {
        String defContractNo = StringUtil.getSerialNumber();
        String url = "https://sdk.yunhetong.com/sdk/contract/fileContract?token=" + token;
        String fileUrl = srcPdfFile;
        //生成合同
        File file = new File(srcPdfFile);
        if (file.exists() && file.isFile()) {
        } else {
            throw new BusinessException("文件不存在");
        }
        String cloudContract = UtilHttp.sendCloudFileContractPost(url, fileUrl, defContractNo, proName);
        JSONObject jo = JSONObject.parseObject(cloudContract);
        JSONObject jojo = jo.getJSONObject("value");
        if (!"200".equals(StringUtil.isNull(jo.getString("subCode")))) {
            throw new BusinessException("云合同生成合同返回错误：" + jo.getString("message"));
        }
        String contractId = jojo.getString("contractId");
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.delete()) {
            System.out.println("删除单个文件" + proName + "成功！");
        } else {
            System.out.println("删除单个文件" + proName + "失败！");
        }
        this.name = contractId;
        LogUtil.info("云合同生成合同成功：" + this.name + "---tenderId：" + this.tenderId);
    }

    /**
     * 校验用户添加
     */
    private void checkAddUser() {
        if (StringUtil.isBlank(adminId)) {
            throw new BusinessException("云合同平台信息未初始化");
        }
        tenderCId = this.tenderUserModel.getUserInfo().getCloudContractId();
        if (StringUtil.isBlank(tenderCId)) {
            tenderCId = addUser(this.tenderUserModel.getMobile(), this.tenderUserInfoModel.getUserNature(), this.tenderUserModel.getRealName(),
                    this.tenderUserInfoModel.getCardType(), this.tenderUserModel.getCardNo());
            // 修改用户信息
            tenderUserInfoModel.setCloudContractId(tenderCId);
            QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_INFO_UPDATE.getNid(),
                    QueueCode.QUEUE_CODE_OTHER.getCode(), tenderUserInfoModel, this.tenderUserModel.prototype());
        }
        borrowCId = this.borrowUserModel.getUserInfo().getCloudContractId();
        if (StringUtil.isBlank(borrowCId)) {
            borrowCId = addUser(this.borrowUserModel.getMobile(), this.borrowUserInfoModel.getUserNature(), this.borrowUserModel.getRealName(),
                    this.borrowUserInfoModel.getCardType(), this.borrowUserModel.getCardNo());
            // 修改用户信息
            borrowUserInfoModel.setCloudContractId(borrowCId);
            QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_INFO_UPDATE.getNid(),
                    QueueCode.QUEUE_CODE_OTHER.getCode(), borrowUserInfoModel, this.borrowUserModel.prototype());
        }
    }

    /**
     * 添加用户
     *
     * @param userId
     * @param userType
     */
    private String addUser(String mobile, int userType, String realName, int cardType, String cardNo) {
        String cloudContractId = StringUtil.getSerialNumber();
        this.addUser(cloudContractId, mobile, convertUserType(userType), realName, convertCardType(cardType), cardNo);
        return cloudContractId;
    }

    /**
     * 添加平台自身用户
     */
    public void addUser() {
        init();
        String mobile = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_MOBILE);
        String userType = CLOUD_CONTRACT_SIGN_USER_TYPE_ADMIN;
        String realName = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_REAL_NAME);
        String certifyType = convertCardType(BaseConstant.CARD_TYPE_UNIFIED_SOCIAL_CREDIT_IDENTIFIER);
        String certifyNumber = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_CARD_NO);
        addUser(adminId, mobile, userType, realName, certifyType, certifyNumber);
    }

    /**
     * 添加用户接口
     *
     * @param appUserId     用户id
     * @param mobile        手机号
     * @param userType      用户类型
     * @param realName      姓名
     * @param certifyType   证件类型
     * @param certifyNumber 证件号
     */
    private void addUser(String appUserId, String mobile, String userType, String realName, String certifyType, String certifyNumber) {
        String param = "appId=" + appId + "&appUserId=" + appUserId + "&cellNum=" + mobile + "&userType=" + userType
                + "&userName=" + realName + "&certifyType=" + certifyType + "&certifyNumber=" + certifyNumber
                + "&createSignature=1&password=" + password;
        String url = "https://sdk.yunhetong.com/sdk/userInfo/addDpUser";
        String r = UtilHttp.sendCloudContractPost(url, param);
        JSONObject parseObject = JSONObject.parseObject(r);
        if (!"200".equals(StringUtil.isNull(parseObject.getString("subCode")))) {
            throw new BusinessException("云合同添加用户返回错误：" + parseObject.getString("message"));
        }
        LogUtil.info("云合同添加用户成功：" + mobile + "----" + appUserId);
    }

    /**
     * 获得用户token
     *
     * @param appUserId 用户云合同id（ccid）
     */
    private String getToken(String appUserId) {
        String r = "";
        String url = "https://sdk.yunhetong.com/sdk/token/getToken";
        String param = "appId=" + appId + "&appUserId=" + appUserId + "&password=" + password;
        String gotToken = UtilHttp.sendCloudContractPost(url, param);
        JSONObject Jtoken = JSONObject.parseObject(gotToken);
        JSONObject token = Jtoken.getJSONObject("value");
        if (!Objects.equals(null, token)) {
            r = token.get("token").toString();
        } else {
            throw new BusinessException("token不存在");
        }
        return r;
    }

    /**
     * 下载
     *
     * @param token
     * @param contractId 合同id
     * @param fileUrl    源合同文件地址
     */
    @Override
    public synchronized void downloadContract(String contractId) {
        init();
        this.path = Global.getValue(ConfigParamConstant.SYS_PARAM_PROTOCOL_PIC) + contractId + ".pdf";
        this.adminToken = this.getToken(this.adminId);
        File file = new File(this.path);
        if (!file.exists()) {
            String param = "token=" + this.adminToken + "&contractId=" + contractId;
            UtilHttp.sendGetFile("https://sdk.yunhetong.com/sdk/contract/download", param, this.path);
        }
    }

    /**
     * 证件类型转换
     *
     * @param cardType 证件类型
     * @return
     */
    private String convertCardType(int cardType) {
        String certifyType = "";
        switch (cardType) {
            case BaseConstant.CARD_TYPE_IDENTIFY_CARD:
                certifyType = "1";// 身份证
                break;
            case BaseConstant.CARD_TYPE_UNIFIED_SOCIAL_CREDIT_IDENTIFIER:
                certifyType = "6";// 社会统一信用代码
                break;

            default:
                certifyType = "1";// 身份证
                break;
        }
        return certifyType;
    }

    /**
     * 用户类型转换
     *
     * @param userType 证件类型
     * @return
     */
    private String convertUserType(int userNature) {
        String userType = "";
        switch (userNature) {
            case BaseConstant.USER_NATURE_GENERAL:
                userType = CLOUD_CONTRACT_SIGN_USER_TYPE_GENERAL;// 普通用户
                break;
            case BaseConstant.USER_NATURE_ARTIF:
                userType = CLOUD_CONTRACT_SIGN_USER_TYPE_ARTIF;// 企业用户
                break;

            default:
                userType = CLOUD_CONTRACT_SIGN_USER_TYPE_GENERAL;// 普通用户
                break;
        }
        return userType;
    }
}
