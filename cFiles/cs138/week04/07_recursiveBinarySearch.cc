// recursive binary search
int binary_search(vector<string> v, string key, int imin, int imax) {
		if (imax < imin) {
			return KEY_NOT_FOUND;
		} else {
			int imid = (imin + imax) / 2;
			if (v.at(imid) > key) {
				return binary_search(v, key, imin, imid - 1);
			} else if (v.at(imid) < key) {
				return binary_search(v, key, imid + 1, imax);
			} else {
				return imid;
			}
		}
}
