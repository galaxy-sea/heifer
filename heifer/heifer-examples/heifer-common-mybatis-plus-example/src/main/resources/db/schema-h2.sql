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

DROP TABLE if EXISTS USER;

CREATE TABLE user (
    id bigint(20) NOT NULL COMMENT '主键ID',
    name varchar(30) NULL DEFAULT NULL COMMENT '姓名',
    age int(11) NULL DEFAULT NULL COMMENT '年龄',
    email varchar(50) NULL DEFAULT NULL COMMENT '邮箱',
    grade varchar(50) NULL DEFAULT NULL COMMENT '年级',
    PRIMARY KEY (id)
);