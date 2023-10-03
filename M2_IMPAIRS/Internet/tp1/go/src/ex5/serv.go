package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {
	http.HandleFunc("/compute", compute)
	err := http.ListenAndServe(":8080", nil)
	log.Fatal("ListenAndServe: ", err)
}