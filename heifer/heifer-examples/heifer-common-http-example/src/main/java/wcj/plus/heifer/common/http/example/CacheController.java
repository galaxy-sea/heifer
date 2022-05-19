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

package wcj.plus.heifer.common.http.example;

import plus.wcj.heifer.common.http.cache.control.HttpCacheControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/14
 */
@RestController
@RequestMapping
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("cache/{id}")
    @HttpCacheControl(key = "#id", maxAge = 10)
    public ResponseEntity<Map<String, String>> getCache(@PathVariable String id) {
        return ResponseEntity.ok()
                             .body(new LinkedHashMap<String, String>() {{
                                 put("data", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                             }})
                ;
    }


    @PostMapping("cache/{id}")
    public ResponseEntity<Map<String, String>> modify(@PathVariable String id) {
        cacheService.modify(id);
        return ResponseEntity.ok()
                             .body(new LinkedHashMap<String, String>() {{
                                 put("data", "modify: " + id);
                             }})
                ;
    }


    @GetMapping("html")
    public ResponseEntity<String> html() {
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
        return ResponseEntity.ok()
                             .cacheControl(cacheControl)
                             .body("<!DOCTYPE html>\n" +
                                           "<html>\n" +
                                           "\n" +
                                           "<head>\n" +
                                           "    <meta charset=\"utf-8\">\n" +
                                           "    <script src=\"https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js\">\n" +
                                           "    </script>\n" +
                                           "    <script>\n" +
                                           "        $(document).ready(function () {\n" +
                                           "            $(\"button:nth-child(1)\").click(function () {\n" +
                                           "                $.get(\"/cache/1\", function (data, status) {\n" +
                                           "                    $(\"ol\").append(\"<li>cache1: \" + data.data + \"</li>\");\n" +
                                           "                });\n" +
                                           "            });\n" +
                                           "            $(\"button:nth-child(2)\").click(function () {\n" +
                                           "                $.get(\"/cache/2\", function (data, status) {\n" +
                                           "                    $(\"ol\").append(\"<li>cache2: \" + data.data + \"</li>\");\n" +
                                           "                });\n" +
                                           "            });\n" +
                                           "            $(\"button:nth-child(3)\").click(function () {\n" +
                                           "                $.post(\"/cache/1\", function (data, status) {\n" +
                                           "                    $(\"ol\").append(\"<li>cache1: \" + data.data + \"</li>\");\n" +
                                           "                });\n" +
                                           "            });\n" +
                                           "            $(\"button:nth-child(4)\").click(function () {\n" +
                                           "                $.post(\"/cache/2\", function (data, status) {\n" +
                                           "                    $(\"ol\").append(\"<li>cache2: \" + data.data + \"</li>\");\n" +
                                           "                });\n" +
                                           "            });\n" +
                                           "        });\n" +
                                           "    </script>\n" +
                                           "</head>\n" +
                                           "\n" +
                                           "<body>\n" +
                                           "\n" +
                                           "    <button>get cache1</button>\n" +
                                           "    <button>get cache2</button>\n" +
                                           "    <button>modify cache1</button>\n" +
                                           "    <button>modify cache2</button>\n" +
                                           "    <ol>\n" +
                                           "        <li>start test</li>\n" +
                                           "    </ol>\n" +
                                           "</body>\n" +
                                           "\n" +
                                           "</html>");
    }


}
