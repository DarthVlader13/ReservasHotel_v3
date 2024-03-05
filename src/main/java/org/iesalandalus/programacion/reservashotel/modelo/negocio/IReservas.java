package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IReservas {
    List<Reserva> get();
    Reserva buscar(Reserva reserva);
    void insertar(Reserva reserva) throws OperationNotSupportedException;
    void borrar(Reserva reserva) throws OperationNotSupportedException;
    List<Reserva> getReservas(Huesped huesped);
    List<Reserva> getReservas(TipoHabitacion tipoHabitacion);
    List<Reserva> getReservasFuturas(Habitacion habitacion);
    boolean consultarDisponibilidad(TipoHabitacion tipo, LocalDate fechaInicio, LocalDate fechaFin);
    void realizarCheckin(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException;
    void realizarCheckout(Reserva reserva, LocalDateTime fecha) throws OperationNotSupportedException;
    int getTamano();
}
