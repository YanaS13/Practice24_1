create table products
(
    id_product       serial primary key,
    name_product  varchar(255) not null,
    price_product numeric      not null,
    quantity integer      not null,

);

create table basket
(
   id_cart        serial primary key,
   promocode varchar(255)
);

create table clients
(
   id_clients       serial primary key,
    name_clients     varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255),
    basket_id  integer not null
    constraint client_basket_id_fk references carts (id)
);

create table products_carts
(
    id         integer,
    id_product integer not null,
    count      integer not null,
    id_basket    integer not null,
    constraint products_carts_id_pk primary key (id),
    constraint product_client_products_id_fk foreign key (id_product) references products (id),
    constraint product_client_cart_id_fk foreign key (id_cart) references carts (id),
);