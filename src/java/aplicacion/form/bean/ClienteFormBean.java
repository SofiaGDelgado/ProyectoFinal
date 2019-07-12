package aplicacion.form.bean;

import aplicacion.bean.ClienteBean;
import aplicacion.bean.UsuarioBean;
import aplicacion.modelo.dominio.Cliente;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Sofia Delgado
 */
@ManagedBean
@SessionScoped
public class ClienteFormBean implements Serializable{
    @ManagedProperty(value = "#{clienteBean}")
    private ClienteBean clienteBean;
    
    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;
    
    private Cliente unCliente;
    private String nombre;
    private List<Cliente> listaClientes;
    private Usuario unUsuario;
    private int codigo;
    //private UsuarioBean usuarioBean;
    private List<String> listadoPaises;
    private boolean skip;
   private List <String> listadoTipos;
    
    public ClienteFormBean() {
        unCliente=new Cliente();
        listaClientes=new ArrayList<>();
        unUsuario=new Usuario();
        //clienteBean=new ClienteBean();
        //usuarioBean=new UsuarioBean();
        //Inicializando la lista de paises y agregando algunos paises
        listadoPaises=new ArrayList<>();
        listadoPaises.add("Argentina");
        listadoPaises.add("Brasil");
        listadoPaises.add("Uruguay");
        listadoTipos=new ArrayList<>();
        listadoTipos.add("cliente");
        listadoTipos.add("admin");
    }

     
    
       public Cliente obtenerCliente (int dni){ //error, cambiar
        String resultado=null;
        unCliente = clienteBean.obtenerCliente(dni);
        if(unCliente== null){
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se encontro cliente","No se encontro el cliente");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
        }
        else
        {
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cliente encontrado", "Cliente Valido");
            resultado="paginaCliente?faces-redirect=true"; //usuario 
        }
       return unCliente;
    }

    public void agregarCliente (){         
         getUnUsuario().setEstado(true);
         getUnUsuario().setTipoUsuario("cliente");
         getUnUsuario().setClientes(unCliente);
         
      
         getUnUsuario().setCodigo((int) (Math.random()*1000000));
         
         //getUnUsuario().setClientes(unCliente);         
         try {
             
             getUsuarioBean().agregarUsuario(unUsuario);
             //clienteBean.agregarCliente(unCliente);
             FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro exitoso","Inicie sesion para continuar");
             FacesContext.getCurrentInstance().addMessage(null, facesMessage);
         }catch (Exception e){
             
             FacesMessage facesMessage=new FacesMessage (FacesMessage.SEVERITY_WARN,"Error","Intente nuevamente");
         }
         
    }
    public void agregarAdmin (){         
         getUnUsuario().setEstado(true);
         //getUnUsuario().setTipoUsuario("cliente");
         getUnUsuario().setClientes(unCliente);
         
      
         getUnUsuario().setCodigo((int) (Math.random()*1000000));
         
         //getUnUsuario().setClientes(unCliente);         
         try {
             
             getUsuarioBean().agregarUsuario(unUsuario);
             //clienteBean.agregarCliente(unCliente);
             FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro exitoso","Inicie sesion para continuar");
             FacesContext.getCurrentInstance().addMessage(null, facesMessage);
         }catch (Exception e){
             
             FacesMessage facesMessage=new FacesMessage (FacesMessage.SEVERITY_WARN,"Error","Intente nuevamente");
         }
         
    }

    public void modificarCliente (Cliente unCliente){
       
        if(getListaClientes()!=null){
            getClienteBean().modificarCliente(unCliente);
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_INFO,"Cliente modificado correctamente ","Cliente"+getUnUsuario().getTipoUsuario());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        else {
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_WARN,"Error grave", "No se puede modificar cliente");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        setUnCliente(unCliente);
    }
    public void eliminarCliente (Cliente unCliente){

        if(getListaClientes()!=null){
            getClienteBean().eliminarCliente(unCliente);
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_INFO,"Cliente eliminado correctamente ","Cliente"+getUnUsuario().getTipoUsuario());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        else {
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_WARN,"Error grave", "No se puede eliminar clientes");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        setUnCliente(unCliente);
    }

    public ClienteBean getClienteBean() {
        return clienteBean;
    }

    public void setClienteBean(ClienteBean clienteBean) {
        this.clienteBean = clienteBean;
    }

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Usuario getUnUsuario() {
        return unUsuario;
    }

    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

   

    public List<String> getListadoPaises() {
        return listadoPaises;
    }

    public void setListadoPaises(List<String> listadoPaises) {
        this.listadoPaises = listadoPaises;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public List <String> getListadoTipos() {
        return listadoTipos;
    }

    public void setListadoTipos(List <String> listadoTipos) {
        this.listadoTipos = listadoTipos;
    }

    
    
    
    
}
