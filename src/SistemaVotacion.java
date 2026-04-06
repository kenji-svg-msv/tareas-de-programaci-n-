import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SistemaVotacion {
    public static void main(String[] args) {
        ArrayList<String> votos = new ArrayList<>();
        votos.add("Candidato A");
        votos.add("Candidato B");
        votos.add("Candidato A");

        HashSet<String> candidatosUnicos = new HashSet<>(votos);

        for (String c : candidatosUnicos) {
            int conteo = Collections.frequency(votos, c);
            double porcentaje = (conteo * 100.0) / votos.size();
            System.out.println(c + ": " + conteo + " votos (" + porcentaje + "%)");
        }
    }
}

