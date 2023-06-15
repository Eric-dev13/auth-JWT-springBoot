create table todo (id bigint not null auto_increment, content varchar(255), created_at datetime(6) not null, title varchar(255) not null, updated_at datetime(6), user_id bigint not null, primary key (id)) engine=InnoDB;
create table user (user_id bigint not null auto_increment, created_at datetime(6), firstname varchar(255), lastname varchar(255), password varchar(255), updated_at datetime(6), username varchar(255), primary key (user_id)) engine=InnoDB;
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table todo add constraint FK2ft3dfk1d3uw77pas3xqwymm7 foreign key (user_id) references user (user_id);
create table todo (id bigint not null auto_increment, content varchar(255), created_at datetime(6) not null, title varchar(255) not null, updated_at datetime(6), user_id bigint not null, primary key (id)) engine=InnoDB;
create table user (user_id bigint not null auto_increment, created_at datetime(6), firstname varchar(255), lastname varchar(255), password varchar(255), updated_at datetime(6), username varchar(255), primary key (user_id)) engine=InnoDB;
alter table user add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table todo add constraint FK2ft3dfk1d3uw77pas3xqwymm7 foreign key (user_id) references user (user_id);
