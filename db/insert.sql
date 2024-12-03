insert into roles(name) values('Role 1');
insert into roles(name) values('Role 2');

insert into users(name, role_id) values('Ivan', 1);
insert into users(name, role_id) values('Alena', 2);

insert into rights(name) values('Права 1');
insert into rights(name) values('Права 2');

insert into states(name) values('Состояние 1');
insert into states(name) values('Состояние 2');

insert into categories(name) values('Категория 1');
insert into categories(name) values('Категория 2');

insert into items(name, user_id, state_id, category_id) values('Заявка 1', 1, 1, 1);
insert into items(name, user_id, state_id, category_id) values('Заявка 2', 2, 2, 2);

insert into comments(comment, item_id) values('Комментарий 1', 1);
insert into comments(comment, item_id) values('Комментарий 2', 2);

insert into attachments(name, item_id) values('Набор документов 1', 1);
insert into attachments(name, item_id) values('Набор документов 2', 2);

insert into roles_rights(role_id, rights_id) values(1, 1);
insert into roles_rights(role_id, rights_id) values(2, 2);
