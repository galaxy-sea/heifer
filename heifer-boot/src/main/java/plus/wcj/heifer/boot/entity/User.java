package plus.wcj.heifer.boot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sec_user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    /** 密码 */
    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    /** 手机 */
    @ApiModelProperty(value = "手机")
    @TableField("phone")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    /** 生日 */
    @ApiModelProperty(value = "生日")
    @TableField("birthday")
    private Long birthday;

    /** 性别，男-1，女-2 */
    @ApiModelProperty(value = "性别，男-1，女-2")
    @TableField("sex")
    private Integer sex;

    /** 状态，启用-1，禁用-0 */
    @ApiModelProperty(value = "状态，启用-1，禁用-0")
    @TableField("status")
    private Integer status;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Long createTime;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Long updateTime;


}