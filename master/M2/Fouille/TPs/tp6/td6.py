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

    # On exclue la blague elle-même de la liste des indices similaires
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

        if (joke_id < num_jokes):
            user_dico_ratings[user_id][joke_id] = rating
    return user_dico_ratings

"""
On va coder le moteur de recommandation à proprement parler. 
L’idée qu’on va utiliser ici est de calculer les similarités des blagues entre elles (avec la similarité cosinus), 
et de les utiliser comme poids pour le calcul d’une moyenne pondérée:

rating(b) = (rating(b[i]) * similarity(b[i], b)) / similarity(b[i], b)

On calculera cette formule pour tous les blagues “impaires” (d’index impair), et on proposera donc les K meilleures.
L’idée est donc de se baser sur des ratings connus (les blagues paires) pour classer des blagues inconnues (les blagues impaires), 
en se basant uniquement sur une analyse de similarité fondée sur le contenu.

Indice: on peut calculer d’un seul coup une matrice de similarité à partir d’une matrice TF-IDF, par exemple, avec:
from sklearn.metrics.pairwise import cosine_similarity
cosine_similarity(tfidf)


Recommends k best jokes for a given user.

This recommendation takes as input the ratings of a single user, but only
takes into account the ratings of even-numbered jokes, while it only recommends
Odd-numbered jokes.

Args:
similarity_matrix: A similarity matrix of size NxN.
user_ratings: a dictionary {joke id: rating} containing the known joke
                ratings of a given user.
k: an integer, the number of odd-indexed jokes to recommend.

Returns:
A list of odd joke indices recommended for this user, based on the joke
similarities and using only the user's ratings of even-indexed jokes.
"""
def content_recommend(similarity_matrix, user_ratings, k):

    matrice_sim = cosine_similarity(similarity_matrix)

    # On calcule la moyenne pondérée pour chaque blague impaire
    ratings = defaultdict(float)
    numerateur = 0
    denominateur = 0
    for joke_id in range(1, len(matrice_sim), 2):
        for user_id in user_ratings:
            numerateur += user_ratings[user_id] * matrice_sim[joke_id][user_id]
            denominateur += matrice_sim[joke_id][user_id]
        ratings[joke_id] = numerateur / denominateur
        numerateur = 0
        denominateur = 0

    # On trie les blagues du plus haut rating au plus bas
    sorted_jokes = sort_index(ratings)

    # On sélectionne les k premières blagues
    best_jokes = sorted_jokes[:k]

    # On affiche les k premières blagues de best_jokes et leur rating
    for joke_id in best_jokes:
        print(f"Joke {joke_id} : {ratings[joke_id]}")

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

print("### EXERCICE 1 ###")

# Afficher les indices des blagues les plus similaires
print(f"Blagues similaires à {item_index} : {similar_items}")

### Exercice 2 ###

print("### EXERCICE 2 ###")

r = read_ratings('jester_ratings.csv', 150)
print(r[0])
print(sum([r[0][x] for x in r[0]]))

content_recommend(tfidf_matrix, r[0], 5)