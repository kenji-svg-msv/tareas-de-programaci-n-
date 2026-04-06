import java.util.ArrayList;
import java.util.Collections;

public class ListaCompras {

    private ArrayList<String> productos;

    public ListaCompras() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(String producto) {
        if (!productos.contains(producto)) {
            productos.add(producto);
            System.out.println("Producto agregado: " + producto);
        } else {
            System.out.println("El producto ya existe.");
        }
    }

    public void eliminarProducto(String producto) {
        if (productos.remove(producto)) {
            System.out.println("Producto eliminado: " + producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public boolean buscarProducto(String producto) {
        return productos.contains(producto);
    }

    public int contarProductos() {
        return productos.size();
    }

    public void ordenarAlfabeticamente() {
        Collections.sort(productos);
    }

    public void mostrarLista() {
        System.out.println("Lista: " + productos);
    }
}

