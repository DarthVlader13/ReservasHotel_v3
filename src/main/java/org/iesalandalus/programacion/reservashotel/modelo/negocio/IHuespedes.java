package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IHuespedes {
    List<Huesped> get();
    Huesped buscar(Huesped huesped);
    void insertar(Huesped huesped) throws OperationNotSupportedException;
    void borrar(Huesped huesped) throws OperationNotSupportedException;
    int getTamano();
}
