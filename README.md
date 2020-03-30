# ding-dang-cat
叮当猫点餐

## 本地启动

- 导入 sql 目录下的创建表SQL文件。先执行 `init.sql`，然后执行其他 sql 文件，最后执行`init_data.sql`。
- 修改 application.properties, 修改数据库配置。
- 然后启动 DingDangCatApplication。

## nohup 方式部署

- 修改 xx/application.properties
- 执行 xx/startup.sh

## Docker 方式部署

- 修改 xx/application.properties
- 执行 `docker-compose up -d`
