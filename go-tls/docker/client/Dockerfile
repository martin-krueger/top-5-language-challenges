FROM golang:1.14.2 as builder
WORKDIR /client
COPY go.mod .
RUN go mod download
COPY cmd/ ./cmd
RUN go build -o client ./cmd/client
RUN go build -o extractor ./cmd/extractor

FROM golang:1.14.2

RUN apt update
RUN apt-get -y install -qq --force-yes unzip

RUN wget https://releases.hashicorp.com/vault/1.4.3/vault_1.4.3_linux_amd64.zip
RUN unzip vault_1.4.3_linux_amd64.zip
RUN mv vault /usr/local/bin
RUN rm vault_1.4.3_linux_amd64.zip

WORKDIR /client

COPY --from=builder /client/client ./client
#RUN chmod 755 ./client

COPY --from=builder /client/extractor ./extractor
#RUN chmod 755 ./extractor

COPY ./docker/client/entrypoint.sh ./entrypoint.sh

ENTRYPOINT ["/client/entrypoint.sh"]

CMD ["/client/client"]
