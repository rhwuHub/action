FROM maven:3.8.4-jdk-8 AS builder
WORKDIR /tmp
COPY . /tmp
RUN mvn package -Dmaven.test.skip=true
FROM openjdk:8-jdk-alpine as runner
WORKDIR /app
COPY --from=builder /tmp/Blog-server/target/blog.jar ./blog.jar
# 将本地依赖 JAR 复制到容器中
COPY Blog-server/src/main/resources/lib/jodconverter-2.2.2.jar /app/lib/jodconverter-2.2.2.jar
#CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/blog.jar"]
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-Xbootclasspath/a:/app/lib/jodconverter-2.2.2.jar", "-jar", "/app/blog.jar"]
