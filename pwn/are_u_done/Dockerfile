FROM ubuntu:18.04 AS builder
RUN apt-get update && apt-get install -y build-essential make
WORKDIR /tmp
COPY Makefile .
COPY ./*.c ./
RUN make

FROM ubuntu:18.04
MAINTAINER xehos <xehos@frigon.app>

RUN apt-get update && apt-get install -y socat

RUN useradd -ms /bin/sh challenge
WORKDIR /home/challenge

COPY --from=builder /tmp/are_u_done .
COPY ./flag .

RUN chown -R root:challenge /home/challenge && \
    chmod 750 /home/challenge && \
    chmod 550 /home/challenge/are_u_done && \
    chmod 440 /home/challenge/flag

EXPOSE 8002

CMD ["socat", "-T60", "TCP-LISTEN:8002,reuseaddr,fork,su=challenge","EXEC:/home/challenge/are_u_done"]
