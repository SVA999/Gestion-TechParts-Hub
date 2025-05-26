package Clases;

public class ListaPedidos {

    private NodoPedido frente;
    private NodoPedido fin;

    public ListaPedidos() {
        frente = null;
        fin = null;
    }

    // Encolar pedido
    public void agregarPedido(Pedido pedido) {
        NodoPedido nuevo = new NodoPedido(pedido);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public Pedido verSiguientePedido() {
        if (frente == null) {
            return null;
        }
        return frente.pedido;
    }

    public void eliminarSiguientePedido() {
        if (frente != null) {
            frente = frente.siguiente;
            if (frente == null) {
                fin = null;
            }
        }
    }

    // Procesar el primer pedido
    public Pedido procesarPedido() {
        if (frente == null) {
            return null;
        }
        Pedido pedidoProcesado = frente.pedido;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return pedidoProcesado;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public String mostrarPedidosPendientes() {
        if (frente == null) {
            return "No hay pedidos pendientes.";
        }

        StringBuilder sb = new StringBuilder();
        NodoPedido actual = frente;
        while (actual != null) {
            sb.append(actual.pedido.mostrar()).append("\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }
    
}
