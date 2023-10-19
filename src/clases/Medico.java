package clases;

import tads.ListaN;
import tads.*;
import java.time.LocalDate;

public class Medico implements Comparable<Medico> {

    /**
     * @return the listaConsultas
     */
    public ListaN getListaConsultas() {
        return listaConsultas;
    }

    /**
     * @param listaConsultas the listaConsultas to set
     */
    public void setListaConsultas(ListaN listaConsultas) {
        this.listaConsultas = listaConsultas;
    }

    private String nombre;
    private int codMedico;
    private int tel;
    private int especialidad;
    private ListaN listaConsultas;
    private Cola<Paciente> listaDeEspera;

    public Medico(String elNombre, int elCod, int elTel, int laEspecialida) {
        this.setNombre(elNombre);
        this.setCodMedico(elCod);
        this.setTel(elTel);
        this.setEspecialidad(laEspecialida);
        this.listaDeEspera = new Cola<>();
        this.listaConsultas = new ListaN();
    }

    public int cantElementosParaFecha(LocalDate fecha) {
        int contador = 0;
        for (int i = 0; i < getListaConsultas().cantElementos(); i++) {
            Reserva reserva = (Reserva) getListaConsultas().obtenerElemento(i);

            if (reserva.getFecha().equals(fecha)) {
                contador++;
            }
        }
        return contador;
    }

    public boolean tieneConsultaConPacienteEnFecha(int ciPaciente, LocalDate fecha) {
        for (int i = 0; i < getListaConsultas().cantElementos(); i++) {
            Reserva reserva = (Reserva) getListaConsultas().obtenerElemento(i);

            if (reserva.getFecha().equals(fecha) && reserva.getCiPaciente() == ciPaciente) {
                return true;
            }
        }
        return false;
    }

    public Cola<Paciente> getListaDeEspera() {
        return listaDeEspera;
    }

    public void setListaDeEspera(Cola<Paciente> listaDeEspera) {
        this.listaDeEspera = listaDeEspera;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the codMedico
     */
    public int getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedico the codMedico to set
     */
    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    /**
     * @return the tel
     */
    public int getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(int tel) {
        this.tel = tel;
    }

    /**
     * @return the especialidad
     */
    public int getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object obj) {
        Medico md = (Medico) obj;
        boolean es = false;

        if (this.codMedico == md.codMedico) {
            es = true;
            return es;
        }
        return es;
    }

    @Override
    public int compareTo(Medico o) {
        // return Integer.compare(this.codMedico, o.codMedico);
        return this.getNombre().compareTo(o.getNombre());

    }

    @Override
    public String toString() {
        return "Medico: " + nombre + "\nCodigo: " + codMedico + "\nEspecialidad: " + especialidad + "\n";
    }
}
