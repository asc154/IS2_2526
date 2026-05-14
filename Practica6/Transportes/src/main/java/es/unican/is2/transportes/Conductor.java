package es.unican.is2.transportes;

import java.util.ArrayList;

// WMC = 18 (suma de CC de todos los métodos)
// CCog = 8 (suma de complejidad cognitiva de todos los métodos)
public class Conductor {

    private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dire;

    // WMC: CC constructor = 5
    //   +1 base
    //   +1 if (dni == null || nombre == null || apellido1 == null || direccion == null)
    //   +1 primer || adicional
    //   +1 segundo || adicional
    //   +1 tercer || adicional
    // CCog constructor = 2
    //   +1 if (...) [nivel 0]
    //   +1 secuencia de || (una sola secuencia de operadores iguales)
    public Conductor(String dni, String nombre, String apellido1,
            String apellido2, String direccion) {
        if (dni == null || nombre == null || apellido1 == null || direccion == null) {
            throw new IllegalArgumentException();
        }
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dire = direccion;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public String dni() {
        return dni;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public String getDni() {
        return dni;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public String getNombre() {
        return nombre;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public String getApellido1() {
        return apellido1;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public String apellido2() {
        return apellido2;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public String getDire() {
        return dire;
    }

    // WMC: CC sueldo() = 6
    //   +1 base
    //   +1 for (Transporte t : transportes)
    //   +1 case Mercancias
    //   +1 case MercanciasPeligrosas
    //   +1 case Personas
    //   +1 if (t.getPersonas() < 10)
    // CCog sueldo() = 6
    //   +1 for (...) [nivel 0]
    //   +1 switch (t.categoria()) [nivel 1]
    //   +1 penalización nivel 1 del switch
    //   +1 if (t.getPersonas() < 10) [nivel 2]
    //   +2 penalización nivel 2 del if
    public double sueldo() {
        double sueldoTransportes = 0;
        for (Transporte t : transportes) {                      // WMC+1, CCog+1 [nivel 0]
            double sueldoExtraTransporte = 0.0;
            switch (t.categoria()) {                            // CCog+1+1 [nivel 1]
                case Mercancias:                                // WMC+1
                    sueldoExtraTransporte = t.ton() * 2;
                    break;
                case MercanciasPeligrosas:                      // WMC+1
                    sueldoExtraTransporte = t.ton() * 2 + 50;
                    break;
                case Personas:                                  // WMC+1
                    if (t.getPersonas() < 10)                  // WMC+1, CCog+1+2 [nivel 2]
                        sueldoExtraTransporte = t.horas() * 0.5;
                    else
                        sueldoExtraTransporte = t.horas();
                    break;
            }
            sueldoTransportes += t.horas() * 5 + sueldoExtraTransporte;
        }
        return 700 + sueldoTransportes;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public void anhadeTransporte(Transporte t) {
        transportes.add(t);
    }

    // WMC total = 5+1+1+1+1+1+1+6+1 = 18
    // CCog total = 2+0+0+0+0+0+0+6+0 = 8
}