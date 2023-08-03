import java.util.Date;

public class Mensaje {
    private final String usuario;
    private final String mensaje;
    private final Date hora;

    public Mensaje(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
        this.hora = new Date();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Date getHora() {
        return hora;
    }
}
