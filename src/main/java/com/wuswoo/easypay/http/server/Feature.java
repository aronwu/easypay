package com.wuswoo.easypay.http.server;

import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * Created by wuxinjun on 16/9/22.
 */
public class Feature {

    /**
     * HTTP response object to string时json seraial的特征
     */
    public static final SerializerFeature[] RESPONSE_JSON_SERIAL_FEATURE =
        {
            SerializerFeature.WriteNonStringKeyAsString
        };
    /**
     * HTTP 默认字符集
     */
    public static final Charset charset = CharsetUtil.UTF_8;

    /**
     * 是否开启HTTP Acccess Log
     */
    public static boolean enableHttpAccessLog = true;
}
