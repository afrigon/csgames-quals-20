#!/usr/bin/env python3
import signal
import random
import string

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
    s = set()

    for c in question:
        if c not in s:
            s.add(c)
        else:
            s.remove(c)

    return len(s) == 0 or len(s) == 1

def parse(got):
    if got == "True":
        return True
    elif got == "False":
        return False
    else:
        print("Invalid input! format is \"{}\"".format(FORMAT))
        exit()

def validate(expected, got):
    return expected == got

QUESTION_COUNT = 100
MAX_WORD_LENGTH = 12
TIMEOUT = 5
FORMAT = "True|False"

palindromes = ["tattarrattat", "aibohphobia", "detartrated", "kinnikinnik", "aoxomoxoa", "malayalam", "deleveled", "evittive", "redivider", "releveler", "rotavator", "adinida", "deified", "hadedah", "murdrum", "nauruan", "peeweep", "racecar", "reifier", "repaper", "reviver", "rotator", "seities", "sememes", "senones", "soosoos", "degged", "denned", "hallah", "mallam", "marram", "redder", "renner", "revver", "selles", "sesses", "succus", "terret", "tirrit"]

signal.signal(signal.SIGALRM, timeout)

print("Alright, for this one I want you to tell me if the string is a permutation of a palindrome. U know, words like anna and civic but shuffled.")
print("The charset is [a-z]")

for i in range(QUESTION_COUNT):
    expected = random.random() > 0.5
    
    question = ""
    if expected:
        question = list(random.choice(palindromes))
        random.shuffle(question)
        question = "".join(question)
    else:
        word_len = int(random.random() * MAX_WORD_LENGTH + 1)
        question = ''.join(random.choices(string.ascii_lowercase, k=word_len))
    
    expected = solve(question)
    print(question)

    signal.alarm(TIMEOUT)
    got = input()
    signal.alarm(0)

    got = parse(got)

    if not validate(expected, got):
        fail()

    print("GOOD")

win()

