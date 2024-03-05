package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO("Solo Alojamiento", 0),
    ALOJAMIENTO_DESAYUNO("Alojamiento y Desayuno", 15),
    MEDIA_PENSION("Media Pensi�n", 30),
    PENSION_COMPLETA("Pensi�n Completa", 50);

    private final String cadenaAMostrar;
    private final double incrementoPrecio;

    // Constructor con la visibilidad adecuada
    Regimen(String cadenaAMostrar, double incrementoPrecio) {
        this.cadenaAMostrar = cadenaAMostrar;
        this.incrementoPrecio = incrementoPrecio;
    }
    // M�todo getter
    public double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    // M�todo toString que devuelve la cadena esperada por los tests
    @Override
    public String toString() {
        return (ordinal() + 1) + ".-" + cadenaAMostrar;
    }
}