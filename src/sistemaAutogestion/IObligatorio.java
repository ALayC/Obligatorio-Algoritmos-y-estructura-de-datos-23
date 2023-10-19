package sistemaAutogestion;

import java.util.Date;


public interface IObligatorio {
    
    /*
    **************** REGISTROS **************************************
    */
    
    //pre: que max sea valido (entre 0 y el tope)    post: se crea sistema correctamente
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico);
     //pre:  no existencia de un medico con el mismo codMedico, especialidad valida      post:registro correcto del medico
    public Retorno registrarMedico(String nombre,int codMedico,int tel, int especialidad); 
     //pre: existe un medico con ese codigo. El medico no podrá consultas en espera      post: se elimina el medico con el codigo indicado
    public  Retorno eliminarMedico(int codMedico); 
     //pre: no existe un paciente con esa CI      post: se registra correctamente el paciente en el sistema
    public Retorno agregarPaciente(String nombre, int CI, String direccion); 
     //pre: debe estar registrada la CI en el sistema. El paciente no debe tener ninguna consulta agendada       post: elimina el paciente del sistema
    public Retorno eliminarPaciente(int CI); 

     /*
    **************** GESTIÓN DE CONSULTAS **************************************
    */
      
    //pre:      post:
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha);
    //pre:      post:
    public Retorno cancelarReserva(int codMedico, int ciPaciente); 
    //pre:      post:
    public Retorno anunciaLlegada(int codMedico, int CIPaciente); 
    //pre:      post:
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta);   
    //pre:      post:
    public Retorno cerrarConsulta(String codMédico, Date fechaConsulta); 
    
 
      /*
    **************** LISTADO Y REPORTES **************************************
    */
    
    //pre:       post: se listan los medicos ordenados alfabeticamente
    public Retorno listarMédicos(); 
    //pre:      post: listar paciente en orden de registro
    public Retorno listarPacientes();     
    //pre:      post:
    public Retorno listarConsultas(int codMedico); 
    //pre:      post:
    public Retorno listarPacientesEnEspera (String codMedico, Date fecha);    
    //pre:      post:
    public Retorno consultasPendientesPaciente(int CIPaciente);   
    //pre:      post:
    public Retorno historiaClínicaPaciente (int ci);    
    //pre:      post:
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año); 
     
}
