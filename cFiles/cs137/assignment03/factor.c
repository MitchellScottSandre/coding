#include <stdbool.h>
#include <stdio.h>
bool isPrime(int num) {
    int divisor = 2;
    if (num <= 1) return false;
    while (num / divisor >= divisor) {
        if (num % divisor == 0) return false;
        divisor++;
    }
    return true;
}

int nextLargestPrimeNumber(int x) {
    //printf("\nincoming prime number is %d\n", x);
    int i = x + 1;
    int nextPrime;
    while (true) {
        if (isPrime(i) == true) {
            return i;
        }
        i++;
    }
    return -1;
}

void factor (int number) {
    printf("%d = ", number);
    int nextPrime = 2;
    if (isPrime(number) == true) {
        printf("%d", number);
    } else {
        while (number > 1) {
            if (number % nextPrime == 0) { //evenly divisible by the next prime
                printf("%d", nextPrime);
                number /= nextPrime;
                if (number > 1) printf("*");
            } else {
                nextPrime = nextLargestPrimeNumber(nextPrime);
            }
        }

    }
    printf("\n");
}
