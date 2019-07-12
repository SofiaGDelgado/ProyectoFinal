package aplicacion.form.bean;

import aplicacion.bean.UsuarioBean;
import aplicacion.bean.DetalleBean;
import aplicacion.modelo.dominio.Cliente;
import aplicacion.modelo.dominio.Detalle;
import aplicacion.modelo.dominio.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author BRENDA
 */
@ManagedBean
@ViewScoped
public class UsuarioFormBean implements Serializable{
    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;
    @ManagedProperty(value = "#{detalleBean}")
    private DetalleBean detalleBean;
    private Usuario unUsuario;
    private String nombre;
    private String pass;
    private String nombreUsuario;
    private List<Usuario> listaUsuarios;
    private Detalle unDetalle;
    private Cliente unCliente;
    private String nombreCambioContraseña;
    private String passCambiada;

    public UsuarioFormBean() {
    }
   
    public  void init(){
        unUsuario=new Usuario();
        listaUsuarios=new ArrayList<>();
        unDetalle=new Detalle();
    }

    public UsuarioFormBean(UsuarioBean usuarioBean, DetalleBean detalleBean, Usuario unUsuario, String nombre, String pass, String nombreUsuario, List<Usuario> listaUsuarios, Detalle unDetalle, Cliente unCliente, String nombreCambioContraseña, String passCambiada) {
        this.usuarioBean = usuarioBean;
        this.detalleBean = detalleBean;
        this.unUsuario = unUsuario;
        this.nombre = nombre;
        this.pass = pass;
        this.nombreUsuario = nombreUsuario;
        this.listaUsuarios = listaUsuarios;
        this.unDetalle = unDetalle;
        this.unCliente = unCliente;
        this.nombreCambioContraseña = nombreCambioContraseña;
        this.passCambiada = passCambiada;
    }
   

    public String validarUsuario ( ){
        String pagina=null;
        setUnUsuario(getUsuarioBean().validarUsuario(getNombre(), getPass()));
        System.out.println(getNombre()+ getPass());
        if(getUnUsuario()!=null)
        {
            if(getUnUsuario().getTipoUsuario().equalsIgnoreCase("admin"))
            {   
               
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().getSessionMap().put("usuariovalidado", unUsuario);
                fc.getExternalContext().getSessionMap().put("tipousuario", true);
               
                pagina="ventasproducto?faces-redirect=true";
               
            }
            else
            {
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariovalidado", unUsuario);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipousuario", false);
                 pagina="pagocompra?faces-redirect=true";
             
            }
        }
        else
        {
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña o nombre de usuario invalidos", "por favor vuelva a ingresarlas");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return pagina;
    }
    public void cambiarContraseña(){
        Usuario u= new Usuario();
       
       u=obtenerUsuario(getNombreCambioContraseña());
       
       u.setPassword(getPassCambiada()); 
        System.out.println(u.getNombreUsuario());
        System.out.println(u.getCodigo());
       modificarUsuario(u);
    }
    public Usuario obtenerUsuario (String nombreUsuario){
        String resultado=null;
        unUsuario = usuarioBean.obtenerUsuario(nombreUsuario);
        if(unUsuario== null){
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Credenciales Invalidas","Credenciales Invalidas");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
        }
        else
        {
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario Valido", "Usuario Valido");
            if (unUsuario.getNombreUsuario().equals("usuario")){
                    resultado="paginaCliente?faces-redirect=true"; //usuario
            }
            else
                {
                resultado="usuarios";
                }  
        }
       return unUsuario;
    }
    public List <Usuario> obtenerListaUsuariosActivos(){
            listaUsuarios= usuarioBean.obtenerListaUsuariosActivos();
            if(unUsuario== null){
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Credenciales Invalidas","Credenciales Invalidas");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
        }
        
        return listaUsuarios;
    }


     public void agregarUsuario ( ){
        getUnUsuario().setEstado(true);
           //getUnUsuario().setTipoUsuario("cliente");
           //getUnUsuario().setCodigo((int) (Math.random()*1000000));
           //getUnUsuario().setClientes(unCliente);
           try {
               usuarioBean.agregarUsuario(unUsuario);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro exitoso","Usuario" + unUsuario.getApellidos());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
        
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","No se pudo crear usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                       
           }
           setUnUsuario(new Usuario());
    }

    public void modificarUsuario (Usuario unUsuario){
       getUnUsuario().setEstado(true);
       setListaUsuarios(obtenerListaUsuariosActivos());
        if(getListaUsuarios()!=null){
            getUsuarioBean().modificarUsuario(unUsuario);
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO,"Modificacion exitosa","Se cambio la contraseña del usuario "+getUnUsuario().getNombreUsuario());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        else {
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error", "Intente nuevamente");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        setUnUsuario(unUsuario);
    }
    public void eliminarUsuario (Usuario unUsuario){
        getUnUsuario().setEstado(true);
        if(getListaUsuarios()!=null){
            getUsuarioBean().eliminarUsuario(unUsuario);
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario eliminado correctamente ","Usuario"+getUnUsuario().getTipoUsuario());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        else {
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error grave", "No se puede eliminar usuarios");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        setUnUsuario(unUsuario);
    }
    
     
    /**
     * @return the usuarioBean
     */
    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    /**
     * @param usuarioBean the usuarioBean to set
     */
    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
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

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the listaUsarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * @return the unDetalle
     */
    public Detalle getUnDetalle() {
        return unDetalle;
    }

    /**
     * @param unDetalle the unDetalle to set
     */
    public void setUnDetalle(Detalle unDetalle) {
        this.unDetalle = unDetalle;
    }

    /**
     * @return the detalleBean
     */
    public DetalleBean getDetalleBean() {
        return detalleBean;
    }

    /**
     * @param detalleBean the detalleBean to set
     */
    public void setDetalleBean(DetalleBean detalleBean) {
        this.detalleBean = detalleBean;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    public String getNombreCambioContraseña() {
        return nombreCambioContraseña;
    }

    public void setNombreCambioContraseña(String nombreCambioContraseña) {
        this.nombreCambioContraseña = nombreCambioContraseña;
    }

    public String getPassCambiada() {
        return passCambiada;
    }

    public void setPassCambiada(String passCambiada) {
        this.passCambiada = passCambiada;
    }
    
    
}
