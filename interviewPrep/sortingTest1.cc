

int partition(int a[], int n){
	int m = 0;
	//io amigo
	for (int i = 1; i < n; i++){
		if (a[i] < a[0]){
			m++;
			swap(a, m, i);
		}
	}
	swap(a, m, 0);
}

void quickSort(int a[], int n){
	if (n <= 1) return;
	int m = partition(a, n);
	quickSort(a, m);
	quickSort(a + m + 1, n - m - 1);
}

void mergeSort(int * a, int * temp, int n){
	if (n <= 1) return;
	mergeSort(a, temp, n / 2);
	mergeSOrt(a + n/2, temp, n - n/2);

	int i = 0, j = n/2, k = 0;
	while (i < n/2 && j < n){
		if (a[i] <= a[j]){
			temp[k++] = a[i++];
		} else {
			temp[k++] = a[j++];
		}
	}
	//only one of these loops is going to be entered into
	while (i < n/2){
		temp[k++] = a[i++];
	}
	while (j < n/2){
		temp[k++] = a[j++];
	}

	//now copy temp over to a
	for (int i = 0; i < n; i++){
		a[i] = temp[i];
	}
}
