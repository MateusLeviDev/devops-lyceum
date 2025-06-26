# --- Stage 1: Build ---
FROM golang:1.23-alpine AS builder

WORKDIR /app

COPY go.mod ./
RUN go mod download

COPY main.go ./

RUN CGO_ENABLED=0 GOOS=linux go build -ldflags="-s -w" -o app .

FROM scratch

COPY --from=builder /app/app /app/app

EXPOSE 9090

ENTRYPOINT ["/app/app"]
