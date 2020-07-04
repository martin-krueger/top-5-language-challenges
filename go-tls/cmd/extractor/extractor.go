package main

import (
	"io/ioutil"
	"log"
	"os"
	"regexp"
)

func main() {
	file := os.Args[1]
	role := os.Args[2]

	data, err := ioutil.ReadFile(file)

	if err != nil {
		log.Fatal(err)
	}

	s := string(data)

	cert(s, role)
	ca(s, role)
	key(s, role)
}

func cert(s string, role string) {
	regex := regexp.MustCompile("certificate.*((?sU:-----BEGIN CERTIFICATE-----.*-----END CERTIFICATE-----))")
	cert := regex.FindAllStringSubmatch(s, 1)[0][1]
	err := ioutil.WriteFile(role+".cert", []byte(cert), 0600)

	if err != nil {
		log.Fatal(err)
	}
}

func ca(s string, role string) {
	regex := regexp.MustCompile("issuing_ca.*((?sU:-----BEGIN CERTIFICATE-----.*-----END CERTIFICATE-----))")
	cert := regex.FindAllStringSubmatch(s, 1)[0][1]
	err := ioutil.WriteFile(role+".ca", []byte(cert), 0600)

	if err != nil {
		log.Fatal(err)
	}
}

func key(s string, role string) {
	regex := regexp.MustCompile("private_key.*((?sU:-----BEGIN RSA PRIVATE KEY-----.*-----END RSA PRIVATE KEY-----))")
	cert := regex.FindAllStringSubmatch(s, 1)[0][1]
	err := ioutil.WriteFile(role+".key", []byte(cert), 0600)

	if err != nil {
		log.Fatal(err)
	}
}
