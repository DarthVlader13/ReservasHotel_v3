package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    SALIR("Salir") {
        @Override
        public void ejecutar() {
            vista.terminar();
        }
    },
    INSERTAR_HUESPED("Insertar hu�sped") {
        @Override
        public void ejecutar() {
            Vista.insertarHuesped();
        }
    },
    BUSCAR_HUESPED("Buscar hu�sped") {
        @Override
        public void ejecutar() {
            vista.buscarHuesped();
        }
    },
    BORRAR_HUESPED("Borrar hu�sped") {
        @Override
        public void ejecutar() {
            Vista.borrarHuesped();
        }
    },
    MOSTRAR_HUESPEDES("Mostrar hu�spedes") {
        @Override
        public void ejecutar() {
            vista.mostrarHuespedes();
        }
    },
    INSERTAR_HABITACION("Insertar habitaci�n") {
        @Override
        public void ejecutar() {
            Vista.insertarHabitacion();
        }
    },
    BUSCAR_HABITACION("Buscar habitaci�n") {
        @Override
        public void ejecutar() {
            Vista.buscarHabitacion();
        }
    },
    BORRAR_HABITACION("Borrar habitaci�n") {
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

    // Constructor de la enumeraci�n.
    Opcion(String mensajeAMostrar) {
        this.mensajeAMostrar = mensajeAMostrar;
    }

    // M�todo est�tico para asignar la vista.
    public static void setVista(Vista vista) {
        Opcion.vista = vista;
    }

    // M�todo abstracto que deber� ser implementado por cada instancia del enum.
    public abstract void ejecutar();

    public String getMensajeAMostrar() {
        return mensajeAMostrar;
    }

    @Override
    public String toString() {
        return ordinal() + " .- " + mensajeAMostrar;
    }
}
