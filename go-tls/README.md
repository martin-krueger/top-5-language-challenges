# GO - TLS Connection

Download Vault [https://www.vaultproject.io/downloads](https://www.vaultproject.io/downloads), unzip and make it available in path.

Run Vault server:
```shell script
make vault
```

Copy `Root Token` value from server output and execute:

```shell script
export VAULT_ADDR='http://127.0.0.1:8200'
export VAULT_TOKEN=<root token>
```

Create and initialize pki backend:

```shell script
make init-pki
```

Create client and server certs:

```shell script
make certs
```

Build binaries:

```shell script
make build
```

Start server:

```shell script
make server
```

Run client:

```shell script
make client
```