#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

char a[] = { 0x3d, 0xc9, 0xae, 0xeb, 0xb0, 0xb6, 0x7f, 0xd2, 0x36, 0xd1, 0x86, 0xcf, 0xaa, 0x8e, 0xc9, 0x14, 0x73, 0xb9, 0x2, 0x62, 0x3c, 0x18, 0x74, 0x23, 0 };
char b[] = { 0x4d, 0xb9, 0xc6, 0x87, 0xd8, 0xc6, 0xf, 0xba, 0x5a, 0xb9, 0xf6, 0xbf, 0xc2, 0xe2, 0xa1, 0x64, 0x3, 0xd1, 0x6e, 0xa, 0x4c, 0x68, 0x1c, 0x4f, 0 };
// key = pphlh

int validate(char* key) {
    int len = strlen(key);
    int a_len = strlen(a);

    if (!strlen) return 0;

    for (int i = 0; i < a_len; ++i) {
        if ((a[i] ^ key[i % len]) != b[i]) {
            return 0;
        }
    }

    return 1;
}

int flag() {
    char flag[100];

    FILE* fd = fopen("./flag", "r");
    fread(flag, 90, 1, fd);
    fclose(fd);

    printf("%s", flag);
    return 0;
}

void usage() {
    printf("Usage: ./secure_crypto\n");
}

int main(int argc, char* argv[]) {
    setvbuf(stdout, NULL, _IONBF, 0);
    setvbuf(stdin, NULL, _IONBF, 0);
    setvbuf(stderr, NULL, _IONBF, 0);

    if (argc > 1) {
        usage();
        return EXIT_FAILURE;
    }

    printf("What is the key: ");

    char key[24];
    int len = read(0, key, 23);

    if (len == 0) {
        printf("Invalid Input");
        return EXIT_FAILURE;
    }

    key[len-1] = 0;
    
    if (validate(key)) {
        return flag();
    }

    printf("WRONG");
    return EXIT_FAILURE;
}

