create sequence task_seq start 1 increment 1;
create sequence users_seq start 1 increment 1;

create table task (
                      id          int8        not null,
                      description varchar(255),
                      issued_date timestamp,
                      status      varchar(255),
                      title       varchar(50) not null,
                      user_id     int8,
                      primary key (id)
);

create table users (
                       id       int8         not null,
                       email    varchar(30)  not null,
                       name     varchar(30)  not null,
                       password varchar(255) not null,
                       role     varchar(255) not null,
                       primary key (id)
);
