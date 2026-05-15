package es.unican.is2.transportes;

// WMC = 2 (suma de CC de todos los métodos)
// CCog = 0 (suma de complejidad cognitiva de todos los métodos)
public class TransporteMercancias extends Transporte {

    private static final double EXTRA_TONELADA = 2.0;

    // WMC: CC = 1 (secuencial, llama a super)
    // CCog: 0 (secuencial)
    public TransporteMercancias(double horas, int toneladas) {
        super(horas, toneladas, false);
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    @Override
    public double extraSueldo() {
        return ton() * EXTRA_TONELADA;
    }

    // WMC total = 1+1 = 2
    // CCog total = 0+0 = 0
}