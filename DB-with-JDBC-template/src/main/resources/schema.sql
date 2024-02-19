create table it_projects (
project_id number not null,
project_name varchar2(255) not null,
start_date date not null,
target_end_date date not null,
actual_end_date date,
created_on date not null,
created_by varchar2(255) not null,
modified_on date,
modified_by varchar2(255)
);

alter table it_projects
add constraint it_projects_pk
primary key (project_id);

alter table it_projects
add constraint it_projects_uk
unique (project_name);