import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RegistroTemperaturas {
    public static void main(String[] args) {
        ArrayList<Double> temps = new ArrayList<>(Arrays.asList(28.5, 31.2, 29.8, 33.1, 30.5, 27.9, 35.0));

        double max = Collections.max(temps);
        double min = Collections.min(temps);

        double promedio = temps.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

        long sobreTreinta = temps.stream()
                .filter(t -> t > 30)
                .count();

        String tendencia = (temps.get(temps.size() - 1) > temps.get(0)) ? "Subiendo" : "Bajando";

        System.out.println("Máxima: " + max);
        System.out.println("Mínima: " + min);
        System.out.println("Promedio: " + promedio);
        System.out.println("Días > 30°C: " + sobreTreinta);
        System.out.println("Tendencia: " + tendencia);
    }
}
