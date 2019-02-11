package com.zc.mall.core.user.executer;

import com.zc.mall.core.common.executer.BaseExecuter;
import com.zc.mall.core.user.model.UserIdentifyModel;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.stereotype.Component;

/**
 * 用户实名请求-法人
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
@Component
public class UserRealNameRequestArtifExecuter extends BaseExecuter {

    private UserIdentifyModel model;

    @Override
    public void init() {
        if (!(this.obj instanceof UserIdentifyModel)) {
            throw new BusinessException("实例不存在");
        }
        this.model = (UserIdentifyModel) this.obj;
        this.user = model.getUser();
    }

    @Override
    public void handleInterface() {
//		Object pay = ReflectUtil.invokeMethod(FuiouPayMethod.WEBARTIFREG.getClassName(), FuiouPayMethod.WEBARTIFREG.getMethodName(), 
//											  StringUtil.getSerialNumber(), 
//											  StringUtil.isNull(this.user.getId()), 
//											  this.user.getMobile(), 
//											  model.getCompanyName(), 
//											  model.getRealName(), 
//											  model.getCardNo(), 
//											  StringUtil.isNull(this.user.getEmail()), 
//											  "", 
//											  model.getCardType() == BaseConstant.CARD_TYPE_IDENTIFY_CARD ? "0" : "7", 
//											  "", 
//											  "", 
//											  "");
//		model.setPayReg(pay);
    }
}
