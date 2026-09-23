package examen.actividades.repository;

import java.io.IOException;
import java.util.List;


public interface Repositorio<Actividad> {


    List<Actividad> cargarTodos() throws IOException;


    void guardarTodos(List<Actividad> elementos) throws IOException;
}