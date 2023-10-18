import random
from collections import defaultdict
from typing import List

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
    words = defaultdict(str)
    l1 = []
    l2 = []
    tmpDup = []
    i = 0
    for line in open(sms_file, 'r').readlines():
        if line.startswith('ham'):
            isSpam = False
        else:
            isSpam = True
        tmpL1 = []
        tmpL2 = []
        for word in line.split():
            if word not in tmpDup:
                words[i] = word
                i += 1
                tmpDup.append(word)
            if isSpam:
                for key in words.keys():
                    if words.get(key) == word:
                        tmpL1.append(key)
            else:
                for key in words.keys():
                    if words.get(key) == word:
                        tmpL2.append(key)
        l1.append(tmpL1)
        l2.append(tmpL2)

    return (words, l1, l2)