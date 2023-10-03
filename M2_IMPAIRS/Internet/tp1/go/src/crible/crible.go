package main

import "fmt"

func sieveOfEratosthenes(limit int) []int {
	// Créer un tableau pour marquer les nombres comme premiers ou non.
	isPrime := make([]bool, limit+1)
	for i := 2; i <= limit; i++ {
		isPrime[i] = true
	}

	// Appliquer l'algorithme du crible d'Eratosthène.
	for p := 2; p*p <= limit; p++ {
		if isPrime[p] == true {
			for i := p * p; i <= limit; i += p {
				isPrime[i] = false
			}
		}
	}

	// Construire la liste des nombres premiers.
	primes := []int{}
	for p := 2; p <= limit; p++ {
		if isPrime[p] {
			primes = append(primes, p)
		}
	}

	return primes
}

func main() {
	limit := 1000
	primes := sieveOfEratosthenes(limit)

	fmt.Println("Liste des nombres premiers inférieurs à", limit, ":")
	for _, prime := range primes {
		fmt.Println(prime)
	}
}
