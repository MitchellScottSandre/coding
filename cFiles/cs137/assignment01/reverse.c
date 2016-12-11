#include <stdio.h>
int main(void){
    int x;
    scanf("%d", &x);
    if (x == 0) {
        printf("0\n");
    } else if (x < 0) {
        printf("-");
        x=-x;
    }
    while (x >= 1) {
        printf("%d",x%10);
        x/=10;
    }
    printf("\n");
    return 0;
}
