CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '登录邮箱',
  `password` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '登录密码',
  `createdTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `manager` int(11) DEFAULT '0' COMMENT '1: 管理员, 0: 普通用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_email_uindex` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

CREATE TABLE `t_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT 'APP 名称',
  `env` varchar(30) COLLATE utf8_bin NOT NULL COMMENT 'APP 应用环境',
  `description` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'APP 描述',
  `createdTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `lastUpdatedTime` bigint(20) DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_app_name_env_uindex` (`name`,`env`),
  KEY `t_app_name_index` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='配置应用';

CREATE TABLE `t_user_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) DEFAULT NULL COMMENT '用户 ID',
  `appId` int(11) DEFAULT NULL COMMENT '应用 ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_app_userId_appId_uindex` (`userId`,`appId`),
  KEY `t_user_app_userId_index` (`userId`),
  KEY `t_user_app_appId_index` (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户管理的APP';

CREATE TABLE `t_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` int(11) DEFAULT NULL COMMENT '应用 ID',
  `key` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '属性键',
  `value` varchar(1024) COLLATE utf8_bin NOT NULL COMMENT '属性值',
  `description` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '属性值',
  `createdTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `lastUpdatedTime` bigint(20) DEFAULT '0' COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_config_appId_key_uindex` (`appId`,`key`),
  KEY `t_config_key_index` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='属性配置';

CREATE TABLE `t_config_changed_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appName` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '应用名称',
  `appEnv` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '应用环境',
  `configId` int(11) NOT NULL COMMENT '属性键',
  `action` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '操作类型',
  `operator` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '操作员',
  `original` varchar(2048) COLLATE utf8_bin DEFAULT NULL COMMENT '配置原始值',
  `changed` varchar(2048) COLLATE utf8_bin DEFAULT NULL COMMENT '配置修改后值',
  `createdTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='配置修改历史记录';
