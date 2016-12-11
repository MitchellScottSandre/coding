#include "event.h"
//start time is part of the event, end time is NOT part of it
//tod has hour, minute
//event has start, end
int freetime (struct event schedule[], int n, int hour, int min){
    int minutesInDay[5000] = {0};
    int startM, endM;

    for (int i = 0; i < n; i++){//structs in schedule array
            startM = schedule[i].start.hour * 60 + schedule[i].start.minute;
            endM = schedule[i].end.hour * 60 + schedule[i].end.minute;
            for (int j = startM; j < endM; j++){
                minutesInDay[j] = 1;//1 means not free
            }
    }
    if (minutesInDay[hour * 60 + min] == 0){//it is NOT TAKEN
        return 1;
    }

    return 0;
}
