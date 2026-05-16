package es.unican.is2.transportes;

import java.util.ArrayList;
import java.util.List;

// WMC = 6 (suma de CC de todos los métodos)
// CCog = 4 (suma de complejidad cognitiva de todos los métodos)
public class GestionTransportes {

    private ArrayList<Conductor> cs = new ArrayList<Conductor>();

    // WMC: CC buscaConductor() = 3
    //   +1 base
    //   +1 for (Conductor c : cs)
    //   +1 if (c.dni().equals(DNI))
    // CCog buscaConductor() = 3
    //   +1 for (...) [nivel 0]
    //   +1 if (...) [nivel 1]
    //   +1 penalización nivel 1 del if
    public Conductor buscaConductor(String DNI) {
        for (Conductor c : cs) {                    // WMC+1, CCog+1 [nivel 0]
            if (c.dni().equals(DNI))                // WMC+1, CCog+1+1 [nivel 1]
                return c;
        }
        return null;
    }

    // WMC: CC anhadeConductor() = 2
    //   +1 base
    //   +1 if (buscaConductor(dni) != null)
    // CCog anhadeConductor() = 1
    //   +1 if (...) [nivel 0]
    public boolean anhadeConductor(String dni, String nombre, String apellido1,
            String apellido2, String direccion) {
        if (buscaConductor(dni) != null)            // WMC+1, CCog+1 [nivel 0]
            return false;
        cs.add(new Conductor(dni, nombre, apellido1, apellido2, direccion));
        return true;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public List<Conductor> conductores() {
        return cs;
    }

    // WMC total = 3+2+1 = 6
    // CCog total = 3+1+0 = 4
}