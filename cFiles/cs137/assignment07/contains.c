//#include <strlib.h>
#include <string.h>
#include <stdlib.h>
s
//counts number of times that t appears in s


int contains(char *s, char *t) {
    int wordCounter = 0;
    char *nullPointer = 0;
    if (s == nullPointer || t == nullPointer || strlen(t) > strlen(s) || strlen(t) == 0 || strlen(s) == 0) return nullPointer;
    int sLength = strlen(s), tLength = strlen(t);

    for (int i = 0; i <= sLength - tLength; i++) {
        if (s[i] == t[0]) { //begin complete comparison counter
            if (tLength == 1) wordCounter++;
            else {
                for (int j = 1; j < tLength; j++) {
                    if (s[i + j ] != t[j]) break;
                    if (j == tLength - 1) wordCounter++;
                }
            }
        }
    }
    return wordCounter;
}
