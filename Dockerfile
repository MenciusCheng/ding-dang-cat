FROM java:8
MAINTAINER Marvel Cheng

WORKDIR /app

# 设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

COPY xx/ding-dang-cat-0.0.1-SNAPSHOT.jar ./ding-dang-cat.jar

CMD ["java", "-jar", "./ding-dang-cat.jar"]
