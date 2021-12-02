package controlador;

import java.util.List;
import modelo.Bebida;

public interface BebidaControlador {
    
    boolean agregar(Bebida bebida);
    
    Bebida buscarPorId(Integer id);
    
    List<Bebida> buscarTodos();
    
    void actualizar(Integer id, Bebida bebida);
    
    void eliminar(Integer id);
    
}
