INSERT INTO heifer_boot.rbac_account (id, username, phone, email, password, nickname, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, create_time, create_by, update_time, update_by)
VALUES (1, 'admin', '15395777777', 'a@qq.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', 'admin', FALSE, FALSE, FALSE, FALSE, '2021-11-19 17:02:14', 1, '2021-11-19 17:02:38', NULL),
       (2, 'user', '12345678910', 'a@qq.com', '$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq', 'admin', FALSE, FALSE, FALSE, FALSE, '2021-11-19 17:02:14', 1, NULL, NULL);

INSERT INTO heifer_boot.rbac_tenant (id, name, create_time, create_by, update_time, update_by)
VALUES (1, 'heifer-admin', '2021-11-19 17:00:38', 1, NULL, NULL),
       (2, 'heifer-test', '2021-11-19 17:00:38', 1, '2021-11-19 17:01:06', NULL);


INSERT INTO heifer_boot.rbac_dept (id, parent_id, rbac_tenant_id, name, create_time, create_by, update_time, update_by)
VALUES (1, 0, 1, 'heifer-admin1', '2021-11-19 17:08:31', 1, '2021-11-19 17:09:06', NULL),
       (2, 1, 1, 'heifer-admin1.2', '2021-11-19 17:08:31', 1, NULL, NULL),
       (100, 0, 1, 'heifer-test部门1', '2021-11-19 17:08:31', 1, '2021-11-19 17:09:06', NULL),
       (101, 100, 1, 'heifer-test部门1.2', '2021-11-19 17:08:31', 1, NULL, NULL);

INSERT INTO heifer_boot.rbac_account_manage (id, rbac_account_id, rbac_tenant_id, rbac_dept_id, create_time, create_by)
VALUES (1, 1, 1, 1, '2021-11-19 17:10:55', 1),
       (2, 2, 2, 100, '2021-11-19 17:17:20', 1),
       (3, 1, 1, 2, '2021-11-19 17:10:55', 1);

INSERT INTO heifer_boot.rbac_permission (id, parent_id, name, permission, type, sort, create_time, create_by, update_time, update_by)
VALUES (1, 0, 'heifer:admin', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (2, 0, 'heifer:admin:test', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (3, 0, 'heifer:admin:select', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (4, 0, 'heifer:admin:update', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (5, 0, 'heifer:admin:delete', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (6, 0, 'heifer:admin:insert', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (101, 0, 'heifer-test:admin', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (102, 0, 'heifer-test:admin:test', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (103, 0, 'heifer-test:admin:select', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (104, 0, 'heifer-test:admin:update', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (105, 0, 'heifer-test:admin:delete', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL),
       (106, 0, 'heifer-test:admin:insert', 'heifer', 'client', 0, '2021-11-19 17:18:40', 1, NULL, NULL);

INSERT INTO heifer_boot.rbac_role (id, rbac_tenant_id, name, create_time, create_by, update_time, update_by)
VALUES (1, 1, 'admin', '2021-11-19 17:21:21', 1, NULL, NULL),
       (2, 1, 'boss', '2021-11-19 17:21:21', 1, NULL, NULL),
       (3, 1, 'it', '2021-11-19 17:21:21', 1, NULL, NULL),
       (101, 2, 'admin', '2021-11-19 17:21:21', 1, NULL, NULL),
       (102, 2, 'boss', '2021-11-19 17:21:21', 1, NULL, NULL),
       (103, 2, 'it', '2021-11-19 17:21:21', 1, NULL, NULL);

