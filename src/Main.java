
import Clases.*;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        try {

            Inventario inventario = new Inventario();

            /*
        System.out.println("Inventario original:");
        inventario.mostrarInventario();

        System.out.println("\nOrdenado por nombre:");
        inventario.ordenarPorNombre();
        inventario.mostrarInventario();

        System.out.println("\nBuscar por nombre:");
        ParteHardware resultado = inventario.buscarPorNombre("CPU Ryzen 5");
        if (resultado != null) {
            resultado.mostrarInfo();
        } else {
            System.out.println("Producto no encontrado.");
        }

        // ✅ Actualización de stock con registro en la lista enlazada
        System.out.println("\nActualizando stock de 'CPU Ryzen 5'...");
        boolean actualizado = inventario.actualizarStock("C456", 12);
        if (actualizado) {
            System.out.println("Stock actualizado correctamente.");

            // 🔽 Mostrar la parte con stock actualizado
            ParteHardware actualizadoParte = inventario.buscarPorCodigo("C456");
            if (actualizadoParte != null) {
                actualizadoParte.mostrarInfo();
            }
            
        } else {
            System.out.println("No se pudo actualizar el stock.");
        }

        // ✅ Mostrar historial de cambios registrados en lista enlazada
        System.out.println("\nHistorial de cambios en el inventario:");
        inventario.mostrarHistorialCambios();
        
             */
            byte opcion, opcionBuscar, opcionOrdenar;

            // Cargar datos
            JOptionPane.showMessageDialog(null, inventario.CargarDatos(), "CARGA DE DATOS", 1);

            do {
                opcion = AbrirMenu();

                switch (opcion) {
                    case 1:
                        // Insertar -Registrar Parte
                        inventario.registrarParte(new ParteHardware(
                                JOptionPane.showInputDialog(null, "Ingresa el nombre del componente").toUpperCase(),
                                JOptionPane.showInputDialog(null, "Ingresa el Codigo de Producto").toUpperCase(),
                                JOptionPane.showInputDialog(null, "Ingresa una Descripcion").toUpperCase(),
                                JOptionPane.showInputDialog(null, "Ingresa el Fabricante").toUpperCase(),
                                Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el Precio")),
                                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la cantidad de Stock")),
                                JOptionPane.showInputDialog(null, "Ingresa la Categoria").toUpperCase()
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
                                case 1 -> // Nombre
                                    JOptionPane.showMessageDialog(null,
                                            inventario.buscarPorNombre(
                                                    JOptionPane.showInputDialog(null, "Nombre del producto").toUpperCase()
                                            ));
                                case 2 -> // Codigo
                                    JOptionPane.showMessageDialog(null,
                                            inventario.buscarPorCodigo(
                                                    JOptionPane.showInputDialog(null, "Codigo del producto").toUpperCase()
                                            ));
                                case 3 -> // Categoria
                                    JOptionPane.showMessageDialog(null,
                                            inventario.buscarPorCategoria(
                                                    JOptionPane.showInputDialog(null, "Categoria del producto").toUpperCase()
                                            ));
                                case 4 ->
                                    JOptionPane.showMessageDialog(null,
                                            inventario.buscarPorRangoPrecio(
                                                    Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el Precio Minimo")),
                                                    Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el Precio Maximo"))
                                            ));
                                default ->
                                    JOptionPane.showMessageDialog(null, "\tOpción inválida.");
                            }
                        } while (opcionBuscar != 0);
                        break;
                    case 3:
                        // Actualizar Stock
                        JOptionPane.showMessageDialog(null, inventario.actualizarStock(
                                JOptionPane.showInputDialog(null, "Ingresa el Codigo de producto").toUpperCase(),
                                Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la nueva cantidad"))));
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
                                    JOptionPane.showMessageDialog(null, "\tOpción inválida.");
                            }
                        } while (opcionOrdenar != 0);
                        break;
                    case 6:
                        //Selecciona los k productos con menor cantidad en stock
                        JOptionPane.showMessageDialog(null,
                            inventario.seleccionarKMenorStock(Integer.parseInt(
                                JOptionPane.showInputDialog(null, "Ingresa cantidad a consultar"))
                        ));
                        break;
                    case 7:
                        //Selecciona mostrar Historial de Cambios
                        JOptionPane.showMessageDialog(null,
                            inventario.mostrarHistorialCambios());
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "\tSaliendo del programa...", "SALIDA", 0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "\tOpción inválida.");
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

                        <h3 style="color:#5a7ab4">------------------</h3>
                            <p style="color:#b45274">0. Salir</p>
                    </html>
                                        """, "MENU", 1);

            if (sOpcion == null) {
                return 0;
            } else if (sOpcion.isBlank() || sOpcion.isEmpty()) {
                return -1;
            } else {
                return Byte.parseByte(sOpcion);
            }

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
                return -1;
            } else {
                return Byte.parseByte(sOpcion);
            }

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
                return -1;
            } else {
                return Byte.parseByte(sOpcion);
            }

        } catch (Exception e) {
            throw new Exception("Error al AbrirMenuOrdenar:\n" + e);
        }
    }

}
