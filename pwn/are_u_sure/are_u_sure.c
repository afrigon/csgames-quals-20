#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void is_ready() {
    char name[0x30];
    char buffer[0x30];

    puts("Alright, you passed the first test, what's your name?");
    fgets(name, 0x20, stdin);
    int len = strlen(name);
    name[len-1] = 0;

    printf("Thanks %s, I've stored your name at %p if you're looking for it.\n", name, &name);

    puts("ARE YOU SURE YOU'RE UP FOR THE CHALLENGE ?!?!");
    fgets(buffer, 0x90, stdin);
}

void yes() {
    puts("Sike!\n");
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

