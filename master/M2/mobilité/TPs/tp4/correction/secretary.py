from __future__ import division
#import numpy as np
candidates = np.arange(1, n+1)
# simulate random order of candidates being interviewed
np.random.shuffle(candidates)

def choose_candidate(n, reject=np.e):
    '''Choose a candidate from a list of n candidates using
    a specified strategy.
    reject: percentage of candidates to initially reject (optimal strategy by default)
    '''

    candidates = np.arange(1, n+1)
    np.random.shuffle(candidates)
    
    if reject == np.e:
        stop = int(round(n/reject))
    else:
        stop = int(round(reject*n/100))

    best_from_rejected = np.min(candidates[:stop])
    rest = candidates[stop:]
    
    try:
    	return rest[rest < best_from_rejected][0]
    except IndexError:
    	return candidates[-1]

# choose from 100 candidates and run simulation 100,000 times
sim = np.array([choose_candidate(n=100) for i in range(100000)])

##plt.figure(figsize=(10, 6))
##plt.hist(sim, bins=100)
##plt.xticks(np.arange(0, 101, 10))
##plt.ylim(0, 40000)
##plt.xlabel('Chosen candidate')
##plt.ylabel('frequency')
##plt.show()
