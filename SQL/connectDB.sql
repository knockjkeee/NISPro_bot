
CREATE SERVER nispro1
    FOREIGN DATA WRAPPER mysql
    OPTIONS (USER 'user', HOST 'host', DATABASE 'db',Password 'password');

DROP SERVER nispro1;
SHOW STATUS ;
SELECT * FROM mysql.servers;

CREATE TABLE remote_tg_table (
                                 queue_name    varchar(100) not null,
                                 url           varchar(100) not null,
                                 password      varchar(100) not null,
                                 login         varchar(100) not null,
                                 id_telegram   varchar(100) not null,
                                 customer_user varchar(100) not null,
                                 company       varchar(100) not null,
                                 id            bigint auto_increment
                                     primary key
)
    ENGINE=FEDERATED
    CONNECTION='server/table';

show engines;
install plugin federated soname 'ha_federated.so';

# CREATE TABLE `remote_tg_table`(`A` VARCHAR(100),UNIQUE KEY(`A`(30))) ENGINE=FEDERATED
#                                                         CONNECTION='MYSQL://127.0.0.1:3306/TEST/T1';

# mysql://nispro:nispro@192.168.246.110:3306/nisproc1.telegram_bot_registration

