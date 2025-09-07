create table tbl_member
(
    id                       bigint unsigned auto_increment primary key,
    member_name              varchar(255)                                          not null,
    member_email             varchar(255)                                          null,
    kakao_email       varchar(255)                                          null,
    member_provider          enum ('kakao', 'rebound')   default 'rebound'         null,
    member_phone_number      bigint                                                not null,
    member_password          varchar(255)                                          null,
    member_suggestion_count  int unsigned                default '0'               null,
    kakao_profile_url varchar(255)                                          null,
    member_grade            enum ('mentor','subscribe', 'none')     default 'none'   null,
    member_status            enum ('active', 'inactive') default 'active'          null,
    member_common              enum ('admin', 'none')    default 'none'          null,
    created_date             datetime                    default CURRENT_TIMESTAMP null,
    updated_date             datetime                    default CURRENT_TIMESTAMP null,
    lately_date datetime,
    constraint member_email
        unique (member_email),
    constraint kakao_email
        unique (kakao_email),
    constraint member_phone_number
        unique (member_phone_number)
);
select * from tbl_member_profile_file;
select * from tbl_member;
select * from tbl_comment_alarm;
select * from tbl_comment;
select * from tbl_like;
select * from tbl_post;

delete from tbl_member where id = 138;
delete from tbl_like where member_id = 138;
delete from tbl_comment where comment_status = 'inactive';
delete from tbl_post where post_status = 'active';
delete from tbl_comment_alarm where member_id = 131;

insert into tbl_member (member_name, member_email, member_phone_number, member_password, member_status, member_common)
values ('admin', 'admin@gmail.com', '01012345678', '1234', 'active', 'admin');