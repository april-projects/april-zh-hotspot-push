package com.mobaijun.util;

import com.mobaijun.constant.Constant;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: StringUtil
 * class description： 字符串工具类
 *
 * @author MoBaiJun 2022/9/8 9:23
 */
public class StringUtil {

    /**
     * 获取请求地址
     *
     * @param type   类型
     * @param cardId id
     * @return url
     */
    public static String getZhUrl(String type, String cardId) {
        return Constant.ZH_REQUEST_ADDR + type + "/" + cardId.substring(cardId.indexOf("_") + 1);
    }
}
