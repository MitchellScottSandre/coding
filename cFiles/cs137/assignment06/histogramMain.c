#include <stdio.h>
#include <stdlib.h>

int *histogram (const int *a, int n, int *m);

int main (void)
{
    int a[] = {1, 2, 3, 3, 3, 2, 1, 4, 5, 6, 0, -100};
    int *h, m, i;

    h = histogram (a, sizeof(a)/sizeof(a[0]), &m);

    if (h)
    {
        for (i = 0; i < m; i++)
            printf ("%d\n", h[i]);
        free (h);
    }

    //test cases: size of n is 0, all negatives, all 0s, size is 1
    printf("test case: normal, some negatives, 0s, positives, gaps between them, and a large number (20)\n");
    int b[] = {-2, -2, -3, 3, 3, 20, 1, 0, 0, 0, -55, 4, 5, 6, 0, -5, 5, 21, 5 ,5, 6, 6, 7, 7, 11};
    //int *h, m, i;

    h = histogram (b, sizeof(b)/sizeof(b[0]), &m);

    if (h)
    {
        for (i = 0; i < m; i++)
            printf ("%d\n", h[i]);
        free (h);
    }

    printf("test case: all negatives\n");
    int c[] = {-2, -5, -6, -5, -2, -2, -1};
    //int *h, m, i;

    h = histogram (c, sizeof(c)/sizeof(c[0]), &m);

    if (h)
    {
        for (i = 0; i < m; i++)
            printf ("%d\n", h[i]);
        free (h);
    }

    printf("test case: size is 0\n");
    int d[] = {};
    //int *h, m, i;

    h = histogram (d, sizeof(d)/sizeof(d[0]), &m);

    if (h)
    {
        for (i = 0; i < m; i++)
            printf ("%d\n", h[i]);
        free (h);
    }
    return 0;
}
