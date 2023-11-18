# 阶段二：运行阶段
FROM openjdk:21-slim-buster

# 设置工作目录
WORKDIR /app

ARG mysql_host=192.168.0.201
ARG mysql_username=root
ARG mysql_password=123456
ARG qqmail_key=key


# 从构建阶段复制编译好的 JAR 文件到当前镜像
COPY ./target/*.jar ./app.jar

# 暴露容器的 8080 端口
EXPOSE 8080

# 运行应用程序
CMD java  -jar ./app.jar