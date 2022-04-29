package plus.wcj.heifer.metadata.tenant;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/3/13
 */
@Data
public class DataPowersDto {

    /**
     * 所在部门
     */
    private Long deptId;

    /**
     * 拥有的数据权限
     */
    private List<Long> dataPowers;
    /**
     * 是否拥有tenant的全部dept权限，超级管理员权限
     */
    private Boolean tenantDataPower;

    /**
     * 是否拥有当前tenant下的全部dept权限，租户管理员权限
     */
    private Boolean deptDataPower;


    public static DataPowersDto init(DataPowersDto dataPowersDto) {
        if (dataPowersDto == null) {
            dataPowersDto = new DataPowersDto();
        }
        if (dataPowersDto.getDataPowers() == null) {
            dataPowersDto.setDataPowers(new ArrayList<>());
        }
        if (dataPowersDto.getDeptDataPower() == null) {
            dataPowersDto.setDeptDataPower(Boolean.FALSE);
        }
        if (dataPowersDto.getTenantDataPower() == null) {
            dataPowersDto.setTenantDataPower(Boolean.FALSE);
        }
        return dataPowersDto;
    }


}
