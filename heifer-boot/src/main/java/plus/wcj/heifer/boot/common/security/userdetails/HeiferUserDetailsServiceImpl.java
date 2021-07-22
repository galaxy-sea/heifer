package plus.wcj.heifer.boot.common.security.userdetails;


import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.common.security.userdetails.dao.CustomUserDetailsDao;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAdminDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacCustomerDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacPermissionDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacRoleDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacUserDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacUserManageDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 10:29
 */
@Service
@RequiredArgsConstructor
public class HeiferUserDetailsServiceImpl implements UserDetailsService {
    private final CustomUserDetailsDao customUserDetailsDao;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        RbacUserDto user = this.customUserDetailsDao.findUserByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone).orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone));
        RbacAdminDto admin = this.customUserDetailsDao.findAdmin(user.getId());
        RbacCustomerDto customer = this.customUserDetailsDao.findCustomer(user.getId());
        RbacUserManageDto userManage = this.customUserDetailsDao.findUserManage(user.getId());

        List<RbacRoleDto> roles = this.customUserDetailsDao.selectRoleByUserId(user.getId());
        List<Long> roleIds = roles.stream().map(RbacRoleDto::getId).collect(Collectors.toList());

        List<RbacPermissionDto> permissions = this.listPermission(roleIds, user.getId(), user.getRbacOrgId(), userManage);
        List<Long> dataPowers = this.listDataPower(roleIds, user.getId(), user.getRbacOrgId(), userManage);

        return UserPrincipal.create(user, roles, admin, customer, userManage, permissions, dataPowers);
    }

    private List<Long> listDataPower(List<Long> roleIds, Long userId, Long rbacOrgId, RbacUserManageDto userManage) {
        if (userManage != null && BooleanUtils.isTrue(userManage.getAllPower())) {
            return this.listDataPower(rbacOrgId);
        }
        return this.listDataPower(roleIds, userId);
    }

    private List<Long> listDataPower(Long rbacOrgId) {
        return this.customUserDetailsDao.selectDataPowerByOrgId(rbacOrgId);
    }

    private List<Long> listDataPower(List<Long> roleIds, Long userId) {
        return this.customUserDetailsDao.selectDataPower(roleIds, userId);
    }

    private List<RbacPermissionDto> listPermission(List<Long> roleIds, Long userId, Long rbacOrgId, RbacUserManageDto userManage) {
        if (userManage != null && BooleanUtils.isTrue(userManage.getAllAuthority())) {
            return this.listPermission(rbacOrgId);
        }
        return this.listPermission(roleIds, userId);
    }

    private List<RbacPermissionDto> listPermission(Long rbacOrgId) {
        return this.customUserDetailsDao.selectPermissionByrOrgId(rbacOrgId);
    }


    private List<RbacPermissionDto> listPermission(List<Long> roleIds, Long userId) {
        List<RbacPermissionDto> allPermissions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roleIds)) {
            allPermissions = this.customUserDetailsDao.selectPermissionByRoleIdList(roleIds);
        }

        List<RbacPermissionDto> userPermission = this.customUserDetailsDao.selectPermissionByUserId(userId);
        if (CollectionUtils.isNotEmpty(userPermission)) {
            allPermissions.addAll(userPermission);
        }

        return allPermissions;
    }
}
