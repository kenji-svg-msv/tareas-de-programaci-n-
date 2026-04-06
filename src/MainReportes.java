import java.util.ArrayList;

abstract class ExportadorReporte {
    protected ArrayList<String[]> datos;
    protected String titulo;

    public ExportadorReporte(String titulo, ArrayList<String[]> datos) {
        this.titulo = titulo;
        this.datos = datos;
    }

    public abstract String formatearDatos();

    public String exportar() {
        return "Exportando Reporte: " + titulo + "\n" + formatearDatos();
    }
}

class ExportadorCSV extends ExportadorReporte {
    public ExportadorCSV(String titulo, ArrayList<String[]> datos) {
        super(titulo, datos);
    }

    @Override
    public String formatearDatos() {
        StringBuilder sb = new StringBuilder();
        for (String[] fila : datos) {
            sb.append(String.join(",", fila)).append("\n");
        }
        return sb.toString();
    }
}

class ExportadorHTML extends ExportadorReporte {
    public ExportadorHTML(String titulo, ArrayList<String[]> datos) {
        super(titulo, datos);
    }

    @Override
    public String formatearDatos() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n<head><title>").append(titulo).append("</title></head>\n<body>\n");
        sb.append("<h2>").append(titulo).append("</h2>\n");
        sb.append("<table border='1'>\n");

        for (String[] fila : datos) {
            sb.append("  <tr>");
            for (String celda : fila) {
                sb.append("<td>").append(celda).append("</td>");
            }
            sb.append("</tr>\n");
        }

        sb.append("</table>\n</body>\n</html>");
        return sb.toString();
    }
}

public class MainReportes {
    public static void main(String[] args) {
        ArrayList<String[]> info = new ArrayList<>();
        info.add(new String[]{"ID", "Producto", "Precio"});
        info.add(new String[]{"1", "Laptop", "1500"});
        info.add(new String[]{"2", "Mouse", "25"});

        ExportadorReporte exp = new ExportadorCSV("Ventas Abril", info);
        System.out.println(exp.exportar());

        exp = new ExportadorHTML("Ventas Abril", info);
        System.out.println(exp.exportar());
    }
}
