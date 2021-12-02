package controlador.impl;

import bd.Conexion;
import controlador.BebidaControlador;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Bebida;

public class BebidaControladorDefault extends Conexion implements BebidaControlador {

    private static BebidaControladorDefault instancia;

    @Override
    public boolean agregar(Bebida bebida) {
        try {
            String query = String.format("INSERT INTO bebida (id, nombre, precio, proveedor) VALUES (%d, '%s', %.2f, '%s')",
                    bebida.getId(), bebida.getNombre(), bebida.getPrecio(), bebida.getProveedor());
            modificarBase(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Bebida buscarPorId(Integer id) {
        try {
            String query = "SELECT id, nombre, proveedor, precio FROM bebida WHERE id = " + id;
            consultarBase(query);
            if (resultado.next()) {
                return new Bebida(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("proveedor"), resultado.getDouble("precio"));
            } else {
                System.out.println("No se ha encontrado el producto");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex1) {
            System.out.println(ex1.getMessage());
        } finally {
            try {
                desconectarBase();
            } catch (Exception ex) {
                Logger.getLogger(BebidaControladorDefault.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public List<Bebida> buscarTodos() {
        try {
            String query = "SELECT id, nombre, proveedor, precio FROM bebida";
            consultarBase(query);
            List<Bebida> bebidas = new ArrayList();
            while (resultado.next()) {
                Bebida bebida = new Bebida();
                bebida.setId(resultado.getInt("id"));
                bebida.setNombre(resultado.getString("nombre"));
                bebida.setProveedor(resultado.getString("proveedor"));
                bebida.setPrecio(resultado.getDouble("precio"));
            }
            return bebidas;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex1) {
            System.out.println(ex1.getMessage());
        } finally {
            try {
                desconectarBase();
            } catch (Exception ex) {
                Logger.getLogger(BebidaControladorDefault.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public void actualizar(Integer id, Bebida bebida) {
        try{
            obtenerConexion();
            
            String query = "UPDATE bebida SET nombre = ?, proveedor = ?, precio = ? WHERE id=?";
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, bebida.getNombre());
            stmt.setString(2, bebida.getProveedor());
            stmt.setDouble(3, bebida.getPrecio());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error SQL al actualizar libro" + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("Error al actualizar libro" + e.getMessage());
            
        } finally {
            try {
                desconectarBase();
            } catch (Exception ex) {
                Logger.getLogger(BebidaControladorDefault.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            String query = "DELETE FROM bebida WHERE id = " + id;                    
            modificarBase(query);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static BebidaControladorDefault obtenerInstancia() {
        if (instancia == null) {
            instancia = new BebidaControladorDefault();
        }
        return instancia;
    }

}
