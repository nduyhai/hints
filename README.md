# hints
Spring native example

## Generate aot
```shell
mvn spring-aot:generate
```

## Build

```shell
mvn -Pnative -DskipTests package
```

### Run

```shell
./target/hints
```

## Test

```shell
mvn -Pnative test
```