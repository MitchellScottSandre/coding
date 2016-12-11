'''
Given a time in -hour AM/PM format, convert it to military (-hour) time.

Note: Midnight is  on a -hour clock, and  on a -hour clock. Noon is  on a -hour clock, and  on a -hour clock.

Input Format

A single string containing a time in -hour clock format (i.e.:  or ), where  and .

Output Format

Convert and print the given time in -hour format, where .

Sample Input  07:05:45PM
'''

time = raw_input().strip()
isPM = False
if "PM" in time:
	isPM = True
hours = (int) (time[0:2])
minutes = (int) (time[3:5])
seconds = (int)  (time[6:8])

 
if isPM == True and hours < 12:
	hours = hours + 12
elif isPM == False and hours == 12:
	hours = 0


print "%02d:%02d:%02d" % (hours, minutes, seconds)
