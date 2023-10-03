package main

import (
	"fmt"
	"html/template"
	"net/http"
	"strconv"
)

func isPrime(num int64) bool {
	if num <= 1 {
		return false
	}
	for i := int64(2); i*i <= num; i++ {
		if num%i == 0 {
			return false
		}
	}
	return true
}

func primeNumbers(n int64) []int64 {
	primes := []int64{}
	for i := int64(2); i <= n; i++ {
		if isPrime(i) {
			primes = append(primes, i)
		}
	}
	return primes
}

func main() {
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		if r.Method == http.MethodGet {
			if nStr := r.URL.Query().Get("n"); nStr != "" {
				n, err := strconv.ParseInt(nStr, 10, 64)
				if err != nil {
					fmt.Fprintf(w, "Erreur : Veuillez entrer un entier valide.")
					return
				}

				primes := primeNumbers(n)
				fmt.Fprintf(w, "Nombres premiers entre 2 et %d : %v", n, primes)
				return
			}

			tmpl := `
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
    <h1>Donne un nombre bg</h1>
    <form action="/" method="get">
        <input type="text" id="n" name="n">
        <input type="submit" value="Calculer">
    </form>
</body>
</html>`
			t, _ := template.New("webpage").Parse(tmpl)
			t.Execute(w, nil)
		}
	})

	http.ListenAndServe(":8080", nil)
}
