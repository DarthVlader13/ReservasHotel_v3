package org.iesalandalus.programacion.reservashotel.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Reserva {
    private static final int MAX_NUMERO_MESES_RESERVA = 2;
    private static final int MAX_HORAS_POSTERIOR_CHECKOUT = 12;
    public static final String FORMATO_FECHA_RESERVA = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_HORA_RESERVA = "dd/MM/yyyy HH:mm";

    private Huesped huesped;
    private Habitacion habitacion;
    private Regimen regimen;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double precio;
    private int numeroPersonas;

    // Constructor completo
    public Reserva(Huesped huesped, Habitacion habitacion, Regimen regimen, LocalDate fechaInicioReserva, LocalDate fechaFinReserva, int numeroPersonas) {
        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
        calcularPrecio(); // Asegurar que el precio se calcula correctamente
    }

    // Constructor de copia
    public Reserva(Reserva reserva) {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");
        }
        setHuesped(reserva.huesped);
        setHabitacion(reserva.habitacion);
        setRegimen(reserva.regimen);
        setFechaInicioReserva(reserva.fechaInicioReserva);
        setFechaFinReserva(reserva.fechaFinReserva);
        setNumeroPersonas(reserva.numeroPersonas);
        if (reserva.checkIn != null) {
            setCheckIn(reserva.checkIn);
        }
        if (reserva.checkOut != null) {
            setCheckOut(reserva.checkOut);
        }
    }

    public Reserva(Huesped huesped, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin, Regimen regimen) {
    }

    // Getters y setters
    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: El huésped de una reserva no puede ser nulo.");
        }
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: La habitación de una reserva no puede ser nula.");
        }
        this.habitacion = habitacion;
    }

    public Regimen getRegimen() {
        return regimen;
    }

    public void setRegimen(Regimen regimen) {
        if (regimen == null) {
            throw new NullPointerException("ERROR: El régimen de una reserva no puede ser nulo.");
        }
        this.regimen = regimen;
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioReserva) {
        if (fechaInicioReserva == null) {
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        }
        this.fechaInicioReserva = fechaInicioReserva;
    }

    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }

    public void setFechaFinReserva(LocalDate fechaFinReserva) {
        if (fechaFinReserva == null) {
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");
        }
        this.fechaFinReserva = fechaFinReserva;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
        calcularPrecio(); // Recalcular precio al hacer checkout
    }

    public double getPrecio() {
        return precio;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    // Métodos adicionales

    // ReservasHotel_v3 se hacen modificaciones en el método CalcularPrecio
    public void calcularPrecio() {
        long numeroDeDias = ChronoUnit.DAYS.between(fechaInicioReserva, fechaFinReserva);
        if (numeroDeDias <= 0) {
            numeroDeDias = 1; // Considera un mínimo de 1 día de estancia
        }
        double precioPorNoche = habitacion.getPrecio();
        double incrementoPorRegimen = regimen.getIncrementoPrecio(); // Esto puede ser un valor fijo o un porcentaje
        double incrementoPorNochePorPersona = precioPorNoche * incrementoPorRegimen;

        // Asume que getNumeroMaximoPersonas devuelve el número correcto de personas para la habitación
        int maximoPersonasHabitacion = habitacion.getNumeroMaximoPersonas();
        if (numeroPersonas > maximoPersonasHabitacion) {
            throw new IllegalArgumentException("ERROR: El número de personas supera la capacidad de la habitación.");
        }

        precio = (precioPorNoche + incrementoPorNochePorPersona) * numeroDeDias * numeroPersonas;
    }


    @Override
    public int hashCode() {
        return Objects.hash(habitacion, fechaInicioReserva);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reserva other = (Reserva) obj;
        return Objects.equals(habitacion, other.habitacion) &&
                Objects.equals(fechaInicioReserva, other.fechaInicioReserva);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA);
        return String.format("Reserva[huesped=%s, habitacion=%s, regimen=%s, fechaInicioReserva=%s, fechaFinReserva=%s, checkIn=%s, checkOut=%s, precio=%.2f, numeroPersonas=%d]",
                huesped, habitacion, regimen,
                fechaInicioReserva.format(formatter),
                fechaFinReserva.format(formatter),
                (checkIn == null) ? "No registrado" : checkIn.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA_RESERVA)),
                (checkOut == null) ? "No registrado" : checkOut.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA_RESERVA)),
                precio, numeroPersonas);
    }
}
