package nomina.modelos;

import java.time.LocalDate;

//Modelo para mapeo de lectura de lineas del archivo de csv y representarlo como objetos
public class PersonaEmpleado {

    //Atributos del empleado
    private String nombre;
    private String apellido;
    private String rut;
    private String cargo;
    private double salarioBase;
    
    //Validaciones
    private double bonos;
    private double descuentos;
    private LocalDate fechaIngreso;

    // Calculados
    private int antiguedad;
    private double bonificacionAntiguedad;
    private double salarioFinal;

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public void setBonificacionAntiguedad(double bonificacionAntiguedad) {
        this.bonificacionAntiguedad = bonificacionAntiguedad;
    }

    public void setSalarioFinal(double salarioFinal) {
        this.salarioFinal = salarioFinal;
    }

    // Getters y setters

    public PersonaEmpleado(String nombre, String apellido, String rut, String cargo, double salarioBase, double bonos,
            double descuentos, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.bonos = bonos;
        this.descuentos = descuentos;
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getBonos() {
        return bonos;
    }

    public double getDescuentos() {
        return descuentos;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public double getBonificacionAntiguedad() {
        return bonificacionAntiguedad;
    }

    public double getSalarioFinal() {
        return salarioFinal;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + rut + ")";
    }

}
