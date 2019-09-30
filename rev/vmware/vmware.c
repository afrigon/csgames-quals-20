#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define BYTECODE_LENGTH 50
#define STACK_SIZE 0x100

u_int32_t registers[4];
u_int32_t pc = 0;
u_int32_t sp = 0;
u_int32_t stack[STACK_SIZE];

void dump_state() {
    printf("DUMPING DEBUG STATE\n");
    printf("RAX = 0x%08X\n", registers[0]);
    printf("RBX = 0x%08X\n", registers[1]);
    printf("RCX = 0x%08X\n", registers[2]);
    printf("RDX = 0x%08X\n", registers[3]);
    printf("PC  = 0x%08X\n", pc);
    printf("SP  = 0x%08X\n", sp);
    printf("\nSTACK\n");

    for (int i = sp; i >= 0; --i) {
        printf("0x%08X: 0x%08X\n", i * 4, stack[i]);
    }

    printf("\n");
}

void halt() {
    exit(EXIT_SUCCESS);
}

void sigill() {
    printf("SIGILL: illegal instruction at 0x%08X\n", pc);
    exit(EXIT_FAILURE);
}

void sigsegv() {
    printf("SIGSEGV: Illegal memory access\n");
    exit(EXIT_FAILURE);
}

u_int32_t* get_register(u_int8_t index) {
    int i = index - 0x30;
    if (i < 0 || i > 3) {
        sigill();
    }
    return &registers[i];
}

void push(u_int32_t value) {
    if (sp > STACK_SIZE) {
        sigsegv();
    }

    sp += 1;
    stack[sp] = value;
}

u_int32_t pop() {
    if (sp <= 0) {
        sigsegv();
    }
    
    u_int32_t value = stack[sp];
    sp -= 1;
    return value;
}

void inc(u_int32_t* reg) {
    *reg = *reg + 1;
}

void movi(u_int32_t* reg, u_int32_t value) {
    *reg = value;
}

void mov(u_int32_t* dst, u_int32_t* src) {
    *dst = *src;
}

void load_flag() {
    char flag[16];
    FILE* fd = fopen("./flag", "r");
    fread(flag, 14, 1, fd);
    push(*(u_int32_t *)(flag));
    push(*(u_int32_t *)(flag+4));
    push(*(u_int32_t *)(flag+8));
    push(*(u_int32_t *)(flag+12));
}

void echo() {
    printf("%s\n", (char *)stack + registers[1]);
}

void vm_syscall() {
    switch (registers[0]) {
        case 0x34:
            load_flag();
            break;
        case 0x61:
            echo();
            break;
    }
}

int run_bytecode(unsigned char* bytecode) {
    u_int32_t* dst;
    u_int32_t* src;
    u_int32_t value;

    while (pc < BYTECODE_LENGTH) {
        unsigned char opcode = bytecode[pc++];

        switch (opcode) {
            case 0xfd:
                dst = get_register(bytecode[pc++]);
                inc(dst);
                break;
            case 0xdd:
                dst = get_register(bytecode[pc++]);
                value = *(u_int32_t *)(bytecode + pc);
                movi(dst, value);
                pc += 4;
                break;
            case 0xde:
                dst = get_register(bytecode[pc++]);
                src = get_register(bytecode[pc++]);
                mov(dst, src);
                break;
            case 0xaa:
                src = get_register(bytecode[pc++]);
                push(*src);
                break;
            case 0xab:
                dst = get_register(bytecode[pc++]);
                *dst = pop();
                break;
            case 0x69:
                vm_syscall();
                break;
            case 0xff:
                dump_state();
                break;
            case 0xfe:
                halt();
            default:
                sigill();
        }
    }

    return EXIT_SUCCESS;
}

void usage() {
    printf("Usage: ./\n");
}

int main(int argc, char* argv[]) {
    if (argc > 1) {
        usage();
        return EXIT_FAILURE;
    } 

    unsigned char bytecode[BYTECODE_LENGTH];
    read(0, bytecode, BYTECODE_LENGTH);

    return run_bytecode(bytecode);
}

