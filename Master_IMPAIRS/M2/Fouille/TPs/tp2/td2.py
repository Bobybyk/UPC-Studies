from random import random
from math import erf, erfc, sqrt

def simulation_coin(num_exp, num_coins_per_exp, num_buckets):
    buckets = [0] * (num_buckets+1)
    for i in range(num_exp):
        tails = 0
        tailsRatio = 0
        for j in range(num_coins_per_exp):
            if random() < 0.5:
                tails += 1
        tailsRatio = tails / num_coins_per_exp
        buckets[int(tailsRatio * num_buckets)] += 1
    return buckets

def proba_normal_var_above(value):
	return erfc(value / sqrt(2)) * 0.5

def proba_sample_mean_above_true_mean_by_at_least(sample, delta):
	n = len(sample)
	empirical_avg = sum(sample) / n
	theoretical_avg = empirical_avg - delta
	variance = sum([(x - empirical_avg)**2 for x in sample]) / (n-1)
	ecart_type = sqrt(variance)
	if delta < 0 and variance == 0:
		return 1 
	if delta == 0 and variance == 0:
		return 0.5
	if ecart_type == 0:
		return 0
	return proba_normal_var_above(sqrt(n) * (empirical_avg - theoretical_avg) / ecart_type)