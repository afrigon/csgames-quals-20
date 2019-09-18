FROM ubuntu:18.04
MAINTAINER xehos <xehos@frigon.app>

RUN apt-get update && apt-get install -y socat

RUN useradd -ms /bin/sh challenge
WORKDIR /home/challenge

COPY ./are_u_sure .
COPY ./flag .

RUN chown -R root:challenge /home/challenge && \
    chmod 750 /home/challenge && \
    chmod 550 /home/challenge/are_u_sure && \
    chmod 440 /home/challenge/flag

EXPOSE 8001

CMD ["socat", "-T60", "TCP-LISTEN:8001,reuseaddr,fork,su=challenge","EXEC:/home/challenge/are_u_sure"]
