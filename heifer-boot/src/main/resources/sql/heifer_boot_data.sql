# 租户基础数据
INSERT INTO rbac_tenant (id, name, create_by)
VALUES (1, 'heifer-admin', 1),
       (2, 'heifer-test', 1),
       (3, 'heifer-user', 1)
;

# rbac基础数据
INSERT INTO rbac_account (id, username, phone, email, password, nickname, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, create_by)
VALUES (1, 'admin', '15395777777', 'a@qq.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', 'admin', FALSE, FALSE, FALSE, FALSE, 1),
       (2, 'user', '12345678910', 'a@qq.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', 'admin', FALSE, FALSE, FALSE, FALSE, 1)
;


INSERT INTO rbac_dept (id, parent_id, rbac_tenant_id, name, create_by)
VALUES (1, 0, 1, 'heifer-admin1', 1),
       (2, 1, 1, 'heifer-admin1.2', 1),
       (100, 0, 1, 'heifer-test部门1', 1),
       (101, 100, 1, 'heifer-test部门1.2', 1)
;

INSERT INTO rbac_permission (id, parent_id, name, permission, type, sort, create_by)
VALUES (1, 0, 'heifer', 'heifer:admin', 'client', 0, 1),
       (2, 0, 'heifer', 'heifer:admin:test', 'button', 0, 1),
       (3, 0, 'heifer', 'heifer:admin:select', 'button', 0, 1),
       (4, 0, 'heifer', 'heifer:admin:update', 'button', 0, 1),
       (5, 0, 'heifer', 'heifer:admin:delete', 'button', 0, 1),
       (6, 0, 'heifer', 'heifer:admin:insert', 'button', 0, 1),
       (101, 0, 'heifer', 'heifer-test:admin', 'client', 0, 1),
       (102, 0, 'heifer', 'heifer-test:admin:test', 'button', 0, 1),
       (103, 0, 'heifer', 'heifer-test:admin:select', 'button', 0, 1),
       (104, 0, 'heifer', 'heifer-test:admin:update', 'button', 0, 1),
       (105, 0, 'heifer', 'heifer-test:admin:delete', 'button', 0, 1),
       (106, 0, 'heifer', 'heifer-test:admin:insert', 'button', 0, 1)
;

INSERT INTO rbac_role (id, rbac_tenant_id, name, create_by)
VALUES (1, 1, 'admin', 1),
       (2, 1, 'boss', 1),
       (3, 1, 'it', 1),
       (101, 2, 'admin', 1),
       (102, 2, 'boss', 1),
       (103, 2, 'it', 1)
;

# 账户基础数据

INSERT INTO rbac_account_manage (id, rbac_account_id, rbac_tenant_id, rbac_dept_id, create_by)
VALUES (1, 1, 1, 1, 1),
       (2, 2, 2, 100, 1),
       (3, 1, 1, 2, 1)
;

INSERT INTO rbac_account_role_rel (id, rbac_account_id, rbac_role_id, create_by)
VALUES (1, 1, 1, 1),
       (2, 1, 2, 1),
       (3, 1, 3, 1),
       (4, 2, 2, 1),
       (5, 2, 3, 1),
       (6, 2, 101, 1),
       (7, 2, 102, 1)
;

# rbac功能权限基础数据

INSERT INTO rbac_tenant_authority (id, rbac_tenant_id, rbac_permission_id, create_by)
VALUES (1, 1, 1, 1),
       (2, 2, 101, 1)
;

INSERT INTO rbac_role_authority (id, rbac_role_id, rbac_permission_id, create_by)
VALUES (1, 1, 2, 1),
       (2, 1, 3, 1),
       (3, 1, 3, 1),
       (4, 2, 4, 1),
       (5, 2, 5, 1)
;
INSERT INTO rbac_account_authority (id, rbac_account_id, rbac_permission_id, create_by)
VALUES (1, 1, 6, 1)
;

# 数据权限基础数据
INSERT INTO rbac_account_data_power (id, rbac_account_id, rbac_dept_id, create_by)
VALUES (1, 1, 1, 1)
;

INSERT INTO rbac_role_data_power (id, rbac_role_id, rbac_dept_id, create_by)
VALUES (1, 1, 2, 1);



