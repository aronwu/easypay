package com.wuswoo.easypay.service.platform.wechat.util;

import com.wuswoo.easypay.service.platform.wechat.PayRefundQueryLoc;
import weixin.popular.bean.paymch.MchOrderInfoResult;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.SecapiPayRefundResult;
import weixin.popular.bean.paymch.UnifiedorderResult;
import weixin.popular.util.MapUtil;
import weixin.popular.util.SignatureUtil;

import java.util.Map;

public class WechatAppUtil {

	public static boolean validateUnifiedorderResultSign(
			UnifiedorderResult result, String key){
		Map<String, String> map = MapUtil.objectToMap(result, "sign");
		String sign = SignatureUtil.generateSign(map, key);
		return sign.equals(result.getSign());
	}

	public static boolean validateMchPayNotifySign(MchPayNotify result, String key){
		Map<String, String> map = MapUtil.objectToMap(result, "sign");
		String sign = SignatureUtil.generateSign(map, key);
		return sign.equals(result.getSign());
	}
	
	public static boolean validateMchOrderInfoResultSign(
			MchOrderInfoResult result, String key){
		Map<String, String> map = MapUtil.objectToMap(result, "sign");
		String sign = SignatureUtil.generateSign(map, key);
		return sign.equals(result.getSign());
	}
	
	public static boolean validateSecapiPayRefundResultSign(
			SecapiPayRefundResult result, String key){
		Map<String, String> map = MapUtil.objectToMap(result, "sign");
		String sign = SignatureUtil.generateSign(map, key);
		return sign.equals(result.getSign());
	}
	
	public static boolean validateRefundqueryResultSign(PayRefundQueryLoc result, String key){
		Map<String, String> map = MapUtil.objectToMap(result, "sign");
		String sign = SignatureUtil.generateSign(map, key);
		return sign.equals(result.getSign());
	}
	
}
