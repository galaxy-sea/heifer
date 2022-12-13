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

package plus.wcj.heifer.boot.examples;

import plus.wcj.libby.http.cache.control.annotation.HttpCacheControl;
import plus.wcj.libby.http.cache.control.annotation.HttpETagBind;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/28
 */
@RestController
@RequestMapping
public class CacheController {
    private Map<String, Long> data = new HashMap<>();

    @GetMapping("cache/{id}")
    @HttpCacheControl(key = "#id",value = "max-age=10, public")
    public Long get(@PathVariable String id) {
        return data.computeIfAbsent(id, s -> System.currentTimeMillis());
    }

    @PostMapping("cache/{id}")
    @HttpETagBind(key = "#id")
    public void post(@PathVariable String id) {
        data.put(id, System.currentTimeMillis());
    }

    @GetMapping("html")
    public ResponseEntity<String> html() {
        return ResponseEntity
                .ok()
                .body("<!DOCTYPE html><html><head><meta charset=\"utf-8\"><script src=\"https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js\"></script><script>$(document).ready(function(){$(\"button:nth-child(1)\").click(function(){$.get(\"./cache/1\",function(data,status){$(\"ol\").append(\"<li>cache1: data: \"+data+\"</li>\")})});$(\"button:nth-child(2)\").click(function(){$.get(\"./cache/2\",function(data,status){$(\"ol\").append(\"<li>cache2: data: \"+data+\"</li>\")})});$(\"button:nth-child(3)\").click(function(){$.post(\"./cache/1\",function(data,status){$(\"ol\").append(\"<li>cache1: modify cache1</li>\")})});$(\"button:nth-child(4)\").click(function(){$.post(\"./cache/2\",function(data,status){$(\"ol\").append(\"<li>cache2: modify cache2</li>\")})})});</script></head><body><button>get cache1</button><button>get cache2</button><button>modify cache1</button><button>modify cache2</button><ol><li>start test</li></ol></body></html>");
    }
}
