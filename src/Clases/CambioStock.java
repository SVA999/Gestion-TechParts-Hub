package Clases;

public class CambioStock {
    String codigoProducto;
    String tipoCambio; // Ej: "Entrada", "Salida", "Actualización"
    int cantidad;
    String fecha;

    public CambioStock(String codigoProducto, String tipoCambio, int cantidad, String fecha) {
        this.codigoProducto = codigoProducto;
        this.tipoCambio = tipoCambio;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String mostrar() {
        return "[" + fecha + "] " + tipoCambio + " en producto " + codigoProducto + ", Nueva cantidad: " + cantidad;
    }
}
