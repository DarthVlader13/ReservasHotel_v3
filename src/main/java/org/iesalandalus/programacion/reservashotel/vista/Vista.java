package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.memoria.Reservas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Vista {

    private Controlador controlador;

    public Vista() {
        // Constructor que podría inicializar componentes de la vista si fuera necesario
        Opcion.setVista(this);
    }

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("¡Hasta la próxima!");
    }

    private void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                consultarDisponibilidad();
                break;
            case REALIZAR_CHECKIN:
                realizarCheckin();
                break;
            case REALIZAR_CHECKOUT:
                realizarCheckout();
                break;
            case SALIR:
                terminar();
                break;
            default:
                System.out.println("Opción no reconocida.");
                break;
        }
    }

    // Método de ejemplo para insertar un huésped
    static void insertarHuesped() {
        try {
            Huesped nuevoHuesped = Consola.leerHuesped();
            huespedes.insertar(nuevoHuesped);
            System.out.println("Huésped insertado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar un huésped nulo.");
        } catch (OperationNotSupportedException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Métodos para buscar, borrar, mostrar, etc.
    void buscarHuesped() {
        Huesped huesped = Consola.leerHuesped();
        Huesped huespedEncontrado = controlador.buscarHuesped(huesped);
        if (huespedEncontrado != null) {
            System.out.println("Huésped encontrado: " + huespedEncontrado);
        } else {
            System.out.println("Huésped no encontrado.");
        }
    }

    static void borrarHuesped() {
        try {
            Huesped huespedBorrar = Consola.getHuespedPorDni();
            huespedes.borrar(huespedBorrar);
            System.out.println("Huésped borrado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un huésped nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    void mostrarHuespedes() {
        List<Huesped> listaHuespedes = Arrays.asList(controlador.getHuespedes());
        listaHuespedes.sort(Comparator.comparing(Huesped::getNombre));
        listaHuespedes.forEach(System.out::println);
    }

    static void insertarHabitacion() {
        try {
            Habitacion nuevaHabitacion = Consola.leerHabitacion();
            habitaciones.insertar(nuevaHabitacion);
            System.out.println("Habitación insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una habitación nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    static void buscarHabitacion() {
        try {
            Habitacion habitacionBuscada = Consola.leerHabitacionPorIdentificador();
            Habitacion encontrada = habitaciones.buscar(habitacionBuscada);
            if (encontrada != null) {
                System.out.println("Habitación encontrada: " + encontrada);
            } else {
                System.out.println("Habitación no encontrada.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar una habitación nula.");
        }
    }

    static void borrarHabitacion() {
        try {
            Habitacion habitacionBorrar = Consola.leerHabitacionPorIdentificador();
            habitaciones.borrar(habitacionBorrar);
            System.out.println("Habitación borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar una habitación nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    void mostrarHabitaciones() {
        List<Habitacion> listaHabitaciones = Arrays.asList((Habitacion) controlador.getHabitaciones());
        listaHabitaciones.sort(Comparator.comparing(Habitacion::getPlanta)
                .thenComparing(Habitacion::getPuerta));
        listaHabitaciones.forEach(System.out::println);
    }


    static void insertarReserva() {
        try {
            Reserva nuevaReserva = Consola.leerReserva();
            reservas.insertar(nuevaReserva);
            System.out.println("Reserva insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una reserva nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    void mostrarReservas() {
        List<Reserva> listaReservas = Arrays.asList((Reserva) controlador.getReservas());
        listaReservas.sort(Comparator.comparing(Reserva::getFechaInicioReserva)
                .reversed()
                .thenComparing(r -> r.getHabitacion().getPlanta())
                .thenComparing(r -> r.getHabitacion().getPuerta()));
        listaReservas.forEach(System.out::println);
    }


    static void anularReserva() {
        try {
            Huesped huesped = Consola.getHuespedPorDni();
            Reserva reservaAnular = reservas.buscar(Consola.leerReserva());
            if (reservaAnular != null && reservaAnular.getHuesped().equals(huesped)) {
                reservas.borrar(reservaAnular);
                System.out.println("Reserva anulada correctamente.");
            } else {
                System.out.println("No se ha encontrado la reserva o no corresponde al huésped indicado.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede anular una reserva nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    static void consultarDisponibilidad() {
        TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio de la reserva (YYYY-MM-DD): ");
        LocalDate fechaFin = Consola.leerFecha("Introduce la fecha de fin de la reserva (YYYY-MM-DD): ");

        boolean disponibilidad = reservas.consultarDisponibilidad(tipoHabitacion, fechaInicio, fechaFin);
        if (disponibilidad) {
            System.out.println("Hay habitaciones disponibles del tipo " + tipoHabitacion + " en las fechas indicadas.");
        } else {
            System.out.println("No hay habitaciones disponibles del tipo " + tipoHabitacion + " en las fechas indicadas.");
        }
    }

    private static void mostrarListaReservas(Reserva[] reservas) {
        if (reservas != null && reservas.length > 0) {
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        } else {
            System.out.println("No hay reservas que mostrar.");
        }
    }

    private void listarReservasHuesped(Huesped huesped) {
        List<Reserva> reservasHuesped = Arrays.stream(controlador.getReservas())
                .filter(reserva -> reserva.getHuesped().equals(huesped))
                .sorted(Comparator.comparing(Reserva::getFechaInicioReserva)
                        .reversed()
                        .thenComparing(r -> r.getHabitacion().getPlanta())
                        .thenComparing(r -> r.getHabitacion().getPuerta()))
                .collect(Collectors.toList());
        reservasHuesped.forEach(System.out::println);
    }


    private void listarReservasTipoHabitacion(TipoHabitacion tipoHabitacion) {
        List<Reserva> reservasTipoHabitacion = Arrays.stream(controlador.getReservas())
                .filter(reserva -> reserva.getHabitacion().getTipoHabitacion() == tipoHabitacion)
                .sorted(Comparator.comparing(Reserva::getFechaInicioReserva)
                        .reversed()
                        .thenComparing(r -> r.getHuesped().getNombre()))
                .collect(Collectors.toList());
        reservasTipoHabitacion.forEach(System.out::println);
    }

    // Métodos para realizar check-in y check-out

    void realizarCheckin() {
        Huesped huesped = Consola.getHuespedPorDni();
        int[] todasReservas = controlador.getReservas();
        List<Reserva> reservasHuesped = Arrays.stream(todasReservas)
                .filter(reserva -> reserva.getHuesped().equals(huesped))
                .collect(Collectors.toList());

        if (reservasHuesped.isEmpty()) {
            System.out.println("No hay reservas para el huésped con DNI: " + huesped.getDni());
            return;
        }

        System.out.println("Selecciona la reserva para realizar el check-in:");
        for (int i = 0; i < reservasHuesped.size(); i++) {
            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
        }
        int indice = Entrada.entero() - 1;
        if (indice < 0 || indice >= reservasHuesped.size()) {
            System.out.println("Selección no válida");
            return;
        }
        Reserva reservaSeleccionada = reservasHuesped.get(indice);
        LocalDateTime fechaHoraCheckin = Consola.leerFechaHora("Introduce la fecha y hora de check-in (YYYY-MM-DDTHH:MM): ");

        try {
            controlador.realizarCheckin(reservaSeleccionada, fechaHoraCheckin);
            System.out.println("Check-in realizado con éxito.");
        } catch (OperationNotSupportedException e) {
            System.out.println("No se pudo realizar el check-in: " + e.getMessage());
        }
    }

    void realizarCheckout() {
        Huesped huesped = Consola.getHuespedPorDni();
        int[] todasReservas = controlador.getReservas();
        List<Reserva> reservasHuesped = Arrays.stream(todasReservas)
                .filter(reserva -> reserva.getHuesped().equals(huesped))
                .toList();

        if (reservasHuesped.isEmpty()) {
            System.out.println("No hay reservas para el huésped con DNI: " + huesped.getDni());
            return;
        }

        System.out.println("Selecciona la reserva para realizar el check-out:");
        for (int i = 0; i < reservasHuesped.size(); i++) {
            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
        }
        int indice = Entrada.entero() - 1;
        if (indice < 0 || indice >= reservasHuesped.size()) {
            System.out.println("Selección no válida");
            return;
        }
        Reserva reservaSeleccionada = reservasHuesped.get(indice);
        LocalDateTime fechaHoraCheckout = Consola.leerFechaHora("Introduce la fecha y hora de check-out (YYYY-MM-DDTHH:MM): ");

        try {
            controlador.realizarCheckout(reservaSeleccionada, fechaHoraCheckout);
            System.out.println("Check-out realizado con éxito.");
        } catch (OperationNotSupportedException e) {
            System.out.println("No se pudo realizar el check-out: " + e.getMessage());
        }
    }

    public void mostrarReservasHuesped() {
        Huesped huesped = Consola.getHuespedPorDni();
        List<Reserva> reservasHuesped = controlador.listarReservasHuesped(huesped);
        if (!reservasHuesped.isEmpty()) {
            reservasHuesped.forEach(System.out::println);
        } else {
            System.out.println("No hay reservas para el huésped con DNI: " + huesped.getDni());
        }
    }
    public void mostrarReservasTipoHabitacion() {
        TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
        List<Reserva> reservasTipoHabitacion = controlador.listarReservasTipoHabitacion(tipoHabitacion);
        if (!reservasTipoHabitacion.isEmpty()) {
            reservasTipoHabitacion.forEach(System.out::println);
        } else {
            System.out.println("No hay reservas para el tipo de habitación: " + tipoHabitacion);
        }
    }

    public void comprobarDisponibilidad() {
        TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();
        LocalDate fechaInicio = Consola.leerFecha("Introduce la fecha de inicio de la reserva (YYYY-MM-DD): ");
        LocalDate fechaFin = Consola.leerFecha("Introduce la fecha de fin de la reserva (YYYY-MM-DD): ");
        Habitacion habitacion = controlador.consultarDisponibilidad(tipoHabitacion, fechaInicio, fechaFin);

        if (habitacion != null) {
            System.out.println("Habitación disponible: " + habitacion);
        } else {
            System.out.println("No hay disponibilidad para el tipo de habitación solicitado.");
        }
    }


}
