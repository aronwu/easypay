CREATE TABLE application
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) COMMENT '应用名称',
    white_list VARCHAR(1000) COMMENT 'IP白名单',
    secret VARCHAR(255) COMMENT '安全key,用于md5签名认证',
    public_key VARCHAR(3000) COMMENT 'pulic key,用于PKI签名校验返回结果',
    private_key VARCHAR(5000) COMMENT '生成签名的私钥',
    app_public_key VARCHAR(3000) COMMENT '应用提供的public key,用于PKI签名校验应用提交的请求',
    app_code VARCHAR(32),
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间'
);
CREATE TABLE notify_failed_log
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    flow_id INT(11) COMMENT 'payment或者refund ID',
    result_type TINYINT(2) COMMENT '返回结果类型 1支付结果 2退款结果',
    notify_count TINYINT(2) COMMENT '已通知次数',
    last_notify_time DATETIME COMMENT '最后一次通知时间',
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间'
);
CREATE UNIQUE INDEX result_id_type ON notify_failed_log (flow_id, result_type);
CREATE TABLE notify_schedule
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    trade_id BIGINT(20) NOT NULL COMMENT '交易ID',
    trade_type TINYINT(2) COMMENT '交易类型 0支付pay 1退款refund',
    platform_id INT(11) COMMENT '平台ID',
    next_notify_time DATETIME COMMENT '下次通知时间',
    notify_count TINYINT(2) DEFAULT '1' NOT NULL COMMENT '通知次数',
    notify_status TINYINT(2) DEFAULT '1' NOT NULL COMMENT '通知状态 0未通知 1通知失败结束 2通知成功结束',
    notify_content VARCHAR(8000) COMMENT '通知内容',
    payment_code VARCHAR(32) NOT NULL,
    notify_error TEXT,
    status TINYINT(4),
    refund_type TINYINT(4) DEFAULT '0' COMMENT '退款类(1：全部退款；2部分退款---返现)',
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间'
);
CREATE TABLE payment
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    payment_code VARCHAR(50) COMMENT '支付单号',
    platform_id INT(11) COMMENT '第三方平台ID',
    app_id INT(11) COMMENT '请求支付的应用ID',
    service_fee_rate INT(11) DEFAULT '0' NOT NULL COMMENT '第三方平台手续费比例',
    amount INT(11) COMMENT '在线支付金额
',
    bank VARCHAR(50) COMMENT '支付银行',
    item_name VARCHAR(255) COMMENT '商品名称',
    item_count INT(11) COMMENT '商品数量',
    item_desc VARCHAR(400) COMMENT '商品描述',
    notify_url VARCHAR(255) COMMENT '支付结果异步通知URL',
    return_url VARCHAR(255) COMMENT '支付结果同步通知URL',
    type INT(11),
    status TINYINT(2) DEFAULT '0' NOT NULL COMMENT '支付状态 0等待支付 1成功 10失败',
    user_id BIGINT(20) DEFAULT '0' NOT NULL COMMENT '下订用户ID',
    client_ip VARCHAR(50) COMMENT '进行支付的买家IP',
    order_id INT(11),
    open_id VARCHAR(200),
    attach VARCHAR(500),
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间'
);
CREATE INDEX index_add_time ON payment (created_time);
CREATE INDEX index_last_modify_time ON payment (updated_time);
CREATE TABLE payment_result
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    payment_code VARCHAR(40) COMMENT '支付号',
    platform_id INT(11) NOT NULL,
    trade_no VARCHAR(128) COMMENT '从第三方平台返回的支付交易号',
    pay_amount INT(11) COMMENT '实际支付金额 以分为单位',
    fee INT(11) COMMENT '第三方平台收取的支付手续费',
    status TINYINT(2) COMMENT '1 success
 2 success but sign verify fail 10 fail
',
    payment_time DATETIME COMMENT '买家支付时间',
    platform_return_code VARCHAR(45) COMMENT '支付平台返回的状态码，可以去 payPlatformReturnCode表中查询具体含义',
    return_check TINYINT(2) DEFAULT '0' NOT NULL COMMENT '记录校验订单是否匹配
0 正常
1 异常
用于校验第三方返回的数据是否和我们发出去的数据保持一致
如果不正确，',
    return_content TEXT COMMENT '从第三方返回的信息内容',
    notify_status TINYINT(2) DEFAULT '0' NOT NULL COMMENT '结果通知状态 0未通知 1 已接收 2 支付确认',
    notify_time TIMESTAMP COMMENT '成功通知时间',
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间'
);
CREATE INDEX created_time ON payment_result (created_time);
CREATE UNIQUE INDEX unique_payment_code ON payment_result (payment_code);
CREATE TABLE platform
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) DEFAULT '',
    service VARCHAR(50) COMMENT '服务名称',
    comments VARCHAR(255) DEFAULT '' COMMENT '备注',
    ansync_wait_sec INT(11) COMMENT '异步等待秒数
当第三方提供异步返回时，需要设置异步等待秒数',
    last_modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_active INT(1) DEFAULT '0' NOT NULL COMMENT '是否有效 0-有效 1无效',
    type INT(1) DEFAULT '0' NOT NULL COMMENT '支付渠道 1-线上 2-线下'
);
CREATE TABLE platform_code
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    code VARCHAR(45) DEFAULT '' NOT NULL COMMENT '返回码',
    description VARCHAR(255) DEFAULT '' NOT NULL COMMENT '描述',
    platform_id INT(11) NOT NULL COMMENT '第三方支付平台ID',
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间'
);
CREATE TABLE refund
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    app_id INT(11) NOT NULL COMMENT '发起退款请求的应用ID',
    platform_id INT(11) NOT NULL COMMENT '支付平台id 1微信APP 2支付宝APP',
    refund_batch_code VARCHAR(50) COMMENT '退款批次号',
    refund_trade_no VARCHAR(128) COMMENT '支付平台返回的支付结果交易号',
    status TINYINT(2) DEFAULT '0' NOT NULL COMMENT '退款状态 (0:"未退款",1:"退款中",2:"退款失败",3:"退款成功")',
    return_code VARCHAR(255) COMMENT '第三方平台的错误代码',
    return_content VARCHAR(2000) COMMENT '第三方平台返回的原始结果',
    notify_time TIMESTAMP COMMENT '通知时间',
    notify_status TINYINT(2) DEFAULT '0' COMMENT '结果通知状态 0未通知 1 已接收 2 退款成功确认',
    success_num SMALLINT(6) DEFAULT '0' NOT NULL COMMENT '成功退款笔数',
    batch_num SMALLINT(6) NOT NULL COMMENT '批次退款笔数',
    user_id BIGINT(20) COMMENT '执行退款操作者user_id',
    order_id INT(11) DEFAULT '0',
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间'
);
CREATE INDEX created_time_in ON refund (created_time);
CREATE UNIQUE INDEX refund_batch_code ON refund (refund_batch_code);
CREATE INDEX updated_time_in ON refund (updated_time);
CREATE TABLE refund_result
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    refund_id BIGINT(20) NOT NULL COMMENT '退款单id',
    platform_id INT(11) NOT NULL COMMENT '支付平台id',
    payment_code VARCHAR(50) NOT NULL COMMENT '支付单号',
    trade_no VARCHAR(128) COMMENT '支付平台返回的支付结果交易号',
    refund_code VARCHAR(50) NOT NULL COMMENT '退款单号',
    refund_trade_no VARCHAR(128) COMMENT '支付平台返回的退款交易号',
    status TINYINT(2) DEFAULT '0' NOT NULL COMMENT '退款状态 (0:"未退款",1:"退款中",2:"退款失败",3:"退款成功")',
    amount INT(11) NOT NULL COMMENT '申请退款金额 以分为单位',
    refund_amount INT(11) COMMENT '实际退款金额 以分为单位',
    refund_channel VARCHAR(32) COMMENT '退款渠道',
    fee INT(11) COMMENT '第三方平台返还的手续费 以分为单位',
    refund_time TIMESTAMP COMMENT '退款完成时间',
    return_code VARCHAR(255) COMMENT '第三方的状态代码',
    return_content VARCHAR(2000) COMMENT '第三方平台返回的原始结果',
    user_id BIGINT(20),
    order_id INT(11) NOT NULL COMMENT '订单系统退款流水号',
    refund_type INT(11) NOT NULL COMMENT '退款类型(1：全部退款；2部分退款---返现)',
    refund_error VARCHAR(2000),
    created_time DATETIME NOT NULL COMMENT '创建时间',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '更新时间'
);
CREATE INDEX refund_code ON refund_result (refund_code);
CREATE INDEX refund_id_trade_no_in ON refund_result (trade_no);
CREATE TABLE result_query
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    trade_id BIGINT(20) COMMENT '交易ID',
    trade_type TINYINT(2) COMMENT '交易类型 0支付 1退款',
    platform_id INT(11) COMMENT '平台ID',
    next_query_time INT(11) COMMENT '下次查询时间',
    query_count TINYINT(2) DEFAULT '1' COMMENT '查询次数',
    query_status TINYINT(2) DEFAULT '1' COMMENT '查询状态 1待查询 2查询失败结束 3查询成功结束',
    return_content VARCHAR(3000) COMMENT '查询返回内容',
    created_time DATETIME COMMENT '创建时间',
    updated_time TIMESTAMP COMMENT '更新时间'
);
CREATE INDEX trade_id_in ON result_query (trade_id);
CREATE UNIQUE INDEX trade_id_type ON result_query (trade_id, trade_type, platform_id);
CREATE INDEX updated_time_in ON result_query (updated_time);
