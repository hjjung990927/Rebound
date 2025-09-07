create table tbl_today_message
(
    id                    bigint unsigned auto_increment
        primary key,
    today_message_title    varchar(255) not null,
    today_message_content varchar(255)                       not null,
    created_date          datetime default CURRENT_TIMESTAMP null,
    updated_date          datetime default CURRENT_TIMESTAMP null,
    counselor_id          bigint unsigned                    null,
    constraint fk_today_message_counselor
        foreign key (counselor_id) references tbl_counselor (id)
);

select * from tbl_today_message;

drop table tbl_today_message;

insert into tbl_today_message(today_message_content, counselor_id)
values ('힘든 순간도 있지만, 그 속에서 조금씩 단단해지고 있습니다.', 1);

alter TABLE tbl_today_message ADD today_message_title varchar(255) not null;

delete from tbl_today_message where counselor_id = 7;