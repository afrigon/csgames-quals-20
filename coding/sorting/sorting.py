#!/usr/bin/env python3
import signal
import random

def win():
    f = open("./flag")
    flag = f.read()
    print(flag)

def fail():
    print("WRONG")
    exit()

def timeout(signum, frame):
    print("TOO SLOW")
    exit()

def solve(question):
    return sorted(question)

def parse(got):
    try:
        return list(map(int, got))
    except:
        print("Invalid input! format is \"{}\"".format(FORMAT))
        exit()

def validate(expected, got):
    return expected == got

QUESTION_COUNT = 100
TIMEOUT = 5
FORMAT = "1, 2, 3, 4"

signal.signal(signal.SIGALRM, timeout)

print("Greetings young one, welcome to the coding challenge!")
print("For this first round, you simply have to sort these numbers for me.")

for i in range(QUESTION_COUNT):
    question_length = int(random.random() * 100) + 1
    question = [ int(random.random() * 100) for _ in range(question_length) ]
    
    expected = solve(question)

    print(", ".join(map(str, question)))

    signal.alarm(TIMEOUT)
    got = input().split(", ")
    signal.alarm(0)

    got = parse(got)

    if not validate(expected, got):
        fail()

    print("GOOD")

win()

