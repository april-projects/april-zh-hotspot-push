package com.mobaijun.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.mobaijun.constant.Constant;
import com.mobaijun.model.ZhEntity;
import com.mobaijun.model.ZhInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
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
        LinkedList<ZhInfo> zhInfos = zhEntity.getData().stream().map(temp -> {
            ZhInfo zhInfo = new ZhInfo();
            // Q_552344513
            zhInfo.setTitle(temp.getTarget().getTitle());
            zhInfo.setUrl(StringUtil.getZhUrl(temp.getTarget().getType(), temp.getCardId()));
            zhInfo.setDetailText(temp.getDetailText());
            zhInfo.setExcerpt(temp.getTarget().getExcerpt());
            String thumbnail = temp.getChildren().get(0).getThumbnail();
            if (thumbnail == null) {
                // 默认图片
                zhInfo.setThumbnail("https://tencent.cos.mobaijun.com/img/banner/4.jpg");
            } else {
                zhInfo.setThumbnail(thumbnail);
            }
            return zhInfo;
        }).collect(Collectors.toCollection(LinkedList::new));
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
            // title
            Files.write(README, Constant.README_TITLE.getBytes());
            // 换行
            Files.write(README, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
            // 分割线
            Files.write(README, Constant.UNDERSCORE.getBytes(), StandardOpenOption.APPEND);
            // 换行
            Files.write(README, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
            // 表格
            Files.write(README, "|      |".getBytes(), StandardOpenOption.APPEND);
            // 换行
            Files.write(README, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
            // 表格地线
            Files.write(README, "| ---- |".getBytes(), StandardOpenOption.APPEND);
            Files.write(README, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
            infoList.forEach(temp -> {
                try {
                    Files.write(README, ("|" + temp.formatMarkdown()).getBytes(), StandardOpenOption.APPEND);
                    Files.write(README, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            log.error(e.getMessage(), "file write failed error");
        }
    }
}
