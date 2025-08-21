package nomina.servicios;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import nomina.exceptions.PersonaEmpleadoException;
import nomina.modelos.PersonaEmpleado;

//Clase que contiene servicios para le lectura de archivo de nomina y generacion de archivos de salida
public class ProcesaCsvService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    List<String[]> validos = new ArrayList<>();
    List<String[]> invalidos = new ArrayList<>();

    double sumaSalarios = 0;
    int sumaAntiguedad = 0;
    int countValidos = 0;

    public List<String[]> getValidos() {
        return validos;
    }

    public List<String[]> getInvalidos() {
        return invalidos;
    }

    public double getSumaSalarios() {
        return sumaSalarios;
    }

    public int getSumaAntiguedad() {
        return sumaAntiguedad;
    }

    public int getCountValidos() {
        return countValidos;
    }

    //Metodo que permite leer el archivo de la nomina
    public void ProcesarCsvService(String ruta) throws IOException {
        PersonaEmpleadoServicio empleadoService = new PersonaEmpleadoServicio();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine(); // Se lee cabecera pero se debe omitir
            linea = br.readLine();
            while (linea != null) {
                if (linea.trim().isEmpty())
                    continue;

                String[] datos = linea.split(",");
                PersonaEmpleado e = new PersonaEmpleado(
                        datos[0].trim(),
                        datos[1].trim(),
                        datos[2].trim(),
                        datos[3].trim(),
                        Double.parseDouble(datos[4].trim()),
                        Double.parseDouble(datos[5].trim()),
                        Double.parseDouble(datos[6].trim()),
                        LocalDate.parse(datos[7].trim(), FORMATTER));
                try {

                    empleadoService.validarYProcesar(e);
                    validos.add(new String[] {
                            e.getNombre(), e.getApellido(), e.getRut(),
                            e.getCargo(), String.valueOf(e.getSalarioFinal())
                    });
                    sumaSalarios += e.getSalarioFinal();
                    sumaAntiguedad += e.getAntiguedad();
                    countValidos++;
                } catch (PersonaEmpleadoException ex) {
                    invalidos.add(new String[] {
                            e.getNombre(), e.getApellido(), e.getRut(),
                            "ERROR: " + ex.getMessage()
                    });
                }
                linea = br.readLine();
            }
        }
    }

    //Metodo que genera archivos de salida. Recibe la ruta y los datos a escrbir por cada linea
    public void escribirArchivoCsv(String ruta, List<String[]> lineas) throws IOException {
        Path path = Paths.get(ruta);
        Files.createDirectories(path.getParent());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (String[] linea : lineas) {
                bw.write(String.join(";", linea));
                bw.newLine();
            }
        }
    }
}
