import random
from math import sqrt
import math

from sklearn.model_selection import KFold

def read_data(filename):
    X = []
    Y = []
    with open(filename, 'r') as file:
        for line in file:
            line = line.replace('\n', '')
            lineSplited = line.split(',')
            points = lineSplited[2:]
            for i in range(len(points)):
                points[i] = float(points[i])
            X.append(points)
            Y.append(True if lineSplited[1] == "M" else False)
        return X, Y

# On calcule la distance euclidienne entre data1 et data2
def simple_distance(data1, data2):

    euclidean_distance = 0


    for i in range(len(data1)):
        euclidean_distance += (data1[i]-data2[i])*(data1[i]-data2[i])

    return sqrt(euclidean_distance)


def k_nearest_neighbors(x, points, dist_function, k):
    closest = []
    for i in range(len(points)):
        closest.append((dist_function(x, points[i]), i))
    closest.sort()
    return [closest[i][1] for i in range(k)]

def split_lines(input, seed, output1, output2):
  random.seed(seed)
  out1 = open(output1, 'w')
  out2 = open(output2, 'w')
  for line in open(input, 'r').readlines():
    if random.randint(0, 1):
      out1.write(line)
    else:
      out2.write(line)

def is_cancerous_knn(x, train_x, train_y, dist_function, k):
    
    cancerous = 0

    # On recupere les k plus proches voisins
    closest = k_nearest_neighbors(x, train_x, dist_function, k)

    # On compte le nombre de cancer
    for i in closest:
        if train_y[i]:
            cancerous = cancerous + 1

    return cancerous >= k/2

def eval_cancer_classifier(test_x, test_y, classifier):
    nbr_fail = 0
    for i in range(len(test_y)):
         if classifier(test_x[i]) != test_y[i]:
              nbr_fail += 1
    return nbr_fail/len(test_y)

def get_fold_list(train, train_index):
    list = []
    for i in train_index:
        list.append(train[i])
    return list

def cross_validation(train_x, train_y, untrained_classifier):

    k_fold = KFold(n_splits=5)
    error = 0

    # pour chaque fold on entraine le classifieur sur les 4 autres et on teste sur le fold courant    
    for train_index, test_index in k_fold.split(train_x):

        # on recupere les listes de train et de test
        list_train_fold_x = get_fold_list(train_x, train_index)
        list_train_fold_y = get_fold_list(train_y, train_index)
        list_test_fold_x = get_fold_list(train_x, test_index)
        list_test_fold_y = get_fold_list(train_y, test_index)

        # on entraine le classifieur sur les 4 autres
        classifier_function = lambda e: untrained_classifier(list_train_fold_x, list_train_fold_y, e)

        # on teste sur le fold courant
        error += eval_cancer_classifier(list_test_fold_x, list_test_fold_y, classifier_function)

    # on retourne la moyenne des erreurs
    return error/5

def sampled_range(mini, maxi, num):
    if not num:
        return []
    lmini = math.log(mini)
    lmaxi = math.log(maxi)
    ldelta = (lmaxi - lmini) / (num - 1)
    out = [x for x in set([int(math.exp(lmini + i * ldelta)) for i in range(num)])]
    out.sort()
    return out

# On cherche le plus petit k, tel que l'erreur soit minimale, pour déduire le nombre de plus proches voisins le plus adapté
def find_best_k(train_x, train_y, untrained_classifier_for_k):

    ideal_values = []
    best_current = 1

    # On cherche le plus petit k, tel que l'erreur soit minimale
    for k in sampled_range(1, len(train_x), 10):

        # On entraine le classifieur
        classifier_funtion = lambda train_x, train_y, e: untrained_classifier_for_k(train_x, train_y, k, e)

        error = cross_validation(train_x, train_y, classifier_funtion)

        if error < best_current:
            best_current = error
            ideal_values.append(k)
            
    return ideal_values[len(ideal_values)-1]