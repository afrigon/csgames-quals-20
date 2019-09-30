FROM ubuntu:18.04
MAINTAINER xehos <xehos@frigon.app>

RUN apt-get update && apt-get install -y socat

RUN useradd -ms /bin/sh challenge
WORKDIR /home/challenge

COPY ./vmware .
COPY ./flag .

RUN chown -R root:challenge /home/challenge && \
    chmod 750 /home/challenge && \
    chmod 550 /home/challenge/vmware && \
    chmod 440 /home/challenge/flag

EXPOSE 8200

CMD ["socat", "-T60", "TCP-LISTEN:8200,reuseaddr,fork,su=challenge","EXEC:/home/challenge/vmware"]
