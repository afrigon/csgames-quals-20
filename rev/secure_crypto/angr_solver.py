#!/usr/bin/env python3
import angr, claripy
from pwn import *

host = "csgames-quals.frigon.app"
port = 8201
filepath = "./secure_crypto"

context.log_level = logging.ERROR

def get_process():
    if args["REMOTE"]:
        return remote(host, port)
    else:
        return process(filepath)

input_len = 6

payload = claripy.BVS("payload", input_len * 8)
proj = angr.Project(filepath, load_options={"auto_load_libs": False})

entry_state = proj.factory.entry_state(args=[filepath], stdin=payload)

simgr = proj.factory.simgr(entry_state)
simgr.run()

simgr.move(from_stash="deadended", to_stash="found", filter_func=lambda s: b"WRONG" not in s.posix.dumps(1))

i = simgr.one_found.solver.eval(payload, cast_to=bytes)

p = get_process()
p.sendline(i.decode())
p.interactive()

