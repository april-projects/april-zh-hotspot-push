package com.mobaijun;

import com.mobaijun.constant.Constant;
import com.mobaijun.model.ZhEntity;
import com.mobaijun.util.DataAnalysisUtil;

/**
 * Hi! friend!
 * <img src="https://tencent.cos.mobaijun.com/img/icon/avatar.jpg" alt="migrate" height="200" width="200">
 *
 * @author mobai
 */
public class RunZhApplication {

    /**
     * run 'RunZhApplication'
     */
    public static void main(String[] args) {
        // 发起请求
        ZhEntity zhDataList = DataAnalysisUtil.getZhDataList(Constant.ZH_DATA_API);
        DataAnalysisUtil.getZhInfoData(zhDataList);
    }
}
