package model;

import java.time.LocalDate;

public class EmpresaVideojuego {

    private static EmpresaVideojuego empresaVideojuego;

    private String razonSocial;

    private LocalDate fechaFundacion;

    private String nit;

    private EmpresaVideojuego() {

    }

    public static EmpresaVideojuego getEmpresa(){
        if (empresaVideojuego == null) {
            empresaVideojuego = new EmpresaVideojuego();
            empresaVideojuego.setRazonSocial("Razon Social");
            empresaVideojuego.setNit("1005753985-2");
            empresaVideojuego.setFechaFundacion(LocalDate.now());
        }

        return empresaVideojuego;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "razonSocial='" + razonSocial + '\'' +
                ",fechaFundacion=" + fechaFundacion +
                ",nit='" + nit + '\'' +
                '}';
    }
}


