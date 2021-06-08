CREATE TABLE `rbac_dept` (
    `id` bigint(0) UNSIGNED NOT NULL,
    `tenant_org_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    `tenant_client_id` bigint(0) UNSIGNED NOT NULL COMMENT '客户端id',
    `name` varchar(50) NOT NULL COMMENT '部门名称',
    `create_by` bigint(20) UNSIGNED NOT NULL,
    `update_by` bigint(20) UNSIGNED NULL,
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) COMMENT = '部门';

CREATE TABLE `rbac_permission` (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `parent_id` bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
    `tenant_client_id` bigint(0) UNSIGNED NOT NULL COMMENT '客户端id',
    `name` varchar(50) NOT NULL COMMENT '权限名称',
    `permission` varchar(50) NOT NULL COMMENT '权限表达式，用:分割',
    `type` tinyint(0) UNSIGNED NOT NULL COMMENT '1：菜单，2：按钮',
    `sort` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序，默认asc',
    `create_by` bigint(20) UNSIGNED NOT NULL,
    `update_by` bigint(20) UNSIGNED NULL,
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) COMMENT = '功能权限';

CREATE TABLE `rbac_role` (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `tenant_org_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    `name` varchar(50) NOT NULL COMMENT '名称',
    `create_by` bigint(20) UNSIGNED NOT NULL,
    `update_by` bigint(20) UNSIGNED NULL,
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) COMMENT = '角色表';

CREATE TABLE `rbac_role_data_power` (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    `rbac_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    `rbac_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    PRIMARY KEY (`id`)
) COMMENT = '角色部门权限';

CREATE TABLE `rbac_role_permission_rel` (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `rbac_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    `rbac_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    PRIMARY KEY (`id`)
) COMMENT = '角色功能权限关系表';

CREATE TABLE `rbac_user` (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `tenant_org_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    `rbac_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `phone` varchar(15) NOT NULL COMMENT '手机号',
    `email` varchar(50) NOT NULL COMMENT '邮箱',
    `password` varchar(100) NOT NULL COMMENT '密码',
    `nickname` varchar(50) NOT NULL COMMENT '昵称',
    `is_account_non_expired` bit NOT NULL DEFAULT 0 COMMENT '指示用户的帐户是否已过期。 过期的帐户无法通过身份验证。\n返回值：\ntrue如果用户的帐户是否有效（即未过期）， false如果不再有效（即到期）',
    `is_account_non_locked` bit NOT NULL DEFAULT 0 COMMENT '指示用户是锁定还是解锁。 锁定的用户无法通过身份验证。\n返回值：\ntrue如果用户没有被锁定， false否则',
    `is_credentials_non_expired` bit NOT NULL DEFAULT 0 COMMENT '指示用户的凭据（密码）是否已过期。 过期的凭据会阻止身份验证。\n返回值：\ntrue如果用户的证书是有效的（即非到期）， false如果不再有效（即到期',
    `is_enabled` bit NOT NULL DEFAULT 0 COMMENT '指示用户是启用还是禁用。 禁用的用户无法通过身份验证。\n返回值：\ntrue如果用户已启用， false否则',
    `create_by` bigint(20) UNSIGNED NOT NULL,
    `update_by` bigint(20) UNSIGNED NULL,
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) COMMENT = '用户表';

CREATE TABLE `rbac_user_data_power` (
    `id` bigint(0) UNSIGNED NOT NULL,
    `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
    `rbac_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    PRIMARY KEY (`id`)
) COMMENT = '用户部门权限';

CREATE TABLE `rbac_user_permission_rel` (
    `id` bigint(0) UNSIGNED NOT NULL,
    `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    `rbac_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    PRIMARY KEY (`id`)
) COMMENT = '用户功能权限表';

CREATE TABLE `rbac_user_role_rel` (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
    `rbac_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) COMMENT = '角色用户关系表';

CREATE TABLE `tenant_client` (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    `name` varchar(50) NOT NULL COMMENT '客户端名称',
    `create_by` bigint(20) UNSIGNED NOT NULL,
    `update_by` bigint(20) UNSIGNED NULL,
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) COMMENT = '客户端\n';

CREATE TABLE `tenant_org` (
    `id` bigint(0) UNSIGNED NOT NULL,
    `name` varchar(50) NOT NULL COMMENT '租户名称',
    `create_by` bigint(20) UNSIGNED NOT NULL,
    `update_by` bigint(20) UNSIGNED NULL,
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) COMMENT = '租户';

CREATE TABLE `tenant_org_client_rel` (
    `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    `tenant_org_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    `tenant_client_id` bigint(0) UNSIGNED NOT NULL COMMENT '客户端id',
    PRIMARY KEY (`id`)
) COMMENT = '租户客户端';

ALTER TABLE `rbac_dept`
    ADD CONSTRAINT `a15` FOREIGN KEY (`tenant_org_id`) REFERENCES `tenant_org` (`id`);
ALTER TABLE `rbac_permission`
    ADD CONSTRAINT `a7` FOREIGN KEY (`parent_id`) REFERENCES `rbac_permission` (`id`);
ALTER TABLE `rbac_permission`
    ADD CONSTRAINT `a6` FOREIGN KEY (`tenant_client_id`) REFERENCES `tenant_client` (`id`);
ALTER TABLE `rbac_role`
    ADD CONSTRAINT `a2` FOREIGN KEY (`tenant_org_id`) REFERENCES `tenant_org` (`id`);
ALTER TABLE `rbac_role_data_power`
    ADD CONSTRAINT `a19` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_role_data_power`
    ADD CONSTRAINT `a20` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_role_permission_rel`
    ADD CONSTRAINT `a9` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_role_permission_rel`
    ADD CONSTRAINT `a10` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`);
ALTER TABLE `rbac_user`
    ADD CONSTRAINT `a1` FOREIGN KEY (`tenant_org_id`) REFERENCES `tenant_org` (`id`);
ALTER TABLE `rbac_user`
    ADD CONSTRAINT `a16` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_user_data_power`
    ADD CONSTRAINT `a17` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_user_data_power`
    ADD CONSTRAINT `a18` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);
ALTER TABLE `rbac_user_permission_rel`
    ADD CONSTRAINT `a11` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);
ALTER TABLE `rbac_user_permission_rel`
    ADD CONSTRAINT `a12` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`);
ALTER TABLE `rbac_user_role_rel`
    ADD CONSTRAINT `a3` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_user_role_rel`
    ADD CONSTRAINT `a4` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);
ALTER TABLE `tenant_org_client_rel`
    ADD CONSTRAINT `a13` FOREIGN KEY (`tenant_org_id`) REFERENCES `tenant_org` (`id`);
ALTER TABLE `tenant_org_client_rel`
    ADD CONSTRAINT `a14` FOREIGN KEY (`tenant_client_id`) REFERENCES `tenant_client` (`id`);
