package model;

import java.time.LocalDate;

public abstract class VideoJuego {

    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private LocalDate fechaLanzamiento;

    public VideoJuego(int id, String nombre, double precio, int stock,
                      LocalDate fechaLanzamiento) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public abstract boolean validarVideoJuego();

    public abstract double calcularPrecio();


    @Override
    public String toString() {
        return "VideoJuego{" +
                "\nid=" + id +
                ",\nnombre='" + nombre + '\'' +
                ",\nprecioNormal=" + precio +
                ",\nstock=" + stock +
                ",\nfechaLanzamiento=" + fechaLanzamiento +
                "\n}";
    }
}
