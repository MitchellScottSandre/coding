#include <stdlib.h>
#include "event.h"
  /* Returns 1 if the specified event does not conflict with the 
     schedule; returns 0 otherwise. The value n specifies the
     size of the array containing the schedule.  */
int available (struct event schedule[], int n, struct event e){
    int minutesInDay[5000] = {0};
    //int minutesOfEvent[5000] = {0};
    int startM, endM;
    for (int i = 0; i < n; i++){//structs in schedule array
        startM = schedule[i].start.hour * 60 + schedule[i].start.minute;
        endM = schedule[i].end.hour * 60 + schedule[i].end.minute;
        for (int j = startM; j < endM; j++){
            minutesInDay[j] = 1;//1 means not free
        }
    }

    for (int i = e.start.hour * 60 + e.start.minute; i < e.end.hour * 60 + e.end.minute; i++){
    	if (minutesInDay[i] == 1) return 0; //conflict
    }
    return 1;
    //now compare the values between the two


}

int compare (const void *a, const void *b){
	const struct event *aCopy = a;
	const struct event *bCopy = b;
	int aVal = aCopy->start.hour * 60 + aCopy->start.minute;
	int bVal = bCopy->start.hour * 60 + bCopy->start.minute;
	if (aVal > bVal) return 1;
	if (aVal < bVal) return -1;
	return 0;

  /* Sorts the schedule (length n) in ascending order according to the start time. */
}
void sortSchedule (struct event schedule[], int n){
	qsort(schedule, n, sizeof(schedule[0]), compare);
}