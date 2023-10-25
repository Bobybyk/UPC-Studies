package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"io/ioutil"
	"time"
)

type jsonMessage struct {
	Id   string `json:"id"`
	Time int64  `json:"time"`
	Body string `json:"body"`
}

func main() {
	var lastETag string

	for {
		url := "https://jch.irif.fr:8082/chat/messages.json?count=4"

		// création requête HTTP.
		req, err := http.NewRequest("GET", url, nil)
		if err != nil {
			fmt.Println("Erreur lors de la création de la requête :", err)
			return
		}

		// ETag de la dernière réponse dans l'en-tête "If-None-Match".
		if lastETag != "" {
			req.Header.Add("If-None-Match", lastETag)
		}

		// envoie de la requête HTTP.
		resp, err := http.DefaultClient.Do(req)
		if err != nil {
			fmt.Println("Erreur lors de la requête HTTP :", err)
			return
		}

		// gestion des erreurs, code de statut 304 (Not Modified), ce qui signifie que la liste de messages n'a pas changé.
		if resp.StatusCode == http.StatusNotModified {
			fmt.Println("Aucun changement dans la liste de messages.")
		} else if resp.StatusCode == http.StatusOK {
			// Si le code de statut est 200 (OK), cela signifie que la liste de messages a changé.
			fmt.Println("La liste de messages a été mise à jour. Voici les derniers 4 messages :")

			// lecture du contenu JSON de la réponse.
			body, err := ioutil.ReadAll(resp.Body)
			if err != nil {
				fmt.Println("Erreur lors de la lecture des données :", err)
				return
			}

			// analysee du JSON dans la slice de messages.
			var messages []jsonMessage
			err = json.Unmarshal(body, &messages)
			if err != nil {
				fmt.Println("Erreur lors de l'analyse JSON :", err)
				return
			}

			// affichage des 4 derniers messages.
			for i := len(messages) - 1; i >= 0 && len(messages)-i <= 4; i-- {
				message := messages[i]
				fmt.Printf("ID: %s\n", message.Id)
				fmt.Printf("Temps: %d\n", message.Time)
				fmt.Printf("Message: %s\n\n", message.Body)
			}

			// mise à jour de la valeur de lastETag avec le nouvel ETag de la réponse.
			lastETag = resp.Header.Get("ETag")
		} else {
			fmt.Printf("Code de statut inattendu : %d\n", resp.StatusCode)
		}

		// cool down
		time.Sleep(5 * time.Second)
	}
}
