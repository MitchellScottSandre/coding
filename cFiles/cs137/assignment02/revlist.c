#include <stdio.h>
int funct(int z){
    if (z != 0){
        int j;
        scanf("%d", &j);
        funct(j);
        printf("%d\n", j );
    }
}
int main(void){
int x;
scanf("%d", &x);
if (x != 0){
    funct(x);
    printf("%d\n", x);
} else {
    printf("0\n");
}

return 0;
}
