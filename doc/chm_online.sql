create database chm_online;

use chm_online;

create table t_chm_user(
  user_id int not null primary key auto_increment,
  user_account varchar(128) not null,
  user_password varchar(128) not null,
  password_random varchar(8) not null,
  nick_name varchar(128) not null,
  sex varchar(1) default '0',
  img_url varchar(128),
  last_login_time varchar(20) not null,
  create_date varchar(20) not null
)engine=innodb default charset utf8;




select * from t_chm_user;