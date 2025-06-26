package main

import (
	"fmt"
	"net/http"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Endpoint V1/ reached successfully!")
}

func main() {
	http.HandleFunc("/v1", helloHandler)
	http.ListenAndServe(":9090", nil)
}
