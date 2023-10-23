package main

import (
	"fmt"
	"github.com/gorilla/websocket"
)

type jsonMessage struct {
	Type string `json:"type"`
	Message string `json:"message,omitempty"`
	Messages []chatMessage `json:"messages,omitempty"`
	Count int `json:"count,omitempty"`
	Error string `json:"error,omitempty"`
}
type chatMessage struct {
	Id string `json:"id,omitempty"`
	Time int64 `json:"time,omitempty"`
	Body string `json:"body"`
}

func main() {
	d := websocket.Dialer{}

	c, _, err := d.Dial("wss://jch.irif.fr:8082/chat/ws", nil)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer c.Close()

	err = c.WriteMessage(websocket.TextMessage, []byte("{\"type\": \"get\", \"count\": 5}"))
	if err != nil {
		fmt.Println(err)
		return
	}
	for {
		_, m, err := c.ReadMessage()
		if err != nil {
			fmt.Println(err)
			return
		}
		fmt.Println(string(m))
	}
}