package es.unican.is2.transportes;

// WMC = 7 (suma de CC de todos los métodos)
// CCog = 3 (suma de complejidad cognitiva de todos los métodos)
public abstract class Transporte {

    private double horas;
    private int ton;
    private int personas;

    // WMC: CC constructor = 4
    //   +1 base
    //   +1 if (horas <= 0 || valor <= 0)
    //   +1 primer || adicional
    //   +1 if (asigna personas o toneladas) — lo gestiona cada subclase, constructor base solo valida
    // CCog constructor = 3
    //   +1 if (horas <= 0 || valor <= 0) [nivel 0]
    //   +1 secuencia de || (una sola secuencia de operadores iguales)
    //   +1 if (valor para personas o ton) [nivel 0]
    public Transporte(double horas, int valor, boolean esPersonas) {
        if (horas <= 0 || valor <= 0) {         // WMC+1, CCog+1 [nivel 0]
            throw new IllegalArgumentException();
        }
        this.horas = horas;
        if (esPersonas) {                        // WMC+1, CCog+1 [nivel 0]
            this.personas = valor;
        } else {
            this.ton = valor;
        }
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public double horas() {
        return horas;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public int ton() {
        return ton;
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    public int getPersonas() {
        return personas;
    }

    // Método abstracto: no computa CC ni CCog al no tener implementación
    public abstract double extraSueldo();

    // WMC total = 4+1+1+1 = 7
    // CCog total = 3+0+0+0 = 3
}