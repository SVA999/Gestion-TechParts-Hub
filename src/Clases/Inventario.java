package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Inventario {

    ArrayList<ParteHardware> listaPartes;
    ListaHistorial historial;  // Lista enlazada personalizada para cambios
    ListaPedidos colaPedidos;

    public Inventario() {
        listaPartes = new ArrayList<>();
        historial = new ListaHistorial();
        colaPedidos = new ListaPedidos();
    }

    public String CargarDatos() throws Exception {
        try {
            // Ruta del archivo
            String archivoRuta = "src\\partes_hardware.txt";

            // Leer el archivo y cargar los Componentes
            try (BufferedReader br = new BufferedReader(new FileReader(archivoRuta))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Dividir la línea por el punto y coma
                    String[] partes = linea.split(",");
                    String nombreParte = partes[0].toUpperCase();
                    String codProducto = partes[1].toUpperCase();
                    String descripcion = partes[2].toUpperCase();
                    String fabricante = partes[3].toUpperCase();
                    Double precio = Double.parseDouble(partes[4]);
                    Integer cantStock = Integer.parseInt(partes[5]);
                    String categoria = partes[6].toUpperCase();

                    // Crear una Empleado y agregarla a la lista
                    this.registrarParte(new ParteHardware(nombreParte, codProducto, descripcion, fabricante, precio, cantStock, categoria));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Carga exitosa";

        } catch (Exception e) {
            throw new Exception("Ha ocurrido un error al calcular el costo: " + e);
        }

    }

    // Agrega una parte al inventario
    public void registrarParte(ParteHardware parte) {
        listaPartes.add(parte);
    }

    // Muestra todo el inventario
    public String mostrarInventario() {
        String sInventario = "";
        for (ParteHardware parte : listaPartes) {
            sInventario += parte.mostrarInfo();
        }
        return sInventario;
    }

    // Búsqueda por nombre
    public String buscarPorNombre(String nombre) {
        for (ParteHardware parte : listaPartes) {
            if (parte.nombre.equalsIgnoreCase(nombre)) {
                return parte.mostrarInfo();
            }
        }
        return "No encontrado";
    }

    // Búsqueda por código
    public String buscarPorCodigo(String codigo) {
        for (ParteHardware parte : listaPartes) {
            if (parte.codigo_producto.equalsIgnoreCase(codigo)) {
                return parte.mostrarInfo();
            }
        }
        return "No encontrado";
    }

    public ParteHardware buscarPorCodigo(String codigo, boolean code) {
        for (ParteHardware parte : listaPartes) {
            if (parte.codigo_producto.equalsIgnoreCase(codigo)) {
                return parte;
            }
        }
        return null;
    }

    // Búsqueda por categoría
    public String buscarPorCategoria(String categoria) {
        StringBuilder sb = new StringBuilder();
        sb.append("Partes encontradas en la categoría '").append(categoria).append("':\n\n");

        boolean encontrado = false;

        for (ParteHardware parte : listaPartes) {
            if (parte.categoria.equalsIgnoreCase(categoria)) {
                encontrado = true;
                sb.append("Nombre: ").append(parte.nombre).append(" - ")
                        .append("Código: ").append(parte.codigo_producto).append(" - ")
                        .append("Descripción: ").append(parte.descripcion).append(" - ")
                        .append("Fabricante: ").append(parte.fabricante).append(" - ")
                        .append("Precio: $").append(parte.precio).append(" - ")
                        .append("Cantidad en stock: ").append(parte.cantidad).append(" - ")
                        .append("Categoría: ").append(parte.categoria).append("\n")
                        .append("--------------------------------------------------\n");
            }
        }

        if (!encontrado) {
            return "No se encontraron partes en la categoría: " + categoria;
        }

        return sb.toString();
    }

    // Búsqueda por rango de precio
    public String buscarPorRangoPrecio(double min, double max) {
        if (min > max) {
            return "Error: el precio mínimo no puede ser mayor que el máximo.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Partes encontradas en el rango de precio $").append(min).append(" - $").append(max).append(":\n\n");

        for (ParteHardware parte : listaPartes) {
            if (parte.precio >= min && parte.precio <= max) {
                sb.append("Nombre: ").append(parte.nombre).append(" - ")
                        .append("Código: ").append(parte.codigo_producto).append(" - ")
                        .append("Descripción: ").append(parte.descripcion).append(" - ")
                        .append("Fabricante: ").append(parte.fabricante).append(" - ")
                        .append("Precio: $").append(parte.precio).append(" - ")
                        .append("Cantidad en stock: ").append(parte.cantidad).append(" - ")
                        .append("Categoría: ").append(parte.categoria).append("\n")
                        .append("--------------------------------------------------\n");
            }
        }

        if (sb.toString().endsWith(":\n\n")) {
            return "No se encontraron partes en el rango de precio especificado.";
        }

        return sb.toString();
    }

    // Actualiza la cantidad en stock de una parte por código
    public String actualizarStock(String codigo, int nuevaCantidad) {
        ParteHardware parte = buscarPorCodigo(codigo, true);
        if (parte != null) {
            parte.cantidad = nuevaCantidad;

            // Crear un registro de cambio e insertarlo en la lista enlazada
            String fecha = java.time.LocalDate.now().toString();
            CambioStock cambio = new CambioStock(codigo, "Actualización de stock", nuevaCantidad, fecha);
            historial.insertarCambio(cambio);

            return "Stock actualizado correctamente para el producto con código: " + codigo
                    + ". Nueva cantidad: " + nuevaCantidad;
        }
        return "Error: No se encontró ninguna parte con el código: " + codigo;
    }

    // Ordena por nombre usando QuickSort
    public void ordenarPorNombre() {
        quickSort(0, listaPartes.size() - 1);
    }

    private void quickSort(int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particion(inicio, fin);
            quickSort(inicio, indicePivote - 1);
            quickSort(indicePivote + 1, fin);
        }
    }

    private int particion(int inicio, int fin) {
        ParteHardware pivote = listaPartes.get(fin);
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (listaPartes.get(j).nombre.compareToIgnoreCase(pivote.nombre) < 0) {
                i++;
                ParteHardware temp = listaPartes.get(i);
                listaPartes.set(i, listaPartes.get(j));
                listaPartes.set(j, temp);
            }
        }
        ParteHardware temp = listaPartes.get(i + 1);
        listaPartes.set(i + 1, listaPartes.get(fin));
        listaPartes.set(fin, temp);
        return i + 1;
    }

    // Ordena por precio usando QuickSort
    public void ordenarPorPrecio() {
        quickSortPrecio(0, listaPartes.size() - 1);
    }

    private void quickSortPrecio(int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particionPrecio(inicio, fin);
            quickSortPrecio(inicio, indicePivote - 1);
            quickSortPrecio(indicePivote + 1, fin);
        }
    }

    private int particionPrecio(int inicio, int fin) {
        ParteHardware pivote = listaPartes.get(fin);
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (listaPartes.get(j).precio < pivote.precio) {
                i++;
                ParteHardware temp = listaPartes.get(i);
                listaPartes.set(i, listaPartes.get(j));
                listaPartes.set(j, temp);
            }
        }
        ParteHardware temp = listaPartes.get(i + 1);
        listaPartes.set(i + 1, listaPartes.get(fin));
        listaPartes.set(fin, temp);
        return i + 1;
    }

    // Ordena por cantidad en stock usando QuickSort
    public void ordenarPorCantidad() {
        quickSortCantidad(0, listaPartes.size() - 1);
    }

    private void quickSortCantidad(int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particionCantidad(inicio, fin);
            quickSortCantidad(inicio, indicePivote - 1);
            quickSortCantidad(indicePivote + 1, fin);
        }
    }

    private int particionCantidad(int inicio, int fin) {
        ParteHardware pivote = listaPartes.get(fin);
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (listaPartes.get(j).cantidad < pivote.cantidad) {
                i++;
                ParteHardware temp = listaPartes.get(i);
                listaPartes.set(i, listaPartes.get(j));
                listaPartes.set(j, temp);
            }
        }
        ParteHardware temp = listaPartes.get(i + 1);
        listaPartes.set(i + 1, listaPartes.get(fin));
        listaPartes.set(fin, temp);
        return i + 1;
    }

    // Selecciona los k productos con menor cantidad en stock y retorna un String
    public String seleccionarKMenorStock(int k) {
        ordenarPorCantidad(); // Ordena por cantidad ascendente
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < k && i < listaPartes.size(); i++) {
            ParteHardware parte = listaPartes.get(i);
            resultado.append(parte.mostrarInfo()).append("\n");
        }

        return resultado.toString().isEmpty() ? "No hay productos disponibles." : resultado.toString();
    }

    // Muestra el historial de cambios en el stock
    public String mostrarHistorialCambios() {

        return historial.mostrarHistorial();
    }

    public String agregarPedido(String codigoProducto, int cantidad) {
        ParteHardware parte = buscarPorCodigo(codigoProducto, true);
        if (parte == null) {
            return "No existe producto con ese código.";
        }
        String fecha = java.time.LocalDate.now().toString();
        Pedido nuevo = new Pedido(codigoProducto, cantidad, fecha);
        colaPedidos.agregarPedido(nuevo);
        return "Pedido agregado correctamente.";
    }

    public String procesarSiguientePedido() {
        if (colaPedidos.estaVacia()) {
            return "No hay pedidos pendientes.";
        }

        Pedido pedido = colaPedidos.verSiguientePedido(); // Solo miramos, no quitamos aún
        ParteHardware parte = buscarPorCodigo(pedido.codigoProducto, true);

        if (parte == null) {
            return "Producto no encontrado: " + pedido.codigoProducto;
        }

        if (parte.cantidad >= pedido.cantidadSolicitada) {
            parte.cantidad -= pedido.cantidadSolicitada;
            colaPedidos.eliminarSiguientePedido(); // Ahora sí lo quitamos
            String fecha = java.time.LocalDate.now().toString();
            historial.insertarCambio(new CambioStock(pedido.codigoProducto, "Despacho por pedido", parte.cantidad, fecha));
            return "Pedido procesado exitosamente:\n" + pedido.mostrar();
        } else {
            return "Stock insuficiente para procesar el pedido:\n" + pedido.mostrar(); // NO lo eliminamos
        }
    }

    public String mostrarPedidosPendientes() {
        return colaPedidos.mostrarPedidosPendientes();
    }

}
