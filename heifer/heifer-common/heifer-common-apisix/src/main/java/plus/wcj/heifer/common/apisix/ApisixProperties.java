/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package plus.wcj.heifer.common.apisix;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * Apisix的配置类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@ConfigurationProperties(prefix = "heifer.apisix")
public class ApisixProperties {

    /**
     * Apisix 地址
     */
    private String serverAddr = "http://192.168.0.1:9080/";
    /**
     * apisix path路径
     */
    private String serverPath = "apisix/admin/";

    /**
     * Apisix token
     */
    private String token = "edd1c9f034335f136f87ad84b625c8f1";

    private boolean cors = false;

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isCors() {
        return cors;
    }

    public void setCors(boolean cors) {
        this.cors = cors;
    }

    @Override
    public String toString() {
        return "ApisixProperties{" +
            "serverAddr='" + serverAddr + '\'' +
            ", serverPath='" + serverPath + '\'' +
            ", token='" + token + '\'' +
            ", cors=" + cors +
            '}';
    }
}
