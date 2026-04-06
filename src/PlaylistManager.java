import java.util.ArrayList;
import java.util.Collections;

class Cancion {
    String titulo, artista, genero;
    int duracionSeg;

    public Cancion(String titulo, String artista, int duracionSeg, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSeg = duracionSeg;
        this.genero = genero;
    }
}

public class PlaylistManager {
    public static void main(String[] args) {
        ArrayList<Cancion> playlist = new ArrayList<>();
        playlist.add(new Cancion("Starboy", "The Weeknd", 230, "Pop"));
        playlist.add(new Cancion("Yellow", "Coldplay", 260, "Rock"));

        Collections.shuffle(playlist); // Aleatorio
        playlist.sort((a, b) -> a.titulo.compareTo(b.titulo)); // Ordenar por título

        int totalSeg = playlist.stream().mapToInt(c -> c.duracionSeg).sum();
        System.out.println("Duración total: " + totalSeg + " segundos");
    }
}

