package examen.actividades.model;


public class ActividadSimple extends Actividad {

    private final int prioridad;

    public ActividadSimple(String codigo, String titulo, EstadoActividad estado,
                           int contador, int prioridad) {
        super(codigo, titulo, estado, contador);
        this.prioridad = prioridad;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public TipoActividad getTipo() {
        return TipoActividad.SIMPLE;
    }
}