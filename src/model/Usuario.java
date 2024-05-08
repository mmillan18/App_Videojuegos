package model;

import java.time.LocalDate;

public class Usuario {

    private int id;
    private String nombre;
    private LocalDate fechaNacimiento;

    public Usuario(int id, String nombre, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean validarUsuario() {
        return nombre != null && id != 0 && fechaNacimiento != null;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "\nid=" + id +
                ",\nnombre='" + nombre + '\'' +
                ",\nfechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
