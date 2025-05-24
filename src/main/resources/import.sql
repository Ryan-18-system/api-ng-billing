create schema if not exists ngbilling;
create sequence if not exists ngbilling.account_seq start with 1 increment by 1;
create table ngbilling.tb_account ( id bigint not null default nextval('ngbilling.account_seq'), active boolean, create_at timestamp(6), update_at timestamp(6), cash numeric(10, 2) not null, number_account integer, primary key (id));
alter table if exists ngbilling.tb_account alter column cash set data type numeric(10, 2);

create sequence ngbilling.bank_percentage_seq start with 1 increment by 20;
create table ngbilling.tb_bank_percentage (id bigint not null default nextval('ngbilling.bank_percentage_seq'), active boolean, create_at timestamp(6), update_at timestamp(6), payment_method varchar(255) not null check (payment_method in ('P','C','D')), percentage_rate numeric(5,4) not null, primary key (id));
alter table if exists ngbilling.tb_bank_percentage drop constraint if exists UKyoputeqkoovfdko01j0mmxyy;
alter table if exists ngbilling.tb_bank_percentage add constraint UKyoputeqkoovfdko01j0mmxyy unique (payment_method);