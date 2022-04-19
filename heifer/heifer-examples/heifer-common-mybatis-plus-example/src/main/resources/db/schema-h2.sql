DROP TABLE if EXISTS USER;

CREATE TABLE user (
    id bigint(20) NOT NULL COMMENT '主键ID',
    name varchar(30) NULL DEFAULT NULL COMMENT '姓名',
    age int(11) NULL DEFAULT NULL COMMENT '年龄',
    email varchar(50) NULL DEFAULT NULL COMMENT '邮箱',
    grade varchar(50) NULL DEFAULT NULL COMMENT '年级',
    PRIMARY KEY (id)
);