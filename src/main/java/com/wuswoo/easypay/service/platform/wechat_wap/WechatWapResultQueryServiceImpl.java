package com.wuswoo.easypay.service.platform.wechat_wap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.*;
import com.wuswoo.easypay.service.payment.IResultQueryService;
import com.wuswoo.easypay.service.platform.wechat.PayRefundQueryLoc;
import com.wuswoo.easypay.service.platform.wechat.util.WechatStatusUtil;
import com.wuswoo.easypay.service.repository.IPaymentService;
import com.wuswoo.easypay.service.repository.IRefundDBService;
import com.wuswoo.easypay.service.repository.IResultQueryDBService;
import com.wuswoo.easypay.service.request.BaseRequest;
import com.wuswoo.easypay.service.util.PayConstant;
import com.wuswoo.easypay.service.util.RandomStringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import weixin.popular.bean.paymch.Refundquery;
import weixin.popular.client.LocalHttpClient;
import weixin.popular.util.MapUtil;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuxinjun on 16/11/28.
 */
public class WechatWapResultQueryServiceImpl implements IResultQueryService {
    private final static Logger logger = LogManager.getLogger(WechatWapResultQueryServiceImpl.class);

    protected  static final String MCH_API_URI = "https://api.mch.weixin.qq.com";
    private BaseRequest request;

    @Autowired
    private IRefundDBService refundDBService;

    @Autowired
    private IResultQueryDBService resultQueryDBService;

    @Autowired
    private IPaymentService paymentService;

    public WechatWapResultQueryServiceImpl(BaseRequest resultQueryRequest) {
        this.request = resultQueryRequest;
    }

    @Override
    public PaymentResult queryPayment(Payment payment) {
        return null;
    }

    @Override
    public RefundResult queryRefund(RefundResult refundResult) {
        logger.info("RefundQuery request info : {}", JSON.toJSONString(refundResult));
        Refundquery  refundquery = new Refundquery();
        List<RefundResult>  refundResults = new ArrayList<RefundResult>();
        Refund  refund = refundDBService.getRefundById(refundResult.getRefundId());
        refundquery.setAppid(this.request.getQueryParam("appid"));
        refundquery.setMch_id(this.request.getQueryParam("mch_id"));
        refundquery.setNonce_str(RandomStringUtils.randomStringByLength(30));
        refundquery.setRefund_id(refundResult.getTradeNo());
        PayRefundQueryLoc result = queryRefundQueryClient(refundquery, this.request.getQueryParam("paterner_key"));
        logger.info("RefundQuery result info: {}", result);
        ResultQuery resultQuery = resultQueryDBService.findResultQuerybyPlatformTradeTypeId(
            PayConstant.PlatformType.WEIXINWAP.intValue(),
            PayConstant.PaymentResultType.REFUND.byteValue(), refundResult.getId());
        if ("SUCCESS".equalsIgnoreCase(result.getReturn_code())) {
            if ("SUCCESS".equalsIgnoreCase(result.getResult_code())) {
                Byte newRefundStatus = WechatStatusUtil.parseRefundStatus(result.getRefund_status_0());
                Byte originRefundStatus = refundResult.getStatus();
                resultQuery.setQueryStatus(newRefundStatus);
                resultQuery.setReturnContent(JSONObject.toJSONString(result));
                resultQueryDBService.saveResultQuery(resultQuery);
                if (newRefundStatus == originRefundStatus) {
                    return refundResult;
                }
                refundResult.setRefundAmount(result.getRefund_fee());
                refundResult.setReturnCode(result.getRefund_status_0());
                refundResult.setStatus(newRefundStatus);
                try {
                    refundResult.setReturnContent(XMLConverUtil.convertToXML(result));
                }catch(Exception ex) {
                    refundResult.setReturnContent(JSONObject.toJSONString(result));
                }
                refundResults.add(refundResult);
                paymentService.saveRefundAndResults(refund, refundResults);
            } else {
                //重新发起退款查询
                resultQueryDBService.nextQuery(resultQuery);
            }
        } else {
            //重新发起退款查询
            resultQueryDBService.nextQuery(resultQuery);
            logger.error(String.format("微信退款查询失败code:%s 错误内容:%s", result.getErr_code()
                ,result.getErr_code_des()));
            throw new EasyPayException("微信退款查询失败");
        }
        return refundResult;
    }


    protected PayRefundQueryLoc queryRefundQueryClient(Refundquery refundquery, String key) {
        Map<String, String> map = MapUtil.objectToMap(refundquery);
        String sign = SignatureUtil.generateSign(map, key);
        refundquery.setSign(sign);
        String refundqueryXml = XMLConverUtil.convertToXML(refundquery);
        HttpUriRequest httpUriRequest = RequestBuilder.post()
            .setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_XML.toString()))
            .setUri(MCH_API_URI)
            .setEntity(new StringEntity(refundqueryXml, Charset.forName("utf-8")))
            .build();
        return LocalHttpClient.executeXmlResult(httpUriRequest, PayRefundQueryLoc.class);

    }

}

