package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;


import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHabitaciones;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Habitaciones implements IHabitaciones {
    private List<Habitacion> coleccionHabitaciones;

    public Habitaciones() {
        this.coleccionHabitaciones = new ArrayList<>();
    }

    public List<Habitacion> get() {
        return new ArrayList<>(coleccionHabitaciones);
    }

    public List<Habitacion> get(TipoHabitacion tipo) {
        return coleccionHabitaciones.stream()
                .filter(h -> h.getTipoHabitacion() == tipo)
                .map(Habitacion::new)
                .collect(Collectors.toList());
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: No se puede insertar una habitación nula.");
        }
        if (coleccionHabitaciones.contains(habitacion)) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }
        coleccionHabitaciones.add(new Habitacion(habitacion) {
            @Override
            public int getNumeroMaximoPersonas() {
                return 0;
            }
        });
    }

    public Habitacion buscar(Habitacion habitacion) {
        int indice = coleccionHabitaciones.indexOf(habitacion);
        return (indice != -1) ? new Habitacion(coleccionHabitaciones.get(indice)) {
            @Override
            public int getNumeroMaximoPersonas() {
                return 0;
            }
        } : null;
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar una habitación nula.");
        }
        if (!coleccionHabitaciones.remove(habitacion)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación con ese identificador.");
        }
    }

    @Override
    public int getTamano() {
        return 0;
    }
}
