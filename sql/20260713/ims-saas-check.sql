-- auto-generated definition
create table rec_finance_approval
(
    id              bigint unsigned auto_increment
        primary key,
    approval_no     varchar(32)                           not null comment '',
    initiator       varchar(64)                           not null comment '提交审批人',
    initiate_time   datetime    default CURRENT_TIMESTAMP not null,
    approval_status varchar(32) default 'PENDING'         not null comment '审批状态',
    bill_count      int         default 1                 not null comment '账单数量',
    approver_id     varchar(64)                           null comment '审批人id',
    approve_time    datetime                              null,
    approve_comment varchar(512)                          null comment '审批备注',
    created_at      datetime    default CURRENT_TIMESTAMP not null,
    updated_at      datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint uk_approval_no
        unique (approval_no)
)
    comment '财管审批单据表';

create index idx_status
    on rec_finance_approval (approval_status);


create table rec_finance_approval_approver
(
    id           bigint unsigned auto_increment
        primary key,
    approval_id  bigint unsigned                    not null comment '',
    uin          varchar(64)                        not null comment '',
    manager_id   varchar(64)                        not null comment '财管负责人id',
    manager_name varchar(64)                        not null comment '负责人名称',
    role_title   varchar(64)                        null comment '角色',
    created_at   datetime default CURRENT_TIMESTAMP not null
)
    comment '审批人快照表';

create index idx_approval
    on rec_finance_approval_approver (approval_id);