

int validate(char* password) {
    
}

int flag() {
    char flag[100];

    int fd = open("./flag");
    read(fd, flag, 90);
    close(fd);

    printf("%s", flag);
    return 0;
}

void usage() {
    printf("Usage: ./secure_crypto key\n");
}

int main(int argc, char* argv[]) {
    if (argc > 2) {
        usage();
        return EXIT_FAILURE;
    }

    printf("What is the key: ");

    char key[24];
    read(0, key, 24);
    
    if (validate(password)) {
        return flag();
    }

    return EXIT_FAILURE;
}

