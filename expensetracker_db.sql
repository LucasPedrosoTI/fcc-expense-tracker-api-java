drop DATABASE expensetrackerdb;
drop user expensetrackerdb;
CREATE user expensetracker WITH password  'password';
CREATE DATABASE expensetrackerdb with template=template0 owner=expensetracker;
\connect expensetrackerdb
alter DEFAULT privileges grant all on tables to expensetracker;
alter DEFAULT privileges grant all on sequences to expensetracker;

CREATE TABLE et_users(
  user_id integer PRIMARY KEY NOT NULL,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  email VARCHAR(30) NOT NULL,
  password text not NULL
);

CREATE TABLE et_categories(
  category_id integer PRIMARY KEY NOT NULL,
  user_id integer NOT NULL,
  title VARCHAR(20) NOT NULL,
  description VARCHAR(50) NOT NULL
);
alter table et_categories add constraint cat_users_fk
FOREIGN key (user_id) references et_users(user_id);

create table et_transactions(
  transaction_id integer PRIMARY KEY NOT NULL,
  category_id integer NOT NULL,
  user_id integer NOT NULL,
  amount numeric(10,2) NOT NULL,
  note VARCHAR(50) NOT NULL,
  transaction_date bigint NOT NULL
);
alter table et_transactions add constraint trans_cat_fk
foreign key (category_id) references et_categories(category_id);
alter table et_transactions add constraint trans_user_fk
foreign key (user_id) references et_users(user_id);

create sequence et_users_seq INCREMENT 1 start 1;
create sequence et_categories_seq INCREMENT 1 start 1;
create sequence et_transactions_seq INCREMENT 1 start 1000;
