package com.mobaijun.constant;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: Constant
 * class description： 通用常量
 *
 * @author MoBaiJun 2022/9/7 17:12
 */
public class Constant {

    /**
     * 热榜 top 50 api
     */
    public static final String ZH_DATA_API = "https://www.zhihu.com/api/v3/feed/topstory/hot-lists/total?limit=50&desktop=true";
    public static final String ZH_REQUEST_ADDR = "https://www.zhihu.com/";
    public static final String README_TITLE = "# 今日热榜 Top 50 ";
    public static final String README_NAME = "ThieuHotList.md";
    public static final String UNDERSCORE = "---";

    /**
     * 钉钉 webhook 地址
     */
    public static final String DING_WEBHOOK = String.format("https://oapi.dingtalk.com/robot/send?access_token=%s",System.getenv("DING_WEBHOOK"));

    /**
     * 飞书地址
     */
    public static final String FS_WEBHOOK = String.format("https://open.feishu.cn/open-apis/bot/v2/hook/%s",System.getenv("FS_WEBHOOK"));

    /**
     * 飞书签名
     */
    public static final String FS_SIGN = "FS_SIGN";
}
