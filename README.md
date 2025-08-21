# Aplicación para procesar la nómina desde archivo CSV

## 📌 Descripción
Aplicación en **Java 8+ con Maven** que procesa un archivo CSV de empleados, valida la información y genera:
- Un archivo con empleados válidos, salario final promedio y antigüedad promedio.
- Un archivo con registros inválidos y la causa del error.

## Estructura
- Archivo de configuración de rutas: `src/main/resources/config.properties`
- Entrada: `src/main/resources/input/archivo.csv`
- Salida: `src/main/resources/output/`

## Consideraciones
- Las carpetas `input/output` se encuentran dentro del proyecto.
- Se debe colocar el archivo CSV en la capreta `input` cuya ruta es: `src/main/resources/input/`
- Se deben configurar la rutas de los archivos de entrada y salida en el archivo: `src/main/resources/config.properties`. A continuación, un ejemplo:
```
file.input=src/main/resources/input/empleados.csv
file.output.validos=src/main/resources/output/empleados_validos.csv
file.output.invalidos=src/main/resources/output/empleados_invalidos.csv
```
## Para ejecutar con maven
```bash
mvn clean install
mvn exec:java
```

## Para ejecutar con java
```bash
java -jar target/procesamiento-nomina-1.0-0.jar nomina.empleados.App
```