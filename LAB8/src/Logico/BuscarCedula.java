package Logico;

import java.util.List;

public class BuscarCedula {
    private List<Estudiantes> estudiantes;

    public BuscarCedula(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiantes buscarPorCedula(String cedula) {
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }
        return null;
    }
}
