package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
)

type TokenResponse struct {
	Token string `json:"token"`
}

func main() {
	// Remplacez "xxx" par votre nom d'utilisateur et "yyy" par "Rosebud".
	data := map[string]string{
		"username": "boby",
		"password": "Rosebud",
	}

	// Encodez les données JSON pour la requête POST
	jsonData, err := json.Marshal(data)
	if err != nil {
		fmt.Println("Erreur lors de l'encodage JSON:", err)
		return
	}

	// Effectuez la requête POST pour obtenir le token
	postURL := "https://jch.irif.fr:8082/get-token"
	resp, err := http.Post(postURL, "application/json", bytes.NewBuffer(jsonData))
	if err != nil {
		fmt.Println("Erreur lors de la requête POST:", err)
		return
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		fmt.Println("La requête POST a échoué. Code de réponse:", resp.Status)
		return
	}

	var tokenResponse TokenResponse
	decoder := json.NewDecoder(resp.Body)
	if err := decoder.Decode(&tokenResponse); err != nil {
		fmt.Println("Erreur lors de la lecture de la réponse JSON:", err)
		return
	}

	// Utilisez le token pour effectuer la requête GET
	getURL := "https://jch.irif.fr:8443/top-secret"
	req, err := http.NewRequest("GET", getURL, nil)
	if err != nil {
		fmt.Println("Erreur lors de la création de la requête GET:", err)
		return
	}

	// Ajoutez l'entête d'autorisation Bearer avec le token obtenu
	req.Header.Add("Authorization", "Bearer "+tokenResponse.Token)

	// Effectuez la requête GET
	resp, err = http.DefaultClient.Do(req)
	if err != nil {
		fmt.Println("Erreur lors de la requête GET:", err)
		return
	}
	defer resp.Body.Close()

	// Vérifiez la réponse
	if resp.StatusCode == http.StatusOK {
		fmt.Println("Requête GET réussie. Code de réponse:", resp.Status)
	} else {
		fmt.Println("La requête GET a échoué. Code de réponse:", resp.Status)
	}
}
