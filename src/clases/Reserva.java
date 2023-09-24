package clases;

import java.util.Date;

public class Reserva implements Comparable<Reserva>{

    private int codMedico;
    private int ciPaciente;
    private Date fecha;
    private  int id =0;
    private static int contadorId=0;
    public Reserva(){
        id=++contadorId;
    }

    public Reserva (int elCodigo, int laCedula, Date laFecha){
        
        this.setCodMedico(elCodigo);
        this.setCiPaciente(laCedula);
        this.setFecha(laFecha);
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
     * @return the ciPaciente
     */
    public int getCiPaciente() {
        return ciPaciente;
    }

    /**
     * @param ciPaciente the ciPaciente to set
     */
    public void setCiPaciente(int ciPaciente) {
        this.ciPaciente = ciPaciente;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

 

      @Override
    public boolean equals(Object obj) {
        Reserva rv = (Reserva) obj;
        boolean es = false;

        if (this.codMedico == rv.codMedico) {
            es=true;
            return es;
        }
        return es;
    }
    
    @Override
    public int compareTo(Reserva o) {
            return Integer.compare(this.ciPaciente, o.ciPaciente);

    }
    
    
}
