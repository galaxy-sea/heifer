# 工程简介

# 数据库

> 命名规范    
1. 表名采用 ``业务``_``表名``_``(后缀)``
2. 表名禁止使用缩写如"order"缩写为"ord"
3. 数据库字段禁止使用缩写 如 ```orderId```缩写为```ordId```
4. 外键Id命名使用表名+Id 如```orderId```
5. 请使用唯一键做唯一约束
6. 索引顺序采用 重复率最低的在左边重复率高的在右边
7. 做好外键冗余


> 前缀后缀说明

1. ``xxx_``前缀表示业务   
2. ``_rel``后缀表示关系 ``一对一``、``多对多``的关系表


## rbac 功能权限 数据权限

| 表名                     | 注释               | 
| ------------------------ | ------------------ | 
| rbac_dept                | 部门               |  
| rbac_permission          | 功能权限           |
| rbac_role                | 角色表             |
| rbac_role_data_power     | 角色部门权限       |
| rbac_role_permission_rel | 角色功能权限关系表 |
| rbac_user                | 用户表             |
| rbac_user_data_power     | 用户部门权限       |
| rbac_user_permission_rel | 用户功能权限表     |
| rbac_user_role_rel       | 角色用户关系表     |

### tenant 多租户

| 表名                  | 注释       | 
| --------------------- | ---------- | 
| tenant_client         | 客户端     |
| tenant_org            | 租户       |
| tenant_org_client_rel | 租户客户端 |


# 框架搭建
## todo

1. mybatais plus
    1. mybatis Generator   ok
    2. 分布式id   ok
3. security
   1. RBAC  ok
   2. jwt   ok
4. validator


## ing
1. jackson
    1. long to string
    2. 
2. resolver//多租户还未开发

## end
1. result返回封装
2. swagger  ok


# 架构
## rbac模型