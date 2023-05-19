package com.mobaijun.util;

import cn.hutool.core.lang.Console;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

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
     * @param sendToFeishu   飞书
     * @param dWebhookUrl    钉钉群机器人推送地址
     * @param fWebhookUrl    飞书群机器人推送地址
     * @param message        推送内容
     */
    public static void sendMessage(boolean sendToDingTalk, boolean sendToFeishu, String dWebhookUrl, String fWebhookUrl, String message) {
        if (sendToDingTalk) {
            sendDingTalkMessage(dWebhookUrl, message);
        }
        if (sendToFeishu) {
            sendFeishuMessage(fWebhookUrl, message);
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
    public static void sendFeishuMessage(String webhookUrl, String message) {
    }
}
