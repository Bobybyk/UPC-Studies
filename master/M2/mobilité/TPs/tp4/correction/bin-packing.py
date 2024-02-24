def nextfit(weight, c):
    res = 0
    rem = c
    for _ in range(len(weight)):
        if rem >= weight[_]:
            rem = rem - weight[_]
        else:
            res += 1
            rem = c - weight[_]
    return res


def firstFit(weight, n, c):
     
    # Initialize result (Count of bins)
    res = 0
     
    # Create an array to store remaining space in bins
    # there can be at most n bins
    bin_rem = [0]*n
     
    # Place items one by one
    for i in range(n):
       
        # Find the first bin that can accommodate
        # weight[i]
        j = 0
        while( j < res):
            if (bin_rem[j] >= weight[i]):
                bin_rem[j] = bin_rem[j] - weight[i]
                break
            j+=1
             
        # If no bin could accommodate weight[i]
        if (j == res):
            bin_rem[res] = c - weight[i]
            res= res+1
    return res

def bestFit(weight, n, c):
     
    # Initialize result (Count of bins)
    res = 0;
 
    # Create an array to store
    # remaining space in bins
    # there can be at most n bins
    bin_rem = [0]*n;
 
    # Place items one by one
    for i in range(n):
         
        # Find the first bin that
        # can accommodate
        # weight[i]
        j = 0;
         
        # Initialize minimum space
        # left and index
        # of best bin
        min = c + 1;
        bi = 0;
 
        for j in range(res):
            if (bin_rem[j] >= weight[i] and bin_rem[j] -
                                       weight[i] < min):
                bi = j;
                min = bin_rem[j] - weight[i];
             
        # If no bin could accommodate weight[i],
        # create a new bin
        if (min == c + 1):
            bin_rem[res] = c - weight[i];
            res += 1;
        else: # Assign the item to best bin
            bin_rem[bi] -= weight[i];
    return res;

def worstFit( weight, n, c):
 
 
    # Initialize result (Count of bins)
    res = 0
 
    # Create an array to store remaining space in bins
    # there can be at most n bins
    bin_rem = [0 for i in range(n)]
     
    # Place items one by one
    for i in range(n):
     
        # Find the best bin that can accommodate
        # weight[i]
 
        # Initialize maximum space left and index
        # of worst bin
        mx,wi = -1,0
 
        for j in range(res):
            if (bin_rem[j] >= weight[i] and bin_rem[j] - weight[i] > mx):
                wi = j
                mx = bin_rem[j] - weight[i]
             
 
        # If no bin could accommodate weight[i],
        # create a new bin
        if (mx == -1):
            bin_rem[res] = c - weight[i]
            res += 1
         
        else: # Assign the item to best bin
            bin_rem[wi] -= weight[i]
     
    return res

def firstFitDec(weight, n, c):
 
    # First sort all weights in decreasing order
    weight.sort(reverse = True)
 
    # Now call first fit for sorted items
    return firstFit(weight, n, c)

# Driver program
weight = [2, 5, 4, 7, 1, 3, 8]
c = 10
n = len(weight)

print("Number of bins required in Next Fit :",nextfit(weight, c))
print("Number of bins required in First Fit : ",firstFit(weight, n, c))
print("Number of bins required in Best Fit : ",bestFit(weight, n, c))
print("Number of bins required in Worst Fit : ",worstFit(weight, n, c))
print("Number of bins required in First Fit Decreasing : ",firstFitDec(weight, n, c))

