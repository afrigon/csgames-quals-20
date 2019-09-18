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

def parse(got):
    try:
        return int(got)
    except:
        print("Invalid input! format is \"{}\"".format(FORMAT))
        exit()

def validate(expected, got):
    return expected == got

chars = ["""
###
# #
# #
# #
###
""",
"""
  #
  #
  #
  #
  #
""",
"""
###
  #
###
#
###
""",
"""
###
  #
###
  #
###
""",
"""
# #
# #
###
  #
  #
""",
"""
###
#
###
  #
###
""",
"""
###
#  
###
# #
###
""",
"""
###
  #
  #
  # 
  #
""",
"""
###
# #
###
# #
###
""",
"""
###
# #
###
  #
  #
"""]

def encode(number):
    items = list(str(number))
    s = " "
    for i in range(5):
        s += "".join([ chars[int(c)].split("\n")[i+1].ljust(4, " ") for c in items ])
        s += "\n "
    return s

QUESTION_COUNT = 100
MAX_NUMBER = 1500
TIMEOUT = 5
FORMAT = "123"

signal.signal(signal.SIGALRM, timeout)

print("I hope u like ascii art. You'll need to tell me what number this is. The format sucks I know :)")

for i in range(QUESTION_COUNT):
    expected = int(random.random() * MAX_NUMBER)

    question = encode(expected)

    print("----")
    print(question)

    signal.alarm(TIMEOUT)
    got = input()
    signal.alarm(0)

    got = parse(got)

    if not validate(expected, got):
        fail()

    print("GOOD")

win()

