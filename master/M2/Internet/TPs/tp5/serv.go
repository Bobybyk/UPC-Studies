package main

import (
	"fmt"
	"net/http"
	"log"
)

func main() {
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		auth := r.Header.Get("Authorization")

		if auth == "" {
			w.Header().Set("WWW-Authenticate", `Basic realm="tp1"`)
			http.Error(w, "Unauthorized", http.StatusUnauthorized)
			return
		}

		log.Println("Authorization:", auth)
		fmt.Fprintf(w, "Hello, world!")
	})

	certFile := "cert.pem"
	keyFile := "key.pem"

	err := http.ListenAndServeTLS(":8080", certFile, keyFile, nil)
	if err != nil {
		fmt.Println(err)
	}
}
