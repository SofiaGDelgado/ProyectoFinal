package aplicacion.bean;

import aplicacion.dao.IUsuarioDAO;
import aplicacion.dao.imp.UsuarioDaoImp;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author BRENDA MENDEZ
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
    private List<Usuario> listaUsarios;
    private IUsuarioDAO usuarioDAO;
    private Usuario unUsuario;

    public UsuarioBean(List<Usuario> listaUsarios, IUsuarioDAO usuarioDAO, Usuario unUsuario) {
        this.listaUsarios = listaUsarios;
        this.usuarioDAO = usuarioDAO;
        this.unUsuario = unUsuario;
    }
    
    
    public UsuarioBean() {
        usuarioDAO= new UsuarioDaoImp();
        listaUsarios=new ArrayList<>();
    }
    public Usuario validarUsuario(String nombreUsuario, String password){
        
        return usuarioDAO.validarUsuario(nombreUsuario, password);
    }
    public Usuario obtenerUsuario (String nombreUsuario){
        return usuarioDAO.obtenerUsuario(nombreUsuario);
    }
    public void modificarUsuario (Usuario unUsuario){
        usuarioDAO.modificarUsuario(unUsuario);
    }
    public void eliminarUsuario (Usuario unUsuario){
        usuarioDAO.eliminarUsuario(unUsuario);
    }
    public List <Usuario> obtenerListaUsuariosActivos(){
        return getUsuarioDAO().obtenerListaUsuariosActivos();
    }
    public void agregarUsuario (Usuario unUsuario){        
        usuarioDAO.agregarUsuario(unUsuario);
    }
    //metodo de cosultar 
    
    /**
     * @return the listaUsarios
     */
    public List<Usuario> getListaUsarios() {
        return listaUsarios;
    }

    /**
     * @param listaUsarios the listaUsarios to set
     */
    public void setListaUsarios(List<Usuario> listaUsarios) {
        this.listaUsarios = listaUsarios;
    }

    /**
     * @return the usuarioDAO
     */
    public IUsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    /**
     * @param usuarioDAO the usuarioDAO to set
     */
    public void setUsuarioDAO(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    /**
     * @return the unUsuario
     */
    public Usuario getUnUsuario() {
        return unUsuario;
    }

    /**
     * @param unUsuario the unUsuario to set
     */
    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }
}
