// package main

// import (
// 	"net/http"

// 	"github.com/gin-gonic/gin"
// )

// type album struct {
// 	ID     string  `json:"id"`
// 	Title  string  `json:"title"`
// 	Artist string  `json:"artist"`
// 	Price  float64 `json:"price"`
// }

// func main() {
// 	router := gin.Default()
// 	router.GET("/albums", getAlbums)

// 	router.Run("localhost:8080")
// }

// func HealthCheckHandler() {
// 	router := gin.Default()
// 	router.GET("/", healthCheck)

// 	router.Run("localhost:8080")
// }

// // func HealthCheckHandler(c *gin.Context) {
// // 	response := HealthCheckResponse{Status: "OK"}
// // 	c.JSON(http.StatusOK, response)
// // }

// // albums slice to seed record album data.
// var albums = []album{
// 	{ID: "1", Title: "Blue Train", Artist: "John Coltrane", Price: 56.99},
// 	{ID: "2", Title: "Jeru", Artist: "Gerry Mulligan", Price: 17.99},
// 	{ID: "3", Title: "Sarah Vaughan and Clifford Brown", Artist: "Sarah Vaughan", Price: 39.99},
// }

// // getAlbums responds with the list of all albums as JSON.
// func getAlbums(c *gin.Context) {
// 	c.IndentedJSON(http.StatusOK, albums)
// }

// func healthCheck(c *gin.Context) {
// 	c.IndentedJSON(http.StatusOK, "hello word")
// }

package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type album struct {
	ID     string  `json:"id"`
	Title  string  `json:"title"`
	Artist string  `json:"artist"`
	Price  float64 `json:"price"`
}

func main() {
	router := gin.Default()

	// Add a home endpoint with a welcome message
	router.GET("/", welcome)

	router.GET("/albums", getAlbums)

	router.Run("localhost:8080")
}

// welcome responds with a welcome message.
func welcome(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, gin.H{"message": "Welcome to my Go API app!"})
}

// albums slice to seed record album data.
var albums = []album{
	{ID: "1", Title: "Blue Train", Artist: "John Coltrane", Price: 56.99},
	{ID: "2", Title: "Jeru", Artist: "Gerry Mulligan", Price: 17.99},
	{ID: "3", Title: "Sarah Vaughan and Clifford Brown", Artist: "Sarah Vaughan", Price: 39.99},
}

// getAlbums responds with the list of all albums as JSON.
func getAlbums(c *gin.Context) {
	c.IndentedJSON(http.StatusOK, albums)
}
