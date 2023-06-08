package plus.wcj.heifer.common.swagger;

import org.springframework.util.StringUtils;

/**
 * <a href="https://htmlcolorcodes.com/zh/yanse-biao/bianping-hua-sheji-yanse-biao/">color</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/12/19
 */
public class HtmlTool {

    private HtmlTool() {
    }

    public static final String TURQUOISE = "#17A589";
    public static final String ALIZARIN = "#CB4335";
    public static final String PETER_RIVER = "#2E86C1";


    public static String b(String text) {
        return "<b>" + fontColor(text, TURQUOISE) + "</b>";
    }

    public static String code(String text) {
        return code(text, PETER_RIVER);
    }

    public static String code(String text, String fontColor) {
        text = "`" + text + " `";
        return "<code>" + fontColor(text, fontColor) + "</code>";
    }


    public static String p(String beforeText, String afterText) {
        if (StringUtils.hasText(beforeText)) {
            return afterText;
        }
        if (StringUtils.hasText(afterText)) {
            return beforeText;
        }
        return beforeText + "<p />" + afterText;
    }

    public static String fontColor(String text, String fontColor) {
        return "<font color=\"" + fontColor + "\">" + text + "</font>";
    }


}
