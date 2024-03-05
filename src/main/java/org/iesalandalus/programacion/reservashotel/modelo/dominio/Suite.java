package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Suite extends Habitacion {

    // Constantes
    public static final int NUM_MAXIMO_PERSONAS = 4;
    public static final int MIN_NUM_BANOS = 1;
    public static final int MAX_NUM_BANOS = 2;

    // Atributos
    private int numBanos;
    private boolean tieneJacuzzi;

    // Constructor completo
    public Suite(int planta, int puerta, double precio, int numBanos, boolean tieneJacuzzi) {
        super(planta, puerta, precio);
        setNumBanos(numBanos);
        setTieneJacuzzi(tieneJacuzzi);
    }

    // Constructor de copia
    public Suite(Suite habitacionSuite) {
        super(habitacionSuite);
        setNumBanos(habitacionSuite.getNumBanos());
        setTieneJacuzzi(habitacionSuite.isTieneJacuzzi());
    }

    public Suite(int planta, int puerta) {
    }

    // Getters y Setters
    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos) {
        if (numBanos < MIN_NUM_BANOS || numBanos > MAX_NUM_BANOS) {
            throw new IllegalArgumentException("ERROR: El número de baños no puede ser inferior a " + MIN_NUM_BANOS + " ni superior a " + MAX_NUM_BANOS + ".");
        }
        this.numBanos = numBanos;
    }

    public boolean isTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(boolean tieneJacuzzi) {
        this.tieneJacuzzi = tieneJacuzzi;
    }

    // Métodos específicos
    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString() {
        String textoJacuzzi = tieneJacuzzi ? "con Jacuzzi" : "sin Jacuzzi";
        return String.format("identificador=%s (%d-%d), precio habitación=%.2f, habitación suite, capacidad=%d personas, baños=%d, %s",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(),
                getNumeroMaximoPersonas(), numBanos, textoJacuzzi);
    }
}
