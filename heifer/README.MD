# heifer 开发进度记录

- heifer-boot(基础完成)
- heifer-common
    - heifer-common-dynamic-datasource (考虑是否使用MyBatis Plus还是MyBatis)
    - heifer-common-feign
    - heifer-common-nacos-discovery(完成)
    - heifer-common-redis(完成)
    - heifer-common-security(完成)
- heifer-gateway
- heifer-metadata(按需添加)
- heifer-plugin
    - heifer-plugin-oss(阿里云完成)
    - heifer-plugin-rbac(50%)
    - heifer-plugin-rbac-security(50%)
- heifer-tools(按需添加)


准备重构 oss模块 多模块国际化问题 升级jdk17


# 文档
## 设计理念
## 模块说明
1. heifer-boot
   1. 全局拦截器
   2. 异常国际化
   3. http Result自动包装
   4. mybatis plus 分页
   5. 通用service crud
2. heifer-common
   1. heifer-common-dynamic-datasource
   2. heifer-common-feign
      1. feign局部异常拦截器
      2. http Result 自动解析
   3. heifer-common-nacos-discovery
      1. nacos注册自动注入部分元数据
   4. heifer-common-redis
      1. spring cache 随机过期时间缓存规则
      2. Redis 锁
   5. heifer-common-security
      1. 需要配合 heifer-plugin-rbac-security或者自定义实现
      2. 注解和配置两种方式 忽略过滤URL
3. heifer-dependencies
   1. 全局依赖版本控制
4. heifer-gateway
   1. 目前没有考虑和Sentinel进行整合
5. heifer-metadata
   1. 全局注解
   2. 通用bean
   3. 全局常量
   4. heifer顶级异常和枚举顶级类
   5. 全局配置
   6. 多租户顶级接口
6. heifer-plugin
   1. heifer-plugin-oss
      1. Aliyun oss
         1. 服务端签名后直传
         2. 302重定向访问私有oss
      2. tencent oss 未实现
   2. heifer-plugin-rbac
      1. 通用rbac模型+acl模型实现
      2. abac模型 todo
      3. 通用数据权限 crud
      4. 通用功能权限 crud
   3. heifer-plugin-rbac-security
      1. jwt 鉴权模型
      2. heifer-plugin-rbac和heifer-common-security的实现
7. heifer-tools
   1. 密码加密器
   2. jwt工具类

## 使用特性

##  