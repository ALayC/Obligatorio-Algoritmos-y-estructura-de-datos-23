package sistemaAutogestion;

import java.time.LocalDate;
import java.util.Date;


public interface IObligatorio {
    
    /*
    **************** REGISTROS **************************************
    */
    
    //pre: Se espera recibir por parametro un entero, mayor que 0 y menor que 15  post: Se crea sistema correctamente
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico);
     //pre:Se espera recibir por parametro un string no vacio,un entero mayor que 0 para el "codMedico",un entero para "tel" y un entero mayor que 0 y menor que 15 para "especialidad"   post:registro correcto del medico
    public Retorno registrarMedico(String nombre,int codMedico,int tel, int especialidad); 
     //pre:Se recibe por parametro un entero,que ya debe estar registrado como medico y no debe tenerninguna consulta agendada post: se elimina el medico con el codigo indicado
    public  Retorno eliminarMedico(int codMedico); 
     //pre: Se recibe por parametro un string no vacio para "nombre" y "direccion", tambien se recibe un entero para "CI"  post: se registra correctamente el paciente en el sistema
    public Retorno agregarPaciente(String nombre, int CI, String direccion); 
     //pre: Se recibe por parametro un entero que debe estar registrado previamente post: elimina el paciente del sistema
    public Retorno eliminarPaciente(int CI); 

     /*
    **************** GESTIÓN DE CONSULTAS **************************************
    */
      
    //pre:Se recibe por parametro un entero para "codMedico" y "ciPaciente" que deben estar previamente registrados, tambien recibe una fecha  post:Se crea la reserva para el paciente, con el medico y la fecha recibida por parametro
    public Retorno reservaConsulta(int codMedico, int ciPaciente, LocalDate fecha);
    //pre: Se recibe por parametro un entero un entero para "codMedico" y  "ciPaciente" que deben estar previamente registrados   post:Se cancela la reserva del paciente con el medico recibido por parametro
    public Retorno cancelarReserva(int codMedico, int ciPaciente); 
    //pre: Se recibe por parametro un entero un entero para "codMedico" y  "ciPaciente" que deben estar previamente registrados  post:Se cambia el estado de la reserva que pasa a estado: "EN_ESPERA"
    public Retorno anunciaLlegada(int codMedico, int CIPaciente); 
    //pre:  Se recibe por parametro un entero un entero para "codMedico" y  "ciPaciente" que deben estar previamente registrados, tambien recive un string no vacio para "detalleDeConsulta"    post:Se cierra la consulta y pasa a estado: "TERMINADA"
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta);   
    //pre: Se recibe por aprametro un entero y una fecha    post:Se cierra la consulta y pasa a estad : "NO_ASISTIO"
    public Retorno cerrarConsulta(int codMedico, LocalDate fechaConsulta); 
    
 
      /*
    **************** LISTADO Y REPORTES **************************************
    */
    
    //pre:       post: Se listan los medicos ordenados alfabeticamente
    public Retorno listarMédicos(); 
    //pre:      post: Se listan los  pacientes en orden de registro
    public Retorno listarPacientes();     
    //pre:      post:Se listan las consultas para el medico que recibe por aprametro ordenadas por dia
    public Retorno listarConsultas(int codMedico); 
    //pre:      post:Se listan todos los pacientes en espera(estado “en espera”) para el medico recibido por parametro 
    public Retorno listarPacientesEnEspera (int codMedico, LocalDate fecha);    
    //pre:      post:Se listan todas las consultas pendientes para el paciente recibido por parametro
    public Retorno consultasPendientesPaciente(int CIPaciente);   
    //pre:      post:Se listan todas las consultas a las que el paciente asistio(estado: cerradas)
    public Retorno historiaClínicaPaciente (int ci);    
    //pre:      post:Se muestra una matriz con dias (filas), y especialidades (columnas) que muestra cuantos pacientes fueron atendidos por especialidad
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año); 
     
}
