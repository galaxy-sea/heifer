package plus.wcj.heifer.boot.manager.sms;

/**
 * 通用的oss顶级接口
 *
 * @author changjinwei
 * @since 2021/7/19
 */

public interface SmsServer {

    /**
     * 自动产生验证码并发送验证码到指定手机
     *
     * @param phoneNumbers 手机号
     *
     * @return 随机数字验证码
     */
    String sendCaptcha(String phoneNumbers);

    /**
     * 发送验证码到指定手机
     *
     * @param phoneNumbers 手机号
     * @param captcha 验证码
     */
    void sendCaptcha(String phoneNumbers, String captcha);

}
