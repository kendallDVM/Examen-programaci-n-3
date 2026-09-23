package examen.actividades;

import examen.actividades.controller.ActividadController;

import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActividadController controlador = new ActividadController();
            controlador.iniciar();
        });
    }
}



