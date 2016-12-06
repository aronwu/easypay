package com.wuswoo.easypay.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wuxinjun on 16/12/6.
 */
public class ExceptionUtil {

    public static String convertExcepitonToString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString(); // stack trace as a string˜
    }
}
