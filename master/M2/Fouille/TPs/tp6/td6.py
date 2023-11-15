from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def sort_indices(values):
    indices = list(range(len(values)))
    for i in range(len(indices)):
        max_idx = i
        for j in range(i+1, len(indices)):
            if values[indices[j]] > values[indices[max_idx]]:
                max_idx = j
        indices[i], indices[max_idx] = indices[max_idx], indices[i]
    return indices

def filter_indices(sim_index, item_index):
    filtered_indices = []
    for i in sim_index:
        if i != item_index:
            filtered_indices.append(i)
    return filtered_indices

def most_similar(tfidf, item_index, k):

    # On calcule la similarité entre une blague donnée et toutes les autres
    cosine_sim = cosine_similarity(tfidf[item_index], tfidf).flatten()

    # On trie les index en fonction de cosine_sim
    sim_index = sort_indices(cosine_sim)

    # On exclue la blague elle-même de la liste des indices similaires
    sim_index = filter_indices(sim_index, item_index)

    # On sélectionne les k blagues les plus similaires
    most_similar_items = sim_index[:k]

    return most_similar_items

# Chargement des données à partir du fichier
data = open('jester_jokes.txt', 'r').readlines()

# Calcule des TF-IDF
vectorizer = TfidfVectorizer()
tfidf_matrix = vectorizer.fit_transform(data)

# Test de la fonction avec la blague 0
nbr_index_to_return = 5
item_index = 0
similar_items = most_similar(tfidf_matrix, item_index, nbr_index_to_return)

# Afficher les indices des blagues les plus similaires
print(f"Blagues similaires à {item_index} : {similar_items}")