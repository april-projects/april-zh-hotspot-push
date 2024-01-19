package com.mobaijun.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.mobaijun.constant.Constant;
import com.mobaijun.model.ZhEntity;
import com.mobaijun.model.ZhInfo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * software：IntelliJ IDEA 2022.1
 * class name: DataAnalysisUtil
 * class description：数据解析工具类
 *
 * @author MoBaiJun 2022/9/7 17:12
 */
public class DataAnalysisUtil {

    /**
     * tools log
     */
    private static final Log log = Log.get(DataAnalysisUtil.class);

    private static final Path README = Paths.get(Constant.README_NAME);

    /**
     * 获取知乎内容详情
     *
     * @param api 请求地址
     * @return 内容详情
     */
    public static ZhEntity getZhDataList(String api) {
        log.info("Know the request address：{}", api);
        JSONObject parseObj = JSONUtil.parseObj(HttpUtil.get(api));
        return JSONUtil.toBean(parseObj, ZhEntity.class);
    }

    /**
     * 获取知乎详情数据
     *
     * @param zhEntity 数据模型
     */
    public static void getZhInfoData(ZhEntity zhEntity) {
        List<ZhInfo> zhInfos = zhEntity.getData().stream().map(temp -> {
            ZhInfo zhInfo = new ZhInfo();
            // Q_552344513
            zhInfo.setTitle(temp.getTarget().getTitle());
            zhInfo.setUrl(StringUtil.getZhUrl(temp.getTarget().getType(), temp.getCardId()));
            zhInfo.setDetailText(temp.getDetailText());
            zhInfo.setExcerpt(temp.getTarget().getExcerpt());
            String thumbnail = temp.getChildren().get(0).getThumbnail();
            // 默认图片
            zhInfo.setThumbnail(thumbnail.isEmpty() ? "./img/1.jpg" : thumbnail);
            return zhInfo;
        }).collect(Collectors.toList());
        // 推送机器人
        sendMsg(zhInfos);
        // 写入 readme
        writerReadme(zhInfos);
    }

    /**
     * 写入 readme
     *
     * @param infoList 详情集合
     */
    public static void writerReadme(List<ZhInfo> infoList) {
        try {
            if (!Files.exists(README)) {
                Files.createFile(README);
            }

            // 使用 StringBuilder 构建文件内容
            StringBuilder content = new StringBuilder();

            // 表格头部
            content.append("| 序号 |    标题    |              地址              |   热度   |          摘抄          |             略缩图             |").append(System.lineSeparator());

            // 表格对齐方式
            content.append("|:----:|:----------:|:----------------------------:|:--------:|:----------------------:|:-----------------------------:|").append(System.lineSeparator());

            // 添加数据行
            int index = 1;
            for (ZhInfo temp : infoList) {
                content.append(String.format("| %-4d | %-10s | [%-30s](%s) | %-7s | %-18s | ![略缩图](%s) |",
                                index++,
                                temp.getTitle(),
                                "查看地址",
                                temp.getUrl(),
                                temp.getDetailText(),
                                temp.getExcerpt(),
                                temp.getThumbnail()))
                        .append(System.lineSeparator());
            }

            // 将内容写入文件
            Files.writeString(README, content.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            log.error("File write failed: " + e.getMessage());
        }
    }

    /**
     * 构件markdown推送信息
     *
     * @param infoList 消息列表
     */
    private static void sendMsg(List<ZhInfo> infoList) {
        StringBuilder msg = new StringBuilder();
        // 钉钉最好不要超过15条热榜，否则无法发送，飞书不要超过10条数据，并且飞书不支持markdown，后续有空在优化飞书显示内容
        infoList.subList(0, 15).forEach(item -> msg.append(item.formatMarkdownMsg())
                .append(System.lineSeparator())
                .append(System.lineSeparator()).append("---")
                .append(System.lineSeparator()).append(System.lineSeparator()));
        RobotClient.sendMessage(false, false, Constant.DING_WEBHOOK, Constant.FS_WEBHOOK, msg.toString());
    }
}
