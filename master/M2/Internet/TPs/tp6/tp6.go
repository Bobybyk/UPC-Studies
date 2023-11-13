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

func reqToByte(msgToSnd Req) []byte {
    idBytes := make([]byte, 4)
    typeBytes := make([]byte, 4)
    lengthBytes := make([]byte, 4)
    bodyBytes := []byte(msgToSnd.Body)

    binary.LittleEndian.PutUint32(idBytes, uint32(msgToSnd.Id))
    binary.LittleEndian.PutUint32(typeBytes, uint32(msgToSnd.Type))
    binary.LittleEndian.PutUint32(lengthBytes, uint32(msgToSnd.Length))

    msgBytes := append(idBytes, typeBytes...)
    msgBytes = append(msgBytes, lengthBytes...)
    msgBytes = append(msgBytes, bodyBytes...)

    return msgBytes
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
		conn, err := net.Dial("udp", url)	
		if err != nil {
			fmt.Println("Erreur lors de la création de la connexion :", err)
			return
		}

		// envoie de la requête HTTP.
		_, err = conn.Write(udpBody[:])
		if err != nil {
			fmt.Println("Erreur lors de l'envoie de la requête :", err)
			return
		}

		rec := make([]byte, 256)

		// lecture du contenu JSON de la réponse.
		_, err = conn.Read(rec)
		if err != nil {
			fmt.Println("Erreur lors de la lecture des données :", err)
			return
		}

		// parsing des trois premiers entiers de rec et vérification de l'id (4 premiers octets)
		var id int32

		id = int32(rec[0]) << 24 | int32(rec[1]) << 16 | int32(rec[2]) << 8 | int32(rec[3])

		fmt.Println("Id : ", id)

		fmt.Println("Reponse : ", string(rec))

		


	}
}