#!/bin/pyton3
# coding=utf-8

import test
td6=test.safe_import('td6', deadline=30)

import math
import os

score = 0.0

from sklearn.feature_extraction.text import TfidfVectorizer
tf=TfidfVectorizer(strip_accents='unicode', sublinear_tf=True)
tfidf=tf.fit_transform(open('jester_jokes.txt','r').readlines())

def is_equal_set(a, b):
  return set(a) == set(b)

score += test.Test(
    td6, 'most_similar', pred=is_equal_set, deadline=5,
    data = [
        (tfidf, 0, 1, [86], 10),
        (tfidf, 0, 2, [86, 102], 10),
        (tfidf, 0, 3, [86, 102, 117], 10),
        (tfidf, 0, 10, [86, 102, 117, 133, 129, 67, 141, 87, 19, 105], 10),
    ])

def lines_eq_modulo_shift1(a, b):
  if len(a) != len(b):
    return False
  for i in range(len(a)):
    aa = a[i]
    bb = b[i]
    if aa == bb:
      continue
    if aa == [None] + bb:
      continue
    return False
  return True

score += test.Test(
    td6, 'read_ratings', deadline=5, pred=lines_eq_modulo_shift1, data=[
    ('jester_ratings_3users.csv', 150,
     [{4: 0.219, 6: -9.281, 7: -9.281, 12: -6.781, 14: 0.875, 15: -9.656, 16: -9.031, 17: -7.469, 18: -8.719, 19: -9.156, 20: -7.188, 21: -8.781, 22: -8.531, 23: -7.906, 24: -7.469, 88: 9.812, 49: 9.906, 101: 0.75, 102: -5.0, 103: 2.938, 104: 2.0, 105: -0.156, 106: 2.031, 107: 5.688, 108: 9.656, 86: 8.0, 92: 9.312, 75: 9.312, 64: 8.781, 71: 8.781, 26: 8.781, 35: 8.781, 118: 8.781, 119: 8.781, 34: 8.781, 52: 8.781, 90: 8.781, 82: 8.781, 31: 8.781, 120: 8.781, 122: 8.781, 28: 8.781, 30: 8.781, 65: 8.688, 68: 8.688, 53: 8.688, 126: 8.688, 127: 0.0, 41: 0.062, 79: 0.062, 48: 0.062, 61: 0.219, 33: -0.25, 50: 0.062, 51: 0.062, 67: -0.125, 80: 0.125, 60: 0.031, 25: 0.031, 91: 3.625, 117: 0.0, 133: 3.344},
      {4: -9.688, 6: 9.938, 7: 9.531, 12: 9.938, 14: 0.406, 15: 3.719, 16: 9.656, 17: -2.688, 18: -9.562, 19: -9.125, 25: 9.938, 26: 9.781, 27: 9.812, 28: 9.906, 29: 3.125, 49: 9.938, 35: 9.844, 31: -4.25, 34: 5.125, 52: 8.0, 68: -5.0, 88: 9.562, 20: 9.844, 53: -4.312, 71: 4.781, 30: 5.5, 48: 4.969, 65: -2.125, 61: 4.75, 64: 3.688, 80: -0.312, 60: -0.781, 82: 3.938, 47: 9.281},
      {4: -9.844, 6: -9.844, 7: -7.219, 12: -2.031, 14: -9.938, 15: -9.969, 16: -9.875, 17: -9.812, 18: -9.781, 19: -6.844, 32: -9.812, 33: -9.781, 36: -9.812, 37: 0.062, 38: 0.0, 104: 1.25, 101: 0.344, 108: -9.812},
     ],
     15),
    ('jester_ratings_small.csv', 8,
     [{4: 0.219, 6: -9.281, 7: -9.281},
      {4: -9.688, 6: 9.938, 7: 9.531},
      {4: -9.844, 6: -9.844, 7: -7.219},
      {4: -5.812, 6: -4.5, 7: -4.906},
      {4: 6.906, 6: 4.75, 7: -5.906},
      {4: -0.031, 6: -9.094, 7: -0.406},
     ],
     15),
    ])

# Based on some hand-drawn 2D graph:
#      [1]
#     .''. 
#   0.8  0.7           The non-specified similarities are loosely derived
#  .'      '.          from the distance (far = low similarity).
# [0]--0.6--[2]        See the matrix.
#  |       .'|'.       Similarities are symmetric.
#  |     .' /   '.
# 0.4  0.3  |     '.
#  |  .'   /       0.3
#  |.'     |         '.
# [3]     /            '.
#   `0.9  |              '.
#      `[4]------0.3------[5]
small_similarity_matrix = [
  [1., .8, .6, .4, .3, .1],
  [.8, 1., .7, .2, .3, .2],
  [.6, .7, 1., .3, .3, .3],
  [.4, .2, .3, 1., .9, .2],
  [.3, .3, .3, .9, 1., .3],
  [.1, .2, .3, .2, .3, 1.],
]

score += test.Test(
    td6, 'content_recommend', deadline=5, pred=is_equal_set,
    data=[
      # Likes 0 and 2 very much, hates 4: should recommend 1, then 5, then 3.
      (small_similarity_matrix, [8., None, 7.5, None, -8.1, None], 1, [1], 7),
      (small_similarity_matrix, [8., None, 7.5, None, -8.1, None], 2, [1, 5], 5),
      (small_similarity_matrix, [8., None, 7.5, None, -8.1, None], 3, [1, 5, 3], 3),
      # Loves 2, hates 0, indifferent to 4: should recommend 5.
      (small_similarity_matrix, [-9., None, 9., None, 0., None], 1, [5], 5),
      # Loves 4 and 0, hates 2: should recommmend 3, then 1, then 5.
      (small_similarity_matrix, [7., None, -5., None, 6., None], 1, [3], 6),
      (small_similarity_matrix, [7., None, -5., None, 6., None], 2, [3, 1], 2),
      (small_similarity_matrix, [7., None, -5., None, 6., None], 3, [3, 1, 5], 2),
    ])

print('SCORE: %d' % int(score))
