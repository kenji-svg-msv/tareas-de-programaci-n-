import java.util.ArrayList;

interface Rastreable {
    String getUbicacion();
    String getEstado();
}

interface Calificable {
    void calificar(int estrellas);
    double getPromedio();
}

interface Notificable {
    String getContacto();

    default void notificar(String mensaje) {
        System.out.println("Notificación a " + getContacto() + ": " + mensaje);
    }
}

class Repartidor implements Calificable, Notificable, Rastreable {
    private String nombre;
    private String telefono;
    private ArrayList<Integer> calificaciones = new ArrayList<>();
    private String ubicacionActual = "Almacén Central";

    public Repartidor(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    @Override
    public void calificar(int estrellas) {
        calificaciones.add(estrellas);
    }

    @Override
    public double getPromedio() {
        return calificaciones.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    @Override
    public String getContacto() {
        return telefono + " (" + nombre + ")";
    }

    @Override
    public String getUbicacion() {
        return ubicacionActual;
    }

    @Override
    public String getEstado() {
        return "En reparto";
    }
}

class Restaurante implements Calificable, Notificable {
    private String nombre;
    private String email;
    private ArrayList<Integer> calificaciones = new ArrayList<>();

    public Restaurante(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public void calificar(int estrellas) {
        calificaciones.add(estrellas);
    }

    @Override
    public double getPromedio() {
        return calificaciones.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    @Override
    public String getContacto() {
        return email;
    }
}

class Cliente implements Notificable {
    private String nombre;
    private String direccion;

    public Cliente(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    @Override
    public String getContacto() {
        return nombre + " en " + direccion;
    }
}

public class MainDelivery {
    public static void main(String[] args) {
        Repartidor r = new Repartidor("Carlos", "555-0123");
        Restaurante res = new Restaurante("Burger King", "info@bk.com");
        Cliente c = new Cliente("Ana", "Calle Falsa 123");

        r.calificar(5);
        r.calificar(4);

        res.notificar("Pedido listo para recoger");
        r.notificar("Voy en camino");
        c.notificar("Tu pedido llegará en 5 minutos");

        System.out.println("Repartidor Rating: " + r.getPromedio());
        System.out.println("Ubicación: " + r.getUbicacion());
    }
}

