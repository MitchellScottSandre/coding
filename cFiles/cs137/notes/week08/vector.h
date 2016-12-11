#ifndef VECTOR_H
#define VECTOR_H
struct vector;

struct vector *vectorCreate();
struct vector *vectorDelete (struct vector *v);//return null
void vectorSet (struct vector *v, int index, int value);
int vectorGet(struct vecotor *v, int index);
int vectorLength(struct vector *v);
#endif
