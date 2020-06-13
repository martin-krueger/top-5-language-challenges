FROM rust:1.40 as builder
WORKDIR /usr/src/number-of-islands
COPY . .
RUN cargo install --path .

FROM debian:buster-slim
COPY --from=builder /usr/local/cargo/bin/rust-number-of-islands /usr/local/bin/number-of-islands
CMD ["number-of-islands"]