FROM maven:3.8.4-openjdk-17 AS builder
# 安装 curl 工具以下载 RPM 包
RUN microdnf install -y curl
# 下载 libstdc++6 的 RPM 包
RUN curl -O http://mirror.centos.org/centos/8/BaseOS/x86_64/os/Packages/libstdc++-8.3.1-5.1.el8.x86_64.rpm
# 安装 libstdc++6 的 RPM 包
RUN rpm -ivh libstdc++-8.3.1-5.1.el8.x86_64.rpm
WORKDIR /tmp
COPY . /tmp
RUN mvn clean package -Dmaven.test.skip=true
FROM openjdk:17-jdk-alpine as runner
WORKDIR /app
COPY --from=builder /tmp/Blog-server/target/blog.jar ./blog.jar
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/blog.jar"]