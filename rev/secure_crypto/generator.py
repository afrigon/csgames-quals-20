#!/usr/bin/env python2
from random import randrange, choice
from pwn import xor
import string

a_len = 24
key_len = 5

a = [ randrange(0xff) for _ in range(a_len) ]
a = map(chr, a)
a = "".join(a)

key = [ choice(string.ascii_lowercase) for _ in range(key_len) ]
key = "".join(key)

b = xor(a, key)

print("char a[] = { " + ", ".join(map(hex, map(ord, list(a)))) + ", 0 };")
print("char b[] = { " + ", ".join(map(hex, map(ord, list(b)))) + ", 0 };")
print("// key = " + key)

