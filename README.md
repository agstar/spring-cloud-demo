# spring cloud demo

##使用maven命令行启动

#####eureka1 ：spring-boot:run -Dport=6868 -Deureka.server=http://localhost:6869/eureka/
#####eureka2 ：spring-boot:run -Dport=6869 -Deureka.server=http://localhost:6868/eureka/

#####user1 ：spring-boot:run -Dport=8281
#####user2 ：spring-boot:run -Dport=8282

#####sso1 ：spring-boot:run -Dport=8381
#####sso2 ：spring-boot:run -Dport=8382

#####apt-gateway1 ：spring-boot:run -Dport=6677
#####apt-gateway2 ：spring-boot:run -Dport=6678