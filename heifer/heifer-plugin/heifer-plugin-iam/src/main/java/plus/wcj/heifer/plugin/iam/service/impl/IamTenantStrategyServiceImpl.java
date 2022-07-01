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

package plus.wcj.heifer.plugin.iam.service.impl;


import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.iam.dao.IamTenantStrategyDao;
import plus.wcj.heifer.plugin.iam.entity.IamTenantStrategy;
import plus.wcj.heifer.plugin.iam.service.IamTenantStrategyService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 租户配置的策略规则 服务实现类
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@Service
public class IamTenantStrategyServiceImpl extends ServiceImpl<IamTenantStrategyDao, IamTenantStrategy, Long> implements IamTenantStrategyService {
    // 自动生成外键查询  请勿修改
    @Override
    public List<IamTenantStrategy> listByIamStrategyId(Long iamStrategyId){
        return super.getBaseMapper().selectByIamStrategyId(iamStrategyId);
   }

    @Override
    public List<IamTenantStrategy> listByIamTenantId(Long iamTenantId){
        return super.getBaseMapper().selectByIamTenantId(iamTenantId);
   }

    // 自动生成外键查询  请勿修改

}