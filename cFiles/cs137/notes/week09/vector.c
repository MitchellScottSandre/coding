#include <assert.h>
#include <stdlib.h>
#include "vector.h"

struct vector{
	int *a;
	int size;
	int length;//how long our vector currently is, actual vector length
}

int vectorLength(struct vector *v){
	assert(v);
	return v->length;
}

int vectorGet(struct vector *v, int index){
	assert(v && index >= 0 && index < v->length);
	return v->a[index];
}

struct vector *vectorDelete(struct vector *v){
	if (v){
		free(v->a);
		free(v);
	}
	return null;
}

void vectorSet(struct vector *v, int index, int value){
	assert(index > 0 && v);
	if (index >= v->size){
		do {
			v-> size *= 2;
		} while (index >= v->size) ;
		v->a = realloc(v->a, v->size * sizeof(int));
		assert (v->a);
	}
	while (index >= v->length){
		v->a[v->length] = 0;
		v->length++;
	}
	v->a[index] = value;

}

struct vector *vectorCreate(){
	struct vector *v = malloc (sizeof(struct vector));
	assert(v);
	v -> size = 1;
	v -> a = malloc(sizeof(int));
	assert(v->a);
	v->length = 0;
	return v;
}