package clases;

import tads.ListaN;
import tads.*;
import java.time.LocalDate;

public class Paciente implements Comparable<Paciente> {

    private String nombre;
    private int ci;
    private String direccion;
    public ListaN HistorialMedico;

    public Paciente(String elNombre, int laCi, String laDirec) {
        this.setNombre(elNombre);
        this.setCi(laCi);
        this.setDireccion(laDirec);
        this.HistorialMedico = new ListaN();
    }

        public ListaN getHistorialMedico() {
        return HistorialMedico;
    }

    /**
     * @param listaConsultas the listaConsultas to set
     */
    public void setHistorialMedico(ListaN HistorialMedico) {
        this.HistorialMedico = HistorialMedico;
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
     * @return the ci
     */
    public int getCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(int ci) {
        this.ci = ci;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int compareTo(Paciente o) {
        return Integer.compare(this.ci, o.ci);

    }

    @Override
    public boolean equals(Object obj) {
        Paciente pc = (Paciente) obj;
        boolean es = false;

        if (this.ci == pc.ci) {
            es = true;
            return es;
        }
        return es;
    }

    @Override
    public String toString() {
        return "Paciente: " + nombre + "\nCedula: " + ci + "\nDireccion: " + direccion + "\n";
    }

}
