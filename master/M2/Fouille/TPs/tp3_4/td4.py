import random
import re

ban_words = [
        "gratuit",
        "offre spéciale",
        "argent facile",
        "prêt",
        "gagner de l'argent",
        "en ligne",
        "viagra",
        "millionnaire",
        "loterie",
        "héritage",
        "crédit",
        "sexuel",
        "crédit",
        "réduction",
        "promotion",
        "gratuit",
        "cadeau",
        "offre",
        "cash"
        "winner"
        "chance"
        "private"
]

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

def tokenize_and_split_2(sms_file) -> tuple:
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
        
        words_in_line = re.findall(r'\b\w+\b', line)

        for word in words_in_line:
            word = word.lower()
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

def naive_bayes_train_ham(sms_file):
    global ban_words
    words, l1, l2 = tokenize_and_split_2(sms_file)
    ham_ratio = len(l2) / (len(l1) + len(l2))
    ham_freq = compute_frequencies(len(words), l1)
    union_freq = compute_frequencies(len(words), l1+l2)
    hamcity = []
    if (len(l1) or len(l2)) == 0:
        return (0.1, words, hamcity)
    for i in range(len(words)):
        if (ham_freq[i]/union_freq[i] == 0) or words.get(i) in ban_words:
            hamcity.append(0.1)
        else:
            hamcity.append(ham_freq[i] / (union_freq[i]))
    return (ham_ratio, words, hamcity)

def naive_bayes_train_2(sms_file):
    global ban_words
    words, l1, l2 = tokenize_and_split_2(sms_file)
    spam_ratio = len(l1) / (len(l1) + len(l2))
    spam_freq = compute_frequencies(len(words), l1)
    union_freq = compute_frequencies(len(words), l1+l2)
    spamicity = []
    if (len(l1) or len(l2)) == 0:
        return (0.1, words, spamicity)
    for i in range(len(words)):
        if (spam_freq[i]/union_freq[i] == 0) or words.get(i) in ban_words:
            spamicity.append(0.1)
        else:
            spamicity.append(spam_freq[i] / (union_freq[i]))
    return (spam_ratio, words, spamicity)

def naive_bayes_train(sms_file):
    words, l1, l2 = tokenize_and_split(sms_file)
    spam_ratio = len(l1) / (len(l1) + len(l2))
    spam_freq = compute_frequencies(len(words), l1)
    union_freq = compute_frequencies(len(words), l1+l2)
    spamicity = []
    for i in range(len(words)):
        if spam_freq[i] == 0:
            spamicity.append(0)
        else:
            spamicity.append(spam_freq[i] / (union_freq[i]))
    return (spam_ratio, words, spamicity)

def naive_bayes_predict(train_spam_ratio, train_words, train_spamicity, sms):
    words = sms.split()
    spam_ratio = train_spam_ratio
    for word in set(words):
        if word in train_words:
            spam_ratio *= train_spamicity[train_words[word]]
    return spam_ratio

def naive_bayes_eval(test_sms_file, f):
    a = 0
    b = 0
    c = 0
    d = 0
    for line in open(test_sms_file, 'r').readlines():
        if line.startswith('ham'):
            isSpam = False
        else:
            isSpam = True
        if f(line.replace('ham', '').replace('spam', '')) != 0:
            if isSpam:
                a += 1
            else:
                b += 1
        else:
            if isSpam:
                c += 1
            else:
                d += 1
    if a + c == 0:
        recall = 1
    else:
        recall = a / (a + c)
    if a + b == 0:
        precision = 1
    else:
        precision = a / (a + b)
    return (recall, precision)

split_lines("SMSSpamCollection", 65157458, "data_train", "data_test")

train_spam_ratio, train_words, train_spamicity = naive_bayes_train("data_train")
train_spam_ratio_2, train_words_2, train_spamicity_2 = naive_bayes_train_2("data_train")
train_ham_ratio, train_words_ham, train_hamicity = naive_bayes_train_ham("data_train")

def classify_spam(sms):
    return naive_bayes_predict(train_spam_ratio, train_words, train_spamicity, sms) > 0.5

def classify_spam_recall(sms):
    return naive_bayes_predict(train_ham_ratio, train_words_ham, train_hamicity, sms) > 0.1

def classify_spam_precision(sms):
    return naive_bayes_predict(train_spam_ratio, train_words, train_spamicity, sms) > 0.9

def classify_spam_balanced(sms):
    return naive_bayes_predict(train_spam_ratio_2, train_words_2, train_spamicity_2, sms) > 0.9

print("classify_spam_recal : ", naive_bayes_eval("data_test", classify_spam_recall))
print("classify_spam_precision : ", naive_bayes_eval("data_test", classify_spam_precision))
print("classify_spam_balanced : ", naive_bayes_eval("data_test", classify_spam_balanced))