#!/bin/bash

export VAULT_ADDR='http://vault:8200'
read -p 'Vault token: ' VAULT_TOKEN;

echo "Generating client certificate"
vault write hello_world/issue/localhost common_name="localhost" ttl="24h" > client.txt

/client/extractor client.txt client

exec "$@"