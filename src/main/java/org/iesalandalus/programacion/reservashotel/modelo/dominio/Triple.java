package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Triple extends Habitacion {

    public static final int NUM_MAXIMO_PERSONAS = 3;
    public static final int MIN_NUM_BANOS = 1;
    public static final int MAX_NUM_BANOS = 3;
    public static final int MIN_NUM_CAMAS_INDIVIDUALES = 0;
    public static final int MAX_NUM_CAMAS_INDIVIDUALES = 3;
    public static final int MIN_NUM_CAMAS_DOBLES = 0;
    public static final int MAX_NUM_CAMAS_DOBLES = 1;

    private int numBanos;
    private int numCamasIndividuales;
    private int numCamasDobles;

    public Triple(int planta, int puerta, double precio, int numBanos, int numCamasIndividuales, int numCamasDobles) {
        super(planta, puerta, precio);
        setNumBanos(numBanos);
        setNumCamasIndividuales(numCamasIndividuales);
        setNumCamasDobles(numCamasDobles);
        validaNumCamas();
    }

    public Triple(Triple habitacionTriple) {
        super(habitacionTriple.getPlanta(), habitacionTriple.getPuerta(), habitacionTriple.getPrecio());
        setNumBanos(habitacionTriple.getNumBanos());
        setNumCamasIndividuales(habitacionTriple.getNumCamasIndividuales());
        setNumCamasDobles(habitacionTriple.getNumCamasDobles());
    }

    public Triple(int planta, int puerta) {
        super();
    }

    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos) {
        if (numBanos < MIN_NUM_BANOS || numBanos > MAX_NUM_BANOS) {
            throw new IllegalArgumentException(ERROR_NUM_BANOS_HABITACION_TRIPLE_INCORRECTO);
        }
        this.numBanos = numBanos;
    }

    public int getNumCamasIndividuales() {
        return numCamasIndividuales;
    }

    public void setNumCamasIndividuales(int numCamasIndividuales) {
        if (numCamasIndividuales < MIN_NUM_CAMAS_INDIVIDUALES || numCamasIndividuales > MAX_NUM_CAMAS_INDIVIDUALES) {
            throw new IllegalArgumentException(ERROR_NUM_CAMAS_INDIVIDUALES_HABITACION_TRIPLE_INCORRECTO);
        }
        this.numCamasIndividuales = numCamasIndividuales;
        validaNumCamas();
    }

    public int getNumCamasDobles() {
        return numCamasDobles;
    }

    public void setNumCamasDobles(int numCamasDobles) {
        if (numCamasDobles < MIN_NUM_CAMAS_DOBLES || numCamasDobles > MAX_NUM_CAMAS_DOBLES) {
            throw new IllegalArgumentException(ERROR_NUM_CAMAS_DOBLE_HABITACION_TRIPLE_INCORRECTO);
        }
        this.numCamasDobles = numCamasDobles;
        validaNumCamas();
    }

    private void validaNumCamas() {
        if (!((numCamasIndividuales == 3 && numCamasDobles == 0) ||
                (numCamasIndividuales == 1 && numCamasDobles == 1))) {
            throw new IllegalArgumentException(ERROR_DISTRIBUCION_CAMAS_HABITACION_TRIPLE_INCORRECTO);
        }
    }

    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitación=%.2f, habitación triple, capacidad=%d personas, baños=%d, camas individuales=%d, camas dobles=%d",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(), getNumeroMaximoPersonas(), numBanos, numCamasIndividuales, numCamasDobles);
    }
}
