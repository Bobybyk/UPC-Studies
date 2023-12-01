#!/bin/pyton3
# coding=utf-8

import test
td7=test.safe_import('td7', deadline=30)

import math
import os

score = 0.0

def is_equal_set(a, b):
  return set(a) == set(b)

score += test.Test(
    td7, 'read_data', deadline=1,
    data = [
        ('test7_0',
         ([[2.3, 1.0, 0.5], [1.1, 3.2, 0.9], [0.2, 0.1, 0.23], [4.1, 1.9, 4.0]],
          [True, False, False, True]),
         10),
        ('test7_1', ([[4.2,2.4,.42], [4.3,3.4,.43]], [False, True]), 10),
    ])


score += test.Test(
    td7, 'simple_distance', deadline=1, pred=test.FloatEq,
    data = [
      ([1.0, 0.4, -0.3, 0.15], [0.1, 4.2, 0.0, -1], 4.081972562377166, 3),
      ([0., 4.], [3., 0.], 5., 2),
      ([0., -4.], [-3., 0.], 5., 3),
      ([0.], [0.], 0., 1),
      ([], [], 0., 1),
    ])

def simple_distance(d1, d2):
  return math.sqrt(sum([(d1[i]-d2[i])**2 for i in range(len(d1))]))

def manhattan_dist(l1, l2):
  d = 0.0
  for i in range(len(l1)):
    d += abs(l1[i]-l2[i])
  return d

example2_x = [.3, .1]
example2_train_x = [[.1, .2], [1.3, .2], [-.3, -.5], [-5.5, 6.6], [0., 0.]]

score += test.Test(
    td7, 'k_nearest_neighbors', deadline=1,
    data = [
      ([1.2, -0.3, 3.4],
       [[2.3, 1.0, 0.5], [1.1, 3.2, 0.9], [0.2, 0.1, 0.23], [4.1, 1.9, 4.0]],
       simple_distance,
       2,
       [2, 0],
       10),
      (example2_x, example2_train_x, manhattan_dist, 4, [0, 4, 1, 2], 10),
    ])

example_x = [1.2, -0.3, 3.4]
example_train_x = [[2.3, 1.0, 0.5], [1.1, 3.2, 0.9], [0.2, 0.1, 0.23], [4.1, 1.9, 4.0]]

score += test.Test(
    td7, 'is_cancerous_knn', deadline=1,
    data = [
      (example_x, example_train_x, [True, False, True, False], simple_distance, 2, True, 5),
      (example_x, example_train_x, [False, False, True, False], simple_distance, 2, True, 5),
      (example_x, example_train_x, [False, False, False, False], simple_distance, 2, False, 5),
      # The 3 NN of example2_x among example2_train_x are #0, #4, #1 (in order).
      (example2_x, example2_train_x, [True, False, False, False, True], manhattan_dist, 3, True, 5),
      (example2_x, example2_train_x, [False, False, True, True, True], manhattan_dist, 3, False, 5),
      (example2_x, example2_train_x, [False, True, False, False, True], manhattan_dist, 3, True, 5),
    ])

score += test.Test(
    td7, 'eval_cancer_classifier', deadline=1, pred=test.FloatEq,
    data = [
      ([[.2, .3], [.3, .4], [.4, .5], [.5, .6], [.6, .7]], [True, False, True, False, True],
       lambda l: l[0] > .35, 0.4, 10),
      ([[.2, .3], [.3, .4], [.4, .5], [.5, .6], [.6, .7]], [True, False, True, False, True],
       lambda l: l[0] < .35, 0.6, 10),
    ])

train_x = [[0.1*x] for x in range(100)]
train_y = [x%4==0 or x%5==0 for x in range(len(train_x))]  # 1/4 + 1/5 - 1/20 = 2/5 = 0.4

def majority_classifier(ignored_train_x, y, ignored_x):
  return 2 * y.count(True) >= len(y)

def true_if_whole_trainset(tx, ignored_y, ignored_x):
  return len(tx) >= len(train_x) - 1

score += test.Test(
    td7, 'cross_validation', deadline=1, pred=test.FloatNearPred(0.1),
    data = [
      (train_x, train_y, majority_classifier, 0.4, 10),
      (train_x, train_y, true_if_whole_trainset, 0.4, 10),
    ])

BEST_K = 17
FACTOR = 1.31
def within_factor_of(x, k):
  return x*FACTOR >= k and x <= FACTOR*k

def majority_classifier_for_some_k(train_x, train_y, k, x):
  majority = 2 * train_y.count(True) >= len(train_y)
  if within_factor_of(k, BEST_K):
    return majority
  else:
    return not majority

score += test.Test(
    td7, 'find_best_k', deadline=1, pred=within_factor_of,
    data = [
      (train_x, train_y, majority_classifier_for_some_k, BEST_K, 10),
      ([1]*30, [True]*30, majority_classifier_for_some_k, BEST_K, 10),
      ([1]*70, [False]*70, majority_classifier_for_some_k, BEST_K, 10),
    ])


print('SCORE: %d' % int(score/1.5))

