package plus.wcj.heifer.boot.common.security.userdetails.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.MapUtils;
import plus.wcj.heifer.boot.common.security.jwt.JwtClaim;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 管理员信息
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_admin")
@ApiModel(value = "RbacAdmin对象", description = "管理员信息")
@NoArgsConstructor
public class RbacAdminDto implements Serializable, JwtClaim<RbacAdminDto> {

    private static final long serialVersionUID = 1L;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    @TableField("rbac_dept_id")
    private Long rbacDeptId;


    @Override
    public Map<String, String> serialize() {
        Map<String, String> map = new HashMap<>(2);
        map.put("rbacDeptId", this.getRbacDeptId().toString());
        return map;
    }

    @Override
    public RbacAdminDto deserialization(Map<String, Object> map) {
        this.setRbacDeptId(MapUtils.getLong(map, "rbacDeptId"));
        return this;
    }
}
