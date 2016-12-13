
#include <stdio.h>

    int *largest(int a[], int n) {
        int m = 0;
        for (int i = 1; i < n; i++) {
            if (a[i]>a[m]) m = i;
        }
        return a + m;   // or return &(a[m]);
    }

    void main () {
        int test[] = {0,-11,2,3,4,3,2,1,0};
        int *p = largest(test, sizeof(test)/sizeof(test[0]));
        printf("%d\n", test[*p]);
    }
