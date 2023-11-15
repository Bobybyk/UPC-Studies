from collections import defaultdict
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def sort_index(values):
    indices = list(range(len(values)))
    for i in range(len(indices)):
        max_idx = i
        for j in range(i+1, len(indices)):
            if values[indices[j]] > values[indices[max_idx]]:
                max_idx = j
        indices[i], indices[max_idx] = indices[max_idx], indices[i]
    return indices

def filter_index(sim_index, item_index):
    filtered_indices = []
    for i in sim_index:
        if i != item_index:
            filtered_indices.append(i)
    return filtered_indices

def most_similar(tfidf, item_index, k):

    # On calcule la similarité entre une blague donnée et toutes les autres
    cosine_sim = cosine_similarity(tfidf[item_index], tfidf).flatten()

    # On trie les index en fonction de cosine_sim
    sim_index = sort_index(cosine_sim)
    sim_index = filter_index(sim_index, item_index)

    # On sélectionne les k blagues les plus similaires
    most_similar_items = sim_index[:k]

    return most_similar_items

def read_ratings(filename, num_jokes):

    with open(filename, 'r') as file:
        lines = file.readlines()
        last_line = lines[-1]

    nbr_entries = int(last_line.split(',')[0])

    user_dico_ratings = [ {} for i in range (nbr_entries+1)] 

    for line in open(filename, 'r').readlines():
        user_id, joke_id, rating = line.split(',')
        user_id = int(user_id)
        joke_id = int(joke_id)
        rating = float(rating)

        user_dico_ratings[user_id][joke_id] = rating

    return user_dico_ratings

def content_recommend(similarity_matrix, user_ratings, k):

    matrice_sim = cosine_similarity(similarity_matrix)

    ratings = defaultdict(float)

    numerateur = 0
    denominateur = 0

    for joke_id in range(1, len(matrice_sim), 2):
        for user_joke, user_rate in user_ratings.items():
            # On ne prend pas en compte les blagues déjà notées par l'utilisateur
            if user_joke%2 == 0 and joke_id not in user_ratings:
                numerateur += user_rate * matrice_sim[joke_id][user_joke]
                denominateur += matrice_sim[joke_id][user_joke]
        if denominateur != 0:
            ratings[joke_id] = numerateur / denominateur
        numerateur = 0
        denominateur = 0

    # On trie les blagues en fonction de leur note
    sorted_jokes_dict = sorted(ratings.items(), key=lambda item: item[1], reverse=True)
    sorted_jokes = [i[0] for i in sorted_jokes_dict]

    # On sélectionne les k premières blagues
    best_jokes = sorted_jokes[:k]

    return best_jokes

### Exercice 1 ###

# Chargement des données à partir du fichier
data = open('jester_jokes.txt', 'r').readlines()

# Calcule des TF-IDF
vectorizer = TfidfVectorizer()
tfidf_matrix = vectorizer.fit_transform(data)

# Test de la fonction avec la blague 0
nbr_index_to_return = 5
item_index = 0
similar_items = most_similar(tfidf_matrix, item_index, nbr_index_to_return)

### Exercice 2 ###

r = read_ratings('jester_ratings.csv', 150)

print(content_recommend(tfidf_matrix, r[0], 10))