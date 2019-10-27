FROM mysql:5.7.18

ADD setup.sql /docker-entrypoint-initdb.d/setup.sql
ADD my.cnf /etc/mysql/conf.d/my.cnf

CMD ["mysqld", "--user=root"]
