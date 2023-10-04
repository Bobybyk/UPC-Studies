#!/bin/pyton3
# coding=utf-8

import test
td1=test.safe_import('td1')

import collections
import math
import time

score = 0.0
N = 1000000

score += test.Test(
    td1,
    'average',
    data=[([1, 3, 2], 2, 1),
          ([0, 0, 0, 0], 0, 1),
          ([-3, 1], -1, 1),
          ([6], 6, 1),
          ([1, 2], 1.5, 1),
          ([x for x in range(N)], float(N - 1) / 2, 3)],
    penalty_per_keyword={'sum':3, 'len':3})

def is_median(l, med):
  lo = 0
  hi = 0
  for i in l:
    if i <= med:
      lo += 1
    if i >= med:
      hi += 1
  n = len(l)
  return lo * 2 >= n and hi * 2 >= n

def is_bad_sample2(l, size_avg_med):
  size, avg, med = size_avg_med
  if len(l) != size:
    print('Wrong size %d: should be %d' % (len(l), size))
    return False
  if abs(avg - sum(l)/len(l)) > 1e-3:
    print('Wrong average %f: should be %f' % (
          sum(l)/len(l), avg))
    return False
  if not is_median(l, med):
    print('Wrong median %.15f: %d are ≤, %d are ≥' %
          (med, len([1 for i in l if i <= med]),
           len([1 for i in l if i >= med])))
    return False
  return True

def is_bad_sample(l, size_and_delta):
  size, delta = size_and_delta
  if len(l) != size:
    print('Wrong size %d: should be %d' % (len(l), size))
    return False
  avg = sum(l)/size
  ls = sorted(l)
  med_lo = ls[(size-1)//2]
  med_hi = ls[size//2]
  return avg - med_lo >= delta - 1e-6 and avg - med_hi <= delta + 1e-6

score += test.Test(
    td1,
    'median',
    pred=test.IsBetween,
    data=[([1, 4, 5], (4, 4), 1),
          ([5, 8, -1, 4, 6, 7], (5, 6), 1),
          ([0, 0, 0, 0], (0, 0), 1),
          ([-3, 1], (-3, 1), 1),
          ([1, 2], (1, 2), 1),
          ([-6], (-6, -6), 1),
          ([(1009 * x) % 100007 for x in range(N)], (50002, 50002), 3)])

score += test.Test(
    td1,
    'bad_sample',
    pred=is_bad_sample,
    data=[(2, 0, (2, 0), 1),
          (2, 1, (2, 1), 1),
          (3, 10, (3, 10), 1),
          (3, -100, (3, -100), 1),
          (50, 123.456, (50, 123.456), 1),
          (999999, -87654.3, (999999, -87654.3), 1),
    ])

score += test.Test(
    td1,
    'bad_sample2',
    pred=is_bad_sample2,
    data=[(2, 0, 0, (2, 0, 0), 1),
          (2, 1, 3, (2, 1, 3), 1),
          (3, 10, 5, (3, 10, 5), 1),
          (3, -100, 3400, (3, -100, 3400), 1),
          (50, 123.456, 456.123, (50, 123.456, 456.123), 1),
          (999999, -87654.3, 987654.3, (999999, -87654.3, 987654.3), 1),
    ])

score += test.Test(
    td1,
    'occurrences',
    data=[([1, 4, 5], {1: 1, 4: 1, 5: 1}, 1),
          ([5, 8, -1, 8, 8, 5], {5: 2, -1: 1, 8: 3}, 1),
          ([0, 0, 0, 0], {0: 4}, 1),
          ([], {}, 1),
          ([-513 for _ in range(N)], {-513: N}, 3),
          ([x for x in range(N)], dict([(x, 1) for x in range(N)]), 3)])

l = [(x * 19652) % 1000000007 for x in range(N)]
score += test.Test(
    td1,
    'unique',
    data=[([1, 4, 5], [1, 4, 5], 1),
          ([5], [5], 1),
          ([3, 3, 3, 3], [3], 1),
          ([1, 2, 1, 2, 1, 1, 2, 2, 1, 2], [1, 2], 1),
          ([5, 1, 1, 3, 5, 5, 5, 2, 2, 0, 5, 3, 0], [5, 1, 3, 2, 0], 5),
          ([-513 for _ in range(N)], [-513], 1),
          (l + l, l, 4)])

score += test.Test(
    td1,
    'squares',
    pred=test.FloatListEq,
    data=[([1, 4, 5], [1, 16, 25], 1),
          ([-5], [25], 1),
          ([0], [0], 1),
          ([3.1, 3.2, 3.3], [9.61, 10.24, 10.89], 1)])

score += test.Test(
    td1, 
    'stddev',
    pred=test.FloatEq,
    data=[([1], 0, 1),
          ([1, 3, 1, 3], 1, 1),
          ([0], 0, 1),
          ([3.1, 3.2, 3.3], 0.0816496580928, 1),
          ([-4, -9, 21.5, 74.2, 99.9, 12345.678], 4587.5226655, 1),
          ([x*0.01 for x in l], 2876023.9943, 1),
         ])

l_small = [x % 1000009 for x in l[:N//10]]
score += test.Test(
    td1,
    'quicksort',
    data=[([4, 1, 5], [1, 4, 5], 1),
          ([-5], [-5], 1),
          ([742, -32, 98, 11, 94, 11, 43, 11, -32, 98, 12, -1],
           [-32, -32, -1, 11, 11, 11, 12, 43, 94, 98, 98, 742], 3),
          (l_small, sorted(l_small), 3),
          ([], [], 2)],
    penalty_per_keyword={'sort':10, 'sorted':10},
    )

# Tests whether a set of values *seems* to observe a uniform distribution of
# integers in [0..n]. Returns an empty string if it seems OK, or a human
# readable error description if it doesn't.
def find_error_in_uniform_int_distrib(vals, n):
  try:
    size = len(vals)
    occurrences = collections.defaultdict(int)
    lo, hi = test.simple_binomial_confidence_interval(size, 1.0/(n+1), 1e-6)
    for v in values:
      occurrences[v] += 1
    if set(occurrences.keys()) != set(range(n+1)):
      return "The set of values returned by your distribution isn't {0,..,%d}" % n
    for value, count in occurrences.items():
      if count < lo or count > hi:
        return 'Value %d seems under- or over-represented: count=%d (expected count=%s)' % (
            value, count, mean)
    return ''
  except Exception as e:
    return 'Caught exception: %s' % e

# Custom test for 'uniform'
if test.has_function(td1, 'uniform'):
  test.PrintSep()
  print('Testing uniform()...')
  cheated = False
  if 'random' in dir(td1):
    for method in dir(td1.random):
      if method[:2] != '__' or method[-2:] != '__':
        print("FAILED: You imported more from the 'random' module than just" +
              ' random.random (detected method: random.%s)' % method)
        cheated = True
        break
  zeroarg = False
  onearg = False
  try:
    td1.uniform()
    zeroarg = True
  except TypeError:
    try:
      td1.uniform(5)
      onearg = True
    except Exception as e:
      print('FAILED: uniform() not correctly implemented: %s' % e)
  values = None
  points = []
  t = 0
  if zeroarg:
    t = time.time()
    values = [td1.uniform() for _ in range(10000)]
    t = time.time() - t
    points = [(5,6), (1,4), (2,2), (6,4), (4,3)]
  elif onearg:
    t = time.time()
    values = [td1.uniform(29) for _ in range(10000)]
    t = time.time() - t
    points = [(28,10), (27,5), (29,6)]
  if values and not cheated:
    t_multiplier = test.ScoreTime(
        [(5e-3,1.0), (1e-2,0.8), (1e-1,0.5), (1,0.0)], t)
    print('Your time score multiplier is %s [time:%s]' % (t_multiplier, t))
    main_err_msg = None
    for (n, s) in points:
      err = find_error_in_uniform_int_distrib(values, n)
      if not err:
        if main_err_msg:
          print("PARTIAL SUCCESS: You implemented a uniform distribution over" +
                " {0,..,%d} instead of {0,..,%d}" % (n, points[0][0]))
          main_err_msg = None
        else:
          print('SUCCESS: You implemented uniform distribution over {0,..,%d}' %
                n)
         
        score += s
        break
      else:
        if not main_err_msg:
          main_err_msg = err
    if main_err_msg:
      print("FAILED: %s" % main_err_msg) 

lo, hi = test.simple_binomial_confidence_interval(1000000, 0.4, 1e-6)
score += test.Test(
    td1,
    'exam_success',
    pred=test.InInterval,
    deadline=10,
    data=[(100, 0.5, test.simple_binomial_confidence_interval(100, 0.5, 1e-5), 2),
          (1000000, 0.4, [lo, 400000, 400001, hi], 2),
          (1000000, 0.4, [lo, 400000, 400001, hi], 2),
          (1000000, 0.4, [lo, 400000, 400001, hi], 2),
          (100, 0.1, test.simple_binomial_confidence_interval(100, 0.1, 1e-5), 1),
          (100, 0.9, test.simple_binomial_confidence_interval(100, 0.9, 1e-5), 1)])


def is_binomial(zero_ones, p):
  """Very very rough and bad approximation"""
  num = 0
  for v in zero_ones:
    if v == 1:
      num += 1
    elif v != 0:
      return False
  ref = len(zero_ones) * p
  return abs(num - ref) < 4 * math.sqrt(ref)


score += test.Test(
    td1,
    'monty_hall',
    pred=is_binomial,
    repeat=10000,
    data=[(False, 1.0/3, 5),
          (True, 2.0/3, 5)])


def pairwise_in_interval(p, p_i):
  x, y = p
  i1, i2 = p_i
  return test.InInterval(x, i1) and test.InInterval(y, i2)


lo, hi = test.simple_binomial_confidence_interval(100000, 2.0/3, 1e-6)
lo *= 1e-5
hi *= 1e-5
score += test.Test(
    td1,
    'monty_hall_simulation',
    pred=pairwise_in_interval,
    data=[(1000, ([x*0.001 for x in test.simple_binomial_confidence_interval(1000, 2.0/3, 1e-5)],
                  [x*0.001 for x in test.simple_binomial_confidence_interval(1000, 1.0/3, 1e-5)]), 7),
          (100000, ([lo,.66666,.66668,hi],[1-hi,.33333,.33335,1-lo]), 3),
          (100000, ([lo,.66666,.66668,hi],[1-hi,.33333,.33335,1-lo]), 3),
          (100000, ([lo,.66666,.66668,hi],[1-hi,.33333,.33335,1-lo]), 3),
          (100000, ([lo,.66666,.66668,hi],[1-hi,.33333,.33335,1-lo]), 3)])


score = int(score / 1.22)

print("SCORE: %d" % int(score))
