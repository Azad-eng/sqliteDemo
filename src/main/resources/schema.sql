create table if not exists intensity_record (
    id INTEGER PRIMARY KEY,
    open_time   VARCHAR(30) NULL,
    close_time  VARCHAR(30) NULL,
    time        REAL NULL,
    intensity   FLOAT NULL,
    create_time TIMESTAMP NULL,
    update_time TIMESTAMP NULL
) ;
