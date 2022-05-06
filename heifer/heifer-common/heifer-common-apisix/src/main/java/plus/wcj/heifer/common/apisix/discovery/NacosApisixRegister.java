package plus.wcj.heifer.common.apisix.discovery;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plus.wcj.heifer.common.apisix.admin.api.RouteClient;
import plus.wcj.heifer.common.apisix.admin.api.UpstreamClient;
import plus.wcj.heifer.common.apisix.properties.ApisixProperties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 将nacos信息注册到Apisix中
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/4
 */
public class NacosApisixRegister implements ApisixRegister {

    private static final Logger log = LoggerFactory.getLogger(NacosApisixRegister.class);
    private final NacosDiscoveryProperties nacosDiscoveryProperties;

    private final ApisixProperties apisixProperties;
    private final RouteClient routeClient;
    private final UpstreamClient upstreamClient;

    public NacosApisixRegister(NacosDiscoveryProperties nacosDiscoveryProperties, ApisixProperties apisixProperties, RouteClient routeClient, UpstreamClient upstreamClient) {
        this.nacosDiscoveryProperties = nacosDiscoveryProperties;
        this.apisixProperties = apisixProperties;
        this.routeClient = routeClient;
        this.upstreamClient = upstreamClient;
    }

    @PostConstruct
    public void init() {
        this.register();
    }


    @Override
    public void register() {
        String namespace = this.nacosDiscoveryProperties.getNamespace();
        String group = this.nacosDiscoveryProperties.getGroup();
        String service = this.nacosDiscoveryProperties.getService();
        namespace = StringUtils.hasText(namespace) ? namespace : "public";

        String id = namespace + "@" + group + "@" + service;
        id = DigestUtils.md5DigestAsHex(id.getBytes(StandardCharsets.UTF_8));


        if (!this.hasUpstream(id)) {
            this.putUpstream(id);
        }
        if (!this.hasRoute(id)) {
            this.putRoute(id);
        }
    }

    private void putRoute(String routeId) {
        Map<String, Object> body = new LinkedHashMap<String, Object>() {{
            put("name", nacosDiscoveryProperties.getService());
            put("uri", "/" + nacosDiscoveryProperties.getService() + "/*");

            put("labels", new LinkedHashMap<String, String>() {{
                put("nacos_namespace", StringUtils.hasText(nacosDiscoveryProperties.getNamespace()) ? nacosDiscoveryProperties.getNamespace() : "public");
                put("nacos_group", nacosDiscoveryProperties.getGroup());
                put("nacos_service", nacosDiscoveryProperties.getService());
            }});
            put("plugins", new LinkedHashMap<String, Object>() {{
                put("proxy-rewrite", new LinkedHashMap<String, Object>() {{
                    put("regex_uri", new String[]{"^/" + nacosDiscoveryProperties.getService() + "/(.*)", "/$1"});
                }});
            }});
            put("upstream_id", routeId);
        }};

        try {
            ResponseEntity<String> response = this.routeClient.route(routeId, body, this.apisixProperties.getToken());
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Apisix Register Error");
            }
            log.info("apisix route register success, routeId is #{}", routeId);
        } catch (Exception e) {
            log.error("Apisix Register Error");
            throw new RuntimeException(e);
        }

    }

    private boolean hasRoute(String routeId) {
        try {
            ResponseEntity<String> response = this.routeClient.route(routeId, this.apisixProperties.getToken());
            HttpStatus statusCode = response.getStatusCode();
            return HttpStatus.OK.equals(statusCode);
        } catch (FeignException.NotFound e) {
            String message = e.getMessage();
            return "{\"message\":\"Key not found\"}".equals(message);
        } catch (Exception e) {
            log.error("Apisix Register Error");
            throw new RuntimeException(e);
        }

    }

    private void putUpstream(String upstreamId) {
        Map<String, Object> body = new LinkedHashMap<String, Object>() {{
            put("type", "roundrobin");
            put("scheme", "http");
            put("discovery_type", "nacos");
            put("pass_host", "pass");
            put("name", nacosDiscoveryProperties.getService());
            put("service_name", nacosDiscoveryProperties.getService());
            put("labels", new LinkedHashMap<String, String>() {{
                put("nacos_namespace", StringUtils.hasText(nacosDiscoveryProperties.getNamespace()) ? nacosDiscoveryProperties.getNamespace() : "public");
                put("nacos_group", nacosDiscoveryProperties.getGroup());
                put("nacos_service", nacosDiscoveryProperties.getService());
            }});

            put("discovery_args", new LinkedHashMap<String, Object>() {{
                put("group_name", nacosDiscoveryProperties.getGroup());
                put("namespace_id", StringUtils.hasText(nacosDiscoveryProperties.getNamespace()) ? nacosDiscoveryProperties.getNamespace() : "public");
            }});
        }};

        try {
            ResponseEntity<String> response = this.upstreamClient.upstream(upstreamId, body, this.apisixProperties.getToken());
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Apisix Register Error");
            }
            log.info("apisix upstream register success, upstreamId is #{}", upstreamId);
        } catch (Exception e) {
            log.error("Apisix Register Error");
            throw new RuntimeException(e);
        }
    }


    private boolean hasUpstream(String upstreamId) {
        try {
            ResponseEntity<String> response = this.upstreamClient.upstream(upstreamId, this.apisixProperties.getToken());
            HttpStatus statusCode = response.getStatusCode();
            return HttpStatus.OK.equals(statusCode);
        } catch (FeignException.NotFound e) {
            String message = e.getMessage();
            return "{\"message\":\"Key not found\"}".equals(message);
        } catch (Exception e) {
            log.error("Apisix Register Error");
            throw new RuntimeException(e);
        }
    }


}
