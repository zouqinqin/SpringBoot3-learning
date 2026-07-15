-- auto-generated definition
create table rec_finance_approval
(
    id              bigint unsigned auto_increment primary key,
    approval_no     varchar(32)                           not null comment '单据编号',
    approval_status varchar(32) default 'PENDING'         not null comment '审批状态',
    bill_count      int         default 1                 not null comment '账单数量',
    approver_user     varchar(64)                           null comment '实际审批人',
    approve_time    datetime                              null comment '实际审批时间',
    approve_comment varchar(512)                          null comment '审批备注',
    `create_date` datetime DEFAULT CURRENT_TIMESTAMP comment '审批创建时间',
    `create_by` int(20) DEFAULT null comment '审批创建人id',
    `create_by_name` varchar(50) COLLATE utf8mb4_bin DEFAULT null comment '审批创建人名称',
    `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_by` int(20) DEFAULT NULL,
    `update_by_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
    constraint uk_approval_no  unique (approval_no)
)
    comment '财管审批单据表';

create index idx_status on rec_finance_approval (approval_status);




create table rec_finance_approval_approver
(
    id           bigint unsigned auto_increment primary key,
    approval_id  bigint unsigned                    not null comment '关联审批单id',
    uin          varchar(64)                        not null comment '',
    manager_id   varchar(64)                        not null comment '财管负责人id',
    manager_name varchar(64)                        not null comment '负责人名称',
    role_title   varchar(64)                        null comment '财管角色',
    create_date datetime DEFAULT CURRENT_TIMESTAMP,
    create_by int(20) DEFAULT NULL,
    create_by_name varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
    update_date datetime DEFAULT CURRENT_TIMESTAMP,
    update_by int(20) DEFAULT NULL,
    update_by_name varchar(50) COLLATE utf8mb4_bin DEFAULT NULL
)
    comment '审批单对应的审批人';

create index idx_approval on rec_finance_approval_approver (approval_id);


approval.setApprovalNo("APV-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
