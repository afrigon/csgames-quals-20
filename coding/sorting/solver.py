#!/usr/bin/env python2
from pwn import *

host = "csgames-quals.frigon.app"
port = 8100
binary_path = "./sorting.py"

context.log_level = logging.ERROR

def get_process():
    if args["REMOTE"]:
        return remote(host, port)
    else:
        return process(binary_path)

p = get_process()
p.recvuntil("For this first round, you simply have to sort these numbers for me.\n")

def printOrExit(s):
    print(s)
    if "flag" in s:
        exit()

def solve(question):
    return sorted(question)

while True:
    question = p.recvline()

    printOrExit(question)

    question = question.split(", ")
    question = map(int, question)
    
    answer = solve(question)

    answer = map(str, answer)
    answer = ", ".join(answer)

    p.sendline(answer)
    p.recvline()

