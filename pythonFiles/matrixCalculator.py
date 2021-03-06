#!/usr/bin/env python2
#encoding: windows-1252

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
import random
import sys
import copy

def optionsMenu():
    print "Enter the number corresponding to the following options:"
    print "1. Matrix Multiplication"
    print "2. Determinant Calculator"
    print "3. Inverse Calculator"
    print "4. System of Linear Equations Solver"
    print "5. Transpose Calculator"
    
def matrixMultiplication():#====================================================================MULTIPLICATION==========================================================================
    print "Matrix Multiplication:"
    print "Enter your first matrix. "
    
    matrix1 = getMatrix(-1)
    print "Enter your second matrix. Remember that this matrix's # of rows must equal matrix # 1's number of columns."
    matrix2 = getMatrix(len(matrix1[0]))
    
    #second matrix is of correct length to allow multiplication
    print "You entered"
    displayMatrix(matrix1)
    print "and"
    displayMatrix(matrix2)
    
    productMatrix = []
    
    for r in range(0, len(matrix1)): #rows of matrix 1
        temp = [] 
        for c in range (0, len(matrix2[0])): # cols of matrix 2
            # r and c are index of where product of entries go in to NEW matrix
            #now you iterate through matrix1 COLS and matrix 2 ROWS
            sum = 0;
            for cc in range (0, len(matrix1[0])): # matrix 1 cols
                for rr in range(0, len(matrix2)): # matrix 2 rows
                    if rr == cc:
                        sum += matrix1[r][cc] * matrix2 [rr][c]
                    
            temp.append(sum)
        
        productMatrix.append(temp)
        
    print "Matrix 1 x Matrix 2 is:"
    displayMatrix(productMatrix)
    
#will need to make a cofactor matrix function to get the inverse...determinant function uses this too...

def matrixWithIandJRemoved(iRow, jCol, matrix=[[]]):
    #print "Matrix with i and j removed... recieved:"
    #displayMatrix(matrix)
    newMatrix = []
    for r in range(0, len(matrix)):
        temp = []
        for c in range (0, len(matrix)):
            if r != iRow and c != jCol:
                temp.append(matrix[r][c])
        if len(temp) > 0:    
            newMatrix.append(temp)
    #print "Returned:"
    #displayMatrix(newMatrix)
    return newMatrix
                
                
def solveCofactorRecursively(matrix=[[]]):
    if len(matrix) == 2: 
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
    else: # i'm pretty sure you need to be able to calculate determinants to be able to calculate cofactor... which is used to calculate determinants? ugh
        return solveDeterminantRecursively(matrix)
    
def determineCofactorMatrix(matrix=[[]]):#must be at least 3 x 3 in size?
    cofMatrix = []
    for r in range(0, len(matrix)):
        temp = []
        for c in range (0, len(matrix)):
            smallerMatrix = matrixWithIandJRemoved(r, c, matrix)
            x = solveCofactorRecursively(smallerMatrix)
            x = x * (-1) ** ((int)(r) + (int)(c))
            temp.append(x)
        cofMatrix.append(temp)
    
    #print "displaying cof matrix..."
    #displayMatrix(cofMatrix)
    return cofMatrix
            
                    
def solveDeterminantRecursively(matrix=[[]]): 
    cofMatrix = []
    if len(matrix) == 2:
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
    if len(matrix) == 3:
        cofMatrix = determineCofactorMatrix(matrix)
        sum = 0
        for c in range(0, 3):
            sum += matrix[0][c] * cofMatrix[0][c]
        return sum
    #for any dimension n
    cofMatrix = determineCofactorMatrix(matrix)
    sum = 0
    for c in range(0, len(matrix)):
        sum += matrix[0][c] * cofMatrix[0][c]
    
    return sum
    
                
    
def determinantCalculator():#====================================================================Determinant==========================================================================
    print "Determinant Calculator:" # must be square
    gotGoodInput = False
    while gotGoodInput == False:
        matrix = getMatrix(-1)
        if len(matrix) == len(matrix[0]):
            gotGoodInput = True
        else:
            print ("Oh no! Matrix entered was not square! Try again.")
    #x = determineCofactorMatrix(matrix)
    x = solveDeterminantRecursively(matrix)
    print "determinant is %d" %x

def transposeCalculator():
    print "Transpose Calculator:"
    matrix = getMatrix(-1)
    transposeMatrix = solveTranspose(matrix)
    displayMatrix(transposeMatrix)
    
def solveTranspose(matrix = [[]]):
    newMatrix = []
    for c in range(len(matrix[0])):
        temp = []
        for r in range (len(matrix)):
            temp.append(matrix[r][c])
        newMatrix.append(temp)
    return newMatrix

def solveInverse(det, cofMatrixTrans = [[]]):
    inverseMatrix = []
    for r in range (len(cofMatrixTrans)):
        temp = []
        for c in range (len(cofMatrixTrans)):
            temp.append( (float) (cofMatrixTrans[r][c]) / (float) (det))
        inverseMatrix.append(temp)
    return inverseMatrix
            
            
def inverseCalculator():#====================================================================Inverse==========================================================================
    print "Inverse Calculator"
    gotGoodInput = False
    while gotGoodInput == False:
        matrix = getMatrix(-1)
        if len(matrix) == len(matrix[0]):
            gotGoodInput = True
        else:
            print ("Oh no! Matrix entered was not square! Try again.")
    #now we have a square matrix
    det = solveDeterminantRecursively(matrix)
    if det != 0:
        if (len(matrix) > 2):
            cofMatrix = determineCofactorMatrix(matrix)
            cofMatrixTranspose = solveTranspose(cofMatrix)
            inverse = solveInverse(det, cofMatrixTranspose)
        elif (len(matrix) == 2):
            inverse = []
            temp1 = []
            temp1.append( (float)(matrix[1][1]) / (float) (det))
            temp1.append( (float)(-matrix[0][1]) / (float) (det))
            inverse.append(temp1)
            temp2 = []
            temp2.append( (float)(-matrix[1][0]) / (float) (det))
            temp2.append( (float)(matrix[0][0]) / (float) (det)) 
            inverse.append(temp2)
        print "Inverse is:"
        displayMatrix(inverse)
    else:
        print "Oh no! Matrix is not invertible as its determinant is 0!"
    
def replaceIthColInMatrixWithVector(col, vector = [], matrix = [[]]):
    #newMatrix = matrix #Had this before...making it point to same pointer...passed by reference...ugh
    newMatrix = []
    for r in range(0, len(matrix)):
        temp = []
        for c in range (0, len(matrix)):
            temp.append(matrix[r][c])
        newMatrix.append(temp)
    
    for r in range(0, len(matrix)):
        newMatrix[r][col] = vector[r][0]
    
    return newMatrix
    
def systemSolver():#====================================================================System Solver==========================================================================
    print "System of equations Solver:" #what if it isn't square? --> not invertible..then do row operations... make in terms of some a, b, c, d.... abcd elements of all real numbers
    gotGoodInput = False #must be a square matrix
    print "Enter the square coefficient matrix to solve"
    while gotGoodInput == False:
        matrix = getMatrix(-1)
        if len(matrix) == len(matrix[0]):
            gotGoodInput = True
        else:
            print ("Oh no! Matrix entered was not square! Try again.")
    
    print "Enter the right hand side of the equation vector"
    vector = getMatrix(len(matrix))
    det = solveDeterminantRecursively(matrix)
    if det != 0:
        solutionVector = []
        for c in range (0, len(matrix)): #iterate through all of its columns...
            N_matrix = replaceIthColInMatrixWithVector(c, vector, matrix)
            N_matrix_det = solveDeterminantRecursively(N_matrix)
            solutionVector.append ( (float) (N_matrix_det) / (float) (det))
        print "Solution vector is: "
        for c in range(0, len(matrix)):
            print solutionVector[c]
            
    else:
        print "This system of equations has no solution!"
    
def getMatrix(beforeCols): #fix this...why is python so confusing
    print("Getting Matrix:")
    #have choice to read in matrix from file? or just generate random numbers?
    matrix  = []
    gotGoodInput = False
    while gotGoodInput == False:
        try:
            rows = (int) (raw_input("Enter number of rows: "))
            cols = (int) (raw_input("Enter number of cols: "))
            if rows > 0  and cols > 0:
                if beforeCols != -1:
                    if rows == beforeCols:
                        gotGoodInput = True
                    else:
                        print "Oh no! This matrix's # of rows doesn't match previous matrix's number of columns."
                else:
                    gotGoodInput = True
            else:
                print "Incorrect values of rows and/or cols. Try again."
        except ValueError: 
            print "Oh no! You didn't enter a proper number. Try again." 
    
    choice = 0
    gotGoodInput = False
    print "Now, choose how you want to generate the matrix."
    print "1. Enter manually"
    print "2. Random numbers between -10 and 10"
    while gotGoodInput == False:
        choice = raw_input("Enter your choice here: ")
        if choice == "1":
            gotGoodInput = True
        elif choice == "2":
            gotGoodInput = True
        else:
            print "Wrong input entered. Try again."
    if choice == "1":        
        gotGoodInput = False
        goodCounter = 0
        while gotGoodInput == False:
            goodCounter = 0
            for r in range(0, rows):
                new = []
                new = map(int, raw_input().strip().split(' '))
                matrix.append(new)

            for r in range(0, rows):
                if len(matrix[r]) == cols:
                    goodCounter += 1

            if goodCounter == rows:
                gotGoodInput = True
                print "Valid matrix entered."
            else:
                print "Invalid matrix entered. Try again."
                matrix = [] #make sure to re-set the matrix!
    else:
        #generate random matrix
        for r in range (0, rows):
            new = []
            for c in range (0, cols):
                new.append(random.randint(-10, 10))
            matrix.append(new)
        print "Random Matrix is:"
        displayMatrix(matrix)
    return matrix 
    
    


def displayMatrix(list=[[]]):
    # print list
    rows = len(list)
    cols = len(list[0])
    print "rows %d, cols %d" % (rows, cols)
    for r in list:
        for val in r:
            #print '%4d' % val, #old version apparently
            print '{:>3}'.format(val),
            #print val, #comma makes it print to same line, but adds a space at the end!
        print ""
    #print "done displaying..."
        
#=====================================================================================================MAIN=====================================================================================
    
#main

gotGoodInput = False #main
optionsMenu()
while gotGoodInput == False:

    choice = raw_input("Enter your choice here: ")
    if choice == "1":
        gotGoodInput = True
        matrixMultiplication()
    elif choice == "2":
        gotGoodInput = True
        determinantCalculator()
    elif choice == "3":
        gotGoodInput = True
        inverseCalculator()
    elif choice == "4":
        gotGoodInput = True
        systemSolver()
    elif choice == "5":
        gotGoodInput = True
        transposeCalculator()
    else:
        print "You entered a bad number! Try again."


sys.exit()
    
    


