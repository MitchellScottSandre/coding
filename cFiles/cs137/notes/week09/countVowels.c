#include <stdio.h>

int vowels (const char *s){
	char vowel[] = {'a', 'e', 'i', 'o', 'u'};
	int count = 0;
	for ( ; *s; s++){
		for (int i = 0; i < 5; i++){
			if ( (*s | '\x20') == vowel[i]){//you shuld do #include <ctype.h> tolower(*s) instead
				count++;
				break;
			}

		}
	}
	return count;
}

int main (void){
	char *hi = "Hello" " wOrld";
	printf("\" %s \" contains %d vowels\n", hi, vowels(hi));

return 0;
}
