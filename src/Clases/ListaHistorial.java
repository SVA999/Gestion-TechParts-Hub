public class ListaHistorial {
    private NodoHistorial cabeza; // Inicio de la lista

    public ListaHistorial() {
        cabeza = null;
    }

    // Inserta al inicio de la lista (más eficiente)
    public void insertarCambio(CambioStock cambio) {
        NodoHistorial nuevo = new NodoHistorial(cambio);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    // Muestra todos los cambios
    public void mostrarHistorial() {
        if (cabeza == null) {
            System.out.println("No hay historial de cambios.");
            return;
        }

        NodoHistorial actual = cabeza;
        while (actual != null) {
            actual.cambio.mostrar();
            actual = actual.siguiente;
        }
    }
}
