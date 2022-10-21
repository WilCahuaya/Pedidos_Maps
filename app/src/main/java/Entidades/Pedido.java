package Entidades;

public class Pedido {
    private int id;
    private String cliente;
    private String direccion;
    private String descripcion;
    private String tipo;
    private Float latitud;
    private Float longitud;
    private String estado;
    private String fechaRegistro;

    public Pedido() {
    }

    public Pedido(int id, String cliente, String direccion, String descripcion, String tipo, Float latitud, Float longitud, String estado, String fechaRegistro) {
        this.id = id;
        this.cliente = cliente;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", direccion='" + direccion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", estado='" + estado + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                '}';
    }
}
