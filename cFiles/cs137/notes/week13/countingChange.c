#include <stdio.h>

//given unlimted coins of specified denominations, count the waysto make chane (order doesn't matter)
//ex 5, 10, 5 is the same as 5, 5, 10

int count_change(int coin[], int n, int amount){//array of coin types 5, 10, 25, 100, 200; // size of array // amount trying to get back
	//count ways with at least 1 coin n - 1, decrementing amount + cont ways without coin n - 1
	if (amount == 0) return 1;
	if (n == 0) return 0; //no coins left in array
	if (amount < 0) return 0;//invalid
	return count_change(coin, n, amount - coin[n - 1]) + count_change(coin, n - 1, amount);
}

int main(void){
	int coin[] = {5, 10, 25, 100, 200};
	const int n = sizeof(coin) / sizeof(coin[0]);
	printf("%d\n", count_change(coin, n , 20 ));
	printf("%d\n", count_change(coin, n , 200 ));
}