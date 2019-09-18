#!/usr/bin/env python2
from pwn import *

host = "csgames-quals.frigon.app"
port = 8101
binary_path = "./palindrome.py"

context.log_level = logging.ERROR

def get_process():
    if args["REMOTE"]:
        return remote(host, port)
    else:
        return process(binary_path)

p = get_process()
p.recvuntil("The charset is [a-z]\n")

def printOrExit(s):
    print(s)
    if "flag" in s:
        exit()

def solve(question):
    s = set()

    for c in question:
        if c not in s:
            s.add(c)
        else:
            s.remove(c)

    return len(s) == 0 or len(s) == 1

while True:
    question = p.recvline(keepends=False)
    printOrExit(question)

    answer = solve(question)

    p.sendline(str(answer))
    p.recvline()

