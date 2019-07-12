package aplicacion.form.bean;

import aplicacion.bean.ProductoBean;
import aplicacion.modelo.dominio.Categoria;
import aplicacion.modelo.dominio.Producto;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author BRENDA MENDEZ
 */
@ManagedBean
@ViewScoped
public class ProductoFormBean implements Serializable{

    @ManagedProperty(value = "#{productoBean}")
    private ProductoBean productoBean;
    private Producto unProducto;
    
    private List<Categoria> categoria;
    private transient UploadedFile archivo=null;
    private List <Producto> listaProductos;
    private List <Producto> listaComprados;
    private Producto agregado;

    public ProductoFormBean(Producto unProducto, ProductoBean productoBean, List<Categoria> categoria, List<Producto> listaProductos) {
        this.unProducto = unProducto;
        this.productoBean = productoBean;
        this.categoria = categoria;
        this.listaProductos = listaProductos;
    }
    /**
     * Creates a new instance of ProductoFormBean    /**
     * Creates a new instance of ProductoFormBean
     */
    
    public ProductoFormBean() {

    }
    public void init(){
       listaProductos= new ArrayList<>();
       unProducto=new Producto();
       listaComprados=new ArrayList<>();
    }
    
     /**public Producto obtenerProducto(String nombreProducto) {
        String resultado=null;
        Producto unProducto = productoBean.obtenerProducto(nombreProducto);
        if(unProducto == null){
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Credenciales Invalidas","Credenciales Invalidas");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
        }
        else
        {
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Producto Valido", "ProductoValido");
            if (unProducto.getCodProducto().equals("producto")){
                    resultado="producto?faces-redirect=true"; 
            }
            else
                {
                resultado="productos";
                }  
        }
       return unProducto;
    }
     * @param unProducto
*/
    public void modificarProducto(Producto unProducto) {
         
        if(getArchivo()!=null){
            byte[] contents=getArchivo().getContents();
            getUnProducto().setFoto(contents);
        }
        else{
            getUnProducto().setFoto(null);
        }
        try{
            getProductoBean().modificarProducto(unProducto);
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_INFO,"Producto modificado correctamente ","Producto"+getUnProducto().getDescripcion());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }catch (Exception e){
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_WARN,"Error grave", "No se puede modificar producto");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        setUnProducto(unProducto);      
    }
    public void eliminarProducto (Producto unProducto){
      
        if(getArchivo()!=null){
            byte[] contents=getArchivo().getContents();
            getUnProducto().setFoto(contents);
        }
        else{
            getUnProducto().setFoto(null);
        }
        try{
            getProductoBean().eliminarProducto(unProducto);
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_INFO,"Producto eliminado correctamente ","Producto"+getUnProducto().getDescripcion());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }catch (Exception e){
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_WARN,"Error grave", "No se puede eliminar producto");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        setUnProducto(unProducto);   
    }

    public void agregarProducto (Producto unProducto){
        
        if(getArchivo()!=null){
            byte[] contents=getArchivo().getContents();
            getUnProducto().setFoto(contents);
        }
        else{
            getUnProducto().setFoto(null);
        }
        try{
            getProductoBean().agregarProducto(getUnProducto());
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_INFO,"Producto agregado correctamente ","Producto"+getUnProducto().getDescripcion());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }catch (Exception e){
            FacesMessage facesMessage=new FacesMessage
                (FacesMessage.SEVERITY_WARN,"Error grave", "No se puede agregar producto");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        setUnProducto(new Producto());
    }
    public void agregarCarrito(){
        listaComprados.add(getAgregado());
        setUnProducto(new Producto());
    }

    public StreamedContent  getFotoProducto() throws IOException{
        FacesContext context=FacesContext.getCurrentInstance();
        if(context.getCurrentPhaseId()== PhaseId.RENDER_RESPONSE){
            return new DefaultStreamedContent();
        } 
        else{
            String descrip = context.getExternalContext().getRequestParameterMap().get("descripcion");
            Producto producto = getProductoBean().obtenerProducto(descrip);
            if(producto.getFoto()==null){
                return null;
            }
            else {
                return new DefaultStreamedContent(new ByteArrayInputStream(producto.getFoto()));
            }
        } 
    }

   /**
     * @return the unProducto
     */
    public Producto getUnProducto() {
        return unProducto;
    }

    /**
     * @param unProducto the unProducto to set
     */
    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
    }

    /**
     * @return the productoBean
     */
    public ProductoBean getProductoBean() {
        return productoBean;
    }

    /**
     * @param productoBean the productoBean to set
     */
    public void setProductoBean(ProductoBean productoBean) {
        this.productoBean = productoBean;
    }

    /**
     * @return the categoria
     */
    public List<Categoria> getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the archivo
     */
    public UploadedFile getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the listaProductos
     */
    public List <Producto> getListaProductos() {
        return listaProductos;
    }

    /**
     * @param listaProductos the listaProductos to set
     */
    public void setListaProductos(List <Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List <Producto> getListaComprados() {
        return listaComprados;
    }

    public void setListaComprados(List <Producto> listaComprados) {
        this.listaComprados = listaComprados;
    }

    public Producto getAgregado() {
        return agregado;
    }

    public void setAgregado(Producto agregado) {
        this.agregado = agregado;
    }
    
}