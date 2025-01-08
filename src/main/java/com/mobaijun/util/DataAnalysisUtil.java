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
     * 将 ZhInfo 列表写入 README 文件。
     *
     * @param infoList 包含 ZhInfo 对象的列表，每个对象代表一行表格数据。
     */
    public static void writerReadme(List<ZhInfo> infoList) {
        try {
            ensureReadmeFileExists();

            String content = buildReadmeContent(infoList);
            writeContentToReadme(content);
        } catch (IOException e) {
            log.error("File write failed: {}", e.getMessage(), e);
        }
    }

    /**
     * 确保 README 文件存在。如果文件不存在，则创建该文件。
     *
     * @throws IOException 如果文件创建失败，抛出 IOException。
     */
    private static void ensureReadmeFileExists() throws IOException {
        if (!Files.exists(README)) {
            Files.createFile(README);
        }
    }

    /**
     * 构建 README 文件的内容。
     *
     * @param infoList 包含 ZhInfo 对象的列表，每个对象代表一行表格数据。
     * @return 生成的 Markdown 内容。
     */
    private static String buildReadmeContent(List<ZhInfo> infoList) {
        StringBuilder content = new StringBuilder();

        // 添加标题
        content.append("# 信息列表\n\n");

        // 添加数据行
        int index = 1;
        for (ZhInfo temp : infoList) {
            content.append("<details>\n")
                    .append(String.format("<summary><b>%d. %s</b></summary>\n\n", index++, temp.getTitle()))
                    .append(String.format("- **地址**: [传送门](%s)\n", temp.getUrl()))
                    .append(String.format("- **热度**: %s\n", temp.getDetailText()))
                    .append(String.format("- **摘抄**: %s\n", truncate(temp.getExcerpt(), 40)))
                    // 空行分隔
                    .append("\n")
                    // 图片居中显示
                    .append(formatThumbnail(temp.getThumbnail()))
                    .append("\n</details>\n\n");
        }
        return content.toString();
    }

    /**
     * 格式化略缩图，统一图片大小。
     *
     * @param thumbnailUrl 略缩图的 URL。
     * @return 格式化后的图片 Markdown 或 HTML 代码。
     */
    private static String formatThumbnail(String thumbnailUrl) {
        if (thumbnailUrl == null || thumbnailUrl.isEmpty()) {
            return "暂无图片";
        }
        // 使用 HTML 标签设置图片大小（宽度为 200px）
        return String.format("<img src=\"%s\" alt=\"略缩图\" width=\"200\" />", thumbnailUrl);
    }

    /**
     * 截断字符串，确保其长度不超过指定最大值。
     *
     * @param text      需要截断的字符串。
     * @param maxLength 字符串的最大长度。
     * @return 截断后的字符串。如果原字符串长度小于等于 maxLength，则返回原字符串；否则返回截断后的字符串并添加 "..."。
     */
    private static String truncate(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        return text.length() > maxLength ? text.substring(0, maxLength) + "..." : text;
    }

    /**
     * 将生成的内容写入 README 文件。
     *
     * @param content 需要写入的 Markdown 表格内容。
     * @throws IOException 如果文件写入失败，抛出 IOException。
     */
    private static void writeContentToReadme(String content) throws IOException {
        Files.writeString(README, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
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
