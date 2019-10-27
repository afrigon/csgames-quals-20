FROM ubuntu:18.04
MAINTAINER xehos <xehos@frigon.app>

RUN apt-get update && apt-get install -y socat

RUN useradd -ms /bin/sh challenge
WORKDIR /home/challenge

COPY ./secure_crypto .
COPY ./flag .

RUN chown -R root:challenge /home/challenge && \
    chmod 750 /home/challenge && \
    chmod 550 /home/challenge/secure_crypto && \
    chmod 440 /home/challenge/flag

EXPOSE 8201

CMD ["socat", "-T60", "TCP-LISTEN:8201,reuseaddr,fork,su=challenge","EXEC:/home/challenge/secure_crypto"]
