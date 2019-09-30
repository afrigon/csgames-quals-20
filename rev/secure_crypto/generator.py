#!/usr/bin/env python2
from random import randrange
from pwn import xor

f = open("./flag")
flag = f.read()[:-1]

key = [ randrange(0xff) for _ in range(len(flag)) ]
key = map(chr, key)

key = "".join(key)

m = xor(flag, key)

print("char m[] = { " + ", ".join(map(hex, map(ord, list(m)))) + " };")
print("char key[{}];".format(len(m)))
print("read(0, key, {});".format(len(m)))

