from collections import defaultdict
from random import random
from math import sqrt

def average(lst):
	val = 0
	t = 0
	for i in lst:
		t += 1
	for e in lst:
		val += e
	return val/t

def median(lst):
	t = 0
	lst.sort()
	for i in lst:
		t += 1
	return lst[t//2]

def occurrences(lst):
	occurrences = defaultdict(int)

	for e in lst:
		occurrences[e] += 1

	return occurrences

def unique(lst):
	unique = []
	occurences = occurrences(lst)

	for e in occurences:
		unique.append(e)
	return unique

def squares(lst):
	index = 0
	for e in lst:
		lst[index] = e*e
		index += 1
	return lst

def uniform(n):
	return int(random() * n)

def stddev(lst):
	avg = average(lst)
	ecart = 0
	for e in lst:
		ecart += (e - avg) * (e - avg)
	ecart = ecart / len(lst)
	ecart = sqrt(ecart)
	return ecart

# n : nombre cours inscrit
# p : proba réussite
def exam_success(n, p):
	success = 0
	for i in range(n):
		if random() < p:
			success += 1
	return success

def monty_hall(change):
	if change:
		if uniform(3) < 2:
			return True
		return False
	elif uniform(3) == 0:
		return True
	return False

# p1 succeed change
# p2 succeed without
def monty_hall_simulation(n):

	if n == 0:
		return (0, 0)

	succeed_c = 0
	succeed_n = 0

	for i in range(n):
		if(monty_hall(True)):
			succeed_c += 1
		if(monty_hall(False)):
			succeed_n += 1

	return (succeed_c/n, succeed_n/n)