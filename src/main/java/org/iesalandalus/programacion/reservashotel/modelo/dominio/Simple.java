package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Simple extends Habitacion {

    private static final int NUM_MAXIMO_PERSONAS = 1;

    public Simple(int planta, int puerta, double precio) {
        super(planta, puerta, precio);
    }

    public Simple(Simple habitacionSimple) {
        super(habitacionSimple.getPlanta(), habitacionSimple.getPuerta(), habitacionSimple.getPrecio());
    }

    public Simple(int planta, int puerta) {
    }

    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitación=%.2f, habitación simple, capacidad=%d personas",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(), getNumeroMaximoPersonas());
    }
}
