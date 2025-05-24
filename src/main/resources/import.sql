create schema if not exists ngbilling;
create sequence if not exists ngbilling.account_seq start with 1 increment by 1;
create table ngbilling.tb_account ( id bigint not null default nextval('ngbilling.account_seq'), active boolean, create_at timestamp(6), update_at timestamp(6), cash numeric(10, 2) not null, number_account integer, primary key (id));
