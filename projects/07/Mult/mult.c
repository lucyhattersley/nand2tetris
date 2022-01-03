#include <stdio.h>

int main(int x, int y) {
    int sum;
    sum = 0;
    for(int j = y; j != 0; j--) {
        sum += x;
    }
    printf("%d", sum);  

    return 0;
}