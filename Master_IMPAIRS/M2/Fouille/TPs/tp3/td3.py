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
                if word not in words:
                    words[word] = i
                    i += 1
                if isSpam:
                    tmpL1.append(words[word])
                else:
                    tmpL2.append(words[word])
        if len(tmpL1) != 0:
            l1.append(tmpL1)
        if len(tmpL2) != 0:
            l2.append(tmpL2)

    return (words, l1, l2)

def compute_frequencies(num_words, documents):
    freq = [0] * num_words
    for doc in documents:
        for word in set(doc):
            freq[word] += 1
    for index in range(len(freq)):
        freq[index] /= len(documents)
    return freq

def naive_bayes_train(sms_file):
    words, l1, l2 = tokenize_and_split(sms_file)
    spam_ratio = len(l1) / (len(l1) + len(l2))
    freq1 = compute_frequencies(len(words), l1)
    freq2 = compute_frequencies(len(words), l2)
    spamicity = []
    for index in range(len(freq1)):
        spamicity.append(freq1[index] / (freq1[index] + freq2[index]))
    return (spam_ratio, words, spamicity)