package Gestor_de_tareas;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> añadirTarea(scanner, gestor);
                case "2" -> listarPendientes(gestor);
                case "3" -> marcarCompletada(scanner, gestor);
                case "4" -> eliminarTarea(scanner, gestor);
                case "5" -> salir = true;
                default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }

        System.out.println("¡Hasta luego!");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== GESTOR DE TAREAS =====");
        System.out.println("1. Añadir tarea");
        System.out.println("2. Ver tareas pendientes");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void añadirTarea(Scanner scanner, GestorTareas gestor) {
        System.out.print("Descripción de la tarea: ");
        String descripcion = scanner.nextLine().trim();
        if (descripcion.isEmpty()) {
            System.out.println("La descripción no puede estar vacía.");
            return;
        }
        gestor.añadirTarea(descripcion);
        System.out.println("Tarea añadida.");
    }

    private static void listarPendientes(GestorTareas gestor) {
        List<Tarea> pendientes = gestor.getTareasPendientes();
        if (pendientes.isEmpty()) {
            System.out.println("No hay tareas pendientes.");
            return;
        }
        List<Tarea> todas = gestor.getTodasLasTareas();
        System.out.println("Tareas pendientes:");
        for (Tarea tarea : pendientes) {
            System.out.println(todas.indexOf(tarea) + ". " + tarea);
        }
    }

    private static void marcarCompletada(Scanner scanner, GestorTareas gestor) {
        mostrarTodasConIndice(gestor);
        int indice = pedirIndice(scanner);
        if (gestor.marcarCompletada(indice)) {
            System.out.println("Tarea marcada como completada.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private static void eliminarTarea(Scanner scanner, GestorTareas gestor) {
        mostrarTodasConIndice(gestor);
        int indice = pedirIndice(scanner);
        if (gestor.eliminarTarea(indice)) {
            System.out.println("Tarea eliminada.");
        } else {
            System.out.println("Índice no válido.");
        }
    }

    private static void mostrarTodasConIndice(GestorTareas gestor) {
        List<Tarea> todas = gestor.getTodasLasTareas();
        if (todas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        for (int i = 0; i < todas.size(); i++) {
            System.out.println(i + ". " + todas.get(i));
        }
    }

    private static int pedirIndice(Scanner scanner) {
        System.out.print("Número de tarea: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
