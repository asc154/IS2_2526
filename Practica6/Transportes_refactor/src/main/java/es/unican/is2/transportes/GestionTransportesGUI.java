package es.unican.is2.transportes;

import java.util.LinkedList;
import java.util.List;

import fundamentos.Lectura;
import fundamentos.Mensaje;
import fundamentos.Menu;

// WMC = 17 (suma de CC de todos los métodos)
// CCog = 33 (suma de complejidad cognitiva de todos los métodos)
public class GestionTransportesGUI {

    // WMC: CC main() = 16
    //   +1 base
    //   +1 while(true)
    //   +1 case ANHADE_CONDUCTOR
    //   +1 case ANHADE_TRANSPORTE
    //   +1 case SUELDO_CONDUCTOR
    //   +1 case MEJOR_CONDUCTOR
    //   +1 if (!gt.anhadeConductor(...))
    //   +1 if (c != null)  [ANHADE_TRANSPORTE]
    //   +1 case "P"
    //   +1 case "M"
    //   +1 case "MP"
    //   +1 if (c != null)  [SUELDO_CONDUCTOR]
    //   +1 for (Conductor conductor : gt.conductores())
    //   +1 if (conductor.sueldo() > maxSueldo)
    //   +1 else if (conductor.sueldo() == maxSueldo)
    //   +1 if (resultado.size() == 0)
    //   +1 for (Conductor conductor : resultado)  → Total = 17-1base ya contado = 16
    //
    // CCog main() = 33
    //   +1        while(true) [nivel 0]
    //   +1+1=+2   switch(opcion) [nivel 1, +1 penalización]
    //   +1+2=+3   if(!gt.anhadeConductor(...)) [nivel 2, +2 penalización]
    //   +1+2=+3   if(c!=null) ANHADE_TRANSPORTE [nivel 2, +2 penalización]
    //   +1+3=+4   switch(tipo) [nivel 3, +3 penalización]
    //   +1        else (ANHADE_TRANSPORTE) [siempre +1 sin penalización]
    //   +1+2=+3   if(c!=null) SUELDO_CONDUCTOR [nivel 2, +2 penalización]
    //   +1        else (SUELDO_CONDUCTOR) [siempre +1 sin penalización]
    //   +1+2=+3   for(conductor : gt.conductores()) [nivel 2, +2 penalización]
    //   +1+3=+4   if(conductor.sueldo() > maxSueldo) [nivel 3, +3 penalización]
    //   +1        else if(conductor.sueldo() == maxSueldo) [siempre +1 sin penalización]
    //   +1+2=+3   if(resultado.size()==0) [nivel 2, +2 penalización]
    //   +1        else [siempre +1 sin penalización]
    //   +1+2=+3   for(Conductor conductor : resultado) [nivel 2, +2 penalización]
    //   Total CCog = 1+2+3+3+4+1+3+1+3+4+1+3+1+3 = 33
    public static void main(String[] args) {
        final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1,
                SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

        String dni;
        Lectura lect;
        Conductor c;

        GestionTransportes gt = new GestionTransportes();
        Menu menu = new Menu("Transportes");
        menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
        menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
        menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
        menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);

        int opcion;

        while (true) {                                                          // WMC+1, CCog+1 [nivel 0]
            opcion = menu.leeOpcion();

            switch (opcion) {                                                   // CCog+1+1 [nivel 1]
                case ANHADE_CONDUCTOR:                                          // WMC+1
                    lect = new Lectura("Datos Conductor");
                    lect.creaEntrada("DNI", "");
                    lect.creaEntrada("Nombre", "");
                    lect.creaEntrada("Apellido1", "");
                    lect.creaEntrada("Apellido2", "");
                    lect.creaEntrada("Direccion", "");
                    lect.esperaYCierra();
                    dni = lect.leeString("DNI");
                    String nombre = lect.leeString("Nombre");
                    String apellido1 = lect.leeString("Apellido1");
                    String apellido2 = lect.leeString("Apellido2");
                    String direccion = lect.leeString("Direccion");
                    if (!gt.anhadeConductor(dni, nombre, apellido1,            // WMC+1, CCog+1+2 [nivel 2]
                            apellido2, direccion))
                        mensaje("ERROR", "Ya existe un conductor con DNI " + dni);
                    break;

                case ANHADE_TRANSPORTE:                                         // WMC+1
                    lect = new Lectura("Nuevo transporte");
                    lect.creaEntrada("DNI", "");
                    lect.creaEntrada("Tipo Transporte: P | M | MP", "");
                    lect.creaEntrada("Horas", 0);
                    lect.creaEntrada("Personas", 0);
                    lect.creaEntrada("Toneladas", 0);
                    lect.esperaYCierra();
                    dni = lect.leeString("DNI");
                    String tipo = lect.leeString("Tipo Transporte: P | M | MP");
                    int horas = lect.leeInt("Horas");
                    int personas = lect.leeInt("Personas");
                    int toneladas = lect.leeInt("Toneladas");
                    Transporte t = null;
                    c = gt.buscaConductor(dni);
                    if (c != null) {                                            // WMC+1, CCog+1+2 [nivel 2]
                        switch (tipo) {                                         // CCog+1+3 [nivel 3]
                            case "P":                                           // WMC+1
                                t = new Transporte(horas, CategoriaTransporte.Personas, personas);
                                c.anhadeTransporte(t);
                                break;
                            case "M":                                           // WMC+1
                                t = new Transporte(horas, CategoriaTransporte.Mercancias, toneladas);
                                c.anhadeTransporte(t);
                                break;
                            case "MP":                                          // WMC+1
                                t = new Transporte(horas, CategoriaTransporte.MercanciasPeligrosas, toneladas);
                                c.anhadeTransporte(t);
                                break;
                        }
                    } else {                                                    // CCog+1 [sin penalización]
                        mensaje("ERROR", "No existe un conductor con DNI " + dni);
                    }
                    break;

                case SUELDO_CONDUCTOR:                                          // WMC+1
                    lect = new Lectura("Transportes Peligrosos");
                    lect.creaEntrada("DNI", "");
                    lect.esperaYCierra();
                    dni = lect.leeString("DNI");
                    c = gt.buscaConductor(dni);
                    if (c != null) {                                            // WMC+1, CCog+1+2 [nivel 2]
                        mensaje("Sueldo", "El sueldo del conductor es: " + c.sueldo());
                    } else {                                                    // CCog+1 [sin penalización]
                        mensaje("ERROR", "No existe un conductor con DNI " + dni);
                    }
                    break;

                case MEJOR_CONDUCTOR:                                           // WMC+1
                    List<Conductor> resultado = new LinkedList<Conductor>();
                    double maxSueldo = 0.0;
                    for (Conductor conductor : gt.conductores()) {              // WMC+1, CCog+1+2 [nivel 2]
                        if (conductor.sueldo() > maxSueldo) {                  // WMC+1, CCog+1+3 [nivel 3]
                            maxSueldo = conductor.sueldo();
                            resultado.clear();
                            resultado.add(conductor);
                        } else if (conductor.sueldo() == maxSueldo) {          // WMC+1, CCog+1 [sin penalización]
                            resultado.add(conductor);
                        }
                    }
                    String msj = "";
                    if (resultado.size() == 0) {                               // WMC+1, CCog+1+2 [nivel 2]
                        msj = "No hay conductores";
                    } else {                                                    // CCog+1 [sin penalización]
                        for (Conductor conductor : resultado) {                 // WMC+1, CCog+1+2 [nivel 2]
                            msj += conductor.getNombre() + " "
                                    + conductor.getNombre() + "\n";
                        }
                    }
                    mensaje("MEJOR CONDUCTOR", msj);
                    break;
            }
        }
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    private static void mensaje(String titulo, String txt) {
        Mensaje msj = new Mensaje(titulo);
        msj.escribe(txt);
    }

    // WMC total = 16+1 = 17
    // CCog total = 33+0 = 33
}