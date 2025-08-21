# Aplicacion para procesar La Nómina desde archivo CSV

## 📌 Descripción
Aplicación en **Java 8+ con Maven** que procesa un archivo CSV de empleados, valida la información y genera:
- Un archivo con empleados válidos y su salario final.
- Un archivo con registros inválidos y la causa del error.

## Estructura
- Archivo de configuracion de rutas: `src/main/resources/config.properties`
- Entrada: `src/main/resources/input/empleados.csv`
- Salida: `src/main/resources/output/`

## Para ejecutar
```bash
mvn clean install
mvn exec:java

```bash 
## Comando
java -jar target/procesamiento-nomina-1.0-0.jar nomina.empleados.App