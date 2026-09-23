package examen.actividades.repository;

import examen.actividades.model.Actividad;
import examen.actividades.model.ActividadPresencial;
import examen.actividades.model.ActividadVirtual;
import examen.actividades.model.TipoActividad;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RepositorioActividadTxt implements Repositorio<Actividad> {

    private final Path archivo;

    public RepositorioActividadTxt(String rutaArchivo) {
        this.archivo = Path.of(rutaArchivo);
    }

    @Override
    public List<Actividad> cargarTodos() throws IOException {

        if (Files.notExists(archivo)) {
            return new ArrayList<>();
        }

        List<Actividad> actividades = new ArrayList<>();
        for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {

            if (linea.isBlank()) {
                continue;
            }
            actividades.add(convertirDesdeLinea(linea));
        }
        return actividades;
    }

    @Override
    public void guardarTodos(List<Actividad> actividades) throws IOException {
        Path carpeta = archivo.getParent();
        if (carpeta != null) {
            Files.createDirectories(carpeta);
        }

        List<String> lineas = new ArrayList<>();
        for (Actividad actividad : actividades) {
            lineas.add(convertirALinea(actividad));
        }


        Files.write(
                archivo,
                lineas,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }


    private String convertirALinea(Actividad a) {
        return String.join(";",
                a.getTipo().name(),
                a.getCodigo(),
                a.getNombre(),
                String.valueOf(a.getTarifaBase()),
                String.valueOf(a.getCupoTotal()),
                String.valueOf(a.getInscritos()));
    }


    private Actividad convertirDesdeLinea(String linea) {

        String[] p = linea.split(";", -1);
        if (p.length < 6) {
            throw new IllegalArgumentException("Línea con formato inválido: " + linea);
        }

        String tipo = p[0];
        String codigo = p[1];
        String nombre = p[2];
        double tarifaBase = Double.parseDouble(p[3]);
        int cupoTotal = Integer.parseInt(p[4]);
        int inscritos = Integer.parseInt(p[5]);

        return switch (TipoActividad.valueOf(tipo)) {
            case PRESENCIAL -> new ActividadPresencial(codigo, nombre, tarifaBase, cupoTotal, inscritos);
            case VIRTUAL -> new ActividadVirtual(codigo, nombre, tarifaBase, cupoTotal, inscritos);
        };
    }
}
