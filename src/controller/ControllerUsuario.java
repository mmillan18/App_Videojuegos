package controller;

import model.IActualizable;
import model.Usuario;
import model.VideoJuego;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<IActualizable> guiActualiza = new ArrayList<>();

    public static void agregarUsuario(Usuario usuario) throws RuntimeException {
        if (usuario != null) {
            if (usuario.validarUsuario()) {
                if (!existeId(usuario.getId())) {
                    usuarios.add(usuario);
                    actualizar();
                    System.out.println("Videojuego agregado correctamente.");
                } else {
                    throw new RuntimeException("Error: Ya existe un usuario con ese ID.");
                }
            } else {
                throw new RuntimeException("Error: Campos inválidos para el usuario.");
            }
        } else {
            throw new RuntimeException("Error: usuario nulo.");
        }
    }
    private static boolean existeId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static List<Usuario> listarUsuarios(){
        return usuarios;
    }

    public static void eliminarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuarios.remove(usuario);
                actualizar();
                System.out.println("Usuario eliminado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró ningún usuario con ese ID.");
    }

    public static void eliminarUsuario(String nombre) {
        usuarios.removeIf(usuario -> nombre.equalsIgnoreCase(usuario.getNombre()));
        actualizar();
    }

    public static Usuario buscarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public static Usuario buscarUsuario(String nombre) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    public static void actualizar(){
        for (IActualizable act : guiActualiza){
            act.actualizar();
        }
    }
    public void addActualizable(IActualizable actualizable){
        guiActualiza.add(actualizable);
    }

    public static void actualizarUsuario(Usuario usuario){
        boolean actualizo = false;
        for (Usuario u : usuarios){
            if(u.getId() == usuario.getId()){
                u.setNombre(usuario.getNombre());
                u.setId(usuario.getId());
                u.setFechaNacimiento(usuario.getFechaNacimiento());
                actualizar();
                System.out.println("Usuario actualizado");
                actualizo = true;
                break;
            }
        }
        System.out.println("No se encontro un usuario con es ID");
    }
}
