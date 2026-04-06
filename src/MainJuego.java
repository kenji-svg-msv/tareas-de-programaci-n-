class Personaje {
    protected String nombre;
    protected int vida, ataque, defensa;

    public Personaje(String nombre, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public int atacar(Personaje enemigo) {
        int dano = Math.max(0, this.ataque - enemigo.defensa);
        enemigo.vida -= dano;
        return dano;
    }
}

class Guerrero extends Personaje {
    private int furia;

    public Guerrero(String nombre, int vida, int ataque, int defensa, int furia) {
        super(nombre, vida, ataque, defensa);
        this.furia = furia;
    }

    public void usarHabilidadEspecial(Personaje enemigo) {
        int golpe = this.ataque + (furia / 2);
        enemigo.vida -= golpe;
    }
}

class Mago extends Personaje {
    private int mana;

    public Mago(String nombre, int vida, int ataque, int defensa, int mana) {
        super(nombre, vida, ataque, defensa);
        this.mana = mana;
    }

    public void usarHabilidadEspecial(Personaje enemigo) {
        if (mana >= 20) {
            enemigo.vida -= ataque * 2;
            mana -= 20;
        }
    }
}

class Arquero extends Personaje {
    private int flechas;

    public Arquero(String nombre, int vida, int ataque, int defensa, int flechas) {
        super(nombre, vida, ataque, defensa);
        this.flechas = flechas;
    }

    public void usarHabilidadEspecial(Personaje enemigo) {
        if (flechas > 0) {
            enemigo.vida -= ataque + 10;
            flechas--;
        }
    }
}

public class MainJuego {
    public static void main(String[] args) {
        Guerrero g = new Guerrero("Aragorn", 100, 25, 15, 50);
        Mago m = new Mago("Gandalf", 80, 30, 10, 100);

        System.out.println("Vida inicial Mago: " + m.vida);
        g.atacar(m);
        System.out.println("Vida Mago tras ataque: " + m.vida);

        m.usarHabilidadEspecial(g);
        System.out.println("Vida Guerrero tras hechizo: " + g.vida);
    }
}
