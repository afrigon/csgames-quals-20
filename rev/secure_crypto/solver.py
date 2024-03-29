#!/usr/bin/env python2
from pwn import *

host = "csgames-quals.frigon.app"
port = 8201
binary_path = "./secure_crypto"

context.log_level = logging.ERROR

def get_process():
    if args["REMOTE"]:
        return remote(host, port)
    else:
        return process(binary_path)

p = get_process()

a = [0x3d, 0xc9, 0xae, 0xeb, 0xb0, 0xb6, 0x7f, 0xd2, 0x36, 0xd1, 0x86, 0xcf, 0xaa, 0x8e, 0xc9, 0x14, 0x73, 0xb9, 0x2, 0x62, 0x3c, 0x18, 0x74, 0x23]
b = [0x4d, 0xb9, 0xc6, 0x87, 0xd8, 0xc6, 0xf, 0xba, 0x5a, 0xb9, 0xf6, 0xbf, 0xc2, 0xe2, 0xa1, 0x64, 0x3, 0xd1, 0x6e, 0xa, 0x4c, 0x68, 0x1c, 0x4f]

a = "".join(map(chr, a))
b = "".join(map(chr, b))

key = xor(a, b)
payload = key[0:5]

p.sendline(payload)
p.interactive()

