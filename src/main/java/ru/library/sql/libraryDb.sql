create TABLE Person(
                       id int GENERATED BY DEFAULT as identity PRIMARY KEY ,
                       name varchar(100) not null unique ,
                       age int not null
);
create TABLE Books(
                      id int generated by default as identity PRIMARY KEY ,
                      name varchar(100) not null ,
                      author varchar(100) not null ,
                      year int,
                      person_id int references Person(id) on DELETE SET NULL,
                      owned_at timestamp
)