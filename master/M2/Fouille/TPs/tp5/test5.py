#!/bin/pyton3
# coding=utf-8

import test
td5=test.safe_import('td5', deadline=30)

import math
import os


def test_read_dataset_ignore_eol(l1, l2):
  if not isinstance(l1, list):
    return False
  if not isinstance(l2, list):
    return False
  if len(l1) != len(l2):
    return False
  for i in range(len(l1)):
    if l1[i][0] != l2[i][0]:
      return False
    a = l1[i][1]
    b = l2[i][1]
    if a != b and a != b + '\n' and a != b + '\r' and a != b + '\r\n':
      return False
  return True

score = 0.0

expected_read_dataset = []
expected_spam_count = []
NUM_TESTS = 5
for i in range(NUM_TESTS):
  with open('tmp_%d' % i, 'w') as file:
    num = (i * 578123) % 1009
    count = 0
    dataset = []
    for j in range(num):
      text = 'This is some random gibberish #%d' % j
      spam = 1 if (j * 379871) % 1009 < 200 else 0
      count += spam
      dataset.append((spam, text))
      file.write('%s\t%s\n' % ('spam' if spam == 1 else 'ham', text))
    expected_read_dataset.append(dataset)
    expected_spam_count.append(count)

score += test.Test(
    td5, 'read_dataset', deadline=3,
    pred=test_read_dataset_ignore_eol,
    data=[('tmp_%d' % i, expected_read_dataset[i], 20 / NUM_TESTS)
          for i in reversed(range(NUM_TESTS))])

score += test.Test(
    td5, 'read_dataset', deadline=3,
    data=[('tmp_%d' % i, expected_read_dataset[i], 10 / NUM_TESTS)
          for i in reversed(range(NUM_TESTS))])

score += test.Test(
    td5, 'spam_count', deadline=3,
    data=[(expected_read_dataset[i], expected_spam_count[i], 30 / NUM_TESTS)
          for i in reversed(range(NUM_TESTS))])

for i in range(NUM_TESTS):
  try:
    os.remove('tmp_%d' % i)
  except OSError:
    pass

import scipy

def test_transform_text(result, reference):
  X, Y = result
  if not isinstance(X, scipy.sparse.csr.csr_matrix):
    print("X isn't a scipy.sparse.csr.csr_matrix")
    return False
  ref_rows, ref_cols, ref_nonzeros, ref_Y = reference
  nonzeros = 0
  rows = 0
  cols = 0
  if len(ref_Y) > 0:
    if len(Y) != len(ref_Y):
      print("Y doesn't have the right length.")
      return False
    for i in range(len(Y)):
      if Y[i] != ref_Y[i]:
        print("Y[%d] is %s, should be %s" % (i, Y[i], ref_Y[i]))
        return False
  for row,col in zip(*X.nonzero()):
    #print('[%d][%d] = %s' % (row, col, X[row,col]))
    nonzeros += 1
    if row + 1 > rows:
      rows = row+1
    if col + 1 > cols:
      cols = col+1
  if ref_rows and rows != ref_rows:
    print('X has %s rows, should have %s' % (rows, ref_rows))
    return False
  if ref_cols and cols != ref_cols:
    print('X has %s cols, should have %s' % (cols, ref_cols))
    return False
  if ref_nonzeros and nonzeros != ref_nonzeros:
    print('X has %d nonzeros, should have %d' % (nonzeros, ref_nonzeros))
    return False
  return True

example =  [(0, 'Hello this is some text'),
            (1, 'Another text'),
            (0, 'Is this really some ham'),
            (1, 'No, this is spam'),
            (1, 'Or is it ?')]

score += test.Test(
    td5, 'transform_text', deadline=3, pred=test_transform_text,
    data=[
      (example, (5, 0, 0, []), 10),
      (example, (5, 0, 0, [0,1,0,1,1]), 10),
      (example, (5, 12, 0, [0,1,0,1,1]), 10),
      (example, (5, 12, 19, [0,1,0,1,1]), 10),
    ])



print('=============================================')
print('SCORE: %d' % score)
