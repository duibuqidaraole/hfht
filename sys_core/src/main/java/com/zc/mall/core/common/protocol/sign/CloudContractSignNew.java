package com.zc.mall.core.common.protocol.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.constant.BaseConstant.QueueCode;
import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.common.queue.work.QueueAbstract;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.user.entity.UserIdentify;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.http.UtilHttp;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 云合同签名
 *
 * @author zp
 */
@Component("cloudContractSignNew")
public class CloudContractSignNew extends AbstractProtocolSign {
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
     * 系统参数配置-云合同平台签章id
     **/
    public static final String SYS_PARAM_CLOUD_CONTRACT_ADMIN_MOULAGEID = "cloud_contract_admin_moulage_id";
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
     * 平台云合同印章id
     **/
    private String adminMoulageId;
    /**
     * 借款人云合同id
     **/
    private String borrowCId;
    /**
     * 借款人云合同印章id
     **/
    private String borrowMoulageId;
    /**
     * 投资人云合同id
     **/
    private String tenderCId;
    /**
     * 投资人云合同印章id
     **/
    private String tenderMoulageId;
    /**
     * 平台长期有效token
     **/
    private String token;
    /**
     * 云合同URL
     **/
    private String url = "https://api.yunhetong.com/api";

    @Override
    public void init() {
        appId = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_APP_ID);
        password = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_PASSWORD);
        adminId = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_ID);
        adminMoulageId = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_ID);

    }

    @Override
    public void doSign() {
        token = gotToken();
        // 校验用户添加
        this.checkAddUser();
        // 生成合同
        this.cloudSign(this.token, this.path, this.name);
        // 添加参与者
        this.addPatner(this.name, this.token);
        // 签署合同
        this.signContract(this.name, this.token);
    }

    /**
     * 签署合同
     *
     * @param contractId 合同id
     * @param token      平台token
     */
    private void signContract(String contractId, String token) {
        sign(contractId, this.borrowCId);
        sign(contractId, this.tenderCId);
        sign(contractId, this.adminId);
    }

    private void sign(String contractId, String SignerID) {
        JSONObject partner = new JSONObject();
        partner.put("idType", "0");
        partner.put("idContent", contractId);
        partner.put("signerId", SignerID);
        String borrowSign = UtilHttp.sendPostToCloudContract(url + "/contract/sign", partner.toString(), token);
        System.out.println(borrowSign);
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
        JSONObject jo = new JSONObject();
        jo.put("idType", "0");
        jo.put("idContent", contractId);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObjectr = new JSONObject();// 借款人
        JSONObject jsonObjectp = new JSONObject();// 出借方
        JSONObject jsonObjectc = new JSONObject();// 平台（居间方）
        // 借款人
        jsonObjectr.put("signerId", this.borrowCId);
        jsonObjectr.put("signValidateType", "0");
        jsonObjectr.put("signPositionType", "0");
        jsonObjectr.put("positionContent", Global.getValue(SYS_PARAM_CLOUD_CONTRACT_BORROW_KEY_WORD));
        // 出借方
        jsonObjectp.put("signerId", this.tenderCId);
        jsonObjectp.put("signValidateType", "0");
        jsonObjectp.put("signPositionType", "0");
        jsonObjectp.put("positionContent", Global.getValue(SYS_PARAM_CLOUD_CONTRACT_TENDER_KEY_WORD));
        // 平台
        jsonObjectc.put("signerId", this.adminId);
        jsonObjectc.put("signValidateType", "0");
        jsonObjectc.put("signPositionType", "0");
        jsonObjectc.put("positionContent", Global.getValue(SYS_PARAM_CLOUD_CONTRACT_admin_KEY_WORD));

        jsonArray.add(jsonObjectr);
        jsonArray.add(jsonObjectc);
        jsonArray.add(jsonObjectp);

        jo.put("signers", jsonArray);
        String signer = UtilHttp.sendPostToCloudContract(url + "/contract/signer", jo.toString(), token);
        System.out.println("添加参与者" + signer);
        JSONObject parseObject = JSONObject.parseObject(signer);
        if (!"200".equals(StringUtil.isNull(parseObject.getString("code")))) {
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
        File file = new File(srcPdfFile);
        if (file.exists() && file.isFile()) {
        } else {
            throw new BusinessException("文件不存在");
        }
        JSONObject contract = (JSONObject) JSONObject.parse(UtilHttp.sendCloudFileContractPostNew(url + "/contract/fileContract", srcPdfFile, proName, token));
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.delete()) {
            System.out.println("删除合同文件" + proName + "成功！");
        } else {
            System.out.println("删除合同文件" + proName + "失败！");
        }
        if (StringUtil.isNull(contract.get("code") + "").equals("200")) {
            String contractId = contract.getJSONObject("data").get("contractId").toString();
            System.out.println("合同id" + contractId);
            this.name = contractId;
            LogUtil.info("云合同生成合同成功：" + this.name + "---tenderId：" + this.tenderId);
        } else {
            System.err.println(contract.get("msg"));
            throw new BusinessException("云合同生成合同返回错误：" + contract.getString("message"));
        }
    }

    /**
     * 校验用户添加
     */
    private void checkAddUser() {
        if (StringUtil.isBlank(adminId)) {
            throw new BusinessException("云合同平台信息未初始化");
        }
        tenderCId = this.tenderUserModel.getUserInfo().getCloudContractId();
        tenderMoulageId = this.tenderUserModel.getUserInfo().getCloudMoulageId();
        if (StringUtil.isBlank(tenderCId)) {
            Map<String, String> tenderMap = addUser(this.tenderUserModel.getMobile(), this.tenderUserInfoModel.getUserNature(), this.tenderUserModel.getRealName(),
                    this.tenderUserInfoModel.getCardType(), this.tenderUserModel.getCardNo());
            // 修改用户信息
            tenderCId = tenderMap.get("singnerId");
            tenderMoulageId = tenderMap.get("moulageId");
            tenderUserInfoModel.setCloudContractId(tenderCId);
            tenderUserInfoModel.setCloudMoulageId(tenderMoulageId);
            QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_INFO_UPDATE.getNid(),
                    QueueCode.QUEUE_CODE_OTHER.getCode(), tenderUserInfoModel, this.tenderUserModel.prototype());

            UserIdentify userIdentify = this.tenderUserModel.getUserIdentify();
            userIdentify.setCloudMoulageState(1);
            QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_INFO_UPDATE.getNid(),
                    QueueCode.QUEUE_CODE_OTHER.getCode(), userIdentify, this.tenderUserModel.prototype());
        }
        borrowCId = this.borrowUserModel.getUserInfo().getCloudContractId();
        borrowMoulageId = this.borrowUserModel.getUserInfo().getCloudMoulageId();
        if (StringUtil.isBlank(borrowCId)) {
            Map<String, String> borrowMap = addUser(this.borrowUserModel.getMobile(), this.borrowUserInfoModel.getUserNature(), this.borrowUserModel.getRealName(),
                    this.borrowUserInfoModel.getCardType(), this.borrowUserModel.getCardNo());
            // 修改用户信息
            borrowCId = borrowMap.get("singnerId");
            borrowMoulageId = borrowMap.get("moulageId");
            borrowUserInfoModel.setCloudContractId(borrowCId);
            borrowUserInfoModel.setCloudMoulageId(borrowMoulageId);
            QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_INFO_UPDATE.getNid(),
                    QueueCode.QUEUE_CODE_OTHER.getCode(), borrowUserInfoModel, this.borrowUserModel.prototype());

            UserIdentify userIdentify = this.borrowUserModel.getUserIdentify();
            userIdentify.setCloudMoulageState(1);
            QueueAbstract.send(StringUtil.getSerialNumber(), OrderNid.ORDER_NID_USER_INFO_UPDATE.getNid(),
                    QueueCode.QUEUE_CODE_OTHER.getCode(), userIdentify, this.tenderUserModel.prototype());
        }
    }

    /**
     * 添加用户
     *
     * @param userId
     * @param userType
     */
    private Map<String, String> addUser(String mobile, int userType, String realName, int cardType, String cardNo) {
        Map<String, String> map = this.addUser(mobile, convertUserType(userType), realName, convertCardType(cardType), cardNo);
        return map;
    }

    /**
     * 添加平台自身用户
     */
    public void addUser() {
        init();
        String mobile = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_MOBILE);
        String userType = CLOUD_CONTRACT_SIGN_USER_TYPE_ARTIF;
        String realName = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_REAL_NAME);
        String certifyType = convertCardType(BaseConstant.CARD_TYPE_UNIFIED_SOCIAL_CREDIT_IDENTIFIER);
        String certifyNumber = Global.getValue(SYS_PARAM_CLOUD_CONTRACT_ADMIN_CARD_NO);
        addUser(mobile, userType, realName, certifyType, certifyNumber);
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
    private Map<String, String> addUser(String mobile, String userType, String realName, String certifyType, String certifyNumber) {
        Map<String, String> map = new HashMap<String, String>();
        switch (userType) {
            case "0":
                JSONObject jo = new JSONObject();
                jo.put("caType", "B2");
                jo.put("certifyNum", certifyNumber);
                jo.put("identityRegion", "0");//身份地区：0 大陆，1 香港，2 台湾，3 澳门
                /* if(user.getType() == Enums.AccountType.GuaComBor.getIndex()){
                     user.setMobile(StringUtil.isNull(config.get("companyMobile")));//经销商借款人手机号默认
                 }*/
                jo.put("phoneNo", mobile);
                jo.put("phoneRegion", "0");//手机号地区：0 大陆，1 香港、澳门，2 台湾
                jo.put("userName", realName);
                JSONObject Personlogin = (JSONObject) JSONObject.parse(UtilHttp.sendPostToCloudContract(url + "/user/person", jo.toString(), token));
                System.out.println(Personlogin);
                if (Personlogin != null && Personlogin.getJSONObject("data") != null) {
                    String signerId = Personlogin.getJSONObject("data").get("signerId").toString();
                    //ccid返回成功
                    map.put("singnerId", signerId);
                    JSONObject jojo = new JSONObject();
                    jojo.put("signerId", signerId);
                    jojo.put("borderType", "B1");
                    jojo.put("fontFamily", "F1");
                    JSONObject personMoulage = (JSONObject) JSONObject.parse(UtilHttp.sendPostToCloudContract(url + "/user/personMoulage", jojo.toString(), token));
                    System.out.println(personMoulage);
                    if (StringUtil.isNull(personMoulage.get("code") + "").equals("200")) {
                        map.put("moulageId", personMoulage.getJSONObject("data").get("moulageId").toString());
                        System.err.println("用户注册成功，签章生成成功");
                    } else {
                        try {
                            Exception exception = new Exception("生成签章失败");
                            throw exception;
                        } catch (Exception e) {
                            System.err.println("生成签章失败：" + personMoulage.get("msg"));
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.err.println(Personlogin.get("msg").toString());
                }
                break;
            case "1"://企业用户
                JSONObject jo1 = new JSONObject();
                jo1.put("certifyNum", certifyNumber);
                jo1.put("certifyType", "1");//企业相关证件类型包含：1.社会信用代码，2.营业执照注册号，3.组织机构代码
                jo1.put("phoneNo", mobile);
                jo1.put("caType", "B2");
                jo1.put("userName", realName);
                JSONObject companylogin = (JSONObject) JSONObject.parse(UtilHttp.sendPostToCloudContract(url + "/user/company", jo1.toString(), token));
                System.out.println(companylogin);
                if (companylogin != null && companylogin.getJSONObject("data") != null) {
                    String signerId = companylogin.getJSONObject("data").get("signerId").toString();
                    //ccid返回成功
                    map.put("signerId", signerId);
                    JSONObject jojo = new JSONObject();
                    jojo.put("signerId", signerId);
                    jojo.put("styleType", "1");
                    //jojo.put("textContent", "");//横向文文案
                    //jojo.put("keyContent", "");//防伪码信息
                    JSONObject companyMoulage = (JSONObject) JSONObject.parse(UtilHttp.sendPostToCloudContract(url + "/user/companyMoulage", jojo.toString(), token));
                    System.out.println(companyMoulage);
                    if (StringUtil.isNull(companyMoulage.get("code") + "").equals("200")) {
                        map.put("moulageId", companyMoulage.getJSONObject("data").get("moulageId").toString());
                        System.err.println("用户注册成功，签章生成成功");
                    } else {
                        try {
                            Exception exception = new Exception("生成签章失败");
                            throw exception;
                        } catch (Exception e) {
                            System.err.println("生成签章失败：" + companyMoulage.get("msg"));
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.err.println(companylogin.get("msg").toString());
                }
                break;
            default:
                Exception exception = new Exception("用户数据有误");
                try {
                    throw exception;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        ;
        return map;
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
        token = gotToken();

        this.path = Global.getValue(ConfigParamConstant.SYS_PARAM_PROTOCOL_PIC) + contractId + ".pdf";
        File file = new File(path);
        if (!file.exists()) {
            JSONObject download = (JSONObject) JSONObject.parse(UtilHttp.sendCloudContractGet(url + "/contract/download/0/" + contractId, "", token));
            if (download != null && StringUtil.isNull(download.get("code") + "").equals("200")) {
                String data = download.get("data").toString();
                System.out.println(data);
                UtilHttp.sendGetCCFile(url + "/auth/download/" + data, path);
            }
            // contractCZ(contractId);
            System.out.println(download);
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
                certifyType = "1";// 社会统一信用代码
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

    /**
     * 获得用户token
     *
     * @param appUserId 用户云合同id（ccid）
     */
    public String gotToken() {
        JSONObject jo = new JSONObject();
        jo.put("appId", appId);
        jo.put("appKey", password);
        String token = UtilHttp.getToken(url + "/auth/login", jo.toString());
        return token;
    }
}
