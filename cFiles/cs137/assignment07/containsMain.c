#include <stdio.h>

int contains(char *s, char *t);

int main(void)
{
  printf ("%d\n", contains ("I wanna shoot something!", "thing"));
  printf ("%d\n", contains ("Let's get in range!", "ge"));
  printf ("%d\n", contains ("Wanna see the fireworks?", "wanna"));
  printf ("%d\n", contains ("Look at the pretty explosions!", " "));
  printf ("%d\n", contains ("Kaboom!", ""));


  // "(char *) 0" is simply another way to initialize a NULL pointer
  printf ("%d\n", contains ((char *) 0, "aaa"));
  printf ("%d\n", contains ("aaa", (char *) 0));
  printf ("%d\n", contains ((char *) 0, (char *) 0));

  printf("my test cases\n");
  printf ("%d\n", contains ("", ""));
  printf ("%d\n", contains ("       ", ""));
  printf ("%d\n", contains ("", "hey how is it going"));
  printf ("%d\n", contains ((char *) 0, ""));
  printf ("%d\n", contains ("", (char *) 0));
  printf ("%d\n", contains ("aaaaaaaa", "a"));
  printf ("%d\n", contains ("aaaaaaaa", "aa"));
  printf ("%d\n", contains ("aaaaaaaa", "aaaaaaaaaaaaaaaaa"));
  printf ("%d\n", contains ("  ,  ,  ,  , , , ,", "  "));
  printf ("%d\n", contains ("aaaaaaaa", "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"));

  return 0;
}
