# heifer 开发进度记录

- heifer-boot(基础完成)
- heifer-common
    - heifer-common-apisix
    - heifer-common-discovery
    - heifer-common-dynamic-datasource
    - heifer-common-feign
    - heifer-common-mybatis-plus
    - heifer-common-nacos-discovery
    - heifer-common-polaris-discovery(未测试)
    - heifer-common-redis
    - heifer-common-redis-discovery(未开始)
    - heifer-common-security(完成)
- heifer-gateway
- heifer-metadata(按需添加)
- heifer-plugin
   - heifer-plugin-aliyun-oss
   - heifer-plugin-iam(50% 前端未对接)
   - heifer-plugin-iam-security(50%)
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
   6. 代码生成器未完成
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
      1. 需要配合 heifer-plugin-iam-security或者自定义实现
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
   1. heifer-plugin-aliyun-oss
      1. 服务端签名后直传
      2. 302重定向访问私有oss
      3. Resource支出
   2. heifer-plugin-iam
      1. 通用rbac模型+acl模型实现
      2. abac模型 todo
      3. 通用数据权限 crud
      4. 通用功能权限 crud
   3. heifer-plugin-iam-security
      1. jwt 鉴权模型
      2. heifer-plugin-iam和heifer-common-security的实现
7. heifer-tools
   1. 密码加密器
   2. jwt工具类

## 使用特性

## 未来工作安排
- 近期工作安排
- swagger
  - swagger自动添加到Spring Security拦截白名单  // 暂停 存在安全问题, 使用者自行处理
- jdk17 测试
- iam重构2.0 // 待定
  - abac适配permission的策略 // 待定
  - abac支持spel表达式 // 待定
- 对接前端工程  // 待定
- SaaS差异化 // 待定 
- TenantService 的功能可能会存在风险不可控, 暂停开发吧 // 暂停
- examples 完善 // 待定

- 长远工作安排
- 
- 适配Spring Boot3.0  // todo