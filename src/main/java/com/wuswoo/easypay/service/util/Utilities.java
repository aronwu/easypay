package com.wuswoo.easypay.service.util;

import com.wuswoo.easypay.common.util.DateTimeUtil;

import java.util.Date;

/**
 * Created by wuxinjun on 16/11/2.
 */
public class Utilities {
    /**
     * 产生数字和字母混合的指定位数的随机密码
     *
     * @param count 位数
     * @return 随机密码
     */
    public static String getRandomPwd(int count) {
        String randomPwd = RandomStringUtils.random(count, true, false);
        return randomPwd;
    }

    public static String getRandomNumber(int count) {
        return RandomStringUtils.random(count, false, true);
    }

    public static Integer parseInt(String num) {
        Integer n = null;
        try {
            n = (num == null) ? null : Integer.parseInt(num);
        } catch (Exception e) {

        }
        return n;
    }

    public static Byte parseByte(String num) {
        Byte n = null;
        try {
            n = (num == null) ? null : Byte.parseByte(num);
        } catch (Exception e) {

        }
        return n;
    }

    public static Short parseShort(String num) {
        Short n = null;
        try {
            n = (num == null) ? null : Short.parseShort(num);
        } catch (Exception e) {

        }
        return n;
    }

    public static Long parseLong(String num) {
        Long n = null;
        try {
            n = (num == null) ? null : Long.parseLong(num);
        } catch (Exception e) {

        }
        return n;
    }

    public static Boolean parseBoolean(String num) {
        Boolean n = null;
        try {
            n = (num == null) ? null : Boolean.parseBoolean(num);
        } catch (Exception e) {

        }
        return n;
    }

    public static StringBuilder appendParam(StringBuilder returnStr, String paramId,
        String paramValue) {
        if (returnStr.length() > 0) {
            if (paramValue != null && !paramValue.equals("")) {
                returnStr.append("&").append(paramId).append("=").append(paramValue);
            }
        } else {
            if (paramValue != null && !paramValue.equals("")) {
                returnStr.append(paramId).append("=").append(paramValue);
            }
        }
        return returnStr;
    }

    public static String getFlowCode(boolean production, int padding, long id, int length) {
        StringBuilder sbCode = new StringBuilder(50);
        sbCode.append(production ? "2" : "1");
        sbCode.append(DateTimeUtil.getPaymentCodeDateStr(new Date()));
        sbCode.append(String.format("%02d", padding));
        String strId = new Long(id).toString();
        sbCode.append(strId.length() > length ?
            strId.substring(strId.length() - length) :
            String.format("%0" + length + "d", id));
        return sbCode.toString();
    }

    public static String getRefundCode(boolean production, int platformId, long id, int length) {
        StringBuilder sbCode = new StringBuilder(50);
        sbCode.append(DateTimeUtil.getRefundCodeDateStr(new Date()));
        sbCode.append(String.format("%02d", platformId));
        sbCode.append(production ? "2" : "1");
        String strId = new Long(id).toString();
        sbCode.append(strId.length() > length ?
            strId.substring(strId.length() - length) :
            String.format("%0" + length + "d", id));
        sbCode.append(getRandomNumber(4));
        return sbCode.toString();
    }
}
