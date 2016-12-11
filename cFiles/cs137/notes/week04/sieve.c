void sieve(int a[], int m){
	if (m <= 0) return;
	a[0] = 0;
	if(m == 1) return;
	a[1] = 0;
	if (m==2) return;
	int x, z;
	for (x = 2; x < m; x++){

		a[x] = 1;//may be prime

	}
	for (z = 2; z <= (m-1); z++){
	int i;
	if (a[z]){
		int j;
			for (j = 2*z; j < m; j+=z){
				a[j] = 0;//everything else will be a 1
			}
	}
	}

}
