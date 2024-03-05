package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IReservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reservas implements IReservas {

    private List<Reserva> coleccionReservas;

    public Reservas() {
        this.coleccionReservas = new ArrayList<>();
    }

    public List<Reserva> get() {
        return new ArrayList<>(coleccionReservas);
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new IllegalArgumentException("ERROR: No se puede insertar una reserva nula.");
        }
        if (coleccionReservas.contains(reserva)) {
            throw new OperationNotSupportedException("ERROR: No se puede insertar la reserva.");
        }
        coleccionReservas.add(new Reserva(reserva));
    }

    public Reserva buscar(Reserva reserva) {
        int indice = coleccionReservas.indexOf(reserva);
        return (indice != -1) ? new Reserva(coleccionReservas.get(indice)) : null;
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar una reserva nula.");
        }
        if (!coleccionReservas.remove(reserva)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
    }

    public List<Reserva> getReservas(Huesped huesped) {
        return coleccionReservas.stream()
                .filter(r -> r.getHuesped().equals(huesped))
                .collect(Collectors.toList());
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        return coleccionReservas.stream()
                .filter(r -> r.getHabitacion().getTipoHabitacion() == tipoHabitacion)
                .collect(Collectors.toList());
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        return coleccionReservas.stream()
                .filter(r -> r.getHabitacion().equals(habitacion) && r.getFechaInicioReserva().isAfter(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public boolean consultarDisponibilidad(TipoHabitacion tipo, LocalDate fechaInicio, LocalDate fechaFin) {
        return coleccionReservas.stream()
                .noneMatch(r -> r.getHabitacion().getTipoHabitacion() == tipo &&
                        fechaInicio.isBefore(r.getFechaFinReserva()) &&
                        fechaFin.isAfter(r.getFechaInicioReserva()));
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
        if (reserva == null || fecha == null) {
            throw new IllegalArgumentException("ERROR: No se puede realizar check-in con reserva o fecha nula.");
        }
        Reserva reservaEncontrada = buscar(reserva);
        if (reservaEncontrada == null) {
            throw new OperationNotSupportedException("ERROR: No existe la reserva para realizar check-in.");
        }
        reservaEncontrada.setCheckIn(fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException {
        if (reserva == null || fecha == null) {
            throw new IllegalArgumentException("ERROR: No se puede realizar checkout con reserva o fecha nula.");
        }
        Reserva reservaEncontrada = buscar(reserva);
        if (reservaEncontrada == null) {
            throw new OperationNotSupportedException("ERROR: No existe la reserva para realizar checkout.");
        }
        reservaEncontrada.setCheckOut(fecha);
    }

    @Override
    public int getTamano() {
        return 0;
    }

    // Otros métodos como getTamano() ya no son necesarios con ArrayList y se han eliminado.
}
