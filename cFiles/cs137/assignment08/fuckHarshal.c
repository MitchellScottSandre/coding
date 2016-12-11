#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "poly.h"
#include <math.h>

int main(void) {
	struct poly *p0 = polySetCoefficient(
			polySetCoefficient(polySetCoefficient(polyCreate(), 0, 4.0), 1,
					-1.0), 10, 2.0);
	struct poly *p1 = polyCopy(p0);
	/*struct poly *p2 = polySetCoefficient(
				polySetCoefficient(polySetCoefficient(polyCreate(), 0, 4.0), 1,
						-1.0), 10, 2.0);*/
	//printf("%g\n", polyGetCoefficient(p2, 0));
	printf("%g\n", polyGetCoefficient(p0, 100));
	printf("%d\n", polyDegree(p0));
	polyPrint(p0);
	polyPrint(p1);
	//p2 = polyAdd (p0, p1);

	polyDelete(p0);
	polyDelete(p1);

}

struct poly *polyAdd(struct poly *p0, struct poly *p1) {
	//struct poly *ret = polyCopy(((p0->size <= p1->size)?p0:p1));
	//struct poly *p3 = polyCreate();
	/*for(int i = 0; i < ((p0->size <= p1->size)?p0->size:p1->size) -1; i++){
	 polySetCoefficient(ret, i, *(ret->c + i) + *((p0->c < p1->c?p0->c:p1->c) + i) );
	 }*/
	return p0;
}

struct poly *polyCreate(void) {
	struct poly *ret;
	ret->c = malloc(sizeof(double));
	ret->size = 1;
	ret->c[0] = 0;
	return ret;
}

struct poly *polySetCoefficient(struct poly *p, int i, double value) {
	if ((i + 1) > p->size) {
		int oldSize = p->size;
		p->size = i + 1;
		p->c = realloc(p->c, sizeof(double) * (p->size));
		for (int n = oldSize; n <= i; n++) {
			*((p->c) + n) = 0;
		}
	}
	*((p->c) + (i)) = value;
	return p;
}

double polyGetCoefficient(struct poly *p, int i) {
	return *((p->c) + i);
	//return p->c
}

int polyDegree(struct poly *p) {
	int ret = 0;
	for (int i = 0; i < p->size; i++) {
		if (*((p->c) + i) != 0) {
			ret = i;
		}
	}
	return ret;
}

struct poly *polyDelete(struct poly *p) {
	free(p);
	return NULL;
}

void polyPrint(struct poly *p) {
	int n = polyDegree(p);
	if (fabs(*((p->c) + n)) != 1) {
		printf("%g", *((p->c) + n));

	} else {
		if (*((p->c) + n) == -1) {
			printf("-");
		}
	}
	if (n > 0) {
		printf("x");
	}
	if (n > 1) {
		printf("^%d", n);
	}
	n -= 1;

	for (; n >= 0; n--) {
		if (*((p->c) + n) != 0) {
			if (*((p->c) + n) < 0) {
				printf(" - ");
			} else {
				printf(" + ");
			}

			if (fabs(*((p->c) + n)) != 1) {
				printf("%g", fabs(*((p->c) + n)));
			}

			if (n > 0) {
				printf("x");
			}
			if (n > 1) {
				printf("^%d", n);
			}
		}
	}
	printf("\n");
}

struct poly *polyCopy(struct poly *p) {
	struct poly *ret = polyCreate();
	for (int i = 0; i < p->size; i++) {
		polySetCoefficient(ret, i, *((p->c) + i));
	}
	ret->size = p->size;
	return ret;
}