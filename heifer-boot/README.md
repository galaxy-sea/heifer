
<!-- TOC -->

- [1. 工程简介](#1-工程简介)
- [2. 数据库](#2-数据库)
    - [2.1. 命名规范](#21-命名规范)
    - [2.2. 说明](#22-说明)
    - [2.3. rbac 多租户 功能权限 数据权限](#23-rbac-多租户-功能权限-数据权限)
        - [2.3.1. rbac 基础表](#231-rbac-基础表)
        - [2.3.2. rbac扩展表](#232-rbac扩展表)
- [3. 框架列表](#3-框架列表)

<!-- /TOC -->

# 1. 工程简介

# 2. 数据库


## 2.1. 命名规范
1. 表名采用 ``业务``_``表名``_``(后缀)``
2. 表名禁止使用缩写如"order"缩写为"ord"
3. 数据库字段禁止使用缩写 如 ```orderId```缩写为```ordId```
4. 外键Id命名使用表名+Id 如```orderId```
5. 请使用唯一键做唯一约束
6. 索引顺序采用 重复率最低的在左边重复率高的在右边
7. 做好外键冗余


## 2.2. 说明

1. ``xxx_``前缀表示业务
2. ``_rel``后缀表示关系 ``一对一``、``多对多``的关系表


> 建表模型
```sql
CREATE TABLE `t_users1` (
    `id` bigint(20) unsigned NOT NULL COMMENT '主键id',
    `create_by` bigint(20) unsigned not null ,
    `update_by` bigint(20) unsigned null ,
    `create_time` timestamp default CURRENT_TIMESTAMP,
    `update_time` timestamp on update CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;
-- ) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
```

> 数据权限sql
```sql
SELECT user.*
FROM t_users1 user
WHERE user.rbac_org_id = ?
  AND user.rbac_dept_id IN (?)
```
> 数据权限MyBatis

```mybatisognl

List< Object > selectUser(@Param("tenant") Tenant tenant)


< sql id="Tenant" >
<choose >
< when test="tenant.allPower" >
${tableAlias}.rbac_org_id = #{tenant.orgId}
< /when >
< otherwise >
${tableAlias}.rbac_dept_id IN
< foreach item="item" index="index" collection="tenant.dataPowers" open="(" separator="," close=")" >
#{item}
< /foreach >
< /otherwise >
< /choose >
< /sql >

< select id="selectUser" >
select user.*
from t_users1 user
<include refid="plus.wcj.heifer.boot.extension.dao.SqlTemplate.Tenant" >
< property name="tableAlias" value="user" / >
< /include >
</select >
```



## 2.3. rbac 多租户 功能权限 数据权限

### 2.3.1. rbac 基础表

| 表名                | 注释               | 业务说明                                                            |
| ------------------- | ------------------ | ------------------------------------------------------------------- |
| rbac_permission     | 权限表             |
| rbac_role           | 角色表             |
| rbac_role_authority | 角色功能权限       | role数据权限需要给user继承                                          |
| rbac_user           | 用户表             | 仅记录简要的账号信息,详细信息由``rbac_admin`` ``rbac_customer``维护 |
| rbac_user_role_rel  | 用户角色关联表 M2M | 对对多关系表                                                        |

### 2.3.2. rbac扩展表
| 表名                 | 注释           | 业务说明                                                                        |
| -------------------- | -------------- | ------------------------------------------------------------------------------- |
| rbac_dept            | 部门           | 主要用作数据权限                                                                |
| rbac_org             | 组织           | 租户隔离表                                                                      |
| rbac_org_authority   | 组织功能权限   | 租户拥有的权限, ``rbac_role_authority`` ``rbac_user_authority``的数据均来自这里 |
| rbac_role_data_power | 角色数据权限   | ``user``的数据权限要继承``role``                                                |
| rbac_admin           | 管理员表       | user的扩展表, 2B用户使用                                                        |
| rbac_customer        | 顾客表         | user的扩展表, 2C用户使用                                                        |
| rbac_user_authority  | 用户功能权限表 | 继承``role``的权限,两者需要合并权限                                             |
| rbac_user_data_power | 用户数据权限表 | 继承``role``的权限,两者需要合并权限                                             |
| rbac_user_manage     | 用户权限管理   | 用户是否拥有``org``的全部``authority``和``power``                               |

# 3. 框架列表

1. spring
    1. [spring-boot](https://github.com/spring-projects/spring-boot)
        1. spring-boot-starter-web
        2. spring-boot-starter-validation
        3. spring-boot-configuration-processor
        4. spring-boot-starter-test
    2. [spring-security](https://github.com/spring-projects/spring-security)
    3. [spring-data-redis](https://github.com/spring-projects/spring-data-redis)
    4. [spring-integration](https://github.com/spring-projects/spring-integration)
    5. [spring-data-mongodb](https://github.com/spring-projects/spring-data-mongodb)
1. ORM
    1. [mybatis-plus](https://github.com/baomidou/mybatis-plus)
    2. [mybatis-3](https://github.com/mybatis/mybatis-3)
    3. [mysql-connector-java](https://github.com/mysql/mysql-connector-j)
2. encryption
    1. [jasypt-spring-boot](https://github.com/ulisesbocchio/jasypt-spring-boot)
    2. [Nimbus-JWT](https://github.com/Connect2id/Nimbus-JWT)
3. Apache commons
    1. [commons-collections](https://github.com/apache/commons-collections)
    2. [commons-lang3](https://github.com/apache/commons-lang)
    3. [commons-pool2](https://github.com/apache/commons-pool)
    4. [freemarker](https://github.com/apache/freemarker)
4. other commons
    1. [xk-time](https://github.com/xkzhangsan/xk-time)
    2. [lombok](https://github.com/projectlombok/lombok)
5. api doc
    1. [springfox](https://github.com/springfox/springfox)