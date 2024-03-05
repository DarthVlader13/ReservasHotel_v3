package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.util.Objects;

public abstract class Habitacion {
    public static final double MIN_PRECIO_HABITACION = 40.0;
    public static final double MAX_PRECIO_HABITACION = 150.0;
    public static final int MIN_NUMERO_PUERTA = 0;
    public static final int MAX_NUMERO_PUERTA = 14;
    public static final int MIN_NUMERO_PLANTA = 0;
    public static final int MAX_NUMERO_PLANTA = 2;

    private String identificador;
    private int planta;
    private int puerta;
    private double precio;

    public Habitacion(int planta, int puerta, double precio) {
        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
        generarIdentificador();
    }

    public Habitacion() {

    }

    public Habitacion(Habitacion habitacion) {
    }

    private void generarIdentificador() {
        identificador = String.format("%d-%02d", planta, puerta);
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        if (planta < MIN_NUMERO_PLANTA || planta > MAX_NUMERO_PLANTA) {
            throw new IllegalArgumentException("ERROR: El n�mero de planta no es v�lido.");
        }
        this.planta = planta;
        generarIdentificador();
    }

    public int getPuerta() {
        return puerta;
    }

    public void setPuerta(int puerta) {
        if (puerta < MIN_NUMERO_PUERTA || puerta > MAX_NUMERO_PUERTA) {
            throw new IllegalArgumentException("ERROR: El n�mero de puerta no es v�lido.");
        }
        this.puerta = puerta;
        generarIdentificador();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < MIN_PRECIO_HABITACION || precio > MAX_PRECIO_HABITACION) {
            throw new IllegalArgumentException("ERROR: El precio no es v�lido.");
        }
        this.precio = precio;
    }

    // Este m�todo abstracto se implementar� en cada subclase concreta.
    public abstract int getNumeroMaximoPersonas();

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Habitacion other = (Habitacion) obj;
        return Objects.equals(identificador, other.identificador);
    }

    @Override
    public String toString() {
        return String.format("identificador=%s, planta=%d, puerta=%d, precio=%.2f",
                identificador, planta, puerta, precio);
    }

    public TipoHabitacion getTipoHabitacion() {
        return null;
    }
}
