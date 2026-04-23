package com.daniel.crud.dao;

import com.daniel.crud.config.Conexion;
import com.daniel.crud.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, correo) VALUES (?, ?)";

        try (Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.executeUpdate();

            System.out.println("Usuario insertado correctamente");

        } catch (Exception e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public List<Usuario> listar() {

        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"));
                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }

    public void actualizar(Usuario usuario) {

        String sql = "UPDATE usuarios SET nombre = ?, correo = ? WHERE id = ?";

        try (Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setInt(3, usuario.getId());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario actualizado correctamente");
            } else {
                System.out.println("No se encontró el usuario con ese ID");
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminar(int id) {

        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario eliminado correctamente");
            } else {
                System.out.println("No se encontró el usuario con ese ID");
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public List<Usuario> buscarPorNombre(String nombre) {

        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nombre LIKE ?";

        try (Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"));
                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return lista;
    }

}
