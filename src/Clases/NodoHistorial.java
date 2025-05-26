package Clases;

public class NodoHistorial {
    public CambioStock cambio;
    public NodoHistorial siguiente;

    public NodoHistorial(CambioStock cambio) {
        this.cambio = cambio;
        this.siguiente = null;
    }
}
