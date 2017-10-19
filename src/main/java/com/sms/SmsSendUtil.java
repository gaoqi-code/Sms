package com.sms;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * Created by hxq on 2017/4/12.
 *
 *结果调用返回结果
 //    {
 //        "result": 0, //0表示成功(计费依据)，非0表示失败
 //            "errmsg": "OK", //result非0时的具体错误信息
 //            "ext": "", //用户的session内容，腾讯server回包中会原样返回
 //            "sid": "xxxxxxx", //标识本次发送id，标识一次短信下发记录
 //            "fee": 1 //短信计费的条数
 //    }
 */
public class SmsSendUtil {


    /**
     * @param phoneNumber 手机号
     * @param content 内容
     * @return
     */
    public static boolean generalSingleSend(String phoneNumber,String content) {
        boolean isSuccess = false;
        if (VerifyUtil.checkPhoneNumber(phoneNumber) && StringUtils.isNotEmpty(content)) {
            SmsSingleSender singleSender = SmsSingleSender.getIstance();
            SmsSingleSenderResult singleSenderResult;
            try {
                singleSenderResult = singleSender.send(0, "86", phoneNumber, content, "", "");
                if (singleSenderResult != null && singleSenderResult.result == VerifyUtil.Status.SUCCESS.getVal()) {
                    isSuccess = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    /**
     * 指定模板单发
     * @param phoneNumber 手机号
     * @param tmplId 模板id
     * @param templateContent  模板内容为：测试短信，{1}，{2}，{3}，上学。
     * @return
     */
    public static boolean templateSingleSend(String phoneNumber,int tmplId,ArrayList<String> templateContent) {
        boolean isSuccess = false;
        if (VerifyUtil.checkPhoneNumber(phoneNumber) && CollectionUtils.isNotEmpty(templateContent)) {
            SmsSingleSender singleSender = SmsSingleSender.getIstance();
            SmsSingleSenderResult singleSenderResult;
            try {
                singleSenderResult = singleSender.sendWithParam("86", phoneNumber, tmplId, templateContent, "", "", "");
                if (singleSenderResult != null && singleSenderResult.result == VerifyUtil.Status.SUCCESS.getVal()) {
                    isSuccess = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }

    public static void main(String[] args) {
//        generalSingleSend("15765056585","666测试");
        ArrayList<String> params = new ArrayList<>();
        params.add("藏族自治州");
        templateSingleSend("18236920845",22928,params);
    }

}
