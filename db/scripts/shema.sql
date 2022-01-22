create table if not exists pages(
    id serial primary key,
    url text
);

create table if not exists words(
    id serial primary key,
    word text,
    count int,
    page_id int references pages(id)
);

create table statistica(
    id serial primary key,
    word text,
    count int
);

delete from words;

delete from pages;