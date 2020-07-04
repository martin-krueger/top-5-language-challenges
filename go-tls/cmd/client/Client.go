package main

import (
	"bufio"
	"crypto/tls"
	"crypto/x509"
	"fmt"
	"io/ioutil"
	"log"
	"os"
)

func main() {
	log.SetFlags(log.Lshortfile)

	cer, err := tls.LoadX509KeyPair("client.cert", "client.key")
	if err != nil {
		log.Fatal(err)
	}

	caCert, err := ioutil.ReadFile("client.ca")
	pool := x509.NewCertPool()
	pool.AppendCertsFromPEM(caCert)

	conf := &tls.Config{
		InsecureSkipVerify: true,
		Certificates:       []tls.Certificate{cer},
		ClientCAs:          pool,
	}

	conn, err := tls.Dial("tcp", "localhost:8080", conf)
	if err != nil {
		log.Println(err)
		return
	}
	defer conn.Close()

	reader := bufio.NewReader(os.Stdin)
	for {
		fmt.Print("> ")
		text, _ := reader.ReadString('\n')

		n, err := conn.Write([]byte(text))
		if err != nil {
			log.Println(n, err)
			return
		}

		buf := make([]byte, 100)
		n, err = conn.Read(buf)
		if err != nil {
			log.Println(n, err)
			return
		}

		println("R", string(buf[:n]))

	}
}
