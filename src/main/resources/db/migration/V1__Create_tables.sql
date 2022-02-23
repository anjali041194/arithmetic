create table equation (
                          id int not null,
                          expression varchar(100) not null,
                          result long not null,
                          date_created timestamp not null,
                          date_updated timestamp not null,
                          primary key (id)
);

create sequence equation_id_seq START WITH 1 INCREMENT BY 1;

create index equation_expression_idx on equation(expression);
create index equation_date_created_idx on equation(date_created);