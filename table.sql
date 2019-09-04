DROP TABLE IF EXISTS account;
CREATE TABLE account (
uuid varchar(36) NOT NULL PRIMARY KEY,
name varchar(255) NOT NULL,
last_seen datetime DEFAULT NULL,
note varchar(255) DEFAULT NULL
);

DROP TABLE IF EXISTS item;
CREATE TABLE item (
uuid varchar(36) NOT NULL PRIMARY KEY,
account_name varchar(255) NOT NULL COMMENT '用户名外键',
title varchar(255) DEFAULT NULL COMMENT '标题',
amount decimal(64,0) DEFAULT NULL COMMENT '合计',
currency int(64) DEFAULT NULL COMMENT '货币类型',
period int(64) DEFAULT NULL COMMENT '时间段',
icon varchar(255) DEFAULT NULL,
incomes_or_expenses int(64) DEFAULT NULL
);

DROP TABLE IF EXISTS saving;
CREATE TABLE saving (
uuid varchar(36) NOT NULL PRIMARY KEY,
account_name varchar(255) NOT NULL,
amount decimal(64,0) DEFAULT '0' COMMENT '存款金额',
currency int(64) DEFAULT '1' COMMENT '货币类型(1: USD,2: EUR,3: RUB)',
interest decimal(64,0) DEFAULT '0' COMMENT '利息'
);
