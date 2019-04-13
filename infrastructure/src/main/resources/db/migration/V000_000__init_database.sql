create table ACCOUNT(
 id varchar2(36) not null,
 account_number varchar2(26) not null ,
 name varchar2(26) not null,
 creation_date timestamp not null,
 amount decimal(12,2), primary key(id),
 constraint account_unique UNIQUE (account_number)
);
create table TRANSACTION_HISTORY(
  id varchar2(36) not null,
  payer_id varchar2(26) not null,
  payee_id varchar2(26) not null,
  creation_date timestamp not null,
  amount decimal(12,2) not null,
  status varchar2(26),
  primary key(id),
  constraint fk_trs_histo_acct_payer foreign key(payer_id) references account(id),
  constraint fk_trs_histo_acct_payee foreign key(payee_id) references account(id)
);