package examen.actividades.model;

public abstract class Actividad {

    private final String codigo;
    private String titulo;
    private EstadoActividad estado;
    private int contador;

    protected Actividad(String codigo, String titulo, EstadoActividad estado, int contador) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.estado = estado;
        this.contador = contador;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public EstadoActividad getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public abstract TipoActividad getTipo();
}