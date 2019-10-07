/*Example of sql script*/
insert into clients (id, first_name, last_name, email, create_at, picture) values (1, 'Ramon','Betancor','ramon.betancor@gmail.net','2019-07-30','');
insert into clients (id, first_name, last_name, email, create_at, picture) values (2, 'Bruce','Banner','bruce.banner@gmail.net','1978-07-29','');
insert into clients (first_name, last_name, email, create_at, picture) values ('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');  
insert into clients (first_name, last_name, email, create_at, picture) values ('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', ''); 
insert into clients (first_name, last_name, email, create_at, picture) values ('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '');
insert into clients (first_name, last_name, email, create_at, picture) values ('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '');

insert into products (name, price, picture, create_at) values ('Panasonic Pantalla LCD', 259, '', '2017-08-25');
insert into products (name, price, picture, create_at) values ('Sony Camara digital DSC-W320B', 1234,'', '2015-07-29');
insert into products (name, price, picture, create_at) values ('Apple iPod shuffle', 149,'', '2010-02-28');
insert into products (name, price, picture, create_at) values ('Sony Notebook Z110', 379,'', '2016-09-19');
insert into products (name, price, picture, create_at) values ('Hewlett Packard Multifuncional F2280', 69,'', '2018-12-11');
insert into products (name, price, picture, create_at) values ('Bianchi Bicicleta Aro 26', 699,'', '1978-07-29');
insert into products (name, price, picture, create_at) values ('Mica Comoda 5 Cajones', 29,'', '2015-01-14');

insert into invoices (description, observation, client_id, create_at) values ('Factura equipos de oficina', null, 1, '2018-09-19');
insert into items_invoice (quantity, invoice_id, product_id) values (1, 1, 1);
insert into items_invoice (quantity, invoice_id, product_id) values (2, 1, 4);
insert into items_invoice (quantity, invoice_id, product_id) values (1, 1, 5);
insert into items_invoice (quantity, invoice_id, product_id) values (2, 1, 7);

insert into invoices (description, observation, client_id, create_at) values ('Factura Bicicleta', 'Alguna nota importante!', 1, '2019-06-19');
insert into items_invoice (quantity, invoice_id, product_id) values (3, 2, 6);