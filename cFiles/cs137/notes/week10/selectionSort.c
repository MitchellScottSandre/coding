    #include <stdio.h>

    void selection_sort (int a[], int n) {
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j<n; j++)
                if (a[j] < a[min]) min = j;

            int temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
    }

    void main (){
        int a[] = {-10,-7,2,14,11,38};
        int n = sizeof(a)/sizeof(a[0]);

        selection_sort(a,n);

        for (int i = 0; i < n; i++) {
            printf("%d, ", a[i]);
        }
        printf("\n");
    }