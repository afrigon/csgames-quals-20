FROM ubuntu:18.04
MAINTAINER xehos <xehos@frigon.app>

RUN apt-get update && apt-get install -y socat python3

RUN useradd -ms /bin/sh challenge
WORKDIR /home/challenge

COPY ./ascii.py .
COPY ./flag .

RUN chown -R root:challenge /home/challenge && \
    chmod 750 /home/challenge && \
    chmod 550 /home/challenge/*.py && \
    chmod 440 /home/challenge/flag

EXPOSE 8102

CMD ["socat", "-T60", "TCP-LISTEN:8102,reuseaddr,fork,su=challenge","EXEC:/home/challenge/ascii.py"]
