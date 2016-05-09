# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table company (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_company primary key (id)
);
create sequence company_seq;

create table linked_account (
  id                            bigint not null,
  user_id                       bigint,
  provider_user_id              varchar(255),
  provider_key                  varchar(255),
  constraint pk_linked_account primary key (id)
);
create sequence linked_account_seq;

create table routescompany (
  id                            bigint not null,
  company_id                    bigint,
  price                         float,
  initial_routes_company_id     bigint,
  final_routes_company_id       bigint,
  constraint pk_routescompany primary key (id)
);
create sequence routescompany_seq;

create table routescompany_street (
  routescompany_id              bigint not null,
  street_id                     bigint not null,
  constraint pk_routescompany_street primary key (routescompany_id,street_id)
);

create table security_role (
  id                            bigint not null,
  role_name                     varchar(255),
  constraint pk_security_role primary key (id)
);
create sequence security_role_seq;

create table street (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_street primary key (id)
);
create sequence street_seq;

create table suggestions (
  id                            bigint not null,
  first_name                    varchar(50),
  last_name                     varchar(50),
  email                         varchar(100),
  phone                         varchar(20),
  company_id                    bigint,
  comment                       varchar(1000),
  created_at                    timestamp not null,
  constraint pk_suggestions primary key (id)
);
create sequence suggestions_seq;

create table token_action (
  id                            bigint not null,
  token                         varchar(255),
  target_user_id                bigint,
  type                          varchar(2),
  created                       timestamp,
  expires                       timestamp,
  constraint ck_token_action_type check (type in ('PR','EV')),
  constraint uq_token_action_token unique (token),
  constraint pk_token_action primary key (id)
);
create sequence token_action_seq;

create table users (
  id                            bigint not null,
  email                         varchar(255),
  name                          varchar(255),
  first_name                    varchar(255),
  last_name                     varchar(255),
  last_login                    timestamp,
  active                        boolean,
  email_validated               boolean,
  constraint pk_users primary key (id)
);
create sequence users_seq;

create table users_security_role (
  users_id                      bigint not null,
  security_role_id              bigint not null,
  constraint pk_users_security_role primary key (users_id,security_role_id)
);

create table users_user_permission (
  users_id                      bigint not null,
  user_permission_id            bigint not null,
  constraint pk_users_user_permission primary key (users_id,user_permission_id)
);

create table user_permission (
  id                            bigint not null,
  value                         varchar(255),
  constraint pk_user_permission primary key (id)
);
create sequence user_permission_seq;

alter table linked_account add constraint fk_linked_account_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_linked_account_user_id on linked_account (user_id);

alter table routescompany add constraint fk_routescompany_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_routescompany_company_id on routescompany (company_id);

alter table routescompany add constraint fk_routescompany_initial_routes_company_id foreign key (initial_routes_company_id) references street (id) on delete restrict on update restrict;
create index ix_routescompany_initial_routes_company_id on routescompany (initial_routes_company_id);

alter table routescompany add constraint fk_routescompany_final_routes_company_id foreign key (final_routes_company_id) references street (id) on delete restrict on update restrict;
create index ix_routescompany_final_routes_company_id on routescompany (final_routes_company_id);

alter table routescompany_street add constraint fk_routescompany_street_routescompany foreign key (routescompany_id) references routescompany (id) on delete restrict on update restrict;
create index ix_routescompany_street_routescompany on routescompany_street (routescompany_id);

alter table routescompany_street add constraint fk_routescompany_street_street foreign key (street_id) references street (id) on delete restrict on update restrict;
create index ix_routescompany_street_street on routescompany_street (street_id);

alter table suggestions add constraint fk_suggestions_company_id foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_suggestions_company_id on suggestions (company_id);

alter table token_action add constraint fk_token_action_target_user_id foreign key (target_user_id) references users (id) on delete restrict on update restrict;
create index ix_token_action_target_user_id on token_action (target_user_id);

alter table users_security_role add constraint fk_users_security_role_users foreign key (users_id) references users (id) on delete restrict on update restrict;
create index ix_users_security_role_users on users_security_role (users_id);

alter table users_security_role add constraint fk_users_security_role_security_role foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;
create index ix_users_security_role_security_role on users_security_role (security_role_id);

alter table users_user_permission add constraint fk_users_user_permission_users foreign key (users_id) references users (id) on delete restrict on update restrict;
create index ix_users_user_permission_users on users_user_permission (users_id);

alter table users_user_permission add constraint fk_users_user_permission_user_permission foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;
create index ix_users_user_permission_user_permission on users_user_permission (user_permission_id);


# --- !Downs

alter table linked_account drop constraint if exists fk_linked_account_user_id;
drop index if exists ix_linked_account_user_id;

alter table routescompany drop constraint if exists fk_routescompany_company_id;
drop index if exists ix_routescompany_company_id;

alter table routescompany drop constraint if exists fk_routescompany_initial_routes_company_id;
drop index if exists ix_routescompany_initial_routes_company_id;

alter table routescompany drop constraint if exists fk_routescompany_final_routes_company_id;
drop index if exists ix_routescompany_final_routes_company_id;

alter table routescompany_street drop constraint if exists fk_routescompany_street_routescompany;
drop index if exists ix_routescompany_street_routescompany;

alter table routescompany_street drop constraint if exists fk_routescompany_street_street;
drop index if exists ix_routescompany_street_street;

alter table suggestions drop constraint if exists fk_suggestions_company_id;
drop index if exists ix_suggestions_company_id;

alter table token_action drop constraint if exists fk_token_action_target_user_id;
drop index if exists ix_token_action_target_user_id;

alter table users_security_role drop constraint if exists fk_users_security_role_users;
drop index if exists ix_users_security_role_users;

alter table users_security_role drop constraint if exists fk_users_security_role_security_role;
drop index if exists ix_users_security_role_security_role;

alter table users_user_permission drop constraint if exists fk_users_user_permission_users;
drop index if exists ix_users_user_permission_users;

alter table users_user_permission drop constraint if exists fk_users_user_permission_user_permission;
drop index if exists ix_users_user_permission_user_permission;

drop table if exists company;
drop sequence if exists company_seq;

drop table if exists linked_account;
drop sequence if exists linked_account_seq;

drop table if exists routescompany;
drop sequence if exists routescompany_seq;

drop table if exists routescompany_street;

drop table if exists security_role;
drop sequence if exists security_role_seq;

drop table if exists street;
drop sequence if exists street_seq;

drop table if exists suggestions;
drop sequence if exists suggestions_seq;

drop table if exists token_action;
drop sequence if exists token_action_seq;

drop table if exists users;
drop sequence if exists users_seq;

drop table if exists users_security_role;

drop table if exists users_user_permission;

drop table if exists user_permission;
drop sequence if exists user_permission_seq;

