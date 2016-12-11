#include "powers.h"

int square (int num){return num * num;}

int cube (int num){return num * square(num);}

int quartic (int num){return square(num) * square(num);}

int quintic (int num){return square(num) * cube(num);}
