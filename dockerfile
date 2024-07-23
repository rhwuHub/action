# 使用 CentOS 8 作为基础镜像
FROM centos:8
# 安装必要的工具和库
RUN dnf install -y \
    java-17-openjdk-devel \
    maven \
    libstdc++ \
    && dnf clean all
WORKDIR /tmp
COPY . /tmp
RUN mvn clean package -Dmaven.test.skip=true
FROM openjdk:17-jdk-alpine as runner
WORKDIR /app
COPY --from=builder /tmp/Blog-server/target/blog.jar ./blog.jar
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/blog.jar"]