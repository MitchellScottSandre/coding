#include <stdbool.h>
bool isLeapYear (int year){
if (year % 400 == 0) return true;
else if (year % 100 == 0) return false;
else if (year % 4 == 0) return true;
else return false;
}

//going from dayofyear, a number between 1 and 366, to the month and day
int dateSplit(int dayOfYear, int year, int *day, int *month){
if (dayOfYear < 1 || dayOfYear > 366 || year < 1753) return 0;//invalid
if (dayOfYear == 366 && isLeapYear(year) == false) return 0;

int dayVal = 0, monthVal = 1, monthLength;
for (int i = 1; i <= dayOfYear; i++){
   if (monthVal <= 7 && monthVal %2 == 1) monthLength = 31;
   else if (monthVal == 2 && isLeapYear(year) == true) monthLength = 29;
   else if (monthVal == 2 && isLeapYear(year) == false) monthLength = 28;
   else if ( monthVal <= 7 && monthVal %2 == 0) monthLength = 30;
   else if ( monthVal >= 8 && monthVal %2 == 1) monthLength = 30;
   else if ( monthVal >= 8 && monthVal %2 == 0) monthLength = 31;
   dayVal++;
   if (dayVal == monthLength && i != dayOfYear ){
        dayVal = 0;
        monthVal++;
   }
}

*day = dayVal;
*month = monthVal;
return 1;
}

//return 1 if conversion is successful, 0 if day or dayOfYear or both are invalid
//year before 1753 is invalid
