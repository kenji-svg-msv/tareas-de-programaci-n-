import java.util.ArrayList;
import java.util.Objects;

class Producto {
    String nombre;
    double precio;
    int cantidad;
    String categoria;

    public Producto(String nombre, double precio, int cantidad, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(nombre, producto.nombre);
    }
}

public class ECommerce {
    public static void main(String[] args) {
        ArrayList<Producto> carrito = new ArrayList<>();
        carrito.add(new Producto("Laptop", 1000, 1, "Tech"));
        carrito.add(new Producto("Mouse", 20, 2, "Tech"));

        double total = carrito.stream()
                .mapToDouble(p -> p.precio * p.cantidad)
                .sum();

        System.out.println("Subtotal: $" + total);
    }
}
