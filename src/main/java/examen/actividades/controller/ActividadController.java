package examen.actividades.controller;

import examen.actividades.model.Actividad;
import examen.actividades.model.ActividadPresencial;
import examen.actividades.model.ActividadVirtual;
import examen.actividades.model.TipoActividad;
import examen.actividades.repository.Repositorio;
import examen.actividades.repository.RepositorioActividadTxt;
import examen.actividades.service.ActividadService;
import examen.actividades.view.VentanaActividades;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ActividadController {

    private final ActividadService servicio;
    private VentanaActividades vista;

    public ActividadController() {

        Repositorio<Actividad> repositorio = new RepositorioActividadTxt("actividades.txt");
        this.servicio = new ActividadService(repositorio);
    }

    public void iniciar() {
        this.vista = new VentanaActividades(this);
        vista.mostrar();
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        try {
            servicio.cargarActividades();
            vista.mostrarResultados(servicio.obtenerTodas());
        } catch (IOException e) {
            vista.mostrarError("No se pudieron cargar los datos. " + e.getMessage());
        }
    }

    public void registrarActividad(String codigo, String nombre, double tarifaBase,
                                   int cupoTotal, TipoActividad tipo) {
        try {
            Actividad actividad;
            if (tipo == TipoActividad.PRESENCIAL) {
                actividad = new ActividadPresencial(codigo, nombre, tarifaBase, cupoTotal, 0);
            } else {
                actividad = new ActividadVirtual(codigo, nombre, tarifaBase, cupoTotal, 0);
            }
            servicio.registrar(actividad);
            vista.mostrarResultados(servicio.obtenerTodas());
            vista.mostrarInfo("Actividad registrada.");
        } catch (IllegalArgumentException e) {
            vista.mostrarError("Datos inválidos: " + e.getMessage());
        }
    }

    public void buscarActividad(String codigo) {
        Optional<Actividad> resultado = servicio.buscarPorCodigo(codigo);
        if (resultado.isPresent()) {
            vista.mostrarResultados(List.of(resultado.get()));
            vista.mostrarInfo("Actividad encontrada.");
        } else {
            vista.mostrarResultados(List.of());
            vista.mostrarError("No existe una actividad con código \"" + codigo + "\".");
        }
    }

    public void inscribir(String codigo) {
        Optional<Actividad> resultado = servicio.buscarPorCodigo(codigo);
        if (resultado.isEmpty()) {
            vista.mostrarError("No existe una actividad con código \"" + codigo + "\".");
            return;
        }

        Actividad actividad = resultado.get();
        if (actividad.getCuposDisponibles() <= 0) {
            vista.mostrarError("No quedan cupos disponibles para \"" + actividad.getNombre() + "\".");
            return;
        }

        actividad.setInscritos(actividad.getInscritos() + 1);
        vista.mostrarResultados(List.of(actividad));
        vista.mostrarInfo("Inscripción realizada. Cupos disponibles: "
                + actividad.getCuposDisponibles() + ".");
    }

    public void mostrarTodas() {
        vista.mostrarResultados(servicio.obtenerTodas());
        vista.mostrarInfo("Se muestran todas las actividades.");
    }

    public void guardarDatos() {
        try {
            servicio.guardarDatos();
            vista.mostrarInfo("Datos guardados correctamente.");
        } catch (IOException e) {
            vista.mostrarError("No se pudieron guardar los datos. Revise la ruta y los permisos.");
        }
    }
}
