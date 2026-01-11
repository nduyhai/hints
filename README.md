# hints
Spring native example

## Generate compile
```shell
mvn -Pnative native:compile
```

## Build

```shell
mvn -Pnative native:build
```

### Run

```shell
./target/hints
```

## Test

```shell
mvn -Pnative native:test
```

## Docker

### JVM Dockerfile

```bash
docker build -t hints:latest .
docker run --rm -p 8080:8080 hints:latest

```

### Native Dockerfile

```bash
docker build -f Dockerfile.native -t hints:native .
```