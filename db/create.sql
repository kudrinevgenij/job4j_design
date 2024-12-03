create table roles (
	id serial primary key,
	name varchar(255)
);

create table users (
	id serial primary key,
	name varchar(255),
	role_id int references roles(id)
);

create table rights (
	id serial primary key,
	name text
);

create table states (
	id serial primary key,
	name varchar(255)
);

create table categories (
	id serial primary key,
	name varchar(255)
);

create table items (
	id serial primary key,
	name varchar(255),
	user_id int references users(id),
	state_id int references states(id),
	category_id int references categories(id)
);

create table comments (
	id serial primary key,
	comment text,
	item_id int references items(id)
);

create table attachments (
	id serial primary key,
	name varchar(255),
	item_id int references items(id) 
);

create table roles_rights (
	id serial primary key,
	role_id int references roles(id),
	rights_id int references rights(id)
)

