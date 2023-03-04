alter table if exists users add constraint user_constraint unique (email);
alter table if exists task add constraint task_constraint foreign key (user_id) references users;