create table if not exists intensity_record (
   `id`          long primary key not null,
   `open_time`   varchar not null,
   `close_time`  varchar not null,
   `time`        real not null,
   `intensity`   float null,
   `create_time` timestamp null default null comment,
   `update_time` timestamp null default null comment
) ;
