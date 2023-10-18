import random

def split_lines(input, seed, output1, output2):
    random.seed(seed)
    output1 = open(output1, 'w')
    output2 = open(output2, 'w')
    for line in open(input, 'r').readlines():
        if random.random() > 0.5:
            output1.write(line)
        else: 
            output2.write(line)
    output1.close()
    output2.close()

def tokenize_and_split(sms_file) -> tuple:
    words = {}
    l1 = []
    l2 = []
    i = 0
    for line in open(sms_file, 'r').readlines():
        if line.startswith('ham'):
            isSpam = False
        else:
            isSpam = True
        tmpL1 = []
        tmpL2 = []
        
        for word in line.split():
            if word != 'ham' and word != 'spam':
                if words.get(word) == None:
                    words[word] = i
                    i += 1
                if isSpam:
                    tmpL1.append(i)
                else:
                    tmpL2.append(i)
        if len(tmpL1) != 0:
            l1.append(tmpL1)
        if len(tmpL2) != 0:
            l2.append(tmpL2)

    return (words, l1, l2)

print(tokenize_and_split("test3_small"))