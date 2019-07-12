package aplicacion.form.bean;

import aplicacion.bean.LoginBean;
import aplicacion.bean.UsuarioBean;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Sofia Delgado
 */
@ManagedBean
@ViewScoped
public class LoginFormBean implements Serializable{
    //Atributos
    @ManagedProperty(value = "#{usuarioBean}") //Para poder usarlo como atributo
    private UsuarioBean usuarioBean;
    private String nombreUs;
   private String passUs;

    //Constructor por defecto
    public LoginFormBean() {
        usuarioBean=new UsuarioBean ();
    }
    //Constructor parametrizado

    public LoginFormBean(UsuarioBean usuarioBean, String nombreUs, String passUs) {
        this.usuarioBean = usuarioBean;
        this.nombreUs = nombreUs;
        this.passUs = passUs;
    }
    
       
    /**
     * Metodo para validar un Usuario
     * @return 
     */
    public String validarUsuario(){
       
          
        String resultado=null;
        Usuario usuario=null;
        usuario = usuarioBean.validarUsuario(nombreUs, passUs);
       
        if(usuario == null){
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Credenciales Invalidas","No existe el usuario");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
        }else{
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Credenciales validadas","Usuario Valido");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioValidado", usuario); 
            //se guarda el "usuario" con el nombre "usuarioValidado" en el SessionMap del Context
            if (usuario.getTipoUsuario().equalsIgnoreCase("admin")){
                resultado="principal_Cliente?faces-redirect=true";
            }
            else{
              resultado="principal_Admin?faces-redirect=true";  
            }
            
           }
        
        return resultado;
    }
    /**
     * Metodo get que ayuda a disponer del usuario cuando se lo necesite
     * actuará como un atributo de la Clase
     * @return usuario
     */
    public String getNombreUsuarioValidado (){
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado"); 
        //crea un usuario con el usuarioValidado extraido del contexto
        return usuario.getApellidos();
    }
    //Agregado codigo luis
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO,"Sesion Cerrada","Sesion Cerrada");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        String resultado="/loggit";
        return resultado;
    }
    //agregado codigo luis
    public int verificarSesion(){
        boolean sesionValida=false;
        int tipo=0;
        Usuario usuario=(Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado");
        if (usuario!=null){
            sesionValida=true;
            if (usuario.getTipoUsuario().equals("cliente")){
                tipo=1;
            }
            if (usuario.getTipoUsuario().equals("admin")){
                tipo=2;
            }
        }
        return tipo;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public String getPassUs() {
        return passUs;
    }

    public void setPassUs(String passUs) {
        this.passUs = passUs;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    
    
    
}
