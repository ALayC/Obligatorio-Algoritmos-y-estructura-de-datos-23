package clases;

import tads.ListaN;
import java.time.LocalDate;

public class Reserva implements Comparable<Reserva> {

    // Enumeración de estados
    public enum EstadoReserva {
        PENDIENTE,
        EN_ESPERA,
        TERMINADA,
        NO_ASISTIO;
    }

    private int codMedico;
    private int ciPaciente;
    private LocalDate fecha;
    private int id = 0;
    private static int contadorId = 0;
    public EstadoReserva estado = EstadoReserva.PENDIENTE; 
    public static ListaN<Reserva> todasLasReservas = new ListaN<Reserva>();

    public Reserva() {
        id = ++contadorId;
    }

    public Reserva(int elCodigo, int laCedula, LocalDate laFecha) {
        this.setCodMedico(elCodigo);
        this.setCiPaciente(laCedula);
        this.setFecha(laFecha);
        todasLasReservas.agregarFinal(this);
    }

    public static ListaN<Reserva> getTodasLasReservas() {
        return todasLasReservas;
    }

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    public int getCiPaciente() {
        return ciPaciente;
    }

    public void setCiPaciente(int ciPaciente) {
        this.ciPaciente = ciPaciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        Reserva rv = (Reserva) obj;
        return this.codMedico == rv.codMedico;
    }

    @Override
    public int compareTo(Reserva o) {
        return Integer.compare(this.ciPaciente, o.ciPaciente);
    }

    public EstadoReserva getEstado() { // Modifica el tipo de retorno
        return estado;
    }

    public void setEstado(EstadoReserva estado) { // Modifica el tipo del parámetro
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva: " + "Codigo medico:" + codMedico + "\nCodigoPaciente: " + ciPaciente + "\nFecha: " + fecha + "\n";
    }
}
