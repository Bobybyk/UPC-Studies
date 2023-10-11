#!/bin/pyton3
# coding=utf-8

import test
import math
from scipy.stats import binom as b
import random
td2=test.safe_import('td2')

score = 0.0
N = 1000000

def LooksLikeBinomialButIsnt(x, t):
  ne, nc, nb = t
  if not isinstance(x, list):
    print("FAILED: Expected a list, got %s" % type(x))
    return False
  if len(x) != nb+1:
    print("FAILED: Expected list of size %d, got size %d" % (nb+1, len(x)))
    return False
  if sum(x) != ne:
    print("FAILED: Expected the sum of the returned list to be %d, but got sum=%s" % (
          ne, sum(x)))
    return False
  num_equal = 0
  eps=1e-6
  acc=1.0/math.sqrt(ne)
  for i in range(nb+1):
    ref_r = b.cdf(nc * (i+1)/nb-eps, nc, 0.5) - b.cdf(nc * i/nb-eps, nc, 0.5)
    d=abs(ref_r - x[i]/ne)
    if int(d * ne + 0.5) < 2:
      num_equal += 1
    if d > 10 * ref_r * acc + 1e-2 + acc/5:
      print("FAILED: the values in the returned histogram seem off. Try to plot them")
      print("ne=%s, nc=%s, nb=%s, bucket #%d, ref_ratio=%s, your=%s" % (ne, nc, nb, i, ref_r, x[i]/ne))
      return False
  if ne > 1000 and num_equal == nb+1:
    print("FAILED: please truly simulate the experiment, don't output theoretical counts")
    return False
  return True


score += test.Test(
    td2,
    'simulation_coin',
    pred=LooksLikeBinomialButIsnt,
    data=[(x, y, z, (x, y, z), 2) for x, y, z in [
          (4000, 10, 4), (4000, 100, 10), (333, 33, 3),
          (333, 3, 33), (5000, 26, 89), (4222, 22, 21),
          (4222, 22, 23), (10000, 13, 30),
          (11, 7, 3), (10, 4, 10)]])

score += test.Test(
    td2,
    'proba_normal_var_above',
    pred=test.FloatEq,
    data=[(0, 0.5, 1),
          (1, 0.15865525393145702, 1),
          (2, 0.022750131948179264, 1),
          (3, 0.0013498980316300993, 1),
          (-1, 0.841344746068543, 1),
          (-2, 0.9772498680518207, 1),
          (-3, 0.9986501019683699, 1),
          (6.2, 2.823158037043265e-10, 1),
          (8.3, 5.205569744890289e-17, 1),
          (-12, 1.0, 1),
          (16.1, 1.2755229244501695e-58, 1),
          (20, 2.7536241186063143e-89, 1),
         ])

score += test.Test(
    td2,
    'proba_sample_mean_above_true_mean_by_at_least',
    pred=test.FloatEq,
    data=[([4, 6], 0.5, 0.3085375387259869, 6),
          ([0, 1, 2, 3, 0, 1, 2, 3], 2, 1.1068700615444273e-06, 6),
          ([1, 2, 4, 8, 16], 3, 0.13569800878653004, 5),
          ([3, 3], 0.000001, 0, 1),
          ([3, 3], -0.000001, 1, 1),
          ([3, 3], 0, 0.5, 1),
         ])

score += test.Test(
    td2,
    'standard_percentile',
    pred=test.FloatNearPred(1e-6),
    data=[(0.5, 0, 1),
          (0.84, 0.99445788321, 1),
          (0.16, -0.99445788321, 1),
          (0.9999, 3.71901648546, 1),
          (1e-12, -7.0344838253, 1),
          (0.99999999, 5.61200124235, 1),
          (1e-100, -21.273453561, 1)])

def PairOfFloatsNear(p1, p2):
  return test.FloatNear(p1[0], p2[0], 1e-6) and test.FloatNear(p1[1], p2[1], 1e-6)

score += test.Test(
    td2,
    'confidence_interval_of_mean',
    pred=PairOfFloatsNear,
    data=[([4, 6], 0.05, (3.355146373048527, 6.644853626951473), 1),
          ([4, 6], 0.09, (3.6592449663097835, 6.3407550336902165), 1),
          ([4, 6], 0.3, (4.475599487291959, 5.524400512708041), 1),
          ([0, 1, 2, 3, 0, 1, 2, 3], 0.28, (1.2537045101486752,
                                            1.7462954898513248), 6),
          ([1, 2, 4, 8, 16], 0.23, (4.184694484913311,
                                    8.21530551508669), 5),
          ([1, 2, 4, 8, 16], 0.499995, (6.199965814148143,
                                        6.200034185851857), 5),
          ([3, 3], 0.499994, (3, 3), 1),
          ([3, 3], 0.1, (3, 3), 1),
         ])

print("SCORE: %d" % int(score / 0.8))
