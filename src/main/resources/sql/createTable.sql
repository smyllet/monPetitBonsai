drop table if exists monPetitBonsai.bonsai;
drop schema if exists monPetitBonsai;

create schema monPetitBonsai;

create table monPetitBonsai.bonsai (
   id UUID,
   name varchar(200),
   species varchar(200),
   acquisition_date varchar(50),
   acquisition_age int,
   owner_id UUID,
   status varchar(50)
);