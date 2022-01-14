drop table if exists monPetitBonsai.users;
drop table if exists monPetitBonsai.authorities;

create table monPetitBonsai.users (
    id uuid primary key,
    username varchar(200) unique,
    password varchar(1000),
    enabled boolean default true
);

create table monPetitBonsai.authorities (
    user_id uuid,
    authority varchar(200),
    foreign key (user_id) references monPetitBonsai.users(id)
)