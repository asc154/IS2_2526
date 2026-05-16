package es.unican.is2.transportes;

import java.util.ArrayList;
import java.util.List;

public class GestionTransportes {

    private ArrayList<Conductor> conductores = new ArrayList<Conductor>();

    // WMC: CC buscaConductor() = 3
    //   +1 base
    //   +1 for (Conductor c : conductores)
    //   +1 if (c.getDni().equals(DNI))
    // CCog buscaConductor() = 3
    //   +1 for (...) [nivel 0]
    //   +1 if (...) [nivel 1]
    //   +1 penalización nivel 1 del if
    public Conductor buscaConductor(String DNI) {
        for (Conductor c : conductores) {               // WMC+1, CCog+1 [nivel 0]
            if (c.getDni().equals(DNI))                 // WMC+1, CCog+1+1 [nivel 1]
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
        if (buscaConductor(dni) != null)                // WMC+1, CCog+1 [nivel 0]
            return false;
        conductores.add(new Conductor(dni, nombre, apellido1, apellido2, direccion));
        return true;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public List<Conductor> conductores() {
        return conductores;
    }

    // WMC: CC mejoresConductores() = 4
    //   +1 base
    //   +1 for (Conductor conductor : conductores)
    //   +1 if (conductor.sueldo() > maxSueldo)
    //   +1 else if (conductor.sueldo() == maxSueldo)
    // CCog mejoresConductores() = 4
    //   +1 for (...) [nivel 0]
    //   +1 if (...) [nivel 1]
    //   +1 penalización nivel 1 del if
    //   +1 else if (...) [siempre +1 sin penalización]
    public List<Conductor> mejoresConductores() {
        List<Conductor> resultado = new ArrayList<Conductor>();
        double maxSueldo = 0.0;
        for (Conductor conductor : conductores) {       // WMC+1, CCog+1 [nivel 0]
            if (conductor.sueldo() > maxSueldo) {       // WMC+1, CCog+1+1 [nivel 1]
                maxSueldo = conductor.sueldo();
                resultado.clear();
                resultado.add(conductor);
            } else if (conductor.sueldo() == maxSueldo) { // WMC+1, CCog+1 [sin penalización]
                resultado.add(conductor);
            }
        }
        return resultado;
    }
    
 // WMC: CC crearTransporte() = 4
//  +1 base
//  +1 case "P"
//  +1 case "M"  
//  +1 case "MP"
//CCog crearTransporte() = 1
//  +1 switch [nivel 0]
public Transporte crearTransporte(String tipo, double horas, int personas, int toneladas) {
   switch (tipo) {
       case "P":  return new TransportePersonas(horas, personas);
       case "M":  return new TransporteMercancias(horas, toneladas);
       case "MP": return new TransporteMercanciasPeligrosas(horas, toneladas);
       default:   throw new IllegalArgumentException("Tipo desconocido: " + tipo);
   }
}

    // WMC total = 3+2+1+4 = 14
    // CCog total = 3+1+0+4 = 9
}