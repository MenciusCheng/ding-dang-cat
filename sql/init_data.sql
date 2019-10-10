INSERT INTO `dingdangcat`.`permission`(`id`, `name`, `remark`, `created_at`, `updated_at`) VALUES (1, '发布任务', NULL, '2019-10-10 15:17:49', '2019-10-10 15:17:49');
INSERT INTO `dingdangcat`.`permission`(`id`, `name`, `remark`, `created_at`, `updated_at`) VALUES (2, '配置任务', NULL, '2019-10-10 15:18:09', '2019-10-10 15:18:09');
INSERT INTO `dingdangcat`.`permission`(`id`, `name`, `remark`, `created_at`, `updated_at`) VALUES (3, '配置所有任务', NULL, '2019-10-10 15:18:16', '2019-10-10 15:18:16');

INSERT INTO `dingdangcat`.`role`(`id`, `name`, `remark`, `created_at`, `updated_at`) VALUES (1, '管理员', NULL, '2019-09-29 14:09:27', '2019-09-29 14:09:27');
INSERT INTO `dingdangcat`.`role`(`id`, `name`, `remark`, `created_at`, `updated_at`) VALUES (2, '发布人', NULL, '2019-10-10 15:15:17', '2019-10-10 15:15:17');
INSERT INTO `dingdangcat`.`role`(`id`, `name`, `remark`, `created_at`, `updated_at`) VALUES (3, '用户', NULL, '2019-10-10 15:15:27', '2019-10-10 15:15:27');

INSERT INTO `dingdangcat`.`role_permission`(`role_id`, `permission_id`, `created_at`) VALUES (2, 1, '2019-10-10 15:19:15');
INSERT INTO `dingdangcat`.`role_permission`(`role_id`, `permission_id`, `created_at`) VALUES (2, 2, '2019-10-10 15:19:22');

INSERT INTO `dingdangcat`.`account_role`(`account_id`, `role_id`, `created_at`) VALUES (1, 1, '2019-10-10 15:32:32');
INSERT INTO `dingdangcat`.`account_role`(`account_id`, `role_id`, `created_at`) VALUES (8, 2, '2019-10-10 15:32:51');

