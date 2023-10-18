#!/bin/pyton3
# coding=utf-8

import test
td3=test.safe_import('td3', deadline=30)

import math
import os


def is_balanced_split(input, _, out1, out2):
  l1 = sorted(open(out1, 'r').readlines())
  l2 = sorted(open(out2, 'r').readlines())
  if abs(len(l1) - len(l2)) > 3 * math.sqrt(len(l1) + len(l2)):
    print("FAILED: The split isn't balanced")
    return False
  lin = sorted(open(input, 'r').readlines())
  if len(l1) + len(l2) != len(lin):
    print("FAILED: The number of lines don't add up : %d + %d != %d" %
          (len(l1), len(l2), len(lin)))
    return False
  if lin != sorted(l1 + l2):
    print("FAILED: The lines don't match")
    return False
  return True


def files_equal(f1, f2):
  return open(f1, 'r').readlines() == open(f2, 'r').readlines()


score = 0.0

open('tmp_0', 'w')
score += test.Test2(td3, 'split_lines', data=[
    ('test3_1', 0, 'tmp_1', 'tmp_2', is_balanced_split, 2),
    ('test3_1', 0, 'tmp_3', 'tmp_4', is_balanced_split, 1),
    ('test3_1', 1, 'tmp_5', 'tmp_6', is_balanced_split, 3),
    ('test3_1', 2, 'tmp_7', 'tmp_8', is_balanced_split, 3),
    ('tmp_0', 42, 'tmp_9', 'tmp_10', is_balanced_split, 1)
    ])

if score >= 9:
  if not (files_equal('tmp_1', 'tmp_3') and
          files_equal('tmp_2', 'tmp_4')):
    print('FAILED test: "same seed means same split"')
  else:
    print('PASSED test: "same seed means same split"')
    score += 2
    if (files_equal('tmp_1', 'tmp_5') or
        files_equal('tmp_2', 'tmp_8')):
      print('FAILED test: "different seed means different split"')
    else:
      print('PASSED test: "different seed means different split"')
      score += 8


for i in range(11):
  try:
    os.remove('tmp_%d' % i)
  except OSError:
    pass


def is_same_tokenized(a, b):
  wa, a0, a1 = a
  wb, b0, b1 = b
  if sorted(wa.keys()) != sorted(wb.keys()):
    print('FAILED: The dictionaries do not have the same words.' +
          '\nExpected: %s\nActual   : %s' % (wb.keys(), wa.keys()))
    return False
  la = [None]*len(wa)
  lb = [None]*len(wb)
  for w, i in wa.items():
    la[i] = w
  for w, i in wb.items():
    lb[i] = w
  aspams = sorted([[la[i] for i in indices] for indices in a0])
  bspams = sorted([[lb[i] for i in indices] for indices in b0])
  ahams = sorted([[la[i] for i in indices] for indices in a1])
  bhams = sorted([[lb[i] for i in indices] for indices in b1])
  return test.Eq(aspams, bspams) and test.Eq(ahams, bhams)


expected_test3_small_words = {
    "I":0, "don't":1, "think":2, "so.":3,
    "Nah":4, "he":5, "goes":6, "there":7,
    "darling":8, "so":9,
    "FreeMsg":10, "Hey":11, "it's":12, "been":13, "3":14, "week's":15,
    "WINNER!!":16,
}

expected_hams = [[0, 1, 2, 3],
                 [4,0,1,2,5,6,7],
                 [8,5,9,6,7],
                 ]
expected_spams = [[10,11,7,8,12,13,14,15],
                  [16],
                 ]

score += test.Test(
    td3, 'tokenize_and_split', pred=is_same_tokenized, deadline=1,
    data=[
        ('test3_small',
         (expected_test3_small_words, expected_spams, expected_hams), 20),
       ])

score += test.Test(
    td3, 'compute_frequencies', pred=test.Eq, deadline=1,
    data=[
        (6, [[0, 1, 1], [0, 4, 0]], [1.0, 0.5, 0.0, 0.0, 0.5, 0.0], 10),
        (4, [[1,3,1,1,3,1,1],[0],[0,0,0,0],[1,3],[0,1]],
         [0.6, 0.6, 0.0, 0.4], 7),
        (2, [[0]], [1.0, 0.0], 5),
        (1, [[]], [0.0], 3),
    ])

def eq_spamicity(out, expected):
  spam_ratio, words, spamicity = out
  expected_spam_ratio, expected_spamicity_dict = expected
  return (test.FloatEq(spam_ratio, expected_spam_ratio) and
      test.FloatDictEq(dict([(w, spamicity[i]) for (w, i) in words.items()]),
                       expected_spamicity_dict))

score += test.Test(
    td3, 'naive_bayes_train', pred=eq_spamicity, deadline=1,
    data=[
        ('test3_0',
         (1.0/3, {'Hello':1.0, 'World':0.0, 'awesome':3.0, 'stuff':3.0, '?':0.0}),
         15),
        ('test3_1',
         (0.34177215189873417,
         {'24': 0.0, '18': 0.0, '40': 0.0, '25': 0.0, '35': 0.0, '47': 0.0, '39': 2.9259259259259256, 'ah': 0.0, 'is': 0.0, 'the': 2.9259259259259256, '7': 0.0, '54': 0.0, '43': 0.0, '67': 2.9259259259259256, '42': 0.0, '34': 2.9259259259259256, '1': 2.9259259259259256, '57': 0.0, '20': 2.9259259259259256, '16': 0.0, '30': 0.0, '3': 0.0, '49': 0.0, '36': 2.9259259259259256, '32': 2.9259259259259256, '44': 2.9259259259259256, '29': 0.0, '66': 0.0, '21': 0.0, '9': 0.0, 'a': 0.0, '41': 0.0, 'ṱĦãŅ': 0.0, 'This': 0.0, '17': 2.9259259259259256, '6': 0.0, '46': 0.0, '58': 2.9259259259259256, '63': 2.9259259259259256, '10': 0.0, '50': 0.0, '....': 2.9259259259259256, '62': 0.0, '45': 2.9259259259259256, '8': 0.0, '53': 0.0, '15': 0.0, '23': 2.9259259259259256, 'with': 0.0, '51': 2.9259259259259256, '26': 0.0, '13': 0.0, '52': 2.9259259259259256, 'long': 0.0, 'World!': 0.0, '22': 2.9259259259259256, '.....': 2.9259259259259256, 'many': 2.9259259259259256, '27': 0.0, '65': 2.9259259259259256, '48': 0.0, '33': 2.9259259259259256, 'file': 0.0, '55': 0.0, '11': 0.0, '56': 0.0, '12': 0.0, '69': 0.0, 'Each': 0.0, '14': 0.0, '61': 2.9259259259259256, '31': 2.9259259259259256, 'N.E.X.T': 0.0, '28': 2.9259259259259256, '60': 0.0, '5': 0.0, '64': 0.0, '4': 2.9259259259259256, '59': 2.9259259259259256, '2': 0.0, 'WeiRDeR': 0.0, '70': 0.0, '68': 0.0, '19': 2.9259259259259256, 'Hello': 0.0, '38': 0.0, '37': 0.0, 'lines': 2.9259259259259256}), 10),
    ])


test_spam_ratio, test_words, test_spamicity = (0.3333333333333,
 {'Hello':0, 'World':1, 'awesome':2, 'stuff':3, '?':4,},
 [1.0, 0.0, 3.0, 3.0, 0.0])

score += test.Test(
    td3, 'naive_bayes_predict', pred=test.FloatNearPred(1e-1), deadline=1,
    data=[
        (test_spam_ratio, test_words, test_spamicity, 'Hello dude', 1.0/3, 5),
        (test_spam_ratio, test_words, test_spamicity, 'awesome stuff!', 1.0, 2),
        (test_spam_ratio, test_words, test_spamicity, 'awesome awesome awesome', 1.0, 3),
        (test_spam_ratio, test_words, test_spamicity, 'Oh no!', 1.0/3, 2),
        (test_spam_ratio, test_words, test_spamicity, 'awesome ? ? ? awesome ? ?', 0.0, 5),
    ])

def int_or_zero(text):
  try:
    return int(text)
  except ValueError:
    return 0

def test_pred_spam(text):
  x = int_or_zero(text)
  if x == 0:
    return 'w' not in text and 'W' not in text
  for group in [(20, 3), (33.5, 2.5), (44.5, 0.5), (51.5, 0.5), (62.5, 4.5)]:
    if abs(x - group[0]) <= 1e-6 + group[1]:
      return True
  return False

score += test.Test(
    td3, 'naive_bayes_eval', pred=test.FloatListEq, deadline=1,
    data=[
        ('test3_0', lambda x:1, (1.0, 1.0/3), 3),
        ('test3_0', lambda x:0, (0.0, 1.0), 3),
        ('test3_0', lambda sms:'awesome' in sms, (1.0, 1.0), 4),
        ('test3_1', test_pred_spam, (0.8518518518518519,
                                     0.696969696969697), 10),
    ])

score += test.Test(
    td3, 'classify_spam',
    data=[
      ("Have some cash ready? Call us at 805805805 BUY NOW FOR SALE follow www.cheapviagra.com", True, 0),
      ("How was your day? It's been horrible at the office today. I need a hug.", False, 0),
    ])

print('=============================================')
print('SCORE: %d' % int(score/1.27))

print('Note: The last function classify_spam*(..) are NOT scored here: there are merely a couple of examples to help you detect syntax or obvious errors. They will be scored by the teacher, on a hidden "test" dataset. You should tune and evaluate the performance of these functions yourself!')
