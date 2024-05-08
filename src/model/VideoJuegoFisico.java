package model;

import java.time.LocalDate;

public class VideoJuegoFisico extends VideoJuego implements IDescuentoAplicable {

    private String estado;

    public VideoJuegoFisico(int id, String nombre, double precio, int stock,
                            LocalDate fechaLanzamiento, String estado) {

        super(id, nombre, precio, stock, fechaLanzamiento);
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "VideoJuegoFisico{" +
                "\n" + super.toString() +
                ",\nestado='" + estado + '\'' +
                "\n}";
    }

    @Override
    public double aplicarDescuento() {
        double descuento = getPrecio() * 0.10;
        setPrecio(getPrecio() - descuento);
        return descuento;
    }

    public double calcularPrecio(){

        double precio = getPrecio();
        return precio;
    }

    @Override
    public boolean validarVideoJuego() {
        return getEstado() != null && !getEstado().isEmpty();
    }
}
