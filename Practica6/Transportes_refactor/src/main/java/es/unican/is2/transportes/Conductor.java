package es.unican.is2.transportes;

import java.util.ArrayList;

// WMC = 13 (suma de CC de todos los métodos)
// CCog = 3 (suma de complejidad cognitiva de todos los métodos)
// CBO = 3: usa Transporte (EFF=1)
//          es usado por GestionTransportes y GestionTransportesGUI (AFF=2)
//          clases que contribuyen: Transporte, GestionTransportes, GestionTransportesGUI
public class Conductor {

    private ArrayList<Transporte> transportes = new ArrayList<Transporte>();

    private static final double SUELDO_BASE = 700;
    private static final double EXTRA_HORA_BASE = 5;

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String direccion;

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
        this.direccion = direccion;
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
    public String getApellido2() {
        return apellido2;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public String getDire() {
        return direccion;
    }

    // WMC: CC sueldo() = 2
    //   +1 base
    //   +1 for (Transporte t : transportes)
    // CCog sueldo() = 1
    //   +1 for (...) [nivel 0]
    public double sueldo() {
        double sueldoTransportes = 0;
        for (Transporte t : transportes) {              // WMC+1, CCog+1 [nivel 0]
            sueldoTransportes += t.horas() * EXTRA_HORA_BASE + t.extraSueldo();
        }
        return SUELDO_BASE + sueldoTransportes;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public void anhadeTransporte(Transporte t) {
        transportes.add(t);
    }

    // WMC total = 5+1+1+1+1+1+2+1 = 13
    // CCog total = 2+0+0+0+0+0+1+0 = 3
}