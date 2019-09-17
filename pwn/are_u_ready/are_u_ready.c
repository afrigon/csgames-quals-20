#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void is_ready() {
    char buffer[50];
    puts("ARE YOU READY FOR THE CSGAMES ?!?!");
    fgets(buffer, 0x50, stdin);
}

void yes() {
    puts("Nice job!\n");
    execve("/bin/sh", 0, 0);
}

void no() {
    puts("Prove it.\n");
}

int main(int argc, char **argv) {
    setvbuf(stdout, NULL, _IONBF, 0);
    setvbuf(stdin, NULL, _IONBF, 0);
    setvbuf(stderr, NULL, _IONBF, 0);

    is_ready();
    no();
}

