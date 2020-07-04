docker-client:
	docker build -f docker/client/Dockerfile -t go-tls-client .

docker-server:
	docker build -f docker/server/Dockerfile -t go-tls-server .

vault:
	@docker run -p 8200:8200 vault

build:
	go build -o work/client ./cmd/client
	go build -o work/server ./cmd/server
	go build -o work/extractor ./cmd/extractor

prepare-shell:
	@echo "lookup 'Root Token: <root token>' and export env vars:"
	@echo
	@echo "export VAULT_ADDR='http://127.0.0.1:8200'"
	@echo "export VAULT_TOKEN=<root token>"

init-pki:
	@mkdir -p work
	vault secrets enable pki
	vault secrets tune -max-lease-ttl=87600h pki
	vault write -field=certificate pki/root/generate/internal \
        common_name="localhost" \
        ttl=87600h > work/CA_cert.crt
	vault write pki/config/urls \
        issuing_certificates="http://127.0.0.1:8200/v1/pki/ca" \
        crl_distribution_points="http://127.0.0.1:8200/v1/pki/crl"
	vault secrets enable -path=hello_world pki
	vault secrets tune -max-lease-ttl=43800h hello_world
	vault write -format=json hello_world/intermediate/generate/internal \
			common_name="localhost Intermediate Authority" \
			| jq -r '.data.csr' > work/pki_intermediate.csr
	vault write -format=json pki/root/sign-intermediate csr=@work/pki_intermediate.csr \
        format=pem_bundle ttl="43800h" \
        | jq -r '.data.certificate' > work/intermediate.cert.pem

	vault write hello_world/intermediate/set-signed certificate=@work/intermediate.cert.pem
	vault write hello_world/roles/localhost \
        allowed_domains="localhost" \
        allow_bare_domains=true \
        allow_subdomains=false \
        max_ttl="720h"

certs:
	vault write hello_world/issue/localhost common_name="localhost" ttl="24h" > work/client.txt
	vault write hello_world/issue/localhost common_name="localhost" ttl="24h" > work/server.txt

server:
	@cd work; \
	./extractor server.txt server; \
	./server

client:
	@echo "CTRL-C to stop"
	@cd work; \
	./extractor client.txt client; \
	./client