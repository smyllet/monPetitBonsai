drop table if exists monPetitBonsai.bonsai;
drop schema if exists monPetitBonsai;

create schema monPetitBonsai;

create table monPetitBonsai.bonsai (
   id UUID primary key not null,
   name varchar(200) not null,
   species varchar(200),
   acquisition_date date,
   acquisition_age int,
   owner_id UUID,
   status varchar(50)
);

insert into monPetitBonsai.bonsai(id, name, species, acquisition_date, acquisition_age, status) values
    ('63696432-01a2-4b43-b162-47b4f1e6062a', 'Pépito', 'Jade tree', '10-06-2021', 25, 'alive'),
    ('21ea2a5a-a7d0-436f-b40b-a369f00748f6', 'Michel', 'Poivrier du japon', '02-07-2008', 9, 'alive')