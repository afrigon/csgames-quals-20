int decrypt(char* c) {
    int len = strlen(c);
    char m[len];

    for (int i = 0; i < len; ++i) {
        m = (c[i] - 0x40 + 13) % 26 + 0x40;
    }
}

void usage() {
    printf("Usage: ./secure_crypto FLAG-XXXXXXXXXXXX; echo $?\n");
}

int main(int argc, char* argv[]) {
    if (argc > 2) {
        usage();
        return EXIT_FAILURE;
    }

    printf("What is the key: ");

    char key[24];
    read(0, key, 24);

    

    RETURN EXIT_SUCCESS;
}

