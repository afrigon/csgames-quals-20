rax = [0x30]
rbx = [0x31]
rcx = [0x32]
rdx = [0x33]

inc = [0xfd]
movi = [0xdd]
mov = [0xde]
push = [0xaa]
pop = [0xab]
dst = [0xff]
hlt = [0xfe]
syscall = [0x69]

sys_load_flag = 0x34
sys_echo = 0x61

def imm(value):
    return [value & 0xff, value >> 8 & 0xff, value >> 16 & 0xff, value >> 24 & 0xff]

def compile(arr):
    return "".join([ chr(item) for sublist in arr for item in sublist ])
