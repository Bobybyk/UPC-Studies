package main

import (
	"fmt"
	"net/http"
	"net"
	"io/ioutil"
	"encoding/json"
)

type jsonAddr struct {
	Host   string `json:"host"`
	Port int64  `json:"port"`
}

func main() {
	url := "https://jch.irif.fr:8443/udp-addresses.json"

	fmt.Println("Récupération des adresses UDP...")

	// création requête HTTP.
	req, err := http.NewRequest("", url, nil)
	if err != nil {
		fmt.Println("Erreur lors de la création de la requête :", err)
		return
	}

	fmt.Println("Création de la requête HTTP...")

	// envoie de la requête HTTP.
	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		fmt.Println("Erreur lors de la requête HTTP :", err)
		return
	}

	// lecture du contenu JSON de la réponse.
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Erreur lors de la lecture des données :", err)
		return
	}

	fmt.Println("Lecture du contenu JSON de la réponse...")

	// parsing du JSON dans la slice de messages.
	var messages []jsonAddr

	err = json.Unmarshal(body, &messages)
	if err != nil {
		fmt.Println("Erreur lors de l'analyse JSON :", err)
		return
	}

	
	// affichage des messages.
	for _, message := range messages {
		fmt.Println("Host : ", message.Host, "\nPort : ", message.Port)
	}

	var udpBody [7]byte
	for i := 0; i < 7; i++ {
		udpBody[i] = 0x00
	}

	// envoie une requête UDP au serveur UDP et affiche le corps de la réponse reçue tout en vérifiant que l'id reçu dans la réponse est égal à l'id émis
	for _, message := range messages {
		url := fmt.Sprintf("%s:%d", message.Host, message.Port)

		// création requête HTTP.
		conn, err := net.ResolveUDPAddr("udp", url)	
		if err != nil {
			fmt.Println("Erreur lors de la création de la connexion :", err)
			return
		}

		// envoie de la requête HTTP.
		socket, err := net.ListenUDP("udp", nil)
		if err != nil {
			fmt.Println("Erreur lors de l'envoie de la requête :", err)
			return
		}

		// send udpBody to server
		n, err := socket.WriteToUDP(udpBody[:], conn)

		msg := make([]byte, 2048)

		

		n, retAddr, err := socket.ReadFromUDP(msg)
		if err != nil {
			fmt.Println("Erreur lors de la lecture des données :", err)
			return
		}

		// parse n bytes of msg (4 first bytes are the id, 1 next byte is the type, 2 next byte is the length, and the rest is the body)
		id := int(msg[0]) << 24 | int(msg[1]) << 16 | int(msg[2]) << 8 | int(msg[3])

		typeMsg := int(msg[4])

		length := int(msg[5]) << 8 | int(msg[6])

        fmt.Println("received from : ", retAddr, "\n    -> id :", id, "\n    -> type :", typeMsg, "\n    -> length : ", length, "\n    -> body : ", string(msg[7:n]))

        /* reply := []byte(fmt.Sprintf("received from you: %v bytes", n))
        n, err = conn.WriteToUDP(reply, retAddr)
        errcheck(err)

        fmt.Println("sent reply %v bytes\n", n) */

	}
}