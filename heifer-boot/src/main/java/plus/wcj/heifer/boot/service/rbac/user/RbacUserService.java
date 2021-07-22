package plus.wcj.heifer.boot.service.rbac.user;

import plus.wcj.heifer.boot.common.security.dto.LoginRequest;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;
import plus.wcj.heifer.boot.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
public interface RbacUserService extends IService<RbacUser> {

    /**
     * 注册接口
     *
     * @param loginRequest 用户注册信息
     */
    void signUp(LoginRequest loginRequest);
}
