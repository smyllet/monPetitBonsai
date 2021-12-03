drop table if exists monPetitBonsai.repotting;
drop table if exists monPetitBonsai.pruning;
drop table if exists monPetitBonsai.watering;
drop table if exists monPetitBonsai.bonsai;
drop table if exists monPetitBonsai.owner;
drop schema if exists monPetitBonsai;

create schema monPetitBonsai;

create table monPetitBonsai.owner (
  id UUID primary key not null,
  name varchar(200) not null
);

create table monPetitBonsai.bonsai (
   id UUID primary key not null,
   name varchar(200) not null,
   species varchar(200),
   acquisition_date date,
   acquisition_age int,
   owner_id UUID,
   status varchar(50),
   foreign key (owner_id) references monPetitBonsai.owner(id)
);

create table monPetitBonsai.watering (
    id UUID primary key not null,
    datetime timestamp not null,
    bonsai_id UUID not null,
    foreign key (bonsai_id) references monPetitBonsai.bonsai(id)
);

create table monPetitBonsai.pruning (
     id UUID primary key not null,
     datetime timestamp not null,
     bonsai_id UUID not null,
     foreign key (bonsai_id) references monPetitBonsai.bonsai(id)
);

create table monPetitBonsai.repotting (
      id UUID primary key not null,
      datetime timestamp not null,
      bonsai_id UUID not null,
      foreign key (bonsai_id) references monPetitBonsai.bonsai(id)
);

insert into monPetitBonsai.owner(id, name) values ('a3387036-4946-11ec-81d3-0242ac130003', 'Bryan'), ('ae1f28b4-4946-11ec-81d3-0242ac130003', 'Nahoufane');

insert into monPetitBonsai.bonsai(id, name, species, acquisition_date, acquisition_age, owner_id, status) values
    ('63696432-01a2-4b43-b162-47b4f1e6062a', 'Mathilde', 'Jade tree', '10-06-2021', 25, 'a3387036-4946-11ec-81d3-0242ac130003', 'ALIVE'),
    ('21ea2a5a-a7d0-436f-b40b-a369f00748f6', 'Théodore', 'Poivrier du japon', '02-07-2008', 9, 'ae1f28b4-4946-11ec-81d3-0242ac130003', 'ALIVE');

insert into monPetitBonsai.watering(id, datetime, bonsai_id) VALUES
    ('7e46fa0c-4948-11ec-81d3-0242ac130003', '2021-11-02 13:32:00', '63696432-01a2-4b43-b162-47b4f1e6062a'),
    ('7e46fc96-4948-11ec-81d3-0242ac130003', '2021-11-08 15:44:00', '63696432-01a2-4b43-b162-47b4f1e6062a'),
    ('7e46fed0-4948-11ec-81d3-0242ac130003', '2021-11-15 05:54:00', '63696432-01a2-4b43-b162-47b4f1e6062a'),
    ('7e46fff2-4948-11ec-81d3-0242ac130003', '2021-11-18 03:31:00', '21ea2a5a-a7d0-436f-b40b-a369f00748f6'),
    ('7e46fe08-4948-11ec-81d3-0242ac130003', '2021-11-08 22:19:00', '21ea2a5a-a7d0-436f-b40b-a369f00748f6');

insert into monPetitBonsai.pruning(id, datetime, bonsai_id) VALUES
    ('f9be6bce-4949-11ec-81d3-0242ac130003', '2021-11-18 03:40:00', '63696432-01a2-4b43-b162-47b4f1e6062a');

insert into monPetitBonsai.repotting(id, datetime, bonsai_id) VALUES
    ('b269ca7e-494a-11ec-81d3-0242ac130003', '2021-11-15 06:01:00', '21ea2a5a-a7d0-436f-b40b-a369f00748f6');