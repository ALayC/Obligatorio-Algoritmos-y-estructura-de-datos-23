package sistemaAutogestion;

import clases.*;
import java.util.Date;
import tads.*;

public class Sistema implements IObligatorio {

    private ListaN<Medico> listaMedico;
    private ListaN<Paciente> listaPaciente;
    private ListaN<Reserva> listaReserva;

    public Sistema() {
        listaMedico = new ListaN();
        listaPaciente = new ListaN();
        listaReserva = new ListaN();

    }

    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {

        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        if (maxPacientesporMedico < 0 || maxPacientesporMedico > 15) {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    @Override
    public Retorno registrarMedico(String nombre, int codMedico, int tel, int especialidad) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico mAux = new Medico(nombre, codMedico, tel, especialidad);
        // Primero, verificamos si el médico ya existe en la lista.
        if (listaMedico.existeElemento(mAux)) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } // Luego, verificamos el rango de la especialidad.
        else if (especialidad < 1 || especialidad > 20) {
            r.resultado = Retorno.Resultado.ERROR_2;
        } // Si no hay errores, entonces agregamos el médico a la lista.
        else {
            listaMedico.agregarOrdenado(mAux);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    @Override
    public Retorno eliminarMedico(int codMedico) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Retorno agregarPaciente(String nombre, int CI, String direccion) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Paciente pAux = new Paciente(nombre, CI, direccion);

        if (listaPaciente.existeElemento(pAux)) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaPaciente.agregarFinal(pAux);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    @Override
    public Retorno eliminarPaciente(int CI) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno anunciaLlegada(int codMedico, int CIPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno cerrarConsulta(String codMédico, Date fechaConsulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno listarMédicos() {
        listaMedico.mostrar();
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;

    }

    @Override
    public Retorno listarPacientes() {
        listaPaciente.mostrar();
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;

    }

    @Override
    public Retorno listarConsultas(int codMédico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno listarPacientesEnEspera(String codMédico, Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
