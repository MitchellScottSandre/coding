#include <stdlib.h>
#include <stdio.h>
#include <string.h>

char *concat(const char *s0, const char *s1) {
    char *s = (char*)malloc((strlen(s0) + strlen(s1)) + 1);
    // fine since sizeof(char) is just 1
    // extra 1 is there for the `\0`
    strcpy(s,s0);
    strcat(s,s1);
    return s;
}

void main() {
    char *hi = concat("hello", "world");
    printf("%s\n", hi);

    free(hi);
}
