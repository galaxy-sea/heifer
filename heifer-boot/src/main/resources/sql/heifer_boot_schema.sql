CREATE TABLE `rbac_account`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `username` varchar(50) NOT NULL COMMENT '账户名',
    `phone` varchar(15) NOT NULL COMMENT '手机号',
    `email` varchar(50) NOT NULL COMMENT '邮箱',
    `password` varchar(100) NOT NULL COMMENT '密码',
    `nickname` varchar(50) NOT NULL COMMENT '昵称',
    `is_account_non_expired` bit NOT NULL DEFAULT 0 COMMENT '指示账户的帐户是否已过期。 过期的帐户无法通过身份验证。\n返回值：\ntrue如果账户的帐户是否有效（即未过期）， false如果不再有效（即到期）',
    `is_account_non_locked` bit NOT NULL DEFAULT 0 COMMENT '指示账户是锁定还是解锁。 锁定的账户无法通过身份验证。\n返回值：\ntrue如果账户没有被锁定， false否则',
    `is_credentials_non_expired` bit NOT NULL DEFAULT 0 COMMENT '指示账户的凭据（密码）是否已过期。 过期的凭据会阻止身份验证。\n返回值：\ntrue如果账户的证书是有效的（即非到期）， false如果不再有效（即到期',
    `is_enabled` bit NOT NULL DEFAULT 0 COMMENT '指示账户是启用还是禁用。 禁用的账户无法通过身份验证。\n返回值：\ntrue如果账户已启用， false否则',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    `update_by` bigint(0) UNSIGNED NULL,
    PRIMARY KEY (`id`)
) COMMENT = '账户表';

CREATE TABLE `rbac_account_authority`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `rbac_account_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    `rbac_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '账户拥有功能权限表';

CREATE TABLE `rbac_account_data_power`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `rbac_account_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    `rbac_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '账户数据权限';

CREATE TABLE `rbac_account_manage`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `rbac_account_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    `rbac_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    `rbac_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '账户与租户的绑定关系';

CREATE TABLE `rbac_account_role_rel`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    `rbac_account_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    `rbac_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '账户拥有角色关系表';

CREATE TABLE `rbac_dept`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    `parent_id` bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
    `rbac_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    `name` varchar(50) NOT NULL COMMENT '部门名称',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    `update_by` bigint(0) UNSIGNED NULL,
    PRIMARY KEY (`id`)
) COMMENT = '部门';

CREATE TABLE `rbac_permission`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `parent_id` bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
    `name` varchar(50) NOT NULL COMMENT '权限名称',
    `permission` varchar(50) NOT NULL COMMENT '权限表达式，用:分割',
    `type` char(10) NOT NULL COMMENT 'client:客户端,menu:菜单,button:按钮',
    `sort` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序，默认asc',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    `update_by` bigint(0) UNSIGNED NULL,
    PRIMARY KEY (`id`)
) COMMENT = '功能权限';

CREATE TABLE `rbac_role`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `rbac_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    `name` varchar(50) NOT NULL COMMENT '名称',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    `update_by` bigint(0) UNSIGNED NULL,
    PRIMARY KEY (`id`)
) COMMENT = '角色表';

CREATE TABLE `rbac_role_authority`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `rbac_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    `rbac_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '角色拥有功能权限关系表';

CREATE TABLE `rbac_role_data_power`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    `rbac_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    `rbac_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '角色数据权限';

CREATE TABLE `rbac_tenant`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    `name` varchar(50) NOT NULL COMMENT '租户名称',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    `update_by` bigint(0) UNSIGNED NULL,
    PRIMARY KEY (`id`)
) COMMENT = '租户';

CREATE TABLE `rbac_tenant_authority`  (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `rbac_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '组织id',
    `rbac_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    `create_by` bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '租户拥有的权限';

ALTER TABLE `rbac_account_authority` ADD CONSTRAINT `a7` FOREIGN KEY (`rbac_account_id`) REFERENCES `rbac_account` (`id`);
ALTER TABLE `rbac_account_authority` ADD CONSTRAINT `a8` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`);
ALTER TABLE `rbac_account_data_power` ADD CONSTRAINT `a10` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_account_data_power` ADD CONSTRAINT `a11` FOREIGN KEY (`rbac_account_id`) REFERENCES `rbac_account` (`id`);
ALTER TABLE `rbac_account_manage` ADD CONSTRAINT `a20` FOREIGN KEY (`rbac_account_id`) REFERENCES `rbac_account` (`id`);
ALTER TABLE `rbac_account_manage` ADD CONSTRAINT `a21` FOREIGN KEY (`rbac_tenant_id`) REFERENCES `rbac_tenant` (`id`);
ALTER TABLE `rbac_account_manage` ADD CONSTRAINT `a22` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_account_role_rel` ADD CONSTRAINT `a3` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_account_role_rel` ADD CONSTRAINT `a4` FOREIGN KEY (`rbac_account_id`) REFERENCES `rbac_account` (`id`);
ALTER TABLE `rbac_dept` ADD CONSTRAINT `a9` FOREIGN KEY (`rbac_tenant_id`) REFERENCES `rbac_tenant` (`id`);
ALTER TABLE `rbac_role` ADD CONSTRAINT `a2` FOREIGN KEY (`rbac_tenant_id`) REFERENCES `rbac_tenant` (`id`);
ALTER TABLE `rbac_role_authority` ADD CONSTRAINT `a5` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_role_authority` ADD CONSTRAINT `a6` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`);
ALTER TABLE `rbac_role_data_power` ADD CONSTRAINT `a12` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_role_data_power` ADD CONSTRAINT `a13` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_tenant_authority` ADD CONSTRAINT `a18` FOREIGN KEY (`rbac_tenant_id`) REFERENCES `rbac_tenant` (`id`);
ALTER TABLE `rbac_tenant_authority` ADD CONSTRAINT `a19` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`);



