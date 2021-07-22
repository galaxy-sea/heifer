package plus.wcj.heifer.boot.manager.sms.aliyun;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.manager.sms.SmsServer;

/**
 * <a href="https://help.aliyun.com/document_detail/215759.html?spm=a2c4g.11186623.6.661.30da535ego6Kgu">首页 > 短信服务 > 开发指南 > SDK参考 > 升级版 SDK > Java SDK</a>
 * <a href="https://help.aliyun.com/document_detail/101300.html?spm=a2c4g.11186623.6.619.4abe10896D5CSf">首页 > 短信服务 > 开发指南 > API参考 > 简介</a>
 * <a href="https://help.aliyun.com/document_detail/101346.html?spm=a2c4g.11186623.6.629.23075695SWfvsz">首页 > 短信服务 > 开发指南 > API参考 > 错误码 > API 错误码</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2021/7/21
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(
        AliyunSmsProperties.class
)
public class AliyunSmsServer implements SmsServer {

    public static final String OK = "OK";

    private final Client client;
    private final AliyunSmsProperties aliyunSmsProperties;


    @Override
    public String sendCaptcha(String phoneNumbers) {
        String captcha = String.valueOf(RandomUtils.nextInt(100000, 999999));
        this.sendCaptcha(phoneNumbers, captcha);
        return captcha;
    }


    @Override
    public void sendCaptcha(String phoneNumbers, String captcha) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumbers)
                .setSignName(this.aliyunSmsProperties.getSignName())
                .setTemplateCode(TemplateCode.SMS_185520034.toString())
                .setTemplateParam("{\"code\":\"" + captcha + "\"}");

        SendSmsResponse sendSmsResponse;
        try {
            sendSmsResponse = this.client.sendSms(sendSmsRequest);
        } catch (Exception e) {
            throw new ResultException(ResultStatus.SMS_NETWORK_EXCEPTION);
        }
        SendSmsResponseBody body = sendSmsResponse.getBody();
        if (!OK.equals(body.getCode())) {
            throw new ResultException(ResultStatus.SMS_SEND_FAIL, body.getMessage());
        }
    }


    private enum TemplateCode {

        /** 验证码 */
        SMS_185520034,
    }
}
