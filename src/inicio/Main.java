package inicio;

import sistemaAutogestion.*;
import clases.*;

public class Main {

    public static void main(String[] args) {

        Prueba p = new Prueba();
        Sistema s = new Sistema();

        p2_registrarMedico(p, s);
        p4_registrarPaciente(p, s);
        ListaMedicos(p, s);
        ListarPacientes(p, s);
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

        p.ver(s.registrarMedico("Andres", 1, 98765432, 4).resultado, Retorno.Resultado.ERROR_1, "No se registra, medico con igual codigo");
        p.ver(s.registrarMedico("Lucas", 4, 65498732, 22).resultado, Retorno.Resultado.ERROR_2, "No se registra, medico con especialidad no valida");

    }

    public static void p4_registrarPaciente(Prueba p, Sistema s) {
        p.ver(s.agregarPaciente("German", 12345678, "calle 1").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("romina", 14523689, "calle 2").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("jose", 32165478, "calle 12").resultado, Retorno.Resultado.OK, "Se registra el paciente");
        p.ver(s.agregarPaciente("Maria", 12345678, "calle 32").resultado, Retorno.Resultado.ERROR_1, "No se registra paciente, misma cedula");

    }

    public static void ListaMedicos(Prueba p, Sistema s) {
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se lista los medicos");
    }

    public static void ListarPacientes(Prueba p, Sistema s) {
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se lista los pacientes");
    }
}
