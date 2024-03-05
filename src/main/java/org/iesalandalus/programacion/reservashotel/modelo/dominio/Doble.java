package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Doble extends Habitacion {

    public static final int NUM_MAXIMO_PERSONAS = 2;
    public static final int MIN_NUM_CAMAS_INDIVIDUALES = 0;
    public static final int MAX_NUM_CAMAS_INDIVIDUALES = 2;
    public static final int MIN_NUM_CAMAS_DOBLES = 0;
    public static final int MAX_NUM_CAMAS_DOBLES = 1;

    private int numCamasIndividuales;
    private int numCamasDobles;

    public Doble(int planta, int puerta, double precio, int numCamasIndividuales, int numCamasDobles) {
        super(planta, puerta, precio);
        setNumCamasIndividuales(numCamasIndividuales);
        setNumCamasDobles(numCamasDobles);
    }

    public Doble(Doble habitacionDoble) {
        super(habitacionDoble.getPlanta(), habitacionDoble.getPuerta(), habitacionDoble.getPrecio());
        setNumCamasIndividuales(habitacionDoble.getNumCamasIndividuales());
        setNumCamasDobles(habitacionDoble.getNumCamasDobles());
    }

    public Doble(int planta, int puerta) {
    }

    public int getNumCamasIndividuales() {
        return numCamasIndividuales;
    }

    public void setNumCamasIndividuales(int numCamasIndividuales) {
        if (numCamasIndividuales < MIN_NUM_CAMAS_INDIVIDUALES || numCamasIndividuales > MAX_NUM_CAMAS_INDIVIDUALES) {
            throw new IllegalArgumentException(ERROR_NUM_CAMAS_INDIVIDUALES_HABITACION_DOBLE_INCORRECTO);
        }
        this.numCamasIndividuales = numCamasIndividuales;
        validaNumCamas();
    }

    public int getNumCamasDobles() {
        return numCamasDobles;
    }

    public void setNumCamasDobles(int numCamasDobles) {
        if (numCamasDobles < MIN_NUM_CAMAS_DOBLES || numCamasDobles > MAX_NUM_CAMAS_DOBLES) {
            throw new IllegalArgumentException(ERROR_NUM_CAMAS_DOBLE_HABITACION_DOBLE_INCORRECTO);
        }
        this.numCamasDobles = numCamasDobles;
        validaNumCamas();
    }

    private void validaNumCamas() {
        if (numCamasIndividuales + numCamasDobles * 2 != NUM_MAXIMO_PERSONAS) {
            throw new IllegalArgumentException(ERROR_DISTRIBUCION_CAMAS_HABITACION_DOBLE_INCORRECTO);
        }
    }

    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitación=%.2f, habitación doble, capacidad=%d personas, camas individuales=%d, camas dobles=%d",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(), getNumeroMaximoPersonas(), numCamasIndividuales, numCamasDobles);
    }
}
