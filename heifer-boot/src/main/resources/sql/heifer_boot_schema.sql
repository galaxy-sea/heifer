DROP TABLE IF EXISTS rbac_admin;
CREATE TABLE rbac_admin (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    rbac_user_id bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
    rbac_dept_id bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '管理员信息';

DROP TABLE IF EXISTS rbac_customer;
CREATE TABLE rbac_customer (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    rbac_user_id bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '顾客信息';

DROP TABLE IF EXISTS rbac_dept;
CREATE TABLE rbac_dept (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    parent_id bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
    rbac_org_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    name varchar(50) NOT NULL COMMENT '部门名称',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '部门';

DROP TABLE IF EXISTS rbac_org;
CREATE TABLE rbac_org (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    name varchar(50) NOT NULL COMMENT '租户名称',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '租户';

DROP TABLE IF EXISTS rbac_org_authority;
CREATE TABLE rbac_org_authority (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    rbac_org_id bigint(0) UNSIGNED NOT NULL COMMENT '组织id',
    rbac_permission_id bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '租户拥有的权限';

DROP TABLE IF EXISTS rbac_permission;
CREATE TABLE rbac_permission (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    parent_id bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
    name varchar(50) NOT NULL COMMENT '权限名称',
    permission varchar(50) NOT NULL COMMENT '权限表达式，用:分割',
    type char(10) NOT NULL COMMENT 'client:客户端,menu:菜单,button:按钮',
    sort tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序，默认asc',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '功能权限';

DROP TABLE IF EXISTS rbac_role;
CREATE TABLE rbac_role (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    rbac_org_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    name varchar(50) NOT NULL COMMENT '名称',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '角色表';

DROP TABLE IF EXISTS rbac_role_authority;
CREATE TABLE rbac_role_authority (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    rbac_role_id bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    rbac_permission_id bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '角色拥有功能权限关系表';

DROP TABLE IF EXISTS rbac_role_data_power;
CREATE TABLE rbac_role_data_power (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    rbac_role_id bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    rbac_dept_id bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '角色数据权限';

DROP TABLE IF EXISTS rbac_user;
CREATE TABLE rbac_user (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    rbac_org_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    username varchar(50) NOT NULL COMMENT '用户名',
    phone varchar(15) NOT NULL COMMENT '手机号',
    email varchar(50) NOT NULL COMMENT '邮箱',
    password varchar(100) NOT NULL COMMENT '密码',
    nickname varchar(50) NOT NULL COMMENT '昵称',
    is_account_non_expired bit NOT NULL DEFAULT 0 COMMENT '指示用户的帐户是否已过期。 过期的帐户无法通过身份验证。\n返回值：\ntrue如果用户的帐户是否有效（即未过期）， false如果不再有效（即到期）',
    is_account_non_locked bit NOT NULL DEFAULT 0 COMMENT '指示用户是锁定还是解锁。 锁定的用户无法通过身份验证。\n返回值：\ntrue如果用户没有被锁定， false否则',
    is_credentials_non_expired bit NOT NULL DEFAULT 0 COMMENT '指示用户的凭据（密码）是否已过期。 过期的凭据会阻止身份验证。\n返回值：\ntrue如果用户的证书是有效的（即非到期）， false如果不再有效（即到期',
    is_enabled bit NOT NULL DEFAULT 0 COMMENT '指示用户是启用还是禁用。 禁用的用户无法通过身份验证。\n返回值：\ntrue如果用户已启用， false否则',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '用户表';

DROP TABLE IF EXISTS rbac_user_authority;
CREATE TABLE rbac_user_authority (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    rbac_user_id bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
    rbac_permission_id bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '用户拥有功能权限表';

DROP TABLE IF EXISTS rbac_user_data_power;
CREATE TABLE rbac_user_data_power (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    rbac_user_id bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
    rbac_dept_id bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '用户数据权限';

DROP TABLE IF EXISTS rbac_user_manage;
CREATE TABLE rbac_user_manage (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    rbac_user_id bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
    is_all_power bit NOT NULL DEFAULT 0 COMMENT '全部数据权限，T全部，F部分',
    is_all_authority bit NOT NULL DEFAULT 0 COMMENT '全部功能权限，T全部，F部分',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '用户是否拥有全部数据权限和功能权限';

DROP TABLE IF EXISTS rbac_user_role_rel;
CREATE TABLE rbac_user_role_rel (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    rbac_user_id bigint(0) UNSIGNED NOT NULL COMMENT '用户id',
    rbac_role_id bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '用户拥有角色关系表';


