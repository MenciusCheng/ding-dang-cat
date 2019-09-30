CREATE TABLE `ding_task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) COLLATE utf8mb4_bin NOT NULL COMMENT '任务名称',
  `start_at` time NOT NULL COMMENT '开始时间',
  `end_at` time NOT NULL COMMENT '结束时间',
  `max_count` int(11) NOT NULL COMMENT '人数上限',
  `repeat_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '重复类型，1: 一次; 2: 工作日; 3: 每周五;',
  `enabled` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否启用，0: 否; 1: 是;',
  `apply_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '报名状态，1: 未开始; 2: 进行中; 3: 已结束; 4: 已停止;',
  `manager_id` int(10) unsigned NOT NULL COMMENT '管理人员 ID',
  `description` varchar(255) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '报名描述',
  `ding_webhook` varchar(255) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '钉钉 webhook 地址',
  `deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除，0: 否; 1: 是;',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '创建人',
  `updated_by` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='钉钉报名任务表';

CREATE TABLE `ding_task_apply` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ding_task_id` int(10) unsigned NOT NULL COMMENT '关联的钉钉报名任务 ID',
  `ding_task_name` varchar(60) COLLATE utf8mb4_bin NOT NULL COMMENT '关联的钉钉报名任务名称',
  `apply_date` date NOT NULL COMMENT '报名日期',
  `completed` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否完成，0: 否; 1: 是;',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ding_task_id_apply_date` (`ding_task_id`,`apply_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='钉钉报名任务申请表';

CREATE TABLE `ding_task_apply_staff` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ding_task_apply_id` int(10) unsigned NOT NULL COMMENT '关联的钉钉报名任务申请 ID',
  `staff_id` int(10) unsigned NOT NULL COMMENT '申请人 ID',
  `remark` varchar(60) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '备注',
  `cancelled` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否取消，0: 否; 1: 是;',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ding_task_apply_id_staff_id` (`ding_task_apply_id`,`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='钉钉报名任务申请人员表';