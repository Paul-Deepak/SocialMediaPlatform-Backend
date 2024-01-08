create database social_media_platform;
use social_media_platform;

create table users(user_id int unsigned auto_increment primary key, user_name varchar(50) not null, email varchar(30) unique not null, password varchar(20), profile_pic mediumblob, bio varchar(100),created_date date not null,modified_date date, isactive boolean not null);
create table posts(post_id int unsigned auto_increment primary key, user_id int unsigned, content mediumblob, posted_on timestamp, last_modified_on timestamp, FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID));
create table comments(comment_id int unsigned auto_increment primary key, user_id int unsigned, post_id int unsigned,comment_text varchar(50) not null, commented_on timestamp, last_modified_on timestamp, FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID),FOREIGN KEY(POST_ID) REFERENCES POSTS(POST_ID));
create table likes(like_id int unsigned auto_increment primary key, user_id int unsigned,like_type varchar(8) not null,liked_on timestamp, last_modified_on timestamp);
create table friendlist(friendlist_id int unsigned auto_increment primary key, request_time timestamp, modified_time timestamp, status varchar(20), friend_id bigint not null, request_sent_by bigint not null, user_id bigint, FOREIGN KEY(friend_id) REFERENCES USERS(USER_ID), FOREIGN KEY(request_sent_by) REFERENCES USERS(USER_ID), FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID));