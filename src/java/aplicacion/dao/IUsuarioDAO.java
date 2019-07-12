package aplicacion.dao;

import aplicacion.modelo.dominio.Usuario;
import java.util.List;

/**
 *  Interfaz del objeto Usuario 
 *   QUE ES LO QUE SE VA A HACER
 * @author Sofia Delgado
 */
public interface IUsuarioDAO {
    public Usuario validarUsuario (String nombreUsuario, String password);
    public Usuario obtenerUsuario (String nombreUsuario);
    public void modificarUsuario (Usuario unUsuario);
    public void eliminarUsuario (Usuario unUsuario);
    public List <Usuario> obtenerListaUsuariosActivos();
    public void agregarUsuario (Usuario unUsuario);
}
