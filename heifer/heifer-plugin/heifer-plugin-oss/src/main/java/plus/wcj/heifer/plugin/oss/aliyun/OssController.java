package plus.wcj.heifer.plugin.oss.aliyun;

import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.plugin.oss.OssServer;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/2/22
 */
@RestController
@ResultResponseBody
@RequestMapping("/oss")
public class OssController {

    private final OssServer ossServer;

    public OssController(OssServer ossServer) {
        this.ossServer = ossServer;
    }


    @GetMapping("/policy/**")
    public Map<String, String> policy(@RequestParam(value = "ossKey", defaultValue = OssServer.DEFAULT_OSS_KEY) String ossKey,
                                      HttpServletRequest request) {
        String ossObjectDir = Utils.extractPathWithinPattern((String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE),
                                                             (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)
        );
        return this.ossServer.policy(ossObjectDir, ossKey);
    }


    @GetMapping("/**")
    public void redirect(@RequestParam(value = "ossKey", defaultValue = OssServer.DEFAULT_OSS_KEY) String ossKey,
                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ossObjectPath = Utils.extractPathWithinPattern((String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE),
                                                              (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)
        );
        URL redirect = this.ossServer.redirect(ossObjectPath, ossKey);
        response.sendRedirect(redirect.toString());
    }


    private static class Utils {

        private static final String PATH_SEPARATOR = "/";
        private static final boolean TRIM_TOKENS = false;
        private static final boolean IGNORE_EMPTY_TOKENS = true;
        private static final Map<String, String> STRING_STRING_REFERENCE_MAP = new WeakHashMap<>();
        // private static final Map<String, String> STRING_STRING_REFERENCE_MAP = new ReferenceMap<String, String>();


        public static String extractPathWithinPattern(String pattern, String path) {
            return STRING_STRING_REFERENCE_MAP.computeIfAbsent(path, key -> {
                String[] patternParts = StringUtils.tokenizeToStringArray(pattern, PATH_SEPARATOR, TRIM_TOKENS, IGNORE_EMPTY_TOKENS);
                String[] pathParts = StringUtils.tokenizeToStringArray(path, PATH_SEPARATOR, TRIM_TOKENS, IGNORE_EMPTY_TOKENS);
                StringBuilder builder = new StringBuilder();
                boolean pathStarted = false;

                for (int segment = 0; segment < patternParts.length; segment++) {
                    String patternPart = patternParts[segment];
                    if (patternPart.indexOf('*') > -1 || patternPart.indexOf('?') > -1) {
                        for (; segment < pathParts.length; segment++) {
                            //noinspection AlibabaAvoidComplexCondition
                            if (pathStarted || (segment == 0 && !pattern.startsWith(PATH_SEPARATOR))) {
                                builder.append(PATH_SEPARATOR);
                            }
                            builder.append(pathParts[segment]);
                            pathStarted = true;
                        }
                    }
                }
                return builder.toString();
            });
        }
    }


}
