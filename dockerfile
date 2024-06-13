FROM registry.cn-hangzhou.aliyuncs.com/rhwu/hub:jdk-8 AS builder
WORKDIR /tmp
COPY . /tmp
RUN mvn package -Dmaven.test.skip=true -s /tmp/settings.xml
FROM registry.cn-hangzhou.aliyuncs.com/rhwu/hub:maven-jkd-8 as runner
WORKDIR /app
COPY --from=builder /tmp/Blog-server/target/blog.jar ./blog.jar
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/blog.jar"]
