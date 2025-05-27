
import Clases.*;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        try {

            Inventario inventario = new Inventario();

            byte opcion, opcionBuscar, opcionOrdenar;

            // Cargar datos
            JOptionPane.showMessageDialog(null, inventario.CargarDatos(), "CARGA DE DATOS", 1);

            do {
                opcion = AbrirMenu();

                switch (opcion) {
                    case 1:
                        // Insertar -Registrar Parte
                        inventario.registrarParte(new ParteHardware(
                                leerTexto("Ingresa el nombre del componente"),
                                leerTexto("Ingresa el Codigo de Producto"),
                                leerTexto("Ingresa una Descripcion"),
                                leerTexto("Ingresa el Fabricante"),
                                leerDouble("Ingresa el Precio"),
                                leerEntero("Ingresa la cantidad de Stock"),
                                leerTexto("Ingresa la Categoria")
                        ));

                        JOptionPane.showMessageDialog(null, "Parte registrada exitosamente.");

                        break;
                    case 2:
                        // Imprimir
                        JOptionPane.showMessageDialog(null, inventario.mostrarInventario());
                        break;
                    case 5:
                        // Buscar
                        do {
                            opcionBuscar = AbrirMenuBuscar();
                            switch (opcionBuscar) {
                                case 0 ->
                                    AbrirMenu();
                                case 1 -> // Buscar por Nombre
                                    JOptionPane.showMessageDialog(null,
                                            inventario.buscarPorNombre(
                                                    leerTexto("Nombre del producto")
                                            ));
                                case 2 -> // Buscar por Código
                                    JOptionPane.showMessageDialog(null,
                                            inventario.buscarPorCodigo(
                                                    leerTexto("Código del producto")
                                            ));
                                case 3 -> // Buscar por Categoría
                                    JOptionPane.showMessageDialog(null,
                                            inventario.buscarPorCategoria(
                                                    leerTexto("Categoría del producto")
                                            ));
                                case 4 -> // Buscar por Rango de Precio
                                    JOptionPane.showMessageDialog(null,
                                            inventario.buscarPorRangoPrecio(
                                                    leerDouble("Ingresa el Precio Mínimo"),
                                                    leerDouble("Ingresa el Precio Máximo")
                                            ));
                                default ->
                                    JOptionPane.showMessageDialog(null, "\tOpción inválida, vuelve a intentar");
                            }
                        } while (opcionBuscar != 0);
                        break;

                    case 3:
                        // Actualizar Stock
                        JOptionPane.showMessageDialog(null, inventario.actualizarStock(
                                leerTexto("Ingresa el Código de producto"),
                                leerEntero("Ingresa la nueva cantidad")
                        ));
                        break;
                    case 4:
                        // Ordenar
                        do {
                            opcionOrdenar = AbrirMenuOrdenar();
                            switch (opcionOrdenar) {
                                case 0 -> {
                                    AbrirMenu();
                                }
                                case 1 -> {
                                    // Nombre
                                    inventario.ordenarPorNombre();
                                    JOptionPane.showMessageDialog(null, inventario.mostrarInventario());
                                }
                                case 2 -> {
                                    // Precio
                                    inventario.ordenarPorPrecio();
                                    JOptionPane.showMessageDialog(null, inventario.mostrarInventario());
                                }
                                case 3 -> {
                                    // Cantidad Stock
                                    inventario.ordenarPorCantidad();
                                    JOptionPane.showMessageDialog(null, inventario.mostrarInventario());
                                }
                                default ->
                                    JOptionPane.showMessageDialog(null, "\tOpción inválida, vuelve a intentar");
                            }
                        } while (opcionOrdenar != 0);
                        break;
                    case 6:
                        // Selecciona los k productos con menor cantidad en stock
                        JOptionPane.showMessageDialog(null,
                                inventario.seleccionarKMenorStock(
                                        leerEntero("Ingresa la cantidad a consultar")
                                ));
                        break;
                    case 7:
                        //Selecciona mostrar Historial de Cambios
                        JOptionPane.showMessageDialog(null,
                                inventario.mostrarHistorialCambios());
                        break;
                    case 8:
                        // Registrar pedido
                        JOptionPane.showMessageDialog(null,
                                inventario.agregarPedido(
                                        leerTexto("Código del producto").toUpperCase(),
                                        leerEntero("Cantidad solicitada")
                                ));
                        break;
                    case 9:
                        // Procesar siguiente pedido
                        JOptionPane.showMessageDialog(null, inventario.procesarSiguientePedido());
                        break;

                    case 10:
                        // Mostrar pedidos pendientes
                        JOptionPane.showMessageDialog(null, inventario.mostrarPedidosPendientes());
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "\tSaliendo del programa...", "SALIDA", 0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "\tOpción inválida, vuelve a intentar");
                }
            } while (opcion != 0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR:\n" + e);
        }
    }

    // --------------------- METODOS ----------------------//
    public static byte AbrirMenu() throws Exception {
        try {
            String sOpcion = JOptionPane.showInputDialog(null, """
                                    <html>
                <h2 style="color:#678bce">Menú:</h2>

                <h3 style="color:#5a7ab4">--- Operaciones ---</h3>
                    <p style="color:#344667">1. Registrar Parte</p>
                    <p style="color:#344667">2. Mostrar Inventario</p>
                    <p style="color:#344667">3. Actualizar Stock</p>
                    <p style="color:#344667">4. Ordenar</p>

                <h3 style="color:#5a7ab4">--- Consultas ---</h3>
                    <p style="color:#344667">5. Buscar</p>
                    <p style="color:#344667">6. Selecciona los productos con menor cantidad en stock</p>
                    <p style="color:#344667">7. Mostrar Historial de Cambios</p>

                <h3 style="color:#5a7ab4">--- Pedidos ---</h3>
                    <p style="color:#344667">8. Registrar Pedido</p>
                    <p style="color:#344667">9. Procesar Siguiente Pedido</p>
                    <p style="color:#344667">10. Mostrar Pedidos Pendientes</p>

                <h3 style="color:#5a7ab4">------------------</h3>
                    <p style="color:#b45274">0. Salir</p>
            </html>
                                    """, "MENU", 1);

            if (sOpcion == null) {
                return 0;
            } else if (sOpcion.isBlank()) {
                JOptionPane.showMessageDialog(null, "Error: No se ingresó ninguna opción.");
                return -1;
            }

            int opcion = Integer.parseInt(sOpcion);

            return (byte) opcion;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debes ingresar un número entero.");
            return -1;
        } catch (Exception e) {
            throw new Exception("Error al AbrirMenu:\n" + e);
        }
    }

    public static byte AbrirMenuBuscar() throws Exception {
        try {

            String sOpcion = JOptionPane.showInputDialog(null, """
                    <html>
                        <h2 style="color:#678bce">Menú:</h2>

                        <h3 style="color:#5a7ab4">--- Operaciones ---</h3>
                            <p style="color:#344667">1. Buscar por nombre</p>
                            <p style="color:#344667">2. Buscar por código</p>
                            <p style="color:#344667">3. Buscar por categoría</p>
                            <p style="color:#344667">4. Buscar por rango de precio</p>

                        <h3 style="color:#5a7ab4">------------------</h3>
                            <p style="color:#b45274">0. Atras</p>
                    </html>
                                        """, "MENU", 1);

            if (sOpcion == null) {
                return 0;
            } else if (sOpcion.isBlank() || sOpcion.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: No se ingresó ninguna opción.");
                return -1;
            } else {
                return Byte.parseByte(sOpcion);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debes ingresar un número entero.");
            return -1;
        } catch (Exception e) {
            throw new Exception("Error al AbrirMenuBuscar:\n" + e);
        }
    }

    public static byte AbrirMenuOrdenar() throws Exception {
        try {

            String sOpcion = JOptionPane.showInputDialog(null, """
                    <html>
                        <h2 style="color:#678bce">Menú:</h2>

                        <h3 style="color:#5a7ab4">--- Operaciones ---</h3>
                            <p style="color:#344667">1. Ordenar por nombre</p>
                            <p style="color:#344667">2. Ordenar por precio</p>
                            <p style="color:#344667">3. Ordenar por cantidad en stock</p>

                        <h3 style="color:#5a7ab4">------------------</h3>
                            <p style="color:#b45274">0. Atras</p>
                    </html>
                                        """, "MENU", 1);

            if (sOpcion == null) {
                return 0;
            } else if (sOpcion.isBlank() || sOpcion.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: No se ingresó ninguna opción.");
                return -1;
            } else {
                return Byte.parseByte(sOpcion);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debes ingresar un número entero.");
            return -1;
        } catch (Exception e) {
            throw new Exception("Error al AbrirMenuOrdenar:\n" + e);
        }
    }

    //-------------Comprobaciones
    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, mensaje);
                if (input == null) {
                    throw new RuntimeException("Operación cancelada.");
                }
                input = input.trim();
                if (input.isEmpty()) {
                    throw new NumberFormatException();
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingresa un número entero válido.");
            }
        }
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, mensaje);
                if (input == null) {
                    throw new RuntimeException("Operación cancelada.");
                }
                input = input.trim();
                if (input.isEmpty()) {
                    throw new NumberFormatException();
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingresa un número decimal válido.");
            }
        }
    }

    public static String leerTexto(String mensaje) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, mensaje);
            if (input == null) {
                throw new RuntimeException("Operación cancelada.");
            }
            input = input.trim();
            if (!input.isEmpty()) {
                return input.toUpperCase();
            }
            JOptionPane.showMessageDialog(null, "Error: Ingresa un texto válido.");
        }
    }

}
