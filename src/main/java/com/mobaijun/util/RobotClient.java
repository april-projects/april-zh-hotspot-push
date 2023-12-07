package com.mobaijun.util;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * software：IntelliJ IDEA 2023.1.1<br>
 * class name: RobotClient<br>
 * class description: 推送
 *
 * @author MoBaiJun 2023/5/20 5:45
 */
public class RobotClient {

    /**
     * 推送指定平台
     *
     * @param sendToDingTalk 钉钉
     * @param sendToFeiShu   飞书
     * @param dWebhookUrl    钉钉群机器人推送地址
     * @param fWebhookUrl    飞书群机器人推送地址
     * @param message        推送内容
     */
    public static void sendMessage(boolean sendToDingTalk, boolean sendToFeiShu, String dWebhookUrl, String fWebhookUrl, String message) {
        if (sendToDingTalk) {
            sendDingTalkMessage(dWebhookUrl, message);
        }
        if (sendToFeiShu) {
            sendFeiShuMessage(fWebhookUrl, message);
        }
    }

    /**
     * 钉钉
     *
     * @param webhookUrl 推送地址
     * @param message    内容
     */
    public static void sendDingTalkMessage(String webhookUrl, String message) {
        DingTalkClient client = new DefaultDingTalkClient(webhookUrl);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        // isAtAll类型如果不为Boolean，请升级至最新SDK
        at.setIsAtAll(true);
        request.setAt(at);

        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("新鲜出炉-15条知乎热榜");
        markdown.setText(message);
        request.setMarkdown(markdown);
        try {
            OapiRobotSendResponse response = client.execute(request);
            Console.log(response.getMessage() != null ? null : response.getErrmsg());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 飞书
     *
     * @param webhookUrl 推送地址
     * @param message    内容
     */
    public static void sendFeiShuMessage(String webhookUrl, String message) {
        long epochSecond = Instant.now().getEpochSecond();
        //请求的JSON数据，这里用map在工具类里转成json格式
        Map<String, Object> json = new HashMap<>();
        Map<String, Object> text = new HashMap<>();
        json.put("msg_type", "text");
        text.put("text", "新鲜出炉-知乎热榜：" + message);
        json.put("sign", StringUtil.generateSignature(epochSecond));
        json.put("timestamp", epochSecond);
        json.put("content", text);
        HttpRequest.post(webhookUrl).header("Content-Type", "application/json")
                .body(JSONUtil.toJsonStr(json)).execute().body();
    }
}
