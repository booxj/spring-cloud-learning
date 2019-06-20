### spring-cloud-admin
- cloud-admin-server:8769
- cloud-eureka-client:8080
- cloud-eureka-server:8761

### spring-cloud-config （基于git的配置中心）
- config-client:8080
- config-eureka-server:8761
- config-server:8888
    
    
### spring-cloud-config-mysql （基于mysql的配置中心，集成了bus实现热更新）
- mysql-config-client:8080
- mysql-config-server:8888
- mysql-eureka-server:8761
### spring-cloud-eureka （注册中心Demo）

### spring-cloud-eureka-cluster （注册中心集群化）
    java -jar eureka-server-0.0.1-SNAPSHOT.jar - -spring.profiles.active=peer1
    peer1:8701
    peer2:8702
    peer3:8703
    
    client:8710
    
    
### spring-cloud-feign
- feign-customer:9020
- feign-provider:9011-9019

### spring-cloud-gateway    （Gateway 网关，支持动态修改）
- gateway-client1:10001
- gateway-client2:10002
- gateway-server:9999

### spring-cloud-ribbon
- ribbon-customer:9010
- ribbon-provider:9001-9009

### spring-cloud-sleuth

    Spring Cloud Sleuth集成了zipkin组件，主要功能就是在分布式系统中提供追踪解决方案
    
- sleuth-service1:9401
- sleuth-service2:9402
- sleuth-service3:9403
    
### spring-cloud-zk （基于zookeeper实现注册发现，自己实现相关功能）

### spring-cloud-zipkin

    启动zipkin,默认port:9411
    java -jar zipkin-server-2.10.1-exec.jar
    
