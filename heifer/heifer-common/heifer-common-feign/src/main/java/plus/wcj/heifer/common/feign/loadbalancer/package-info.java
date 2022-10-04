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

/**
 * 这是一个很操蛋的模块设计啦，大部分代码都是复制出来的， 每次升级Spring Cloud版本都需要维护一下哦.
 *
 * {@link plus.wcj.heifer.common.feign.loadbalancer.LoadBalancerFeignRequestTransformer}
 * <a href="https://github.com/spring-cloud/spring-cloud-openfeign/pull/735">已经提交pr给了Spring Cloud OpenFeign 也不知道什么时候能够合并进去</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/8/30
 */
package plus.wcj.heifer.common.feign.loadbalancer;