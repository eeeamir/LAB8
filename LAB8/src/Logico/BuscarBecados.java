package Logico;

import java.util.List;
import java.util.ArrayList;

public class BuscarBecados {
    private List<Estudiantes> estudiantes;

    public BuscarBecados(List<Estudiantes> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Estudiantes> buscarBecadosPorCarreraYSexo(String carrera, String sexo) {
        List<Estudiantes> becados = new ArrayList<>();
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getCarrera().equalsIgnoreCase(carrera) && estudiante.getSexo().equalsIgnoreCase(sexo)) {
                becados.add(estudiante);
            }
        }
        return becados;
    }
}
