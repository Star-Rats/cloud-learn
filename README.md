# cloud-learn

![Java](https://img.shields.io/badge/Java-1.8-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.2.2-6DB33F?logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-Hoxton.SR1-6DB33F?logo=spring&logoColor=white)
![Spring Cloud Alibaba](https://img.shields.io/badge/Spring_Cloud_Alibaba-2.1.0-FF6A00?logo=alibabacloud&logoColor=white)

个人学习 Spring Cloud 过程中搭建的微服务工程,同一套父工程下并列演示了 **Spring Cloud Netflix** 与 **Spring Cloud Alibaba** 两套体系的常用组件:注册中心、负载均衡、声明式调用、服务熔断降级、网关、分布式配置中心、消息驱动等,各模块可独立启动、按需组合。

## 技术栈

| 组件 | 版本 |
| --- | --- |
| Java | 1.8 |
| Spring Boot | 2.2.2.RELEASE |
| Spring Cloud | Hoxton.SR1(Netflix 体系) |
| Spring Cloud Alibaba | 2.1.0.RELEASE(Nacos 体系) |
| 持久层 | MyBatis + Druid + MySQL |
| 消息中间件 | RabbitMQ(Spring Cloud Stream) |

## 模块总览

### 注册中心(三种实现横向对比)

| 模块 | 端口 | 说明 |
| --- | --- | --- |
| cloud-eureka-server7001 / 7002 | 7001 / 7002 | Eureka 注册中心,两台互相注册组成集群(7001 附带 Dockerfile) |
| cloud-provider-payment8004 | 8004 | 服务提供者,注册到 **Zookeeper** |
| cloud-providerconsul-payment8006 | 8006 | 服务提供者,注册到 **Consul** |
| cloudalibaba-provider-payment9001 / 9002 | 9001 / 9002 | 服务提供者,注册到 **Nacos**(两实例) |

### 服务提供与调用

| 模块 | 端口 | 说明 |
| --- | --- | --- |
| cloud-api-commons | — | 公共模块:统一返回体 `CommonResult`、实体 `Payment` |
| cloud-provider-payment8001 / 8002 | 8001 / 8002 | 支付服务提供者集群(MySQL + MyBatis) |
| cloud-consumer-order80 | 80 | 服务消费者,使用 **Ribbon** 负载均衡 + RestTemplate |
| cloud-consumer-feign-order80 | 80 | 服务消费者,改用 **OpenFeign** 声明式调用 |
| cloudalibaba-consumer-nacos-order83 | 83 | Nacos 体系消费者,Ribbon 负载均衡 |

### 服务容错(Hystrix)

| 模块 | 端口 | 说明 |
| --- | --- | --- |
| cloud-provider-hygtrix-payment8001 | 8001 | 服务端降级:`@HystrixCommand` 兜底方法 |
| cloud-consumer-feign-hystrix-order80 | 80 | 客户端降级:Feign + Hystrix 全局兜底 |
| cloud-consumer-hystrix-dashboard9001 | 9001 | **Hystrix Dashboard** 熔断监控面板 |

### 网关与配置中心

| 模块 | 端口 | 说明 |
| --- | --- | --- |
| cloud-gateway-gateway9527 | 9527 | **Spring Cloud Gateway**,路由断言 + 自定义全局过滤器 |
| cloud-config-center-3344 | 3344 | **Config** 分布式配置中心(后端接 Git 仓库) |
| cloud-config-client-3355 / 3366 | 3355 / 3366 | 配置客户端,演示 Config + 手动刷新(POM 漂移对比) |

### 消息驱动(Stream + RabbitMQ)

| 模块 | 端口 | 说明 |
| --- | --- | --- |
| cloud-stream-rabbitmq-provider8801 | 8801 | 消息生产者 |
| cloud-stream-rabbitmq-consumer8802 / 8803 | 8802 / 8803 | 消息消费者,演示分组消费与消息持久化 |

## 快速开始

```bash
# 环境要求:JDK 8、Maven 3.6+、MySQL 5.7、RabbitMQ,注册中心按需选装 Eureka / Zookeeper / Consul / Nacos

# 1. 构建整个父工程
mvn clean install -DskipTests

# 2. 先起注册中心(Eureka 集群),再起服务提供者,最后起消费者
java -jar cloud-eureka-server7001/target/*.jar
java -jar cloud-eureka-server7002/target/*.jar
java -jar cloud-provider-payment8001/target/*.jar
java -jar cloud-consumer-feign-order80/target/*.jar

# 3. 验证:浏览器访问 Eureka 控制台查看服务注册情况
#    http://localhost:7001
```

说明:

- 配置中心(3344)的 Git 仓库地址在 `cloud-config-center-3344/src/main/resources/application.yml` 中配置,使用前需改成自己的仓库
- Nacos 体系模块(9001 / 9002 / 83)启动前需本地运行 Nacos Server
- eureka-server7001 模块附带 Dockerfile,可自行构建镜像容器化运行
- 各模块数据库连接、中间件地址均在各自 `application.yml` / `bootstrap.yml` 中,按本地环境调整

## 关于

2021 年系统学习 Spring Cloud 的实战沉淀,从 Netflix 全家桶到 Alibaba 体系横向对比,源码保留原始端口编号与注释,便于对照学习。如对你有帮助,欢迎 Star。
