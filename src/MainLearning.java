import java.util.ArrayList;

abstract class ContenidoCurso {
    protected String titulo;
    protected int duracion; // en minutos
    protected boolean completado = false; // este está bien

    public ContenidoCurso(String titulo, int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public abstract void iniciar();
    public abstract boolean evaluar();
}

class VideoLeccion extends ContenidoCurso {
    public VideoLeccion(String titulo, int duracion) {
        super(titulo, duracion);
    }

    @Override
    public void iniciar() {
        System.out.println("Reproduciendo video: " + titulo);
        this.completado = true;
    }

    @Override
    public boolean evaluar() {
        return true; // Los videos se marcan al verlos
    }
}

class Quiz extends ContenidoCurso {
    private int notaMinima;

    public Quiz(String titulo, int notaMinima) {
        super(titulo, 15); // duración estándar de 15 min para el quiz
        this.notaMinima = notaMinima;
    }

    @Override
    public void iniciar() {
        System.out.println("Iniciando cuestionario: " + titulo);
    }

    @Override
    public boolean evaluar() {
        this.completado = true; // simulación de evaluación
        return true;
    }
}

class Curso {
    private ArrayList<ContenidoCurso> contenidos = new ArrayList<>();

    public void agregarContenido(ContenidoCurso c) {
        contenidos.add(c);
    }

    public double progreso() {
        if (contenidos.isEmpty()) return 0;
        long hechos = contenidos.stream().filter(c -> c.completado).count();
        return (double) hechos / contenidos.size() * 100;
    }
}

public class MainLearning {
    public static void main(String[] args) {
        Curso javaBasico = new Curso();
        VideoLeccion v1 = new VideoLeccion("Intro a POO", 10);
        Quiz q1 = new Quiz("Examen Variables", 70);

        javaBasico.agregarContenido(v1);
        javaBasico.agregarContenido(q1);

        v1.iniciar(); // Marcamos como completado
        System.out.println("Progreso actual: " + javaBasico.progreso() + "%");
    }
}