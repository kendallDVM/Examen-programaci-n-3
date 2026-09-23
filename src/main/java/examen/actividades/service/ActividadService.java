package examen.actividades.service;

import examen.actividades.model.Actividad;
import examen.actividades.repository.Repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ActividadService {

    private final Repositorio<Actividad> repositorio;
    private final List<Actividad> actividades;

    public ActividadService(Repositorio<Actividad> repositorio) {
        this.repositorio = repositorio;
        this.actividades = new ArrayList<>();
    }


    public void cargarActividades() throws IOException {
        List<Actividad> leidas = repositorio.cargarTodos();
        actividades.clear();
        actividades.addAll(leidas);
    }


    public void registrar(Actividad actividad) {
        actividades.add(actividad);
    }


    public List<Actividad> obtenerTodas() {
        return new ArrayList<>(actividades);
    }


    public void guardarDatos() throws IOException {
        repositorio.guardarTodos(actividades);
    }
}