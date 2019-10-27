FROM php:7.0-apache

RUN apt-get update -y
RUN apt-get install python2.7 python-pip -y
RUN docker-php-ext-install mysqli

WORKDIR /var/www/html
COPY ./ ./

RUN chmod 0644 *

ENV PORT 5000
RUN sed -i "s/80/$PORT/g" /etc/apache2/sites-available/000-default.conf /etc/apache2/ports.conf

RUN useradd -ms /bin/bash challenge
USER challenge

ENTRYPOINT []
CMD docker-php-entrypoint apache2-foreground

