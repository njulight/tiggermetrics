DROP TABLE IF EXISTS account;
CREATE TABLE account (
uuid varchar(36) NOT NULL PRIMARY KEY,
name varchar(255) NOT NULL,
last_seen datetime DEFAULT NULL,
note varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS item;
CREATE TABLE item (
uuid varchar(36) NOT NULL PRIMARY KEY,
account_id varchar(36) NOT NULL COMMENT '用户名外键',
title varchar(255) DEFAULT NULL COMMENT '标题',
amount decimal(64,0) DEFAULT NULL COMMENT '合计',
currency int(64) DEFAULT NULL COMMENT '货币类型',
item_time datetime DEFAULT NULL COMMENT '时间',
icon varchar(255) DEFAULT NULL,
incomes_or_expenses int(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS saving;
CREATE TABLE saving (
uuid varchar(36) NOT NULL PRIMARY KEY,
account_id varchar(36) NOT NULL,
amount decimal(64,0) DEFAULT '0' COMMENT '存款金额',
currency int(64) DEFAULT '1' COMMENT '货币类型(1: USD,2: EUR,3: RMB)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
