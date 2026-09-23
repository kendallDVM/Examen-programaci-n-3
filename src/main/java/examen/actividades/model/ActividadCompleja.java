package examen.actividades.model;


public class ActividadCompleja extends Actividad {

    private final int cantidadSubactividades;

    public ActividadCompleja(String codigo, String titulo, EstadoActividad estado,
                             int contador, int cantidadSubactividades) {
        super(codigo, titulo, estado, contador);
        this.cantidadSubactividades = cantidadSubactividades;
    }

    public int getCantidadSubactividades() {
        return cantidadSubactividades;
    }

    @Override
    public TipoActividad getTipo() {
        return TipoActividad.COMPLEJA;
    }
}