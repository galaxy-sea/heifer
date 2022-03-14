package plus.wcj.heifer.plugin.rbac.service.account.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.matedata.service.UserPrincipalService;
import plus.wcj.heifer.matedata.tenant.DataPowersDto;
import plus.wcj.heifer.plugin.rbac.dao.account.RbacAccountDao;
import plus.wcj.heifer.plugin.rbac.pojo.dto.AccountDto;
import plus.wcj.heifer.plugin.rbac.pojo.dto.RoleDto;
import plus.wcj.heifer.plugin.rbac.pojo.dto.TenantDto;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccount;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacAccountServiceImpl extends ServiceImpl<RbacAccountDao, RbacAccount, Long> implements RbacAccountService, UserPrincipalService {


    @Override
    public AccountDto getBy(String phone) {
        return super.getBaseMapper().selectAccountByPhone(phone);
    }

    @Override
    public List<String> getAllPermission(Long id, Long tenantId) {
        List<RoleDto> roleList = this.getAllRole(id, tenantId);
        List<String> permissionList = super.getBaseMapper().selectPermissionBy(id, tenantId, roleList);

        return Stream.concat(roleList.stream().map(role -> "ROLE_" + role.getName()),
                             permissionList.stream()
        ).collect(Collectors.toList());
    }

    @Override
    public DataPowersDto getAllPower(Long id, Long tenantId) {
        List<Long> dataPowers = super.getBaseMapper().getAllPower(id, tenantId);

        DataPowersDto dataPowersDto = new DataPowersDto();
        dataPowersDto.setDataPowers(dataPowers);
        // TODO: 2022/3/13 changjin wei(魏昌进) 保留字段，目前不做扩展
        dataPowersDto.setTenantDataPower(Boolean.FALSE);
        dataPowersDto.setDeptDataPower(Boolean.FALSE);
        return dataPowersDto;
    }

    @Override
    public List<RoleDto> getAllRole(Long id, Long tenantId) {
        return super.getBaseMapper().selectRoleBy(id, tenantId);
    }

    @Override
    public List<TenantDto> getAllTenant(String id) {
        return super.getBaseMapper().selectTenantBy(id);
    }

}
