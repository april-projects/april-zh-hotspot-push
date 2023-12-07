package com.mobaijun.util;

import cn.hutool.core.codec.Base64;
import com.mobaijun.constant.Constant;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    /**
     * 签名校验
     *
     * @return 签名后的字符串
     */
    public static String generateSignature(long timestamp) {
        //把timestamp+"\n"+密钥当做签名字符串
        String stringToSign = timestamp + "\n" + Constant.FS_SIGN;
        //使用HmacSHA256算法计算签名
        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            mac.init(new SecretKeySpec(stringToSign.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] signData = mac.doFinal(new byte[]{});
        return Base64.encode(signData);
    }
}
