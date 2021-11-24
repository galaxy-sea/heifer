package plus.wcj.heifer.boot.common.security.userdetails;


import org.apache.commons.lang3.StringUtils;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatusEnum;
import plus.wcj.heifer.boot.common.security.userdetails.dao.CustomUserDetailsDao;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountManageDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacRoleDto;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class HeiferUserDetailsServiceImpl {
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsDao customUserDetailsDao;


    public RbacAccountDto loadUserByUsername(String usernameOrEmailOrPhone, String password) throws UsernameNotFoundException {
        RbacAccountDto account = this.customUserDetailsDao.findAccountByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone).orElseThrow(() -> new ResultException(ResultStatusEnum.INCORRECT_USERNAME_OR_PASSWORD));
        if (account == null || !passwordEncoder.matches(password, account.getPassword())) {
            throw new ResultException(ResultStatusEnum.INCORRECT_USERNAME_OR_PASSWORD);
        }
        RbacAccountManageDto rbacAccountManageDto = this.customUserDetailsDao.findAccountManageBy(account.getId()).orElseThrow(() -> new ResultException(ResultStatusEnum.INTERNAL_SERVER_ERROR));
        account.setAccountManage(rbacAccountManageDto);
        return account;
    }

    @Cacheable(cacheNames = "allPermission", key = "#rbacTenantId+':'+#rbacAccountId")
    public List<String> getAllPermission(Long rbacTenantId, Long rbacAccountId) {
        List<RbacRoleDto> roleList = customUserDetailsDao.selectRoleBy(rbacAccountId, rbacTenantId);
        List<String> allDistinctPermission = customUserDetailsDao.selectDistinctPermissionBy(rbacAccountId, roleList);
        return Stream.concat(allDistinctPermission.stream(),
                             roleList.stream().map(rbacRoleDto -> "ROLE_" + rbacRoleDto.getName())
        ).collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "allPower", key = "#rbacTenantId+':'+#rbacAccountId")
    public String getAllPower(Long rbacTenantId, Long rbacAccountId) {
        List<Long> allPower = customUserDetailsDao.selectDistinctPowerBy(rbacAccountId);
        return StringUtils.join(allPower, ',');
    }


}
