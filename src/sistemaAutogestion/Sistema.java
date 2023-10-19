package sistemaAutogestion;

import clases.*;
import java.time.LocalDate;
import java.util.Date;
import tads.*;

public class Sistema implements IObligatorio {

    private ListaN<Medico> listaMedico;
    private ListaN<Paciente> listaPaciente;
    private ListaN<Reserva> listaReserva;
    private int maxPaciente;

    public Sistema() {

    }

    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {

        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        if (maxPacientesporMedico < 0 || maxPacientesporMedico > 15) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {

            listaMedico = new ListaN();
            listaPaciente = new ListaN();
            listaReserva = new ListaN();
            maxPaciente = maxPacientesporMedico;
            r.resultado = Retorno.Resultado.OK;

        }

        return r;
    }

    public Retorno agregarDiaDeConsultaMedico(int codMedico, LocalDate fecha) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Medico medicoAux = obtenerMedicoPorCodigo(codMedico);

        // Verificamos si el médico existe
        if (medicoAux == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }

        // Si el médico ya atiende en esa fecha
        if (medicoAux.atiendeEnFecha(fecha)) {
            r.resultado = Retorno.Resultado.ERROR_2;  // Aquí, ERROR_2 podría significar "El médico ya atiende en esa fecha"
            return r;
        }

        medicoAux.agregarDiaDeConsulta(fecha);
        r.resultado = Retorno.Resultado.OK;

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
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Medico medicoAEliminar = new Medico("", codMedico, 0, 0);

        if (listaMedico.existeElemento(medicoAEliminar)) {
            listaMedico.eliminarElemento(medicoAEliminar);
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
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
    public Retorno eliminarPaciente(int ci) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente pacienteAEliminar = new Paciente("", ci, "");

        if (listaPaciente.existeElemento(pacienteAEliminar)) {
            listaPaciente.eliminarElemento(pacienteAEliminar);
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    public Medico obtenerMedicoPorCodigo(int codigo) {
        for (int i = 0; i < listaMedico.cantElementos(); i++) {
            Medico medico = (Medico) listaMedico.obtenerElemento(i);

            if (medico.getCodMedico() == codigo) {
                return medico;
            }
        }
        return null;
    }

    public Paciente obtenerPacientePorCI(int ci) {
        for (int i = 0; i < listaPaciente.cantElementos(); i++) {
            Paciente paciente = (Paciente) listaPaciente.obtenerElemento(i);

            if (paciente.getCi() == ci) {
                return paciente;
            }
        }
        return null;
    }

    @Override
    public Retorno reservaConsulta(int codMedico, int ciPaciente, LocalDate fecha) {
        //confirmar si esta bien el formato de la fecha
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

        Paciente pacienteAux = obtenerPacientePorCI(ciPaciente);
        Medico medicoAux = obtenerMedicoPorCodigo(codMedico);
        // Verificamos si el paciente existe
        if (pacienteAux == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;

        }

        // Verificamos si el médico existe
        if (medicoAux == null) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        // Verificamos si el médico ya tiene una consulta con el paciente en la fecha dada
        if (medicoAux.tieneConsultaConPacienteEnFecha(ciPaciente, fecha)) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        //agregar nuevo requerimiento de dia de consulta por el medico
        if (!medicoAux.atiendeEnFecha(fecha)) {
            r.resultado = Retorno.Resultado.ERROR_4; // Aquí deberías definir ERROR_4 para este propósito específico.
            return r;
        }
        //

        // Si llegamos aquí, entonces tanto el médico como el paciente son válidos y no tienen consulta previa en la fecha dada.
        // Ahora, verificamos si el médico tiene espacio en su horario para la fecha dada.
        if (medicoAux.cantElementosParaFecha(fecha) < maxPaciente) {
            Reserva nuevaReserva = new Reserva(codMedico, ciPaciente, fecha);
            medicoAux.getListaConsultas().agregarFinal(nuevaReserva);
            r.resultado = Retorno.Resultado.OK;
        } else {
            medicoAux.getColaDeEspera().encolar(pacienteAux);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
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
