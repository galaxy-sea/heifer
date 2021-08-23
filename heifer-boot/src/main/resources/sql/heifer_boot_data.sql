# 基础表信息
INSERT INTO rbac_permission (id, parent_id, name, permission, type, sort, create_time, create_by, update_time, update_by)
VALUES (1, 0, 'heifer管理端', 'heifer', 'CLIENT', 0, NULL, 1, NULL, NULL),
       (2, 0, '测试', 'page:test', 'BUTTON', 1, NULL, 1, NULL, NULL),
       (3, 0, '测试角色', 'page:test:role', 'BUTTON', 1, NULL, 1, NULL, NULL),
       (4, 0, '测试用户', 'page:test:user', 'BUTTON', 1, NULL, 1, NULL, NULL)
;

INSERT INTO rbac_org (id, name, create_time, create_by, update_time, update_by)
VALUES (1, 'heifer管理端', NULL, 1, NULL, NULL),
       (2, 'heifer客户端', NULL, 1, NULL, NULL);


INSERT INTO rbac_dept (id, parent_id, rbac_org_id, name, create_time, create_by, update_time, update_by)
VALUES (1, 0, 1, 'heifer管理端的部门1', NULL, 1, NULL, NULL),
       (2, 1, 1, 'heifer管理端的部门2', NULL, 1, NULL, NULL),
       (10, 0, 2, 'heifer客户端的部门1', NULL, 1, NULL, NULL),
       (11, 10, 2, 'heifer客户端的部门1', NULL, 1, NULL, NULL);


INSERT INTO rbac_user (id, rbac_org_id, username, phone, email, password, nickname, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, create_time, create_by, update_time, update_by)
VALUES (1, 1, 'admin', '12345678910', 'a@a.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '管理员', TRUE, TRUE, TRUE, TRUE, NULL, 1, NULL, NULL),
       (2, 1, 'user', '12345678910', 'a@a.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '普通用户', TRUE, TRUE, TRUE, TRUE, NULL, 1, NULL, NULL),
       (3, 1, 'notRole', '12345678910', 'a@a.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '普通用户', TRUE, TRUE, TRUE, TRUE, NULL, 1, NULL, NULL),
       (10, 2, 'admin', '12345678910', 'a@a.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '管理员', TRUE, TRUE, TRUE, TRUE, NULL, 1, NULL, NULL),
       (11, 2, 'user', '12345678910', 'a@a.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', '普通用户', TRUE, TRUE, TRUE, TRUE, NULL, 1, NULL, NULL);

INSERT INTO rbac_role (id, rbac_org_id, name, create_time, create_by, update_time, update_by)
VALUES (1, 1, 'heifer管理端admin角色', '2021-07-01 10:07:13', 1, NULL, NULL),
       (2, 1, 'heifer管理端user角色', '2021-07-01 10:07:13', 1, NULL, NULL),
       (10, 2, 'heifer客户端admin角色', '2021-07-01 10:07:13', 1, NULL, NULL),
       (11, 2, 'heifer客户端user角色', '2021-07-01 10:07:13', 1, NULL, NULL);

# 功能权限相关
INSERT INTO rbac_org_authority(id, rbac_org_id, rbac_permission_id, create_time, create_by)
VALUES (1, 1, 1, NULL, 1),
       (1, 1, 2, NULL, 1),
       (1, 1, 3, NULL, 1),
       (1, 1, 4, NULL, 1);

INSERT INTO rbac_role_authority(id, rbac_role_id, rbac_permission_id, create_time, create_by)
VALUES (1, 1, 1, NULL, 1),
       (1, 1, 2, NULL, 1),
       (1, 1, 3, NULL, 1),
       (1, 1, 4, NULL, 1);

INSERT INTO rbac_user_authority(id, rbac_user_id, rbac_permission_id, create_time, create_by)
VALUES (1, 3, 1, NULL, 1),
       (1, 3, 4, NULL, 1);


# 数据权限相关
INSERT INTO rbac_role_data_power(id, rbac_role_id, rbac_dept_id, create_time, create_by)
VALUES (1, 1, 1, NULL, 1);



INSERT INTO rbac_user_data_power(id, rbac_user_id, rbac_dept_id, create_time, create_by)
VALUES (1, 1, 2, NULL, 1);


# 用户相关

INSERT INTO rbac_admin(id, rbac_user_id, rbac_dept_id, create_time, create_by, update_time, update_by)
VALUES (1, 1, 1, NULL, 1, NULL, NULL);

INSERT INTO rbac_customer(id, rbac_user_id, create_time, create_by, update_time, update_by)
VALUES (1, 1, NULL, 1, NULL, NULL),
       (1, 2, NULL, 1, NULL, NULL),
       (1, 3, NULL, 1, NULL, NULL);

INSERT INTO rbac_user_role_rel(id, rbac_user_id, rbac_role_id, create_time, create_by)
VALUES (1, 1, 1, NULL, 1),
       (1, 2, 2, NULL, 1);

INSERT INTO rbac_user_manage(id, rbac_user_id, is_all_power, is_all_authority, create_time, create_by, update_time, update_by)
VALUES (1, 1, TRUE, TRUE, NULL, 1, NULL, NULL);

















