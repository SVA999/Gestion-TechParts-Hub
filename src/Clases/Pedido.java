package Clases;

public class Pedido {
    String codigoProducto;
    int cantidadSolicitada;
    String fecha;

    public Pedido(String codigoProducto, int cantidadSolicitada, String fecha) {
        this.codigoProducto = codigoProducto;
        this.cantidadSolicitada = cantidadSolicitada;
        this.fecha = fecha;
    }

    public String mostrar() {
        return "[" + fecha + "] Pedido de " + cantidadSolicitada + " unidades del producto " + codigoProducto;
    }
}
