package main

import (
	"crypto/tls"
	"net/http"
	"time"
	"fmt"
	"io"
	"strings"
)

func getChatIds() []string {
	transport := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{
		Transport: transport,
		Timeout:   50 * time.Second,
	}

	resp, err := client.Get("https://jch.irif.fr:8082/chat")
	if err != nil {
		fmt.Println("Error:", err)
		return make([]string, 0)
	}
	defer resp.Body.Close()

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Error:", err)
		return make([]string, 0)
	}

	list := strings.Split(string(body), "\n")

	return list

}

func getMsgById(id int) string {
	transport := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{
		Transport: transport,
		Timeout:   50 * time.Second,
	}

	msg, err := client.Get("https://jch.irif.fr:8082/chat/" + string(id))
	if err != nil {
		fmt.Println("Error:", err)
		return ""
	}
	defer msg.Body.Close()

	body, err2 := io.ReadAll(msg.Body)
	if err2 != nil {
		fmt.Println("Error:", err2)
		return ""
	}

	return string(body)
}

func getChatMsg(list[] string) []string {
	transport := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{
		Transport: transport,
		Timeout:   50 * time.Second,
	}

	listMsg := make([]string, len(list))

	for i := 0 ; i < len(list) ; i++ {
		if (list[i] == "") {
			continue
		}
		msg, err := client.Get("https://jch.irif.fr:8082/chat/" + list[i])
		if err != nil {
			fmt.Println("Error:", err)
			return make([]string, 0)
		}
		defer msg.Body.Close()

		body, err2 := io.ReadAll(msg.Body)
		if err2 != nil {
			fmt.Println("Error:", err2)
			return make([]string, 0)
		}

		listMsg[i] = string(body)
	}

	return listMsg
}

func printList(list []string) {
	for i := 0 ; i < len(list) ; i++ {
		fmt.Println(list[i])
	}
}

func printMsg(msg string) {
	fmt.Println(msg)
}

func postMsg(msg string) {
	transport := &http.Transport{
		TLSClientConfig: &tls.Config{InsecureSkipVerify: true},
	}
	client := &http.Client{
		Transport: transport,
		Timeout:   50 * time.Second,
	}

	resp, err := client.Post("https://jch.irif.fr:8082/chat/", "text/plain", strings.NewReader(msg))
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer resp.Body.Close()
}

func main() {
	var listIds []string = getChatIds()
	var listMsg []string = getChatMsg(listIds)
	printList(listMsg)
	var msgToPost = ""
	var previousMsg = ""

	for {
		var listIds2 []string = getChatIds()

		if (len(listIds) != len(listIds2)) {
			var listMsg []string = getChatMsg(listIds2)

			for i := len(listIds)-1 ; i < len(listIds2) ; i++ {
				printMsg(listMsg[i])
			}
			listIds = listIds2
		}
		fmt.Scanf("%s", &msgToPost)
		if (msgToPost != previousMsg) {
			postMsg(msgToPost)
			previousMsg = msgToPost
		}
	}
}