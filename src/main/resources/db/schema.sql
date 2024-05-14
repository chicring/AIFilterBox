create table IF NOT EXISTS ai_message
(
    id            INTEGER
        primary key autoincrement,
    message_id    TEXT                not null,
    title         TEXT,
    description   TEXT,
    pic_url       TEXT,
    owner_name    TEXT,
    owner_pic_url TEXT,
    type_name     TEXT,
    platform_name TEXT,
    time          TEXT,
    url           TEXT,
    create_time   TimeStamp default (datetime('now', 'localtime')),
    update_time   TimeStamp default (datetime('now', 'localtime')),
    used          integer   default 0 not null
);

create table IF NOT EXISTS ai_option
(
    id          INTEGER
        primary key autoincrement,
    key         text not null
        unique,
    value       text,
    create_time TimeStamp default (datetime('now', 'localtime')),
    update_time TimeStamp default (datetime('now', 'localtime'))
);

create table IF NOT EXISTS ai_subtask
(
    id               INTEGER
        primary key autoincrement,
    name             TEXT                        not null,
    cron_expression  TEXT                        not null,
    enable           INTEGER   default 1         not null,
    type             TEXT      default 'default' not null,
    request_header   TEXT,
    Keyword_matching TEXT,
    keyword_blocking TEXT,
    pattern          TEXT,
    action           TEXT      default 'push'    not null,
    prefix           TEXT,
    task_id          INTEGER                     not null,
    create_time      TIMESTAMP default (datetime('now', 'localtime')),
    update_time      TIMESTAMP default (datetime('now', 'localtime')),
    url              TEXT      default ''        not null,
    domain           TEXT,
    push_type        TEXT      default 'mail'    not null,
    prompt           TEXT      default ''        not null
);

create table IF NOT EXISTS ai_task
(
    id          INTEGER
        primary key autoincrement,
    name        TEXT                 not null,
    description TEXT      default '' not null,
    enable      INTEGER   default 1  not null,
    create_time TIMESTAMP default (datetime('now', 'localtime')),
    update_time TIMESTAMP default (datetime('now', 'localtime'))
);


