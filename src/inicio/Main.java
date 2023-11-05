package inicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import sistemaAutogestion.*;
import clases.*;

public class Main {

    public static void main(String[] args) {

        Prueba p = new Prueba();
        Sistema s = new Sistema();
        s.crearSistemaDeAutogestion(15);

        p1_crearSistema(p, s);
        p2_registrarMedico(p, s);
        p21_diaDeConsultaMedico(p, s);
        p2_3_eliminarMedico(p, s);
        p4_registrarPaciente(p, s);
        p2_5_eliminarPaciente(p, s);
        p2_6_crearReserva(p, s);
        p2_7_cancelarReserva(p, s);
        p2_8_anunciaLlegada(p, s);
        p2_9_terminarConsultaMedicoPaciente(p, s);
        p2_10_cerrarConsulta(p, s);
        p3_1ListaMedicos(p, s);
        p3_2ListarPacientes(p, s);
        p3_3ListarConsultas(p, s);
        p3_4ListarPacientesEnEspera(p, s);
        p3_5consultasPendientesPaciente(p, s);
        p3_6historiaClínicaPaciente(p, s);
        p3_7reporteDePacientesXFechaYEspecialidad(p, s);

        p.imprimirResultadosPrueba();
    }

    public static void p1_crearSistema(Prueba p, Sistema s) {

        p.ver(s.crearSistemaDeAutogestion(10).resultado, Retorno.Resultado.OK, "Creacion correcta del sistema");
        p.ver(s.crearSistemaDeAutogestion(-1).resultado, Retorno.Resultado.ERROR_1, "No se crea el sistema,cantidad no valida");

    }

    public static void p2_registrarMedico(Prueba p, Sistema s) {
        p.ver(s.registrarMedico("Daniel", 1, 12345678, 3).resultado, Retorno.Resultado.OK, "Se registra el medico");
        p.ver(s.registrarMedico("Lucia", 2, 87654321, 10).resultado, Retorno.Resultado.OK, "Se registra el medico");
        p.ver(s.registrarMedico("Daniela", 3, 14785236, 13).resultado, Retorno.Resultado.OK, "Se registra el medico");
        p.ver(s.registrarMedico("ELIMINAR", 4, 00000000, 14).resultado, Retorno.Resultado.OK, "Se registra el medico");

        p.ver(s.registrarMedico("Andres", 1, 98765432, 4).resultado, Retorno.Resultado.ERROR_1, "No se registra, medico con igual codigo");
        p.ver(s.registrarMedico("Lucas", 5, 65498732, 22).resultado, Retorno.Resultado.ERROR_2, "No se registra, medico con especialidad no valida");
    }

    public static void p21_diaDeConsultaMedico(Prueba p, Sistema s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse("15/10/2023", formatter);
        LocalDate fecha2 = LocalDate.parse("16/10/2023", formatter);
        LocalDate fecha3 = LocalDate.parse("17/10/2023", formatter);
        p.ver(s.agregarDiaDeConsultaMedico(1, fecha).resultado, Retorno.Resultado.OK, "Se agrega fecha de consulta para el medico");
        p.ver(s.agregarDiaDeConsultaMedico(2, fecha2).resultado, Retorno.Resultado.OK, "Se agrega fecha de consulta para el medico");
        p.ver(s.agregarDiaDeConsultaMedico(3, fecha3).resultado, Retorno.Resultado.OK, "Se agrega fecha de consulta para el medico");

    }

    public static void p4_registrarPaciente(Prueba p, Sistema s) {

        p.ver(s.agregarPaciente("German", 12345678, "calle 1").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Romina", 14523689, "calle 2").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Jose", 32165478, "calle 3").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Maria", 23456789, "calle 4").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Lucas", 34567890, "calle 5").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Daniela", 45678901, "calle 6").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Andres", 56789012, "calle 7").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Carolina", 67890123, "calle 8").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Martin", 78901234, "calle 9").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Isabel", 89012345, "calle 10").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Roberto", 90123456, "calle 11").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("ELIMINAR", 45678912, "calle 13").resultado, Retorno.Resultado.OK, "Se registra el paciente");

    }

    public static void p2_3_eliminarMedico(Prueba p, Sistema s) {
        p.ver(s.eliminarMedico(300).resultado, Retorno.Resultado.ERROR_1, "Este medico no existe");
        p.ver(s.eliminarMedico(4).resultado, Retorno.Resultado.OK, "Se elimina el medico");
        p.ver(s.eliminarMedico(999).resultado, Retorno.Resultado.ERROR_1, "Este medico no existe");
    }

    public static void p2_5_eliminarPaciente(Prueba p, Sistema s) {
        p.ver(s.eliminarPaciente(100).resultado, Retorno.Resultado.ERROR_1, "Este paciente no existe");
        p.ver(s.eliminarPaciente(45678912).resultado, Retorno.Resultado.OK, "Se elimina paciente");
        p.ver(s.eliminarPaciente(444444).resultado, Retorno.Resultado.ERROR_1, "Este paciente no existe");

    }

    public static void p2_6_crearReserva(Prueba p, Sistema s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse("15/10/2023", formatter);
        LocalDate fecha2 = LocalDate.parse("16/10/2023", formatter);
        LocalDate fecha3 = LocalDate.parse("17/10/2023", formatter);
        p.ver(s.reservaConsulta(1, 12345678, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para German");
        //p.ver(s.reservaConsulta(1, 12345678, fecha2).resultado, Retorno.Resultado.OK, "Crea la reserva para German");
        p.ver(s.reservaConsulta(1, 14523689, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Romina");
        p.ver(s.reservaConsulta(1, 32165478, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Jose");
        p.ver(s.reservaConsulta(1, 23456789, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Maria");
        p.ver(s.reservaConsulta(1, 34567890, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Lucas");
        p.ver(s.reservaConsulta(1, 45678901, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Daniela");
        p.ver(s.reservaConsulta(1, 56789012, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Andres");
        p.ver(s.reservaConsulta(1, 67890123, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Carolina");
        p.ver(s.reservaConsulta(1, 78901234, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Martin");
        p.ver(s.reservaConsulta(1, 89012345, fecha).resultado, Retorno.Resultado.OK, "Crea la reserva para Isabel");

        p.ver(s.reservaConsulta(2, 12345678, fecha2).resultado, Retorno.Resultado.OK, "Crea la reserva para German");
        p.ver(s.reservaConsulta(2, 14523689, fecha2).resultado, Retorno.Resultado.OK, "Crea la reserva para Romina");
        p.ver(s.reservaConsulta(2, 32165478, fecha2).resultado, Retorno.Resultado.OK, "Crea la reserva para Jose");

        p.ver(s.reservaConsulta(3, 12345678, fecha3).resultado, Retorno.Resultado.OK, "Crea la reserva para German");
        p.ver(s.reservaConsulta(3, 14523689, fecha3).resultado, Retorno.Resultado.OK, "Crea la reserva para Romina");
        p.ver(s.reservaConsulta(3, 32165478, fecha3).resultado, Retorno.Resultado.OK, "Crea la reserva para Jose");

        /*p.ver(s.reservaConsulta(1, 90123456, fecha).resultado, Retorno.Resultado.OK, "Paciente agregado a lista de espera");/*

        //p.ver(s.reservaConsulta(1, 12345678, fecha).resultado, Retorno.Resultado.ERROR_3, "Paciente ya tiene reserva para ese dia");
        //p.ver(s.reservaConsulta(1, 41, fecha).resultado, Retorno.Resultado.ERROR_1, "No existe paciente con esa CI");
        //p.ver(s.reservaConsulta(1000000, 12345678, fecha).resultado, Retorno.Resultado.ERROR_2, "No existe docotor con esa codigo");*/
 /*
        /*
         */
    }

    public static void p2_7_cancelarReserva(Prueba p, Sistema s) {

        p.ver(s.cancelarReserva(1, 12345678).resultado, Retorno.Resultado.OK, "Reserva eliminada");
    }

    public static void p2_8_anunciaLlegada(Prueba p, Sistema s) {

        p.ver(s.anunciaLlegada(1, 12345678).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");
        p.ver(s.anunciaLlegada(1, 14523689).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");
        p.ver(s.anunciaLlegada(1, 32165478).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");

        p.ver(s.anunciaLlegada(2, 12345678).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");
        p.ver(s.anunciaLlegada(2, 14523689).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");
        p.ver(s.anunciaLlegada(2, 32165478).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");

        p.ver(s.anunciaLlegada(3, 12345678).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");
        p.ver(s.anunciaLlegada(3, 14523689).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");
        p.ver(s.anunciaLlegada(3, 32165478).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente correctamente");
    }

    public static void p2_9_terminarConsultaMedicoPaciente(Prueba p, Sistema s) {
        p.ver(s.terminarConsultaMedicoPaciente(12345678, 1, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");
        p.ver(s.terminarConsultaMedicoPaciente(14523689, 1, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");
        p.ver(s.terminarConsultaMedicoPaciente(32165478, 1, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");

        p.ver(s.terminarConsultaMedicoPaciente(12345678, 2, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");
        p.ver(s.terminarConsultaMedicoPaciente(14523689, 2, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");
        p.ver(s.terminarConsultaMedicoPaciente(32165478, 2, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");

        p.ver(s.terminarConsultaMedicoPaciente(12345678, 3, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");
        p.ver(s.terminarConsultaMedicoPaciente(14523689, 3, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");
        p.ver(s.terminarConsultaMedicoPaciente(32165478, 3, "Dolor de panza").resultado, Retorno.Resultado.OK, "Se cierra la consulta y se agrega al historial medico");

        p.ver(s.terminarConsultaMedicoPaciente(99999999, 3, "Dolor de panza").resultado, Retorno.Resultado.ERROR_1, "Esa cedula no existe");
        
        

    }

    public static void p2_10_cerrarConsulta(Prueba p, Sistema s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse("15/10/2023", formatter);

        LocalDate fecha2 = LocalDate.parse("16/10/2023", formatter);

        p.ver(s.cerrarConsulta(1, fecha).resultado, Retorno.Resultado.OK, "Paciente no asiste a la consulta, se deja registro en el historial medico");

    }

    public static void p3_1ListaMedicos(Prueba p, Sistema s) {
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se lista los medicos");
    }

    public static void p3_2ListarPacientes(Prueba p, Sistema s) {
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se lista los pacientes");

    }

    public static void p3_3ListarConsultas(Prueba p, Sistema s) {
        p.ver(s.listarConsultas(1).resultado, Retorno.Resultado.OK, "Se lista las consultas");

    }

    public static void p3_4ListarPacientesEnEspera(Prueba p, Sistema s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse("15/10/2023", formatter);
        p.ver(s.listarPacientesEnEspera(1, fecha).resultado, Retorno.Resultado.OK, "Se lista los pacientes en espera");

    }

    public static void p3_5consultasPendientesPaciente(Prueba p, Sistema s) {
        p.ver(s.consultasPendientesPaciente(12345678).resultado, Retorno.Resultado.OK, "Lista de reservas por paciente");

    }

    public static void p3_6historiaClínicaPaciente(Prueba p, Sistema s) {
        p.ver(s.historiaClínicaPaciente(12345678).resultado, Retorno.Resultado.OK, "Se lista historial del paciente para consultas temrinadas o no asistio");

    }

    public static void p3_7reporteDePacientesXFechaYEspecialidad(Prueba p, Sistema s) {
        p.ver(s.reporteDePacientesXFechaYEspecialidad(10, 2023).resultado, Retorno.Resultado.OK, "Se lista historial del paciente para consultas temrinadas o no asistio");

    }

}