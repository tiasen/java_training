create table user
(
    id         int auto_increment primary key,
    age        int          null,
    name       varchar(50) null,
    create_at  timestamp  default current_timestamp,
    updated_at timestamp  default current_timestamp on update current_timestamp
);

insert into user (name, age) value ('zhangsan', 19) , ("lisi", 20), ("wangwu", 40), ("zhaoliu", 10);
