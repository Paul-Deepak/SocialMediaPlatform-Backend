create database social_media_platform_app;
use social_media_platform_app;

-- create table users(user_id int unsigned auto_increment primary key, user_name varchar(50) not null, email varchar(30) unique not null, password varchar(20), profile_pic mediumblob, bio varchar(100),created_date date not null,modified_date date, isactive boolean not null);
-- create table posts(post_id int unsigned auto_increment primary key, user_id int unsigned, content mediumblob, posted_on timestamp, last_modified_on timestamp, FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID));
-- create table comments(comment_id int unsigned auto_increment primary key, user_id int unsigned, post_id int unsigned,comment_text varchar(50) not null, commented_on timestamp, last_modified_on timestamp, FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID),FOREIGN KEY(POST_ID) REFERENCES POSTS(POST_ID));
-- create table likes(like_id int unsigned auto_increment primary key, user_id int unsigned,like_type varchar(8) not null,liked_on timestamp, last_modified_on timestamp);
-- create table friendlist(friendlist_id int unsigned auto_increment primary key, request_time timestamp, modified_time timestamp, status varchar(20), friend_id bigint not null, request_sent_by bigint not null, user_id bigint, FOREIGN KEY(friend_id) REFERENCES USERS(USER_ID), FOREIGN KEY(request_sent_by) REFERENCES USERS(USER_ID), FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID));

create table users(user_id int unsigned auto_increment primary key, user_name varchar(50) not null, email varchar(30) unique not null, password varchar(20), profile_pic mediumblob, bio varchar(100),created_date date not null,modified_date date, isactive boolean not null);
create table posts(post_id int unsigned auto_increment primary key, user_id int unsigned, content mediumblob, created_on timestamp, last_modified_on timestamp, FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID));
create table comments(comment_id int unsigned auto_increment primary key, user_id int unsigned, post_id int unsigned,comment_text varchar(50) not null, created_on timestamp, last_modified_on timestamp, FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID),FOREIGN KEY(POST_ID) REFERENCES POSTS(POST_ID));
create table likes(like_id int unsigned auto_increment unique key, user_id int unsigned, type varchar(8) not null,created_on timestamp, last_modified_on timestamp, type_id int unsigned,primary key(user_id,type_id,type), foreign key (user_id) references users(user_id));
create table friendlist(friendlist_id int unsigned auto_increment unique key, request_time timestamp, modified_time timestamp, status_id int, request_sent_by int unsigned, request_sent_to int unsigned,primary key(request_sent_by,request_sent_to), FOREIGN KEY(request_sent_by) REFERENCES USERS(USER_ID), FOREIGN KEY(request_sent_to) REFERENCES USERS(USER_ID),FOREIGN KEY(status_ID) REFERENCES status(status_ID));
create table status(status_id int primary key, status varchar(10));

insert into status values('0','PENDING'),('1','ACCEPTED'),('2','REJECTED'),('3','UNFRIEND');

select * from users;

-- for searching post in a friend's list of posts
SELECT * from Posts p INNER JOIN Users u ON u.user_id = p.user_id 
INNER JOIN Friendlist f ON u.user_id = f.request_sent_by 
WHERE (f.request_sent_by = 4 AND f.request_sent_to = 3 AND f.status_id = '1' ) 
OR (f.request_sent_to = 3 AND f.request_sent_by = 4 AND f.status_id = '1' ) 
AND LOWER(p.post_caption) LIKE LOWER(CONCAT('%', 'are', '%'));

-- Search post in all the friends of user
select * from posts p inner join users u on u.user_id = p.user_id
INNER JOIN Friendlist f ON u.user_id = f.request_sent_by 
where (f.request_sent_by = 4 and f.status_id='1')
or (f.request_sent_to = 4 and f.status_id='1')
AND p.post_caption LIKE LOWER(CONCAT('%', 'are', '%'));

delete from posts where post_caption=null;