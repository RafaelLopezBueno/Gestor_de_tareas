package Gestor_de_tareas;

import java.util.ArrayList;
import java.util.List;

public class GestorTareas {

    private final List<Tarea> tareas = new ArrayList<>();

    public void añadirTarea(String descripcion) {
        tareas.add(new Tarea(descripcion));
    }

    public List<Tarea> getTareasPendientes() {
        List<Tarea> pendientes = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (!tarea.isCompletada()) {
                pendientes.add(tarea);
            }
        }
        return pendientes;
    }

    public List<Tarea> getTodasLasTareas() {
        return tareas;
    }

    public boolean marcarCompletada(int indice) {
        if (indiceValido(indice)) {
            tareas.get(indice).marcarCompletada();
            return true;
        }
        return false;
    }

    public boolean eliminarTarea(int indice) {
        if (indiceValido(indice)) {
            tareas.remove(indice);
            return true;
        }
        return false;
    }

    private boolean indiceValido(int indice) {
        return indice >= 0 && indice < tareas.size();
    }
}
