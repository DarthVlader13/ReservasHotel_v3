package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("Salir") {
        @Override
        public void ejecutar() {
            vista.terminar();
        }
    },
    INSERTAR_HUESPED("Insertar huésped") {
        @Override
        public void ejecutar() {
            Vista.insertarHuesped();
        }
    },
    BUSCAR_HUESPED("Buscar huésped") {
        @Override
        public void ejecutar() {
            vista.buscarHuesped();
        }
    },
    BORRAR_HUESPED("Borrar huésped") {
        @Override
        public void ejecutar() {
            Vista.borrarHuesped();
        }
    },
    MOSTRAR_HUESPEDES("Mostrar huéspedes") {
        @Override
        public void ejecutar() {
            vista.mostrarHuespedes();
        }
    },
    INSERTAR_HABITACION("Insertar habitación") {
        @Override
        public void ejecutar() {
            Vista.insertarHabitacion();
        }
    },
    BUSCAR_HABITACION("Buscar habitación") {
        @Override
        public void ejecutar() {
            Vista.buscarHabitacion();
        }
    },
    BORRAR_HABITACION("Borrar habitación") {
        @Override
        public void ejecutar() {
            Vista.borrarHabitacion();
        }
    },
    MOSTRAR_HABITACIONES("Mostrar habitaciones") {
        @Override
        public void ejecutar() {
            vista.mostrarHabitaciones();
        }
    },
    INSERTAR_RESERVA("Insertar reserva") {
        @Override
        public void ejecutar() {
            Vista.insertarReserva();
        }
    },
    ANULAR_RESERVA("Anular reserva") {
        @Override
        public void ejecutar() {
            Vista.anularReserva();
        }
    },
    MOSTRAR_RESERVAS("Mostrar reservas") {
        @Override
        public void ejecutar() {
            vista.mostrarReservas();
        }
    },
    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad") {
        @Override
        public void ejecutar() {
            Vista.consultarDisponibilidad();
        }
    },
    REALIZAR_CHECKIN("Realizar check-in") {
        @Override
        public void ejecutar() {
            vista.realizarCheckin();
        }
    },
    REALIZAR_CHECKOUT("Realizar check-out") {
        @Override
        public void ejecutar() {
            vista.realizarCheckout();
        }
    };

    private static Vista vista;
    private final String mensajeAMostrar;

    // Constructor de la enumeración.
    Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }

    // Método estático para asignar la vista.
    public static void setVista(Vista vista) {
        Opcion.vista = vista;
    }

    // Método abstracto que deberá ser implementado por cada instancia del enum.
    public abstract void ejecutar();

    public String getMensajeAMostrar() {
        return mensajeAMostrar;
    }

    @Override
    public String toString() {
        return ordinal() + " .- " + mensajeAMostrar;
    }
}
