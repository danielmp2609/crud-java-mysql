package com.daniel.crud.service;

import com.daniel.crud.dao.UsuarioDAO;
import com.daniel.crud.model.Usuario;

import java.util.List;

public class UsuarioService {

    private UsuarioDAO dao = new UsuarioDAO();

    public void crearUsuario(Usuario u) {
        dao.insertar(u);
    }

    public List<Usuario> listarUsuarios() {
        return dao.listar();
    }

    public void actualizarUsuario(Usuario u) {
        dao.actualizar(u);
    }

    public void eliminarUsuario(int id) {
        dao.eliminar(id);
    }

    public List<Usuario> buscarPorNombre(String nombre) {
        return dao.buscarPorNombre(nombre);
    }
}