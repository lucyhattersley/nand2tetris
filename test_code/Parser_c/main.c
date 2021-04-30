// main.c
#include <stdio.h>

int main(int argc, char *argv[]) {
    printf("Arguments count: %d\n", argc);
    for (int i = 0; i < argc; i++) {
        printf("Argument %6d: %s\n", i, argv[i]);
    }
    return 0;
}