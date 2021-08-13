package main

import (
	"fmt"
	"os"
	"bufio"
);

func main() {
	reader := bufio.NewReader(os.Stdin)
	fmt.Print("Enter name: ")
	text, _ := reader.ReadString('\n')
	fmt.Printf("Hello, %s!", text)
}
