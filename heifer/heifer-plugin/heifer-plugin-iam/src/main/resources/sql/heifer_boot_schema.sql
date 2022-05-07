/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

DROP TABLE IF EXISTS iam_access;
CREATE TABLE iam_access (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    iam_account_manage_id bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    access_key_id char(30) NOT NULL COMMENT 'access_key_id',
    access_key_secret char(30) NOT NULL COMMENT 'access_key_secret',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS iam_account;
CREATE TABLE iam_account (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    username varchar(50) NOT NULL COMMENT '账户名',
    phone varchar(15) NOT NULL COMMENT '手机号',
    email varchar(50) NOT NULL COMMENT '邮箱',
    password varchar(100) NOT NULL COMMENT '密码',
    nickname varchar(50) NOT NULL COMMENT '昵称',
    is_enabled bit NOT NULL DEFAULT 0 COMMENT '指示账户是启用还是禁用。 禁用的账户无法通过身份验证。\n返回值：\ntrue如果账户已启用， false否则',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP (0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '账户表';


DROP TABLE IF EXISTS iam_account_authority;
CREATE TABLE iam_account_authority (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    iam_account_id bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    iam_permission_id bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '账户拥有功能权限表';


DROP TABLE IF EXISTS iam_account_data_power;
CREATE TABLE iam_account_data_power (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    iam_account_id bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    iam_dept_id bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '账户数据权限';


DROP TABLE IF EXISTS iam_account_manage;
CREATE TABLE iam_account_manage (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    iam_account_id bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    iam_dept_id bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '账户与租户的绑定关系';


DROP TABLE IF EXISTS iam_account_role_rel;
CREATE TABLE iam_account_role_rel (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    iam_account_id bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
    iam_role_id bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '账户拥有角色关系表';


DROP TABLE IF EXISTS iam_dept;
CREATE TABLE iam_dept (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    parent_id bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    name varchar(50) NOT NULL COMMENT '部门名称',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP (0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '部门';


DROP TABLE IF EXISTS iam_permission;
CREATE TABLE iam_permission (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    parent_id bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
    name varchar(50) NOT NULL COMMENT '权限名称',
    permission varchar(50) NOT NULL COMMENT '权限表达式，用:分割',
    type char(10) NOT NULL COMMENT 'client:客户端,menu:菜单,button:按钮',
    sort tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序，默认asc',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP (0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '功能权限';


DROP TABLE IF EXISTS iam_role;
CREATE TABLE iam_role (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    name varchar(50) NOT NULL COMMENT '名称',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP (0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '角色表';


DROP TABLE IF EXISTS iam_role_authority;
CREATE TABLE iam_role_authority (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    iam_role_id bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    iam_permission_id bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '角色拥有功能权限关系表';


DROP TABLE IF EXISTS iam_role_data_power;
CREATE TABLE iam_role_data_power (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    iam_role_id bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
    iam_dept_id bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '角色数据权限';


DROP TABLE IF EXISTS iam_tenant;
CREATE TABLE iam_tenant (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
    name varchar(50) NOT NULL COMMENT '租户名称',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    update_time timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP (0),
    update_by bigint(0) UNSIGNED NULL,
    PRIMARY KEY (id)
) COMMENT = '租户';


DROP TABLE IF EXISTS iam_tenant_authority;
CREATE TABLE iam_tenant_authority (
    id bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
    iam_tenant_id bigint(0) UNSIGNED NOT NULL COMMENT '组织id',
    iam_permission_id bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
    create_time timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0),
    create_by bigint(0) UNSIGNED NOT NULL,
    PRIMARY KEY (id)
) COMMENT = '租户拥有的权限';

ALTER TABLE iam_access
    ADD CONSTRAINT a27 FOREIGN KEY (iam_account_manage_id) REFERENCES iam_account_manage (id);
ALTER TABLE iam_account_authority
    ADD CONSTRAINT a7 FOREIGN KEY (iam_account_id) REFERENCES iam_account (id);
ALTER TABLE iam_account_authority
    ADD CONSTRAINT a8 FOREIGN KEY (iam_permission_id) REFERENCES iam_permission (id);
ALTER TABLE iam_account_authority
    ADD CONSTRAINT a25 FOREIGN KEY (iam_tenant_id) REFERENCES iam_tenant (id);
ALTER TABLE iam_account_data_power
    ADD CONSTRAINT a10 FOREIGN KEY (iam_dept_id) REFERENCES iam_dept (id);
ALTER TABLE iam_account_data_power
    ADD CONSTRAINT a11 FOREIGN KEY (iam_account_id) REFERENCES iam_account (id);
ALTER TABLE iam_account_data_power
    ADD CONSTRAINT a24 FOREIGN KEY (iam_tenant_id) REFERENCES iam_tenant (id);
ALTER TABLE iam_account_manage
    ADD CONSTRAINT a20 FOREIGN KEY (iam_account_id) REFERENCES iam_account (id);
ALTER TABLE iam_account_manage
    ADD CONSTRAINT a21 FOREIGN KEY (iam_tenant_id) REFERENCES iam_tenant (id);
ALTER TABLE iam_account_manage
    ADD CONSTRAINT a22 FOREIGN KEY (iam_dept_id) REFERENCES iam_dept (id);
ALTER TABLE iam_account_role_rel
    ADD CONSTRAINT a3 FOREIGN KEY (iam_role_id) REFERENCES iam_role (id);
ALTER TABLE iam_account_role_rel
    ADD CONSTRAINT a4 FOREIGN KEY (iam_account_id) REFERENCES iam_account (id);
ALTER TABLE iam_account_role_rel
    ADD CONSTRAINT a26 FOREIGN KEY (iam_tenant_id) REFERENCES iam_tenant (id);
ALTER TABLE iam_dept
    ADD CONSTRAINT a9 FOREIGN KEY (iam_tenant_id) REFERENCES iam_tenant (id);
ALTER TABLE iam_role
    ADD CONSTRAINT a2 FOREIGN KEY (iam_tenant_id) REFERENCES iam_tenant (id);
ALTER TABLE iam_role_authority
    ADD CONSTRAINT a5 FOREIGN KEY (iam_role_id) REFERENCES iam_role (id);
ALTER TABLE iam_role_authority
    ADD CONSTRAINT a6 FOREIGN KEY (iam_permission_id) REFERENCES iam_permission (id);
ALTER TABLE iam_role_data_power
    ADD CONSTRAINT a12 FOREIGN KEY (iam_role_id) REFERENCES iam_role (id);
ALTER TABLE iam_role_data_power
    ADD CONSTRAINT a13 FOREIGN KEY (iam_dept_id) REFERENCES iam_dept (id);
ALTER TABLE iam_role_data_power
    ADD CONSTRAINT a23 FOREIGN KEY (iam_tenant_id) REFERENCES iam_tenant (id);
ALTER TABLE iam_tenant_authority
    ADD CONSTRAINT a18 FOREIGN KEY (iam_tenant_id) REFERENCES iam_tenant (id);
ALTER TABLE iam_tenant_authority
    ADD CONSTRAINT a19 FOREIGN KEY (iam_permission_id) REFERENCES iam_permission (id);

