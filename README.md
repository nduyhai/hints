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
docker build -f Dockerfile.native --build-arg IMAGE_NAME=hints -t hints:native .
docker run --rm -p 8080:8080 hints:native
```

### AOT (JVM) + Maven cache

```pgsql
TRAINING (build time)
  └─ analyze configuration, beans, proxies
  └─ generate Java source + metadata
  └─ compile them into the JAR

RUNTIME
  └─ JVM loads pre-generated code
  └─ skips reflection, scanning, condition evaluation
```

```bash
docker build -f Dockerfile.aot -t hints:aot .
docker run --rm -p 8080:8080 hints:aot

```

### CDS (Boot CDS support) + Maven cache
```pgsql
TRAINING (build time)
  └─ run app in a controlled mode
  └─ record loaded classes
  └─ generate CDS archive (.jsa)

RUNTIME
  └─ JVM memory-maps the archive
  └─ skips class parsing & verification

```
```bash
docker build -f Dockerfile.cds -t hints:cds .
docker run --rm -p 8080:8080 hints:cds
```
## Maven

Upgrade maven
```bash
mvn -N wrapper:wrapper -Dmaven=3.9.9

or 

mvn -N org.apache.maven.plugins:maven-wrapper-plugin:3.2.0:wrapper "-Dmaven=3.9.9"

```