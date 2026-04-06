public class RelojDigital {
    private int horas, minutos, segundos;
    private int alarmaH = -1, alarmaM = -1;

    public RelojDigital(int h, int m, int s) {
        this.horas = h;
        this.minutos = m;
        this.segundos = s;
    }

    public void configurarAlarma(int h, int m) {
        this.alarmaH = h;
        this.alarmaM = m;
    }

    public void verificarAlarma() {
        if (horas == alarmaH && minutos == alarmaM && segundos == 0) {
            System.out.println("¡¡¡ALERTA: LA ALARMA ESTÁ SONANDO!!!");
        }
    }

    public void avanzarSegundo() {
        segundos++;
        if (segundos >= 60) { segundos = 0; minutos++; }
        if (minutos >= 60) { minutos = 0; horas++; }
        if (horas >= 24) { horas = 0; }
        verificarAlarma();
    }

    public String mostrarFormato24h() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public String mostrarFormato12h() {
        String ampm = horas >= 12 ? "PM" : "AM";
        int h = horas % 12;
        if (h == 0) h = 12;
        return String.format("%d:%02d:%02d %s", h, minutos, segundos, ampm);
    }

    public static void main(String[] args) {
        RelojDigital miReloj = new RelojDigital(23, 59, 59);
        miReloj.configurarAlarma(0, 0);

        System.out.println("Hora actual: " + miReloj.mostrarFormato24h());
        miReloj.avanzarSegundo();
        System.out.println("Hora después de avanzar: " + miReloj.mostrarFormato12h());
    }
}

