create table users
(
    id         int8 not null,
    firstName   varchar(255) not null,
    lasttName   varchar(255) not null,
    email   varchar(255) not null,
    birthday timestamp not null,
    role      varchar(255),
    primary key (id)
);

