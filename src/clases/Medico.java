package clases;

import tads.ListaN;
import tads.*;

public class Medico implements Comparable<Medico> {

    private String nombre;
    private int codMedico;
    private int tel;
    private int especialidad;
    private ListaN listaConsultas;

    public Medico(String elNombre, int elCod, int elTel, int laEspecialida) {
        this.setNombre(elNombre);
        this.setCodMedico(elCod);
        this.setTel(elTel);
        this.setEspecialidad(laEspecialida);
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
            es=true;
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
