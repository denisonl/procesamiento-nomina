package nomina.utilities;

import java.util.HashSet;
import java.util.Set;

//Clase que permite almacenar ruts para poder determinar si son unicos.

public class RutValidador {

    private static Set<String> ruts = new HashSet<>();

    public static boolean esUnico(String rut) {
        if (ruts.contains(rut)) {
            return false;
        }
        ruts.add(rut);
        return true;
    }
}
