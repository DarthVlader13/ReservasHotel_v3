package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Consola {

    // Constructor privado
    private Consola() {
    }
    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int ordinal;
        do {
            System.out.print("Elige una opci�n: ");
            try {
                ordinal = Entrada.entero();
            } catch (NumberFormatException e) {
                ordinal = -1;
            }
        } while (ordinal < 0 || ordinal >= Opcion.values().length);

        return Opcion.values()[ordinal];
    }

    public static Huesped leerHuesped() {
        System.out.print("Introduce el nombre del hu�sped: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce los apellidos del hu�sped: ");
        String apellidos = Entrada.cadena();
        System.out.print("Introduce el DNI del hu�sped: ");
        String dni = Entrada.cadena();
        LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento del hu�sped (YYYY-MM-DD): ");
        return new Huesped(nombre, apellidos, dni, fechaNacimiento);
    }

    public static Huesped getHuespedPorDni() {
        System.out.print("Introduce el DNI del hu�sped: ");
        String dni = Entrada.cadena();
        return new Huesped("NombreFicticio", "ApellidosFicticios", dni, null);
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        do {
            try {
                System.out.print(mensaje);
                String fechaStr = Entrada.cadena();
                fecha = LocalDate.parse(fechaStr);
            } catch (DateTimeException e) {
                System.out.println("Error: Formato de fecha incorrecto.");
            }
        } while (fecha == null);
        return fecha;
    }

    //En este m�todo, usamos un bucle do-while que seguir� pidiendo al usuario que introduzca la fecha y hora hasta que proporcione una cadena v�lida que pueda ser parseada a un objeto LocalDateTime.
    public static LocalDateTime leerFechaHora(String mensaje) {
        LocalDateTime fechaHora = null;
        do {
            try {
                System.out.print(mensaje);
                String fechaHoraStr = Entrada.cadena();
                fechaHora = LocalDateTime.parse(fechaHoraStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha y hora incorrecto. El formato correcto es: YYYY-MM-DDTHH:MM");
            }
        } while (fechaHora == null);
        return fechaHora;
    }
    public static Habitacion leerHabitacion() {
        System.out.print("Introduce el n�mero de planta de la habitaci�n: ");
        int planta = Entrada.entero();
        System.out.print("Introduce el n�mero de puerta de la habitaci�n: ");
        int puerta = Entrada.entero();
        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        Habitacion habitacion = null;
        switch (tipoHabitacion) {
            case SIMPLE:
                habitacion = new Simple(planta, puerta); // Asumiendo que hay un constructor con estos par�metros
                break;
            case DOBLE:
                habitacion = new Doble(planta, puerta); // Asumiendo que hay un constructor con estos par�metros
                break;
            case TRIPLE:
                habitacion = new Triple(planta, puerta); // Asumiendo que hay un constructor con estos par�metros
                break;
            case SUITE:
                habitacion = new Suite(planta, puerta); // Asumiendo que hay un constructor con estos par�metros
                break;
            default:
                System.out.println("Tipo de habitaci�n no reconocido.");
                break;
        }
        return habitacion;
    }

    public static Habitacion leerHabitacionPorIdentificador() {
        System.out.print("Introduce el n�mero de planta de la habitaci�n: ");
        int numeroPlanta = Entrada.entero();
        System.out.print("Introduce el n�mero de puerta de la habitaci�n: ");
        int numeroPuerta = Entrada.entero();
        TipoHabitacion tipoHabitacion = leerTipoHabitacion();
        return new Habitacion(numeroPlanta, numeroPuerta, tipoHabitacion.ordinal()) {
            @Override
            public int getNumeroMaximoPersonas() {
                return 0;
            }
        };
    }

    public static TipoHabitacion leerTipoHabitacion() {
        System.out.println("Elige un tipo de habitaci�n:");
        for (TipoHabitacion tipo : TipoHabitacion.values()) {
            System.out.println(tipo);
        }
        int ordinal;
        do {
            System.out.print("Introduce el n�mero correspondiente al tipo de habitaci�n: ");
            ordinal = Entrada.entero();
        } while (ordinal < 0 || ordinal >= TipoHabitacion.values().length);
        return TipoHabitacion.values()[ordinal];
    }

    public static Regimen leerRegimen() {
        System.out.println("Elige un r�gimen:");
        for (Regimen regimen : Regimen.values()) {
            System.out.println(regimen);
        }
        int ordinal;
        do {
            System.out.print("Introduce el n�mero correspondiente al r�gimen: ");
            ordinal = Entrada.entero();
        } while (ordinal < 0 || ordinal >= Regimen.values().length);
        return Regimen.values()[ordinal];
    }

    public static Reserva leerReserva() {
        Huesped huesped = leerHuesped();
        Habitacion habitacion = leerHabitacion();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio de la reserva (YYYY-MM-DD): ");
        LocalDate fechaFin = leerFecha("Introduce la fecha de fin de la reserva (YYYY-MM-DD): ");
        Regimen regimen = leerRegimen();
        return new Reserva(huesped, habitacion, fechaInicio, fechaFin, regimen);
    }
}