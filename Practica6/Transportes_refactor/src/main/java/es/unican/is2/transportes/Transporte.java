package es.unican.is2.transportes;

public abstract class Transporte {

    private double horas;
    private int ton;
    private int personas;
    private CategoriaTransporte cat;

    // WMC: CC constructor = 5
    //   +1 base
    //   +1 if (horas <= 0 || valor <= 0 || cat == null)
    //   +1 primer || adicional
    //   +1 segundo || adicional
    //   +1 if (cat.equals(CategoriaTransporte.Personas))
    // CCog constructor = 3
    //   +1 if (horas <= 0 || valor <= 0 || cat == null) [nivel 0]
    //   +1 secuencia de || (una sola secuencia de operadores iguales)
    //   +1 if (cat.equals(...)) [nivel 0]
    public Transporte(double horas, CategoriaTransporte cat, int valor) {
        if (horas <= 0 || valor <= 0 || cat == null) {
            throw new IllegalArgumentException();
        }
        this.horas = horas;
        this.cat = cat;
        if (cat.equals(CategoriaTransporte.Personas)) {
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
    public CategoriaTransporte categoria() {
        return cat;
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

    // WMC total = 5+1+1+1+1 = 9
    // CCog total = 3+0+0+0+0 = 3
}