import java.util.ArrayList;

abstract class Figura {
    protected String color;

    public Figura(String color) {
        this.color = color;
    }

    public abstract double calcularArea();
    public abstract double calcularPerimetro();
}

class Circulo extends Figura {
    private double radio;

    public Circulo(String color, double radio) {
        super(color);
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
}

class Rectangulo extends Figura {
    private double base, altura;

    public Rectangulo(String color, double base, double altura) {
        super(color);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (base + altura);
    }
}

class Triangulo extends Figura {
    private double base, altura, ladoA, ladoB, ladoC;

    public Triangulo(String color, double base, double altura, double ladoA, double ladoB, double ladoC) {
        super(color);
        this.base = base;
        this.altura = altura;
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return ladoA + ladoB + ladoC;
    }
}

public class MainFiguras {
    public static void main(String[] args) {
        ArrayList<Figura> figuras = new ArrayList<>();
        figuras.add(new Circulo("Rojo", 5));
        figuras.add(new Rectangulo("Azul", 4, 10));
        figuras.add(new Triangulo("Verde", 3, 4, 3, 4, 5));

        double areaTotal = 0;
        for (Figura f : figuras) {
            areaTotal += f.calcularArea();
        }
        System.out.println("Área total: " + areaTotal);
    }
}

