package nomina.servicios;

import java.time.LocalDate;
import java.time.Period;

import nomina.exceptions.PersonaEmpleadoException;
import nomina.modelos.PersonaEmpleado;
import nomina.utilities.RutValidador;

//Clase para el manejo de validaciones y generar exceptions ante un error.

public class PersonaEmpleadoServicio {
    final int salarioBase = 400000;
    final double porcentajeMaximoAlSalarioBase = 0.5;
    final double porcentajeBonificacionMayorCincoAnios = 0.10;
    final double porcentajeBonificacionMayorTresAnios = 0.05;
    final int antiguedadesAnios[] = { 3, 5 };

    public PersonaEmpleado validarYProcesar(PersonaEmpleado e) throws PersonaEmpleadoException {
        // 1. Validar RUT único
        if (!RutValidador.esUnico(e.getRut())) {
            throw new PersonaEmpleadoException("RUT duplicado");
        }

        // 2. Salario base mínimo
        if (e.getSalarioBase() < salarioBase) {
            throw new PersonaEmpleadoException("Salario base menor a " + salarioBase);
        }

        // 3. Bonos <= 50% salario base
        if (e.getBonos() > e.getSalarioBase() * (porcentajeMaximoAlSalarioBase)) {
            throw new PersonaEmpleadoException("Bonos superan el 50% del salario base");
        }

        // 4. Descuentos <= salario base
        if (e.getDescuentos() > e.getSalarioBase()) {
            throw new PersonaEmpleadoException("Descuentos superan el salario base");
        }

        // 5. Fecha de ingreso válida
        if (e.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new PersonaEmpleadoException("Fecha de ingreso futura");
        }

        // Calcular antigüedad
        int antiguedad = Period.between(e.getFechaIngreso(), LocalDate.now()).getYears();
        e.setAntiguedad(antiguedad);

        // Calcular bonificación por antigüedad
        double bonificacion = 0;
        if (antiguedad > antiguedadesAnios[1]) {
            bonificacion = e.getSalarioBase() * porcentajeBonificacionMayorCincoAnios;
        } else if (antiguedad >= antiguedadesAnios[0]) {
            bonificacion = e.getSalarioBase() * porcentajeBonificacionMayorTresAnios;
        }
        e.setBonificacionAntiguedad(bonificacion);

        // Calcular salario final
        double salarioFinal = (e.getSalarioBase() + e.getBonos() + bonificacion) - e.getDescuentos();
        e.setSalarioFinal(salarioFinal);

        return e;
    }
}
