# 阶段一：构建阶段
FROM maven:latest AS builder

# 设置工作目录
WORKDIR /app

# 将settings.xml文件复制到容器中
COPY settings.xml /root/.m2/

# 复制项目的 pom.xml 文件
COPY pom.xml .

# 复制整个项目源代码
COPY src ./src

# 编译和打包应用程序
RUN mvn package -DskipTests

# 阶段二：运行阶段
FROM openjdk:21-slim-buster

# 设置工作目录
WORKDIR /app

# 从构建阶段复制编译好的 JAR 文件到当前镜像
COPY --from=builder /app/target/*.jar ./app.jar

# 暴露容器的 8080 端口
EXPOSE 8080

# 运行应用程序
CMD ["java", "-jar", "./app.jar"]