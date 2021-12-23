package plus.wcj.heifer.plugin.rbac.service.impl;

import plus.wcj.heifer.matedata.exception.ResultException;
import plus.wcj.heifer.matedata.exception.ResultStatusEnum;
import plus.wcj.heifer.plugin.rbac.pojo.dto.AccountDto;
import plus.wcj.heifer.plugin.rbac.service.AuthService;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountService;
import plus.wcj.heifer.tools.crypto.password.PasswordConfig;
import plus.wcj.heifer.tools.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/23
 */
@Service
@Import(PasswordConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final RbacAccountService rbacAccountService;

    @Override
    public AccountDto login(String phone, String password) {
        AccountDto account = rbacAccountService.getBy(phone);
        if (account == null || !passwordEncoder.matches(password, account.getPassword())) {
            throw new ResultException(ResultStatusEnum.INCORRECT_USERNAME_OR_PASSWORD);
        }
        String jwt = JwtUtil.createJwt(accountDto, true);
        return new JwtResponse(jwt);
    }
}
