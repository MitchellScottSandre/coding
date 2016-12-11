#include <assert.h>
#include <math.h>
double my_atain2(double y, double x){
	assert(x != 0.0);
	assert (y != 0.0);
	if (x != NAN && y != NAN){

	return atan(y, x); // too many parameters, not quite sure how to solve this question
	}

}
