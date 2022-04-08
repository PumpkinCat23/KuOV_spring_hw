DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS post_tag;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS post;

TODO
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS "user";


CREATE TABLE post (
    post_id bigserial PRIMARY KEY,
    user_id biginteger
    title varchar(100) NOT NULL,
    content text NOT NULL,
    dt_created timestamp NOT NULL,
    dt_updated timestamp
);

CREATE TABLE tag (
    tag_id bigserial PRIMARY KEY,
    name varchar(50) NOT NULL
);


CREATE TABLE post_tag (
    post_id bigint REFERENCES post(post_id) ON DELETE CASCADE,
    tag_id bigint REFERENCES tag(tag_id) ON DELETE CASCADE,
    PRIMARY KEY (post_id, tag_id)
);

CREATE TABLE "user" (
    user_id bigserial PRIMARY KEY,
    username varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    created_at timestamp NOT NULL,
    is_active boolean NOT NULL      --?????
)

CREATE TABLE role (
    role_id bigserial PRIMARY KEY,
    name varchar(50) NOT NULL
)

CREATE TABLE user_role (
    user_id bigint REFERENCES role(role_id) ON DELETE CASCADE,
    role_id bigint REFERENCES "user"(user_id) ON DELETE CASCADE,
    PRIMARY KEY (role_id, user_id)
)


CREATE TABLE comment (
    comment_id bigserial PRIMARY KEY,
    user_id biginteger
    post_id bigint REFERENCES post(post_id) ON DELETE CASCADE,
    content text,
    dt_created timestamp NOT NULL,
    dt_updated timestamp
);








--Data


insert into post (title, content, dt_created, dt_updated)
	values ('Day 1', 'It''s all good!', current_timestamp - interval '2 days', null);
insert into post (title, content, dt_created, dt_updated)
	values ('Day 2', 'It''s all ok!', current_timestamp - interval '1 days', null);
insert into post (title, content, dt_created, dt_updated)
	values ('Day 3', 'It''s all bad!', current_timestamp, null);

insert into tag (name) values ('news');
insert into tag (name) values ('life');
insert into tag (name) values ('day');
insert into tag (name) values ('mood');
insert into tag (name) values ('ideas');
insert into tag (name) values ('thoughts');

insert into post_tag(post_id, tag_id) values (1, 1);
insert into post_tag(post_id, tag_id) values (1, 2);
insert into post_tag(post_id, tag_id) values (2, 3);
insert into post_tag(post_id, tag_id) values (2, 2);
insert into post_tag(post_id, tag_id) values (2, 1);
insert into post_tag(post_id, tag_id) values (2, 5);
insert into post_tag(post_id, tag_id) values (3, 3);
insert into post_tag(post_id, tag_id) values (3, 2);
insert into post_tag(post_id, tag_id) values (3, 6);

insert into comment (post_id, content, dt_created)
    values (2, 'Nice!', current_timestamp);
insert into comment (post_id, content, dt_created)
    values (1, 'Awesome!', current_timestamp);
insert into comment (post_id, content, dt_created)
    values (1, 'Excellent!', current_timestamp);
insert into comment (post_id, content, dt_created)
    values (1, 'Wonderful!', current_timestamp);
insert into comment (post_id, content, dt_created)
    values (3, 'Disgusting!', current_timestamp);
insert into comment (post_id, content, dt_created)
    values (3, 'Atrocious!', current_timestamp);
