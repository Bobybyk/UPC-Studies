package main

import (
	"fmt"
	"log"
	"net/http"
	"strings"
)

func main() {
	http.HandleFunc("/hello.text", helloText)
	http.HandleFunc("/hello.html", helloHtml)
	http.HandleFunc("/name-get", nameGet)
	http.HandleFunc("/name-post", namePost)
	http.HandleFunc("/request-name", nameReq)
	err := http.ListenAndServe(":8080", nil)
	log.Fatal("ListenAndServe: ", err)
}

func nameReq(w http.ResponseWriter, r *http.Request) {
	if r.Method != "HEAD" && r.Method != "GET" {
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
		return
	}
	r.ParseForm()
	var data strings.Builder 
	data.WriteString("<!DOCTYPE html><html><head></head><body><p>Votre nom est ")
	data.WriteString(r.Form.Get("name"))
	data.WriteString("</p></body></html>")
	w.Header().Set("Content-Type", "text/html; charset=utf-8")
	fmt.Fprintf(w, data.String())
}

func helloText(w http.ResponseWriter, r *http.Request) {
	if r.Method != "HEAD" && r.Method != "GET" {
		http.Error(w, "method no allowed", http.StatusMethodNotAllowed)
		return
	}
	w.Header().Set("Content-Type", "text/plain; charset=utf-8")
}

func helloHtml(w http.ResponseWriter, r *http.Request) {
	const data = `<!DOCTYPE html>
<html>
<head></head>
<body>
<p>Bonjour!</p>
</body>
</html>`
	if r.Method != "HEAD" && r.Method != "GET" {
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
		return
	}
	w.Header().Set("Content-Type", "text/html; charset=utf-8")
	fmt.Fprintf(w, data)
}

func nameGet(w http.ResponseWriter, r *http.Request) {
	const data = `<!DOCTYPE html>
<html>
<head></head>
<body>
<form action="/request-name" method="get">
Votre nom: <input type="text" name="name"/> <input type="submit"/></form>
</body>
</html>`
	if r.Method != "HEAD" && r.Method != "GET" {
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
		return
	}
	w.Header().Set("Content-Type", "text/html; charset=utf-8")
	fmt.Fprintf(w, data)
}

func namePost(w http.ResponseWriter, r *http.Request) {
	const data = `<!DOCTYPE html>
<html>
<head></head>
<body>
<form action="/request-name-post" method="post">
Votre nom: <input type="text" name="name"/> <input type="submit"/></form>
</body>
</html>`
	if r.Method != "HEAD" && r.Method != "GET" {
		http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
		return
	}
	w.Header().Set("Content-Type", "text/html; charset=utf-8")
	fmt.Fprintf(w, data)
}
