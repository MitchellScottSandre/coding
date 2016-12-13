#include <stdio.h>

int main(){
  int i;
  int meatBalls[5] = {7,9,42,21,3};

  printf("Traversal using for loop:\n");
  printf("Element \t Address \t\t Value \n");
  for(i = 0; i < 5; i++){
    printf("meatBalls[%d]: \t %p \t %d \n", i, &meatBalls[i], meatBalls[i]);
  }

  //recall: array names are pointers to first element
  printf("\n meatBalls: \t \t %p \n", meatBalls);
  // dont need to use & sign for memory address of meatBalls since it is just a  pointer
  //to the first element of the array, so it's already a memory address

  //dereferencing
  printf("\n *meatBalls: \t \t %d \n", *meatBalls);
  //now you get 7 since it gets the value of it from dereferencing it

  printf("\n *(meatBalls + 2): \t %d \n \n", *(meatBalls+2));

  printf("Traversal using pointer arithmetic:\n");
  printf("Element \t Address \t\t Value \n");
  for(int *p = meatBalls; p < meatBalls + 5; p++) {
    printf("p: \t \t %p \t %d \n", p, *p);
  }

  printf("\nLargest element of meatBalls using pointer arithmetic:\n");
  int *max = meatBalls;
  for(int *p = meatBalls + 1; p < meatBalls + 5; p++) {
    if (*max < *p) max = p;
  }
  printf("*max == %d\n",*max);
}
