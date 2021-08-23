# 组织架构
TRUNCATE heifer_boot.rbac_org;
INSERT INTO heifer_boot.rbac_org (id, name, create_time, create_by, update_time, update_by)
VALUES (10, 'heifer后台机构', '2021-08-23 17:25:08', 1, '2021-08-23 17:25:59', NULL),
       (20, 'heifer前台机构', '2021-08-23 17:25:59', 1, NULL, NULL);

# 部门
TRUNCATE heifer_boot.rbac_dept;
INSERT INTO heifer_boot.rbac_dept (id, parent_id, rbac_org_id, name, create_time, create_by, update_time, update_by)
VALUES (10, 0, 10, 'heifer技术部', '2021-08-23 17:28:55', 1, NULL, NULL),
       (11, 10, 10, 'heifer菜鸟组', '2021-08-23 17:28:55', 1, NULL, NULL),
       (12, 10, 10, 'heifer老油条组', '2021-08-23 17:30:29', 1, NULL, NULL),
       (20, 0, 10, 'heifer运营部', '2021-08-23 17:28:55', 1, NULL, NULL);

# 角色
TRUNCATE heifer_boot.rbac_role;
INSERT INTO heifer_boot.rbac_role (id, rbac_org_id, name, create_time, create_by, update_time, update_by)
VALUES (10, 10, 'heifer admin角色', '2021-08-23 17:33:32', 1, NULL, NULL),
       (20, 10, 'heifer后端角色', '2021-08-23 17:33:32', 1, NULL, NULL),
       (30, 10, 'heifer全栈角色', '2021-08-23 17:33:32', 1, NULL, NULL),
       (40, 10, 'heifer测试角色', '2021-08-23 17:33:33', 1, NULL, NULL),
       (50, 10, 'heifer产品角色', '2021-08-23 17:33:33', 1, NULL, NULL);

# 用户
TRUNCATE heifer_boot.rbac_user;
INSERT INTO heifer_boot.rbac_user (id, rbac_org_id, username, phone, email, password, nickname, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, create_time, create_by, update_time, update_by)
VALUES (10, 10, 'admin', '12345678910', 'a@qq.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '管理员', TRUE, TRUE, TRUE, TRUE, '2021-08-23 17:36:51', 1, NULL, NULL),
       (20, 10, 'it', '12345678910', 'a@qq.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '管理员', TRUE, TRUE, TRUE, TRUE, '2021-08-23 17:36:51', 1, NULL, NULL),
       (30, 10, 'test', '12345678910', 'a@qq.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '管理员', TRUE, TRUE, TRUE, TRUE, '2021-08-23 17:36:51', 1, NULL, NULL);

# 权限
TRUNCATE heifer_boot.rbac_permission;
INSERT INTO heifer_boot.rbac_permission (id, parent_id, name, permission, type, sort, create_time, create_by, update_time, update_by)
VALUES (10, 0, 'heifer管理端', 'heifer', 'CLIENT', 0, NULL, 1, '2021-08-23 17:41:02', NULL),
       (11, 10, '测试', 'heifer:test', 'BUTTON', 1, NULL, 1, '2021-08-23 17:41:02', NULL),
       (12, 10, '测试角色', 'heifer:test:role', 'BUTTON', 1, NULL, 1, '2021-08-23 17:41:02', NULL),
       (13, 10, '测试用户', 'heifer:test:user', 'BUTTON', 1, NULL, 1, '2021-08-23 17:41:02', NULL);

# 用户——管理员
TRUNCATE heifer_boot.rbac_admin;
INSERT INTO heifer_boot.rbac_admin (id, rbac_user_id, rbac_dept_id, create_time, create_by, update_time, update_by)
VALUES (10, 10, 10, '2021-08-23 17:44:32', 1, NULL, NULL),
       (20, 20, 10, '2021-08-23 17:44:32', 1, NULL, NULL);

# 用户-会员
TRUNCATE heifer_boot.rbac_customer;
INSERT INTO heifer_boot.rbac_customer (id, rbac_user_id, create_time, create_by, update_time, update_by)
VALUES (10, 10, '2021-08-23 17:45:37', 1, NULL, NULL);

# 机构-权限
TRUNCATE heifer_boot.rbac_org_authority;
INSERT INTO heifer_boot.rbac_org_authority (id, rbac_org_id, rbac_permission_id, create_time, create_by)
VALUES (10, 10, 10, '2021-08-23 17:47:50', 1),
       (11, 10, 11, '2021-08-23 17:47:50', 1),
       (12, 10, 12, '2021-08-23 17:47:50', 1),
       (13, 10, 13, '2021-08-23 17:47:50', 1);

# 角色-功能权限
TRUNCATE heifer_boot.rbac_role_authority;
INSERT INTO heifer_boot.rbac_role_authority (id, rbac_role_id, rbac_permission_id, create_time, create_by)
VALUES (10, 10, 10, '2021-08-23 17:49:55', 1),
       (11, 10, 11, '2021-08-23 17:49:55', 1),
       (12, 10, 12, '2021-08-23 17:49:55', 1),
       (13, 10, 13, '2021-08-23 17:49:55', 1);

# 角色-数据权限
TRUNCATE heifer_boot.rbac_role_data_power;
INSERT INTO heifer_boot.rbac_role_data_power (id, rbac_role_id, rbac_dept_id, create_time, create_by)
VALUES (10, 10, 10, '2021-08-23 17:51:22', 1),
       (11, 10, 11, '2021-08-23 17:51:22', 1),
       (12, 10, 12, '2021-08-23 17:51:22', 1),
       (13, 10, 20, '2021-08-23 17:51:22', 1);

### 用户-功能权限
TRUNCATE heifer_boot.rbac_user_authority;
INSERT INTO heifer_boot.rbac_user_authority (id, rbac_user_id, rbac_permission_id, create_time, create_by)
VALUES (10, 10, 10, '2021-08-23 17:54:20', 1),
       (11, 10, 11, '2021-08-23 17:54:20', 1),
       (12, 10, 12, '2021-08-23 17:54:20', 1),
       (13, 10, 13, '2021-08-23 17:54:20', 1);
# 用户-数据权限
TRUNCATE heifer_boot.rbac_user_data_power;
INSERT INTO heifer_boot.rbac_user_data_power (id, rbac_user_id, rbac_dept_id, create_time, create_by)
VALUES (10, 10, 10, '2021-08-23 17:56:08', 1),
       (11, 10, 11, '2021-08-23 17:56:08', 1),
       (12, 10, 12, '2021-08-23 17:56:08', 1),
       (13, 10, 20, '2021-08-23 17:56:08', 1);

# 用户-all 数据权限和功能权限
TRUNCATE heifer_boot.rbac_user_manage;
INSERT INTO heifer_boot.rbac_user_manage (id, rbac_user_id, is_all_power, is_all_authority, create_time, create_by, update_time, update_by)
VALUES (10, 10, TRUE, TRUE, '2021-08-23 17:57:11', 1, NULL, NULL);

# 用户-拥有的角色
TRUNCATE heifer_boot.rbac_user_role_rel;
INSERT INTO heifer_boot.rbac_user_role_rel (id, rbac_user_id, rbac_role_id, create_time, create_by)
VALUES (10, 10, 10, '2021-08-23 17:58:15', 1),
       (11, 10, 20, '2021-08-23 17:58:15', 1),
       (12, 10, 30, '2021-08-23 17:58:15', 1),
       (13, 10, 40, '2021-08-23 17:58:15', 1),
       (14, 10, 50, '2021-08-23 17:58:15', 1);



###
###
###
###
ALTER TABLE rbac_admin
    ADD CONSTRAINT a15 FOREIGN KEY (rbac_user_id) REFERENCES rbac_user (id);
ALTER TABLE rbac_admin
    ADD CONSTRAINT a16 FOREIGN KEY (rbac_dept_id) REFERENCES rbac_dept (id);
ALTER TABLE rbac_customer
    ADD CONSTRAINT a14 FOREIGN KEY (rbac_user_id) REFERENCES rbac_user (id);
ALTER TABLE rbac_dept
    ADD CONSTRAINT a9 FOREIGN KEY (rbac_org_id) REFERENCES rbac_org (id);
ALTER TABLE rbac_org_authority
    ADD CONSTRAINT a18 FOREIGN KEY (rbac_org_id) REFERENCES rbac_org (id);
ALTER TABLE rbac_org_authority
    ADD CONSTRAINT a19 FOREIGN KEY (rbac_permission_id) REFERENCES rbac_permission (id);
ALTER TABLE rbac_role
    ADD CONSTRAINT a2 FOREIGN KEY (rbac_org_id) REFERENCES rbac_org (id);
ALTER TABLE rbac_role_authority
    ADD CONSTRAINT a5 FOREIGN KEY (rbac_role_id) REFERENCES rbac_role (id);
ALTER TABLE rbac_role_authority
    ADD CONSTRAINT a6 FOREIGN KEY (rbac_permission_id) REFERENCES rbac_permission (id);
ALTER TABLE rbac_role_data_power
    ADD CONSTRAINT a12 FOREIGN KEY (rbac_role_id) REFERENCES rbac_role (id);
ALTER TABLE rbac_role_data_power
    ADD CONSTRAINT a13 FOREIGN KEY (rbac_dept_id) REFERENCES rbac_dept (id);
ALTER TABLE rbac_user
    ADD CONSTRAINT a1 FOREIGN KEY (rbac_org_id) REFERENCES rbac_org (id);
ALTER TABLE rbac_user_authority
    ADD CONSTRAINT a7 FOREIGN KEY (rbac_user_id) REFERENCES rbac_user (id);
ALTER TABLE rbac_user_authority
    ADD CONSTRAINT a8 FOREIGN KEY (rbac_permission_id) REFERENCES rbac_permission (id);
ALTER TABLE rbac_user_data_power
    ADD CONSTRAINT a10 FOREIGN KEY (rbac_dept_id) REFERENCES rbac_dept (id);
ALTER TABLE rbac_user_data_power
    ADD CONSTRAINT a11 FOREIGN KEY (rbac_user_id) REFERENCES rbac_user (id);
ALTER TABLE rbac_user_manage
    ADD CONSTRAINT a17 FOREIGN KEY (rbac_user_id) REFERENCES rbac_user (id);
ALTER TABLE rbac_user_role_rel
    ADD CONSTRAINT a3 FOREIGN KEY (rbac_role_id) REFERENCES rbac_role (id);
ALTER TABLE rbac_user_role_rel
    ADD CONSTRAINT a4 FOREIGN KEY (rbac_user_id) REFERENCES rbac_user (id);













