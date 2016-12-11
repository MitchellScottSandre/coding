#include <string.h>
#include <stdlib.h>
#include <stdio.h>
char *alphabetic (const char *s) {
    char *nullPointer = 0;
    char *emptyString = malloc(sizeof(char));
    if (s == nullPointer) return nullPointer; // null pointer case
    if (strlen(s) == 0) return emptyString;
    int sLength = strlen(s);
    //get length of new string
    int alphabeticCharsCounter = 0;
    for (int i = 0; i < sLength; i++) {
        if ( (s[i] >= 65 && s[i] <= 90 ) || (s[i] >= 97 && s[i] <= 122) ) { //good char
            alphabeticCharsCounter++;
        }
    }
    char *newString = malloc (sizeof(char) * alphabeticCharsCounter + 1);
    int j = 0;
    for (int i = 0; i < sLength; i++ ) {
        if ( (s[i] >= 65 && s[i] <= 90 ) || (s[i] >= 97 && s[i] <= 122) ) { //good char
            newString[j] = s[i];
            j++;
        }


    }

    return newString;
}

//no alphabetic characters, return an empty string
//if s is a null pointer, return a null pointer

//char *strcat(char *dest, const char *src)
