package Clases;

public class ParteHardware {

    String nombre;
    String codigo_producto;
    String descripcion;
    String fabricante;
    double precio;
    int cantidad;
    String categoria;

    public ParteHardware(String nombre, String codigo_producto, String descripcion, String fabricante,
            double precio, int cantidad, String categoria) {
        this.nombre = nombre;
        this.codigo_producto = codigo_producto;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public String mostrarInfo() {
        return "\nNombre: " + nombre + ", Código: " + codigo_producto + ", Precio: $" + precio + ", Cantidad: " + cantidad + ", Categoría: " + categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo_producto = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
