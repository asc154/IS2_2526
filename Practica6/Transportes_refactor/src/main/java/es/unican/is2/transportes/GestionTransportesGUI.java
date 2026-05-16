package es.unican.is2.transportes;

import java.util.List;
import fundamentos.Lectura;
import fundamentos.Mensaje;
import fundamentos.Menu;

public class GestionTransportesGUI {

    // WMC: CC main() = 6
    //   +1 base
    //   +1 while(true)
    //   +1 case ANHADE_CONDUCTOR
    //   +1 case ANHADE_TRANSPORTE
    //   +1 case SUELDO_CONDUCTOR
    //   +1 case MEJOR_CONDUCTOR
    // CCog main() = 3
    //   +1 while(true) [nivel 0]
    //   +1 switch(opcion) [nivel 1]
    //   +1 penalización nivel 1 del switch
    public static void main(String[] args) {
        final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1,
                SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

        GestionTransportes gt = new GestionTransportes();
        Menu menu = new Menu("Transportes");
        menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
        menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
        menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
        menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);

        int opcion;

        while (true) {                                          // WMC+1, CCog+1 [nivel 0]
            opcion = menu.leeOpcion();
            switch (opcion) {                                   // CCog+1+1 [nivel 1]
                case ANHADE_CONDUCTOR:                          // WMC+1
                    anhadeConductor(gt);
                    break;
                case ANHADE_TRANSPORTE:                         // WMC+1
                    anhadeTransporte(gt);
                    break;
                case SUELDO_CONDUCTOR:                          // WMC+1
                    sueldoConductor(gt);
                    break;
                case MEJOR_CONDUCTOR:                           // WMC+1
                    mejorConductor(gt);
                    break;
            }
        }
    }

    // WMC: CC anhadeConductor() = 2
    //   +1 base
    //   +1 if (!gt.anhadeConductor(...))
    // CCog anhadeConductor() = 1
    //   +1 if (...) [nivel 0]
    private static void anhadeConductor(GestionTransportes gt) {
        Lectura lect = new Lectura("Datos Conductor");
        lect.creaEntrada("DNI", "");
        lect.creaEntrada("Nombre", "");
        lect.creaEntrada("Apellido1", "");
        lect.creaEntrada("Apellido2", "");
        lect.creaEntrada("Direccion", "");
        lect.esperaYCierra();
        String dni = lect.leeString("DNI");
        String nombre = lect.leeString("Nombre");
        String apellido1 = lect.leeString("Apellido1");
        String apellido2 = lect.leeString("Apellido2");
        String direccion = lect.leeString("Direccion");
        if (!gt.anhadeConductor(dni, nombre, apellido1,        // WMC+1, CCog+1 [nivel 0]
                apellido2, direccion))
            mensaje("ERROR", "Ya existe un conductor con DNI " + dni);
    }

 // WMC: CC anhadeTransporte() = 2
//  +1 base
//  +1 if (c != null)
//CCog anhadeTransporte() = 2
//  +1 if (c != null) [nivel 0]
//  +1 else [sin penalización]
private static void anhadeTransporte(GestionTransportes gt) {
   Lectura lect = new Lectura("Nuevo transporte");
   lect.creaEntrada("DNI", "");
   lect.creaEntrada("Tipo Transporte: P | M | MP", "");
   lect.creaEntrada("Horas", 0);
   lect.creaEntrada("Personas", 0);
   lect.creaEntrada("Toneladas", 0);
   lect.esperaYCierra();
   String dni = lect.leeString("DNI");
   String tipo = lect.leeString("Tipo Transporte: P | M | MP");
   int horas = lect.leeInt("Horas");
   int personas = lect.leeInt("Personas");
   int toneladas = lect.leeInt("Toneladas");
   Conductor c = gt.buscaConductor(dni);
   if (c != null) {                                    // WMC+1, CCog+1 [nivel 0]
       Transporte t = gt.crearTransporte(tipo, horas, personas, toneladas);
       c.anhadeTransporte(t);
   } else {                                            // CCog+1 [sin penalización]
       mensaje("ERROR", "No existe un conductor con DNI " + dni);
   }
}

    // WMC: CC sueldoConductor() = 2
    //   +1 base
    //   +1 if (c != null)
    // CCog sueldoConductor() = 1
    //   +1 if (...) [nivel 0]
    private static void sueldoConductor(GestionTransportes gt) {
        Lectura lect = new Lectura("Transportes Peligrosos");
        lect.creaEntrada("DNI", "");
        lect.esperaYCierra();
        String dni = lect.leeString("DNI");
        Conductor c = gt.buscaConductor(dni);
        if (c != null) {                                        // WMC+1, CCog+1 [nivel 0]
            mensaje("Sueldo", "El sueldo del conductor es: " + c.sueldo());
        } else {                                                // CCog+1 [sin penalización]
            mensaje("ERROR", "No existe un conductor con DNI " + dni);
        }
    }

    // WMC: CC mejorConductor() = 3
    //   +1 base
    //   +1 if (resultado.size() == 0)
    //   +1 for (Conductor conductor : resultado)
    // CCog mejorConductor() = 4
    //   +1 if (resultado.size() == 0) [nivel 0]
    //   +1 else [siempre +1 sin penalización]
    //   +1 for (...) [nivel 1]
    //   +1 penalización nivel 1 del for
    private static void mejorConductor(GestionTransportes gt) {
        List<Conductor> resultado = gt.mejoresConductores();
        String msj = "";
        if (resultado.size() == 0) {                            // WMC+1, CCog+1 [nivel 0]
            msj = "No hay conductores";
        } else {                                                // CCog+1 [sin penalización]
            for (Conductor conductor : resultado) {             // WMC+1, CCog+1+1 [nivel 1]
                msj += conductor.getNombre() + " "
                        + conductor.getApellido1() + "\n";
            }
        }
        mensaje("MEJOR CONDUCTOR", msj);
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    private static void mensaje(String titulo, String txt) {
        Mensaje msj = new Mensaje(titulo);
        msj.escribe(txt);
    }

    // WMC total = 6+2+2+2+3+1 = 16
    // CCog total = 3+1+4+1+4+0 = 11
}