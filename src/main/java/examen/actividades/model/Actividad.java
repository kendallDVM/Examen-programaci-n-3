package examen.actividades.model;

public abstract class Actividad {

    private String codigo;
    private String nombre;
    private double tarifaBase;
    private int cupoTotal;
    private int inscritos;

    protected Actividad(String codigo, String nombre, double tarifaBase, int cupoTotal, int inscritos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tarifaBase = tarifaBase;
        this.cupoTotal = cupoTotal;
        this.inscritos = inscritos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public int getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(int cupoTotal) {
        this.cupoTotal = cupoTotal;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }


    public int getCuposDisponibles() {
        return cupoTotal - inscritos;
    }


    public abstract TipoActividad getTipo();


    public abstract double calcularTarifaFinal();
}
