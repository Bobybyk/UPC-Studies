package main

import (
	"fmt"
	"net/http"
	"log"
)

func main() {
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		username, password, ok := r.BasicAuth()

		if !ok || username != "einstein" || password != "elsa" {
			w.Header().Set("WWW-Authenticate", `Basic realm="tp1"`)
			http.Error(w, "Unauthorized", http.StatusUnauthorized)
			return
	}

		log.Println("Authorized user:", username)
		fmt.Fprintf(w, "Hello, world!")
	})

	// Générer un certificat autonome pour le serveur (usage de test uniquement)
	certFile := "cert.pem"
	keyFile := "key.pem"

	// Démarrez le serveur HTTPS
	err := http.ListenAndServeTLS(":8080", certFile, keyFile, nil)
	if err != nil {
		fmt.Println(err)
	}
}
