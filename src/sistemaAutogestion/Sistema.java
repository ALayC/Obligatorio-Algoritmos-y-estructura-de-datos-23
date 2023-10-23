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
            r.resultado = Retorno.Resultado.ERROR_2;
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

    public Reserva obtenerReserva(int ci, int codMed) {
        for (int i = 0; i < listaReserva.cantElementos(); i++) {
            Reserva reserva = (Reserva) listaReserva.obtenerElemento(i);
            if (reserva.getCiPaciente() == ci && reserva.getCodMedico() == codMed) {
                return reserva;
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

        if (!medicoAux.atiendeEnFecha(fecha)) {
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        }
        //

        // Si llegamos aquí, entonces tanto el médico como el paciente son válidos y no tienen consulta previa en la fecha dada.
        // Ahora, verificamos si el médico tiene espacio en su horario para la fecha dada.
        if (medicoAux.cantElementosParaFecha(fecha) < maxPaciente) {
            Reserva nuevaReserva = new Reserva(codMedico, ciPaciente, fecha);
            medicoAux.getListaConsultas().agregarFinal(nuevaReserva);
            listaReserva.agregarFinal(nuevaReserva);
            r.resultado = Retorno.Resultado.OK;
        } else {
            medicoAux.getColaDeEspera().encolar(pacienteAux);
            r.resultado = Retorno.Resultado.OK;
        }

        return r;
    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente pacienteAux = obtenerPacientePorCI(ciPaciente);
        Medico medicoAux = obtenerMedicoPorCodigo(codMedico);

        if (pacienteAux == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        if (medicoAux == null) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        Reserva reserva = obtenerReserva(ciPaciente, codMedico);
        if (reserva == null || reserva.estado.equals(Reserva.EstadoReserva.TERMINADA)) {
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        if (!reserva.estado.equals(Reserva.EstadoReserva.PENDIENTE)) {
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        }

        // aca se puede cancelar la reserva
        listaReserva.borrarElemento(reserva);

        // Si hay pacientes en lista de espera, desencolamos el primero y hacemos una reserva para él
        LocalDate fechaReservaCancelada = reserva.getFecha();
        if (!medicoAux.getColaDeEspera().isEmpty()) {
            Paciente pacienteEnEspera = medicoAux.getColaDeEspera().desencolar();
            reservaConsulta(medicoAux.getCodMedico(), pacienteEnEspera.getCi(), fechaReservaCancelada);
        }

        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno anunciaLlegada(int codMedico, int ciPaciente) {

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
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        Reserva reserva = obtenerReserva(ciPaciente, codMedico);
        // Verificamos si la reserva existe
        if (reserva == null) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        } else {
            reserva.estado = reserva.estado.EN_ESPERA;
        }
        r.resultado = Retorno.Resultado.OK;
        return r;

    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente pacienteAux = obtenerPacientePorCI(CIPaciente);
        Medico medicoAux = obtenerMedicoPorCodigo(codMedico);

        // Verificamos si el paciente existe
        if (pacienteAux == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        // Verificamos si el médico existe
        if (medicoAux == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        Reserva reserva = obtenerReserva(CIPaciente, codMedico);
        if (reserva.estado.equals(Reserva.EstadoReserva.EN_ESPERA)) {
            reserva.estado = reserva.estado.TERMINADA;
            r.resultado = Retorno.Resultado.OK;
            pacienteAux.getHistorialMedico().agregarFinal(detalleDeConsulta);
            return r;
        }

        return r;
    }

    @Override //REVISAR ERROR 2 
    public Retorno cerrarConsulta(int codMedico, LocalDate fechaConsulta) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Medico medicoAux = obtenerMedicoPorCodigo(codMedico);

        // Verificar si el médico existe
        if (medicoAux == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }

        boolean reservaEncontrada = false;  // Variable para identificar si se encontró alguna reserva para ese médico y fecha

        for (int i = 0; i < listaReserva.cantElementos(); i++) {
            Reserva reserva = (Reserva) listaReserva.obtenerElemento(i);

            if (reserva.getCodMedico() == codMedico
                    && reserva.getFecha().equals(fechaConsulta)
                    && reserva.estado == Reserva.EstadoReserva.PENDIENTE) {

                reserva.estado = Reserva.EstadoReserva.NO_ASISTIO;

                Paciente pacienteReserva = obtenerPacientePorCI(reserva.getCiPaciente());

                String entradaHistorial = "El paciente no asiste a la consulta con el medico " + codMedico
                        + " en la fecha " + fechaConsulta;
                pacienteReserva.getHistorialMedico().agregarFinal(entradaHistorial);

                reservaEncontrada = true;  // Marcamos que se encontró una reserva
            }
        }

        // Si no se encontró ninguna reserva que coincida con el médico y la fecha
        if (!reservaEncontrada) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }

        r.resultado = Retorno.Resultado.OK;
        return r;
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
        Medico medicoAux = obtenerMedicoPorCodigo(codMédico);
        listarConsultasRecursivo(medicoAux.getListaConsultas().inicio);
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;
    }

    private void listarConsultasRecursivo(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            listarConsultasRecursivo(nodo.getSiguiente());
        }
    }

    @Override
    public Retorno listarPacientesEnEspera(int codMédico, LocalDate fecha) {

        Medico medicoAux = obtenerMedicoPorCodigo(codMédico);

        ListaN<Reserva> listaOriginal = medicoAux.getListaConsultas();//obtenemos todas las reservas para ese medico

        ListaN<Reserva> listaAuxParaPacienteEnEspera = new ListaN<>();

        for (int i = 0; i < listaOriginal.cantElementos(); i++) {
            Reserva reservaActual = listaOriginal.obtenerElemento(i);
            if (reservaActual.getEstado() == Reserva.EstadoReserva.EN_ESPERA) {
                listaAuxParaPacienteEnEspera.agregarFinal(reservaActual);
            }
        }
        listaAuxParaPacienteEnEspera.mostrar();
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;
    }

    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente) {
        ListaN<Reserva> reservasPendientes = new ListaN<>();
        consultasPendientesPaciente(Reserva.todasLasReservas.getInicio(), CIPaciente, reservasPendientes);
        reservasPendientes.mostrar();
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;
    }

    private void consultasPendientesPaciente(Nodo nodoActual, int CIPaciente, ListaN<Reserva> reservasPendientes) {
        if (nodoActual == null) {
            return;
        }

        Reserva reservaActual = (Reserva) nodoActual.getDato();
        if (reservaActual.getCiPaciente() == CIPaciente && reservaActual.estado == Reserva.EstadoReserva.PENDIENTE) {
            reservasPendientes.agregarFinal(reservaActual);
        }

        consultasPendientesPaciente(nodoActual.getSiguiente(), CIPaciente, reservasPendientes);
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
