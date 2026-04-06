abstract class Cobro {
    protected double monto;

    public Cobro(double monto) {
        this.monto = monto;
    }

    public abstract boolean procesar();
    public abstract double calcularComision();

    // Factory Method
    public static Cobro crear(String tipo, double monto) {
        switch (tipo.toLowerCase()) {
            case "recurrente": return new CobroRecurrente(monto, 30);
            case "unico":      return new CobroUnico(monto);
            case "diferido":   return new CobroDiferido(monto, 12, 0.15);
            default: throw new IllegalArgumentException("Tipo no válido");
        }
    }
}

class CobroRecurrente extends Cobro {
    private int periodicidad;

    public CobroRecurrente(double monto, int periodicidad) {
        super(monto);
        this.periodicidad = periodicidad;
    }

    @Override
    public boolean procesar() { return true; }

    @Override
    public double calcularComision() { return monto * 0.02; }
}

class CobroUnico extends Cobro {
    public CobroUnico(double monto) {
        super(monto);
    }

    @Override
    public boolean procesar() { return true; }

    @Override
    public double calcularComision() { return 5.0; } // Comisión fija
}

class CobroDiferido extends Cobro {
    private int cuotas;
    private double interes;

    public CobroDiferido(double monto, int cuotas, double interes) {
        super(monto);
        this.cuotas = cuotas;
        this.interes = interes;
    }

    @Override
    public boolean procesar() { return true; }

    @Override
    public double calcularComision() { return monto * interes; }
}

public class MainPagos {
    public static void main(String[] args) {
        // Usando el Factory para crear objetos sin conocer la clase concreta
        Cobro pago1 = Cobro.crear("recurrente", 50.0);
        Cobro pago2 = Cobro.crear("diferido", 1200.0);

        System.out.println("Comisión pago 1: " + pago1.calcularComision());
        System.out.println("Comisión pago 2: " + pago2.calcularComision());
    }
}

