# test-shop 项目demo 

**Swagger地址：http://localhost:8066/swagger-ui.html**


1.本项目以SpringBoot为基础进行开发
  目前融入的技术有：Kafka，Elastic-job，Zookeeper，Redis，Lambda，线程池，Apollo配置，Swagger2，java8日期类；
  Eureka，Feign，Ribbon，Hystrix，Zuul，Zipkin等
  配置Redisson分布式锁
  

2.模块的依赖关系
 api -> entity；
 controller -> service -> model -> entity；


3.后续不断完善一些开源技术融入到本demo中...

4.备注：

（1）启动本项目前，需要先安装Zookeeper，Kafka，Redis，Apollo等中间件。
（2）如果想要监控Elastic-job的任务调度，去克隆elastic-job源代码生成war包，通过start.bat进行启动即可。


欢迎Star，欢迎Fork...
