#include <stdbool.h>



int dayOfYear(int day, int month, int year){
if (year < 1753) return -1;//too low a year
if (month == 2 && day > 29) return -1;//feb 30th
if (isLeapYear(year) == false && month == 2 && day == 29) return -1;
if (day < 1 || day > 31 || month < 1 || month > 12) return -1;//0 day, 32 day, 0 month, or 13 month,
if (month <= 7 && month % 2 == 0 && day == 31) return -1;//even month and month <= 7 feb IS DIFFERENT = 2 = 28; apr = 4 = 30; jun = 6 = 30//30 days
if (month >= 8 && month % 2 == 1 && day == 31) return -1;

int dayOfYear = 0, i;
for (i = 1; i < month; i++){
    if (i <=7 && i % 2 == 1) dayOfYear += 31;// jan, mar, may, july
    else if (i == 2 && isLeapYear(year) == true ) dayOfYear += 29;//FEB
    else if (i == 2 && isLeapYear(year) == false) dayOfYear += 28;//FEB
    else if (i <=7 && i % 2 == 0 && month != 2) dayOfYear += 30;// apr, jun
    else if (i >=8 && i % 2 == 1) dayOfYear += 30;//sept, nov
    else if (i >=8 && i % 2 == 0) dayOfYear += 31;//aug, oct, dec
}

dayOfYear += day;

return dayOfYear;


//odd month and month <= 7;;;(jan = 1 = 31, mar = 3 = 31; may = 5 = 31; july = 7 = 31////////31 days
//even month and month > 7 ;;(aug = 8 = 31; oct = 10 = 31; dec = 12 = 31
//even month and month <= 7;; feb IS DIFFERENT = 2 = 28; apr = 4 = 30; jun = 6 = 30//30 days
//odd month and month > 7 ;;;;sept = 9 = 30; nov = 11 = 30

}
