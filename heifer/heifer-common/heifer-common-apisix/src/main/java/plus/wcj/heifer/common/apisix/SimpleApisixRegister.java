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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plus.wcj.heifer.common.apisix.admin.api.RouteClient;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;


/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/9
 */
public class SimpleApisixRegister implements ApisixRegister {

    private static final Logger logger = LoggerFactory.getLogger(SimpleApisixRegister.class);
    private final RouteClient routeClient;
    private final ObjectProvider<List<ApisixCustomizer>> ApisixCustomizers;

    private final ApisixProperties apisixProperties;

    public SimpleApisixRegister(RouteClient routeClient, ObjectProvider<List<ApisixCustomizer>> apisixCustomizers, ApisixProperties apisixProperties) {
        this.routeClient = routeClient;
        this.ApisixCustomizers = apisixCustomizers;
        this.apisixProperties = apisixProperties;
    }

    @EventListener
    public void register(ApplicationReadyEvent applicationReadyEvent) {
        Route route = new Route();
        List<ApisixCustomizer> apisixCustomizers = ApisixCustomizers.getIfAvailable(LinkedList::new);
        apisixCustomizers.stream().sorted(AnnotationAwareOrderComparator.INSTANCE)
                         .forEach(apisixCustomizer -> apisixCustomizer.customizer(route));
        if (!hasRoute(route.getId())) {
            routeClient.route(route.getId(), apisixProperties.getToken(), route);
            logger.info("apisix create route:{}", route.getId());
        }

    }


    private boolean hasRoute(String routeId) {
        try {
            ResponseEntity<String> response = this.routeClient.route(routeId, this.apisixProperties.getToken());
            HttpStatus statusCode = response.getStatusCode();
            return HttpStatus.OK.equals(statusCode);
        }  catch (Exception e) {
            return false;
        }
    }
}
