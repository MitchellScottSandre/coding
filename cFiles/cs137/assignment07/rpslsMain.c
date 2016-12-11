#include <stdio.h>

int rpsls(const char *player1, const char *player2);

int main (void)
{
  printf ("%d\n", rpsls("rock","paper"));
  printf ("%d\n", rpsls("rock","rock"));
  printf ("%d\n", rpsls("paper","rock"));
  printf ("%d\n", rpsls("lizard",(char*)0));
  printf ("%d\n", rpsls("Rock", "paper"));
  printf ("%d\n", rpsls("lizard","lizard"));
  printf ("%d\n", rpsls("Spock","lizard"));
  printf ("%d\n", rpsls("lizard","rock"));
  printf ("%d\n", rpsls("rock","paper"));
  printf ("%d\n", rpsls("paper","rock"));
  printf ("%d\n", rpsls("",""));


  return 0;
}
