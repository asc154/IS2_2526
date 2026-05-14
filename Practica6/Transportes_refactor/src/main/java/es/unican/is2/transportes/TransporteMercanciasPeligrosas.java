package es.unican.is2.transportes;

// WMC = 2 (suma de CC de todos los métodos)
// CCog = 0 (suma de complejidad cognitiva de todos los métodos)
// CBO = 2: hereda de Transporte y usa CategoriaTransporte implícitamente via super (EFF=1)
//          es usada por GestionTransportesGUI (AFF=1)
//          clases que contribuyen: Transporte, GestionTransportesGUI
public class TransporteMercanciasPeligrosas extends Transporte {

    private static final double EXTRA_TONELADA = 2.0;
    private static final double EXTRA_FIJO = 50.0;

    // WMC: CC = 1 (secuencial, llama a super)
    // CCog: 0 (secuencial)
    public TransporteMercanciasPeligrosas(double horas, int toneladas) {
        super(horas, CategoriaTransporte.MercanciasPeligrosas, toneladas);
    }

    // WMC: CC = 1 (secuencial)
    // CCog: 0 (secuencial)
    @Override
    public double extraSueldo() {
        return ton() * EXTRA_TONELADA + EXTRA_FIJO;
    }

    // WMC total = 1+1 = 2
    // CCog total = 0+0 = 0
}