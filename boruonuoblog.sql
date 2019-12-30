DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nickName` varchar(256) NOT NULL COMMENT '昵称',
  `pwd` varchar(512) NOT NULL COMMENT '密码（加密字符串）',
  `mobile` char(11) NOT NULL COMMENT '手机（唯一）',
  `email` varchar(255) NOT NULL COMMENT '邮箱（唯一）',
  `type` smallint(6) NOT NULL DEFAULT '1' COMMENT '用户类型：\r\n1=博主用户\r\n2=管理用户\r\n',
  `des` varchar(512) DEFAULT NULL COMMENT '个人描述',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态\r\n0=正常\r\n1=停用\r\n',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bowen`;
CREATE TABLE `bowen` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博文ID',
  `title` varchar(256) NOT NULL COMMENT '博文标题',
  `abstrack` varchar(512) NOT NULL COMMENT '博文摘要',
  `content` text NOT NULL COMMENT '博文内容',
  `typeId` int(11) NOT NULL COMMENT '类别ID',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `name` varchar(256) NOT NULL COMMENT '类别名称',
  `des` varchar(512) DEFAULT NULL COMMENT '类别描述',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态\r\n0=正常\r\n1=停用\r\n2=删除\r\n',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `name` varchar(256) NOT NULL COMMENT '标签名称',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态\r\n0=正常\r\n1=停用\r\n2=删除\r\n',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bowen_tag`;
CREATE TABLE `bowen_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博文标签ID',
  `tagId` int(11) NOT NULL COMMENT '标签ID',
  `bowenId` int(11) NOT NULL COMMENT '博文ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;