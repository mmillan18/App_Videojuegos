package model;

import java.time.LocalDate;

public class VideoJuegoDigital extends VideoJuego  {

    private String claveActivacion;
    private Usuario usuario;

    public VideoJuegoDigital(int id, String nombre, double precio, int stock,
                             LocalDate fechaLanzamiento, String calveActivacion)
    {
        super(id, nombre, precio, stock, fechaLanzamiento);
        this.claveActivacion = calveActivacion;
        this.usuario = usuario;
    }

    public String getClaveActivacion() {
        return claveActivacion;
    }

    public void setClaveActivacion(String claveActivacion) {
        this.claveActivacion = claveActivacion;
    }

    public Usuario getUsuario() {return this.usuario;}

    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public double calcularPrecio(){

        double precio = getPrecio();
        return precio;
    }

    @Override
    public String toString() {
        return "VideoJuegoDigital{" +
                "\n" + super.toString() +
                ",\nclaveActivacion='" + claveActivacion + '\'' +
                ",\nnombreUsuario='" + usuario.getNombre() + '\'' +
                "\n}";
    }


    @Override
    public boolean validarVideoJuego() {
        return getClaveActivacion() != null && !getClaveActivacion().isEmpty();
    }
}
