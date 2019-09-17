#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    setvbuf(stdout, NULL, _IONBF, 0);
    setvbuf(stdin, NULL, _IONBF, 0);
    setvbuf(stderr, NULL, _IONBF, 0);

    char buffer[0x20];

    puts("What are u doing with your life? Are you done?");
    puts("Aren't you tired of looking at a screen all day?");
    fgets(buffer, 0x10, stdin);

    puts("Fine, last one. You're going to play outside with your friends after this.");
    puts("Understood?");
    fgets(buffer, 0x200, stdin);
}

