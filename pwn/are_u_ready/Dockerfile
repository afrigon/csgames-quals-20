FROM ubuntu:18.04
MAINTAINER xehos <xehos@frigon.app>

RUN apt-get update && apt-get install -y socat

RUN useradd -ms /bin/sh challenge
WORKDIR /home/challenge

COPY ./are_u_ready .
COPY ./flag .

RUN chown -R root:challenge /home/challenge && \
    chmod 750 /home/challenge && \
    chmod 550 /home/challenge/are_u_ready && \
    chmod 440 /home/challenge/flag

EXPOSE 8000

CMD ["socat", "-T60", "TCP-LISTEN:8000,reuseaddr,fork,su=challenge","EXEC:/home/challenge/are_u_ready"]
