
import java.util.ArrayList;

class Animal {
    protected String nombre;
    protected double peso;

    public Animal(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String hacerSonido() { return "..."; }
    public double calcularAlimentoDiario() { return peso * 0.03; }
}

class Mamifero extends Animal {
    protected int gestacionDias;

    public Mamifero(String nombre, double peso, int gestacionDias) {
        super(nombre, peso);
        this.gestacionDias = gestacionDias;
    }
}

class Leon extends Mamifero {
    public Leon(String nombre, double peso, int gestacionDias) {
        super(nombre, peso, gestacionDias);
    }

    @Override
    public String hacerSonido() { return "ROAR!"; }

    @Override
    public double calcularAlimentoDiario() { return peso * 0.05; }
}

class Ave extends Animal {
    protected double envergaduraAlas;

    public Ave(String nombre, double peso, double envergaduraAlas) {
        super(nombre, peso);
        this.envergaduraAlas = envergaduraAlas;
    }
}

class Aguila extends Ave {
    public Aguila(String nombre, double peso, double envergaduraAlas) {
        super(nombre, peso, envergaduraAlas);
    }

    @Override
    public String hacerSonido() { return "¡Chillido agudo!"; }
}

public class MainZoo {
    public static void main(String[] args) {
        ArrayList<Animal> zoo = new ArrayList<>();
        zoo.add(new Leon("Simba", 190, 110));
        zoo.add(new Aguila("Alpha", 5, 2.1));

        for (Animal a : zoo) {
            System.out.println(a.nombre + " dice: " + a.hacerSonido());
            System.out.println("Alimento necesario: " + a.calcularAlimentoDiario() + "kg");
            System.out.println("---");
        }
    }
}

