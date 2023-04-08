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
CREATE TABLE `iam_access`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_account_manage_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
  `access_key_id` char(30) NOT NULL COMMENT 'access_key_id',
  `access_key_secret` char(30) NOT NULL COMMENT 'access_key_secret',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '开放平台信息';


DROP TABLE IF EXISTS iam_account;
CREATE TABLE `iam_account`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '账户名',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `is_enabled` bit NOT NULL DEFAULT 0 COMMENT '指示账户是启用还是禁用。 禁用的账户无法通过身份验证。\n返回值：\ntrue如果账户已启用， false否则',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '账户表';


DROP TABLE IF EXISTS iam_account_authority;
CREATE TABLE `iam_account_authority`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_account_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `iam_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '账户拥有功能权限表';


DROP TABLE IF EXISTS iam_account_data_power;
CREATE TABLE `iam_account_data_power`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_account_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
  `iam_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '账户数据权限';


DROP TABLE IF EXISTS iam_account_manage;
CREATE TABLE `iam_account_manage`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_account_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `iam_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
  `super_admin` bit(1) NOT NULL DEFAULT false COMMENT '是否超级管理员',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '账户与租户的绑定关系';


DROP TABLE IF EXISTS iam_account_role_rel;
CREATE TABLE `iam_account_role_rel`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
  `iam_account_id` bigint(0) UNSIGNED NOT NULL COMMENT '账户id',
  `iam_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '账户拥有角色关系表';


DROP TABLE IF EXISTS iam_dept;
CREATE TABLE `iam_dept`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
  `parent_id` bigint(0) UNSIGNED NOT NULL COMMENT '父节点名称',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '部门';


DROP TABLE IF EXISTS iam_permission;
CREATE TABLE `iam_permission`  (
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


DROP TABLE IF EXISTS iam_role;
CREATE TABLE `iam_role`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `iam_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '角色表';


DROP TABLE IF EXISTS iam_role_authority;
CREATE TABLE `iam_role_authority`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `iam_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '角色拥有功能权限关系表';


DROP TABLE IF EXISTS iam_role_data_power;
CREATE TABLE `iam_role_data_power`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
  `iam_role_id` bigint(0) UNSIGNED NOT NULL COMMENT '角色id',
  `iam_dept_id` bigint(0) UNSIGNED NOT NULL COMMENT '部门id',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '租户id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '角色数据权限';


DROP TABLE IF EXISTS iam_strategy;
CREATE TABLE `iam_strategy`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `strategy_enum` varchar(50) NOT NULL COMMENT '策略，需要硬编码',
  `strategy_name` varchar(50) NOT NULL COMMENT '策略名称',
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
) COMMENT = 'abac策略规则';


DROP TABLE IF EXISTS iam_tenant;
CREATE TABLE `iam_tenant`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '租户名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `update_time` timestamp(0) NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) UNSIGNED NULL,
  PRIMARY KEY (`id`)
) COMMENT = '租户';


DROP TABLE IF EXISTS iam_tenant_authority;
CREATE TABLE `iam_tenant_authority`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '组织id',
  `iam_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT = '租户拥有的权限';


DROP TABLE IF EXISTS iam_tenant_strategy;
CREATE TABLE `iam_tenant_strategy`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_strategy_id` bigint(0) UNSIGNED NOT NULL COMMENT 'abac权限id',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '组织id',
  `strategy_value` varchar(50) NOT NULL COMMENT '策略值，需要硬编码',
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
) COMMENT = '租户配置的策略规则';


DROP TABLE IF EXISTS iam_tenant_strategy_authority;
CREATE TABLE `iam_tenant_strategy_authority`  (
  `id` bigint(0) UNSIGNED NOT NULL COMMENT '主键ID',
  `iam_tenant_strategy_id` bigint(0) UNSIGNED NOT NULL COMMENT 'abac权限id',
  `iam_tenant_id` bigint(0) UNSIGNED NOT NULL COMMENT '组织id',
  `iam_permission_id` bigint(0) UNSIGNED NOT NULL COMMENT '功能权限id',
  `create_by` bigint(0) UNSIGNED NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`)
) COMMENT = '租户配置的策略规则与功能权限的绑定';

