# 使用 Ubuntu 作为基础镜像
FROM ubuntu:20.04 AS builder

# 更新系统和安装必要的工具和库
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    libstdc++6 \
    && apt-get clean
WORKDIR /tmp
COPY . /tmp
RUN mvn clean package -Dmaven.test.skip=true
FROM openjdk:17-jdk-alpine as runner
WORKDIR /app
COPY --from=builder /tmp/Blog-server/target/blog.jar ./blog.jar
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/blog.jar"]