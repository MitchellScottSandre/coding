void findMostWordsPerLine_PassToFormatter_CreateAllLinesArray_thenCallDisplayFunction(int n, vector<string> allWords, string c2, string format){
	vector<vector <string> > allLines;
	vector<string> thisLine;
	int lineLength = 0, startIndex = 3;
	bool keepPrinting = true, printedThisLine, lastWord;
	lastWord = false;

	for (int i = 3; i < allWords.size(); i++){
		if (lineLength + allWords[i].length() < n){
			thisLine.push_back(allWords[i]);
			lineLength += allWords[i].length() + 1;//for the space!!!
		} else if (lineLength + allWords[i].length() == n){
			thisLine.push_back(allWords[i]);
			lineLength += allWords[i].length() ;//NO space!!!
		} else if (allWords[i].length() > n){
			thisLine = callFormatter(thisLine, n, format);
			allLines.push_back(thisLine);
			//displayLineBothJustified(thisLine, n);
			thisLine.clear();
			string shorterS = allWords[i].substr(0, n);
			thisLine.push_back(shorterS);
			thisLine = callFormatter(thisLine, n, format);
			allLines.push_back(thisLine);

			thisLine.clear();
			lineLength = 0;
		} else {//greater than n, display this line, then must start a new line!
			thisLine = callFormatter(thisLine, n, format);
			allLines.push_back(thisLine);
			thisLine.clear();
			lineLength = 0;
			i--;
		}
		if (i == allWords.size() - 1){//last line since it has last word, has not been printed
			//displayLineBothJustified(thisLine, n);
			thisLine = callFormatter(thisLine, n, format);
			allLines.push_back(thisLine);
		}

	}


}
