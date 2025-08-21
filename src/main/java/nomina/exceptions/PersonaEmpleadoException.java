package nomina.exceptions;

//Clase para generar Exception y ser aplicada de forma transversal
public class PersonaEmpleadoException extends Exception {
    public PersonaEmpleadoException(String mensaje) {
        super(mensaje);
    }
}
