CREATE TABLE `rbac_admin`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
  `rbac_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '管理员信息';

CREATE TABLE `rbac_customer`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '顾客信息';

CREATE TABLE `rbac_dept`  (
  `id` bigint(0) UNSIGNED NOT NULL,
  `parent_id` bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
  `rbac_org_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '部门';

CREATE TABLE `rbac_org`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '租户名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '租户';

CREATE TABLE `rbac_org_authority`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `rbac_org_id` bigint(0) UNSIGNED NOT NULL COMMENT '组织id',
  `rbac_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '租户拥有的权限';

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
  `rbac_org_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
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

CREATE TABLE `rbac_user`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `rbac_org_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `is_account_non_expired` bit NOT NULL DEFAULT 0 COMMENT '指示用户的帐户是否已过期。 过期的帐户无法通过身份验证。\n返回值：\ntrue如果用户的帐户是否有效（即未过期）， false如果不再有效（即到期）',
  `is_account_non_locked` bit NOT NULL DEFAULT 0 COMMENT '指示用户是锁定还是解锁。 锁定的用户无法通过身份验证。\n返回值：\ntrue如果用户没有被锁定， false否则',
  `is_credentials_non_expired` bit NOT NULL DEFAULT 0 COMMENT '指示用户的凭据（密码）是否已过期。 过期的凭据会阻止身份验证。\n返回值：\ntrue如果用户的证书是有效的（即非到期）， false如果不再有效（即到期',
  `is_enabled` bit NOT NULL DEFAULT 0 COMMENT '指示用户是启用还是禁用。 禁用的用户无法通过身份验证。\n返回值：\ntrue如果用户已启用， false否则',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '用户表';

CREATE TABLE `rbac_user_authority`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
  `rbac_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '用户拥有功能权限表';

CREATE TABLE `rbac_user_data_power`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
  `rbac_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '用户数据权限';

CREATE TABLE `rbac_user_manage`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
  `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
  `is_all_power` bit NOT NULL DEFAULT 0 COMMENT '全部数据权限，T全部，F部分',
  `is_all_authority` bit NOT NULL DEFAULT 0 COMMENT '全部功能权限，T全部，F部分',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '用户是否拥有全部数据权限和功能权限';

CREATE TABLE `rbac_user_role_rel`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
  `rbac_user_id` bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
  `rbac_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '用户拥有角色关系表';

ALTER TABLE `rbac_admin` ADD CONSTRAINT `a15` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);
ALTER TABLE `rbac_admin` ADD CONSTRAINT `a16` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_customer` ADD CONSTRAINT `a14` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);
ALTER TABLE `rbac_dept` ADD CONSTRAINT `a9` FOREIGN KEY (`rbac_org_id`) REFERENCES `rbac_org` (`id`);
ALTER TABLE `rbac_org_authority` ADD CONSTRAINT `a18` FOREIGN KEY (`rbac_org_id`) REFERENCES `rbac_org` (`id`);
ALTER TABLE `rbac_org_authority` ADD CONSTRAINT `a19` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`);
ALTER TABLE `rbac_role` ADD CONSTRAINT `a2` FOREIGN KEY (`rbac_org_id`) REFERENCES `rbac_org` (`id`);
ALTER TABLE `rbac_role_authority` ADD CONSTRAINT `a5` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_role_authority` ADD CONSTRAINT `a6` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`);
ALTER TABLE `rbac_role_data_power` ADD CONSTRAINT `a12` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_role_data_power` ADD CONSTRAINT `a13` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_user` ADD CONSTRAINT `a1` FOREIGN KEY (`rbac_org_id`) REFERENCES `rbac_org` (`id`);
ALTER TABLE `rbac_user_authority` ADD CONSTRAINT `a7` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);
ALTER TABLE `rbac_user_authority` ADD CONSTRAINT `a8` FOREIGN KEY (`rbac_permission_id`) REFERENCES `rbac_permission` (`id`);
ALTER TABLE `rbac_user_data_power` ADD CONSTRAINT `a10` FOREIGN KEY (`rbac_dept_id`) REFERENCES `rbac_dept` (`id`);
ALTER TABLE `rbac_user_data_power` ADD CONSTRAINT `a11` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);
ALTER TABLE `rbac_user_manage` ADD CONSTRAINT `a17` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);
ALTER TABLE `rbac_user_role_rel` ADD CONSTRAINT `a3` FOREIGN KEY (`rbac_role_id`) REFERENCES `rbac_role` (`id`);
ALTER TABLE `rbac_user_role_rel` ADD CONSTRAINT `a4` FOREIGN KEY (`rbac_user_id`) REFERENCES `rbac_user` (`id`);

