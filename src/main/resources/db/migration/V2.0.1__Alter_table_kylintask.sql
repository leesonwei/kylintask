drop table if exists tb_kylin_task;

create table tb_kylin_task(
id Int(16),
task_name varchar(64),
cube BLOB,
segment varchar(256),
build_type varchar(16),
startTime varchar(32),
end_time varchar(32),
cron varchar(32),
is_full_build BOOLEAN(2),
is_resume BOOLEAN(2),
resume_times Int(2),
is_limit BOOLEAN(2),
task_start TIMESTAMP,
task_end TIMESTAMP,
status varchar(16),
is_done BOOLEAN(2)
);

drop table if exists tb_kylin_info;

create table tb_kylin_info(
id Int(16),
protocol varchar(10),
host varchar(32),
port Int(8),
username varchar(16),
password varchar(16),
isConnect boolean
);