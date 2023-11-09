from sklearn.cluster import KMeans
from sklearn.feature_extraction.text import TfidfVectorizer

def read_dataset(filename):
    data = []
    with open(filename, 'r') as file:
        for line in file:
            type, content = line.split('\t')
            data.append((1 if type == 'spam' else 0, content.removesuffix('\n')))
    return data

def spam_count(pairs):
    return sum([pair[0] for pair in pairs])

def transform_text(pairs):
    vectorizer = TfidfVectorizer()
    X = vectorizer.fit_transform([pair[1] for pair in pairs])
    Y = [pair[0] for pair in pairs]
    return (X, Y)