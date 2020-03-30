FROM java:8
MAINTAINER Marvel Cheng

WORKDIR /app

COPY xx/ding-dang-cat-0.0.1-SNAPSHOT.jar ./ding-dang-cat.jar

CMD ["java", "-jar", "./ding-dang-cat.jar"]
