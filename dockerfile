FROM maven:3.8.4-openjdk-17 AS builder
# 安装 C++ 标准库
RUN yum update && yum install -y libstdc++6
WORKDIR /tmp
COPY . /tmp
RUN mvn clean package -Dmaven.test.skip=true
FROM openjdk:17-jdk-alpine as runner
WORKDIR /app
COPY --from=builder /tmp/Blog-server/target/blog.jar ./blog.jar
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/blog.jar"]