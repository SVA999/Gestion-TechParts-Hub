package Clases;

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

    // Retorna todos los cambios en formato String
    public String mostrarHistorial() {
        if (cabeza == null) {
            return "No hay historial de cambios.";
        }

        StringBuilder historialStr = new StringBuilder();
        NodoHistorial actual = cabeza;

        while (actual != null) {
            historialStr.append(actual.cambio.mostrar()).append("\n");
            actual = actual.siguiente;
        }

        return historialStr.toString();
    }
}
