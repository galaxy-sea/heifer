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

DELETE
FROM user;

INSERT INTO user (id, name, age, email, grade)
VALUES (1, 'Jone', 18, 'test1@baomidou.com', 'PRIMARY'),
       (2, 'Jack', 20, 'test2@baomidou.com', 'PRIMARY'),
       (3, 'Tom', 28, 'test3@baomidou.com', 'PRIMARY'),
       (4, 'Sandy', 21, 'test4@baomidou.com', 'PRIMARY'),
       (5, 'Billie', 24, 'test5@baomidou.com', 'PRIMARY');