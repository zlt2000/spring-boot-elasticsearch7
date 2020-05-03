#  Spring Boot 集成 Elasticsearch 7.x + XPACK
开源的 `Elasticsearch` 是一个分布式的 `RESTful` 风格的搜索和数据分析引擎，是目前全文搜索引擎的首选。

网上关于集成 `ElasticSearch` 的教程大部都是讲使用 `TransportClient` 的，但是该客户端本身并不支持 `XPACK` 安全认证需要引入其他依赖扩展，同时在 `ElasticSearch7` 的版本中已被废弃，并且会在8.x版本中将被移除，官方建议使用：`High Level REST Client`。

并且由于 `ElasticSearch7.x` 某些变动并不向下兼容旧版本，而最新版本的 `Spring Boot Starter` 所依赖的 `ElasticSearch` 客户端还是**6.x**的版本，所以集成的时候需要填不少的坑。

在本场 Chat 中，会包含以下内容：
* `Spring Boot` 集成 `High Level REST Client`
* `Spring Boot` 集成 `XPACK` 认证
* 通过解读源码，解决集成 `ElasticSearch 7.x` 时遇到的坑
* 自定义连接 `ElasticSearch` 的http连接池配置
* 使用 `Spring Data Elasticsearch` 管理索引
* 使用 `Spring Data Elasticsearch` 对索引数据的基本 CRUD 操作
* 使用 `Junit4` 编写所有方法的测试用例
* 以上内容的所有源码