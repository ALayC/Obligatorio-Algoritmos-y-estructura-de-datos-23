package sistemaAutogestion;

import clases.*;
import java.time.LocalDate;
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
        listarConsultas(medicoAux.getListaConsultas().inicio);
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;
    }

    private void listarConsultas(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            listarConsultas(nodo.getSiguiente());
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
        ListaN<Reserva> reservasCerradasONoAsisitidas = new ListaN<>();
        historiaClínicaPaciente(Reserva.todasLasReservas.getInicio(), ci, reservasCerradasONoAsisitidas);
        reservasCerradasONoAsisitidas.mostrar();
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;
    }

    private void historiaClínicaPaciente(Nodo nodoActual, int ci, ListaN<Reserva> reservasCerradasONoAsisitidas) {

        if (nodoActual == null) {
            return;
        }
        Reserva reservaActual = (Reserva) nodoActual.getDato();
        if (reservaActual.getCiPaciente() == ci && (reservaActual.estado == Reserva.EstadoReserva.NO_ASISTIO || reservaActual.estado == Reserva.EstadoReserva.TERMINADA)) {
            reservasCerradasONoAsisitidas.agregarFinal(reservaActual);
        }
        historiaClínicaPaciente(nodoActual.getSiguiente(), ci, reservasCerradasONoAsisitidas);
    }

    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
 
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        
        if (mes <= 0 || mes > 12 || año < 2020 || año > 2023) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r; // Retornamos inmediatamente si los valores no son válidos.
        }
        // Obtenemos todas las reservas.
        ListaN<Reserva> reservas = Reserva.todasLasReservas;
        ListaN<Integer> codigosMedicos = new ListaN<>();

        // Iteramos sobre todas las reservas para recolectar códigos de médicos únicos.
        for (int i = 0; i < reservas.cantElementos(); i++) {
            Reserva reserva = reservas.obtenerElemento(i);
            int codMedico = reserva.getCodMedico();
            if (!codigosMedicos.existeElemento(codMedico)) {
                codigosMedicos.agregarFinal(codMedico);
            }
        }

        // Basado en los códigos de médicos, obtenemos los objetos médicos correspondientes.
        ListaN<Medico> listaMedicos = new ListaN<>();
        for (int i = 0; i < codigosMedicos.cantElementos(); i++) {
            int codigoMedico = codigosMedicos.obtenerElemento(i);
            Medico medico = obtenerMedicoPorCodigo(codigoMedico);
            if (medico != null) {
                listaMedicos.agregarFinal(medico);
            }
        }

        // A partir de los médicos, obtenemos sus especialidades únicas.
        ListaN<Integer> listaEspecialidades = new ListaN<>();
        for (int i = 0; i < listaMedicos.cantElementos(); i++) {
            Medico medico = listaMedicos.obtenerElemento(i);
            int especialidad = medico.getEspecialidad();
            if (!listaEspecialidades.existeElemento(especialidad)) {
                listaEspecialidades.agregarFinal(especialidad);
            }
        }

        // Configuramos el mes y año para el informe.
        LocalDate date = LocalDate.of(año, mes, 1);
        int daysInMonth = date.lengthOfMonth();

        // Inicializamos una matriz para almacenar la cantidad de consultas por especialidad por día.
        int[][] matrizEspecialidadesXDias = new int[daysInMonth][listaEspecialidades.cantElementos()];

        // Guardamos en la matriz los datos de las reservas.
        for (int i = 0; i < Reserva.todasLasReservas.cantElementos(); i++) {
            // Obtener la reserva actual del índice i.
            Reserva reserva = Reserva.todasLasReservas.obtenerElemento(i);

            // Verificar si la reserva pertenece al mes y año especificados y su estado es "TERMINADA".
            if (reserva.getFecha().getMonthValue() == mes
                    && reserva.getFecha().getYear() == año
                    && reserva.getEstado() == Reserva.EstadoReserva.TERMINADA) {

                // Determinar el día del mes de la reserva y ajustar el índice para la matriz.
                int dia = reserva.getFecha().getDayOfMonth() - 1;

                // Obtener el médico asociado a la reserva.
                Medico medico = obtenerMedicoPorCodigo(reserva.getCodMedico());

                // Determinar el índice de la especialidad del médico para la matriz.
                int columna = getIndiceDeEspecialidad(listaEspecialidades, medico.getEspecialidad());

                // Incrementar el contador en la matriz para esta combinación de día y especialidad.
                matrizEspecialidadesXDias[dia][columna]++;
            }
        }

        // Imprimir encabezados de columnas (especialidades).
        System.out.printf("%3s", "Dia"); // Encabezado para la columna de días.
        for (int i = 0; i < listaEspecialidades.cantElementos(); i++) {
            // Obtener y mostrar el código de la especialidad como encabezado de columna.
            int codigoEspecialidad = listaEspecialidades.obtenerElemento(i);
            System.out.printf("%3d", codigoEspecialidad);  // Ancho de 3 caracteres, centrado.
        }
        System.out.println();

        // Imprimir la matriz con encabezados para las filas (días del mes).
        for (int i = 0; i < matrizEspecialidadesXDias.length; i++) {
            // Mostrar el número de día como encabezado de fila.
            System.out.printf("%3d", (i + 1)); // Ajusta el día del mes a un ancho de 3 caracteres.

            // Recorrer y mostrar los valores de la matriz para el día actual.
            for (int j = 0; j < matrizEspecialidadesXDias[i].length; j++) {
                System.out.printf("%3d", matrizEspecialidadesXDias[i][j]);  // Ancho de 3 caracteres.
            }
            System.out.println(); // Salto de línea al finalizar la fila.
        }

        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    private int getIndiceDeEspecialidad(ListaN<Integer> lista, int especialidad) {

        // Iterar sobre la lista buscando la especialidad.
        for (int i = 0; i < lista.cantElementos(); i++) {

            // Comprobar si el elemento actual de la lista es igual a la especialidad buscada.
            if (lista.obtenerElemento(i).equals(especialidad)) {
                // Si se encuentra la especialidad, se devuelve su índice.
                return i;
            }
        }

        // Si no se encuentra la especialidad en la lista tras iterar por todos sus elementos, se devuelve -1.
        // Nota: esto no debería suceder en un escenario normal, pero se incluye para manejar casos inesperados.
        return -1;
    }

}
