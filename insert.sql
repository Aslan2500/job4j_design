insert into roles (name) values ('role1');
insert into users (name, roles_id) values ('name1', 1);
insert into rules (name) values ('rule1');
insert into roles_rules (roles_id, rules_id) values (1, 1);
insert into category (name) values ('category1');
insert into state (name) values ('state1');
insert into item (name, users_id, category_id, state_id) values ('item1', 1, 1, 1);
insert into comments (name, item_id) values ('comment1', 1);
insert into attachs (name, item_id) values ('attach1', 1);