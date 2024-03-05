package org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.IHuespedes;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

public class Huespedes implements IHuespedes {

    private List<Huesped> coleccionHuespedes;

    public Huespedes() {
        this.coleccionHuespedes = new ArrayList<>();
    }

    public List<Huesped> get() {
        return new ArrayList<>(coleccionHuespedes);
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: No se puede insertar un hu�sped nulo.");
        }
        if (coleccionHuespedes.contains(huesped)) {
            throw new OperationNotSupportedException("ERROR: No se puede insertar el hu�sped. Ya existe un hu�sped con ese DNI.");
        }
        coleccionHuespedes.add(new Huesped(huesped));
    }

    public Huesped buscar(Huesped huesped) {
        int indice = coleccionHuespedes.indexOf(huesped);
        return (indice != -1) ? new Huesped(coleccionHuespedes.get(indice)) : null;
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar un hu�sped nulo.");
        }
        if (!coleccionHuespedes.remove(huesped)) {
            throw new OperationNotSupportedException("ERROR: No existe ning�n hu�sped con ese DNI.");
        }
    }

// El m�todo tamanoSuperado y capacidadSuperada no son necesarios en ArrayList.
// El m�todo getTamano se puede implementar directamente utilizando el tama�o de la colecci�n de ArrayList.

    public int getTamano() {
        return coleccionHuespedes.size();
    }

    // El m�todo getCapacidad no es necesario ya que ArrayList maneja su propia capacidad interna y se ajusta din�micamente.
    // Por tanto, se ha eliminado junto con el atributo capacidad.

    // No es necesario el m�todo capacidadSuperada(int indice) ya que ArrayList gestiona su capacidad autom�ticamente.
}

