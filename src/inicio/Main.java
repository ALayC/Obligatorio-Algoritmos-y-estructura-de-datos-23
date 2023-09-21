package inicio;

import sistemaAutogestion.*;
import clases.*;

public class Main {

    public static void main(String[] args) {

        Prueba p = new Prueba();
        Sistema s = new Sistema();
    }

    public static void p1_crearSistema(Prueba p, Sistema s) {

        p.ver(s.crearSistemaDeAutogestion(10).resultado, Retorno.Resultado.OK, "Creacion correcta del sistema");
        p.ver(s.crearSistemaDeAutogestion(-1).resultado, Retorno.Resultado.ERROR_1, "No se crea el sistema,cantidad no valida");

    }

    public static void p2_registrarMedico(Prueba p, Sistema s) {
        p.ver(s.registrarMedico("Daniel", 1, 12345678, 3).resultado, Retorno.Resultado.OK, "Se registra el medico");
        p.ver(s.registrarMedico("Andres", 1, 98765432, 4).resultado, Retorno.Resultado.ERROR_1, "No se registra, medico con igual codigo");
        p.ver(s.registrarMedico("Lucas", 2, 65498732, 22).resultado, Retorno.Resultado.ERROR_2, "No se registra, medico con especialidad no valida");
        
    }
}
