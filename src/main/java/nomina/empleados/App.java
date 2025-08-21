package nomina.empleados;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import nomina.servicios.ProcesaCsvService;

/**
 * Clase principal de ejecucion
 *
 */
public class App {
    public static void main(String[] args) {
        //Colores usados para imprimir mensajes
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_WHITE = "\u001B[37m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RED = "\u001B[31m";

        System.err.println(ANSI_YELLOW + "Iniciando procesamiento de la Nomina de archivos CSV \n");

        Properties prop = new Properties();

        //Lectura del archivo config.properties
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(input);

        } catch (IOException ex) {
            System.out.println(
                    "El archivo de configuracion config.properties no existe. Debe crearlo en la ruta src/main/resources");
            System.exit(1);
        }

        //Fin - Lectura del archivo config.properties


        //Validaciones de propiedades dentro del archivo config.properties

        String inputFile = prop.getProperty("file.input");
        String outputValidosFile = prop.getProperty("file.output.validos");
        String outputInvalidosFile = prop.getProperty("file.output.invalidos");

        if ((inputFile == null || inputFile.trim().isEmpty() )) {
            System.out.println(ANSI_RED +
                    "Debe configuar la propiedad file.input: archivo de entrada");
            System.exit(0);
        }

        if(outputValidosFile == null || outputValidosFile.trim().isEmpty()){
            System.out.println(ANSI_RED +
                    "Debe configuar la propiedad file.output.validos: archivo de salida - empleados validos");
                    System.exit(0);
        }

        if(outputInvalidosFile == null || outputInvalidosFile.trim().isEmpty()){
            System.out.println(ANSI_RED +
                    "Debe configuar la propiedad file.output.invalidos: archivo de salida - empleados invalidos");
            System.exit(0);
        }
        //Fin Validaciones de propiedades dentro del archivo config.properties

        ProcesaCsvService procesamientoArchivo = new ProcesaCsvService();
        leerArchivo(ANSI_GREEN, ANSI_WHITE, ANSI_YELLOW, ANSI_RED, inputFile, procesamientoArchivo);
        EscribirArchivo(ANSI_GREEN, ANSI_WHITE, ANSI_YELLOW, ANSI_RED, outputValidosFile, "validos" , procesamientoArchivo);
        EscribirArchivo(ANSI_GREEN, ANSI_WHITE, ANSI_YELLOW, ANSI_RED, outputInvalidosFile, "invalidos", procesamientoArchivo);

        // Salida a la consola
        imprimirResultados(ANSI_GREEN, ANSI_WHITE, ANSI_YELLOW, procesamientoArchivo);
    }
    //Metodo para le lectura de archivo csv
    private static void leerArchivo(final String ANSI_GREEN, final String ANSI_WHITE, final String ANSI_YELLOW,
            final String ANSI_RED, String inputFile, ProcesaCsvService procesamientoArchivo) {
        try {
            System.out.print(ANSI_YELLOW + "Leyendo archivo de empleados: " + ANSI_WHITE +  inputFile + " ... ");
            procesamientoArchivo.ProcesarCsvService(inputFile);
            System.out.println(ANSI_GREEN + "Ok");
        } catch (IOException e) {
            System.err.println(ANSI_RED + "Error leyendo archivo: " + e.getMessage());
            System.exit(0);
        }
    }
    
    //Metodo para le escritura de archivos
    private static void EscribirArchivo(final String ANSI_GREEN, final String ANSI_WHITE, final String ANSI_YELLOW,
            final String ANSI_RED, String outputFile, String tipoOutputFile,ProcesaCsvService procesamientoArchivo) {
        try {
            System.out.print(ANSI_YELLOW + "Escribiendo archivo de empleados " + tipoOutputFile + ": " + ANSI_WHITE +  outputFile + " ...");
            if(tipoOutputFile.equals("validos"))
                procesamientoArchivo.escribirArchivoCsv(outputFile, procesamientoArchivo.getValidos());

            if(tipoOutputFile.equals("invalidos"))
                procesamientoArchivo.escribirArchivoCsv(outputFile, procesamientoArchivo.getInvalidos());
            System.out.println(ANSI_GREEN + "Ok" );
        } catch (IOException e) {
            System.err.println(ANSI_RED + "Error escribiendo archivo: " + outputFile + " " + e.getMessage());
            System.exit(0);
        }
    }

    //Metodo imprimir resultados finales en la consola
    private static void imprimirResultados(final String ANSI_GREEN, final String ANSI_WHITE, final String ANSI_YELLOW,
            ProcesaCsvService procesamientoArchivo) {
        int cantidadValidos = procesamientoArchivo.getCountValidos();
        System.out.println(ANSI_YELLOW + "Resultados..." + ANSI_WHITE);
        System.out.println("Total de empleados válidos: " + ANSI_GREEN + cantidadValidos + ANSI_WHITE);
        System.out.println("Total de empleados inválidos: " + ANSI_GREEN + procesamientoArchivo.getInvalidos().size() + ANSI_WHITE);
        double sumaSalarios = procesamientoArchivo.getSumaSalarios();
        double sumaAntiguedad = procesamientoArchivo.getSumaAntiguedad();
        if (cantidadValidos > 0) {
            System.out
                    .println("Promedio de salario final: " + ANSI_GREEN + String.format("%.2f", (sumaSalarios / cantidadValidos)) + ANSI_WHITE );
            System.out.println("Antigüedad promedio: " + ANSI_GREEN + String.format("%.2f", (sumaAntiguedad / cantidadValidos)) + " año(s)" + ANSI_WHITE);
        }
    }


}
