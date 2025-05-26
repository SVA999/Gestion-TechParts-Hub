import Clases.*;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // Agregamos algunas partes de ejemplo
        inventario.registrarParte(new ParteHardware("RAM 16GB", "R123", "Memoria RAM", "Kingston", 80.0, 10, "RAM"));
        inventario.registrarParte(new ParteHardware("CPU Ryzen 5", "C456", "Procesador AMD", "AMD", 200.0, 5, "CPU"));
        inventario.registrarParte(new ParteHardware("SSD 1TB", "D789", "Disco Sólido", "Samsung", 120.0, 8, "Disco"));

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
    }
}
