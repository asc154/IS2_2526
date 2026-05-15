package es.unican.is2.transportes;

// WMC = 3 (suma de CC de todos los métodos)
// CCog = 1 (suma de complejidad cognitiva de todos los métodos)
public class TransportePersonas extends Transporte {

    private static final int LIMITE_COLECTIVO = 10;
    private static final double EXTRA_NO_COLECTIVO = 0.5;
    private static final double EXTRA_COLECTIVO = 1.0;

    // WMC: CC = 1 (secuencial, llama a super)
    // CCog: 0 (secuencial)
    public TransportePersonas(double horas, int personas) {
        super(horas, personas, true);
    }

    // WMC: CC = 2
    //   +1 base
    //   +1 if (getPersonas() < LIMITE_COLECTIVO)
    // CCog = 1
    //   +1 if (...) [nivel 0]
    @Override
    public double extraSueldo() {
        if (getPersonas() < LIMITE_COLECTIVO) {         // WMC+1, CCog+1 [nivel 0]
            return horas() * EXTRA_NO_COLECTIVO;
        } else {
            return horas() * EXTRA_COLECTIVO;
        }
    }

    // WMC total = 1+2 = 3
    // CCog total = 0+1 = 1
}