package controller;

import model.IActualizable;
import model.VideoJuego;

import java.util.ArrayList;
import java.util.List;

public class ControllerVideoJuego  {

    private static List<VideoJuego> videoJuegos = new ArrayList<>();
    private static List<IActualizable> guiActualiza = new ArrayList<>();

    public static List<VideoJuego> getVideoJuegos() {
        return videoJuegos;
    }

    public static void setVideoJuegos(List<VideoJuego> videoJuegos) {
        ControllerVideoJuego.videoJuegos = videoJuegos;
    }

    public static void agregarVideoJuego(VideoJuego videoJuego) throws RuntimeException {
        if (videoJuego != null) {
            if (videoJuego.validarVideoJuego()) {
                if (!existeId(videoJuego.getId())) {
                    videoJuegos.add(videoJuego);
                    System.out.println("Videojuego agregado correctamente.");
                    actualizar();
                } else {
                    throw new RuntimeException("Error: Ya existe un videojuego con ese ID.");
                }
            } else {
                throw new RuntimeException("Error: Campos inválidos para el videojuego.");
            }
        } else {
            throw new RuntimeException("Error: Videojuego nulo.");
        }
    }

    private static boolean existeId(int id) {
        for (VideoJuego v : videoJuegos) {
            if (v.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static List<VideoJuego> listarVideoJuegos() {
        return new ArrayList<>(videoJuegos);
    }

    public void addActualizable(IActualizable actualizable){
        guiActualiza.add(actualizable);
    }

    public static void actualizar(){
        for (IActualizable act : guiActualiza){
            act.actualizar();
        }
    }

    // Otros

    public static VideoJuego buscarVideoJuego(int id) {
        for (VideoJuego videojuego : videoJuegos) {
            if (videojuego.getId() == id) {
                return videojuego;
            }
        }
        return null;
    }

    public static List<VideoJuego> buscarVideoJuego(String nombre) {
        List<VideoJuego> resultados = new ArrayList<>();
        for (VideoJuego videojuego : videoJuegos) {
            if (videojuego.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(videojuego);
            }
        }
        return resultados;
    }

    public static VideoJuego buscarVideoJuegoPorNombre(String nombre) {
        for (VideoJuego videojuego : videoJuegos) {
            if (videojuego.getNombre().equalsIgnoreCase(nombre)) {
                return videojuego;
            }
        }
        return null;
    }

    public static void actualizarVideojuego(VideoJuego videojuegoActualizado) {
        for (int i = 0; i < videoJuegos.size(); i++) {
            if (videoJuegos.get(i).getId() == videojuegoActualizado.getId()) {
                videoJuegos.set(i, videojuegoActualizado);
                actualizar();
                System.out.println("Videojuego actualizado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró un videojuego con el ID proporcionado para actualizar.");
    }


    public static void eliminarVideoJuego(int id) {
        for (VideoJuego videoJuego : videoJuegos) {
            if (videoJuego.getId() == id) {
                videoJuegos.remove(videoJuego);
                System.out.println("Videojuego eliminado correctamente.");
                actualizar();
                return;
            }
        }
        System.out.println("No se encontró ningún videojuego con ese ID.");
    }

    public static void eliminarVideoJuego(String nombre) {
        videoJuegos.removeIf(videoJuego -> nombre.equalsIgnoreCase(videoJuego.getNombre()));
        actualizar();
    }

    public static double calcularTotalPrecio() {
        double totalPrecio = 0;
        for (VideoJuego videoJuego : videoJuegos) {
            totalPrecio += videoJuego.calcularPrecio();
        }
        return totalPrecio;
    }
}







