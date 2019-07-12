package aplicacion.bean;


import aplicacion.dao.IUsuarioDAO;
import aplicacion.dao.imp.UsuarioDaoImp;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bean para la pagina Login
 * @author Sofia Delgado
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable{
    //Atributos
    private IUsuarioDAO usuarioDAO;
    
    //Constructor por defecto y inicializacion de usuarioDAO
    public LoginBean() {
        usuarioDAO= new UsuarioDaoImp();
    }
    //Constructor parametrizado
    public LoginBean(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    //Metodos
     public Usuario validarUsuario(String nombreUsuario, String password){
        return usuarioDAO.validarUsuario(nombreUsuario, password);
    }
    //Getter y setter
    public IUsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
}
