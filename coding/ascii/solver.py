#!/usr/bin/env python2
from pwn import *

host = "csgames-quals.frigon.app"
port = 8102
binary_path = "./ascii.py"

context.log_level = logging.ERROR

def get_process():
    if args["REMOTE"]:
        return remote(host, port)
    else:
        return process(binary_path)

p = get_process()
p.recvuntil("The format sucks I know :)\n")

chars = {
    "###\n# #\n# #\n# #\n###": 0,
    "  #\n  #\n  #\n  #\n  #": 1,
    "###\n  #\n###\n#  \n###": 2,
    "###\n  #\n###\n  #\n###": 3,
    "# #\n# #\n###\n  #\n  #": 4,
    "###\n#  \n###\n  #\n###": 5,
    "###\n#  \n###\n# #\n###": 6,
    "###\n  #\n  #\n  #\n  #": 7,
    "###\n# #\n###\n# #\n###": 8,
    "###\n# #\n###\n  #\n  #": 9
}

def solve(lines):
    # partition lines into chunks of 4 chars
    lines = map(lambda n: zip(*(iter(n[1:]),) * 4), lines)

    # switch lines array into digits array
    digits = [ [ line[i] for line in lines ] for i in range(len(lines[0])) ]

    # join digits chars into a clean string
    digits = map(lambda n: "\n".join(map(lambda m: "".join(m[:-1]), n)), digits)

    # the number of digits in the number
    digits_len = len(digits)

    # resolve digit strings to actual number
    digits = map(lambda n: chars.get(n, 0), digits)

    # decimal place adjustment
    digits = [ digits[i] * pow(10, digits_len - 1 - i) for i in range(digits_len) ]

    return sum(digits)

while True:
    separator = p.recvline()
    if "flag" in separator:
        print(separator)
        exit()

    lines = [ p.recvline(keepends=False) for _ in range(5) ]
    print("\n".join(lines))

    answer = solve(lines)
    print(answer)

    p.sendlineafter("\n", str(answer))
    p.recvline()

