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

package plus.wcj.heifer.plugin.iam.security.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 混沌加密， 瞎几把写的
 *
 * @author changjin wei(魏昌进)
 * @since 2022/9/17
 */
public final class ChaosUtils {

    /**
     * 编码
     */
    public static String encoded(String key) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hashpw = BCrypt.hashpw(timestamp, key);
        return timestamp + "." + hashpw;
    }

    /**
     * 解码
     */
    public static boolean decode(String data, String key) {
        long now = System.currentTimeMillis();
        String[] split = data.split("\\.");
        long timestamp = Long.parseLong(split[0]);
        if (now <= timestamp + 30) {
            return false;
        }
        data = data.substring(split[0].length());
        return BCrypt.checkpw(data, key);
    }

    public static String gen() {
        return BCrypt.gensalt();
    }
}
