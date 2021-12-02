package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private static Conexion instancia;
    
    protected Connection conexion = null;
    protected Statement sentencia = null;
    protected ResultSet resultado = null;
    protected PreparedStatement sentenciaPreparada = null;
    private final String user = "root";
    private final String password = "root";
    private final String database = "bd_duoc";

    protected Conexion() {
    }
    
    public void obtenerConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexión" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void desconectarBase() throws Exception {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (sentenciaPreparada != null) {
                sentenciaPreparada.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    protected void modificarBase(String sql) throws Exception {
        try {
            //Creo la conexión con la base
            obtenerConexion();
            //Creo la sentencia
            sentencia = conexion.createStatement();
            //Ejecuto la sentencia con el sql pasado como parámetro
            sentencia.executeUpdate(sql);

        } catch (SQLException ex) {
            System.err.println("LA QUERY FUE: " + sql);
            System.out.println(ex.getMessage());
            try {
                //En caso de error retorno toda modificación para atras
                conexion.rollback();
            } catch (SQLException ex1) {
                throw new Exception(ex1.getMessage());
            }
            throw new Exception(ex.getMessage());
        } finally {
            desconectarBase();
        }
    }

    protected void consultarBase(String sql) throws Exception {
        try {
            //Creamos la conexión a la base
            obtenerConexion();
            //Creamos la sentencia
            sentencia = conexion.createStatement();
            //Ejecutamos la sentencia y obtenemos los resultados
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException ex) {
            throw new Exception("Error de Sistemas");
        }
    }

    public static Conexion obtenerInstancia(){
        if(instancia == null){
            instancia = new Conexion();
        }
        return instancia;
    }
    
}
