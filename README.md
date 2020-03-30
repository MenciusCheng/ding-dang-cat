# ding-dang-cat
叮当猫点餐

## 本地启动

- 导入 sql 目录下的创建表SQL文件。先执行 `init.sql`，然后执行其他 sql 文件，最后执行`init_data.sql`。
- 修改 resources/application.properties，配置数据库等信息。
- 然后启动 DingDangCatApplication。

## 部署

- 根据模板复制配置文件 `cp ./xx/demo-application.properties ./xx/application.properties`
- 修改 ./xx/application.properties，配置数据库等信息。

## nohup 方式部署

- 执行 `sh ./xx/startup.sh`

## Docker 方式部署

- 执行 `docker-compose up -d`
