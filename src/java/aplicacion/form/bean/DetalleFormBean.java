/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.form.bean;

import aplicacion.bean.DetalleBean;
import aplicacion.modelo.dominio.Detalle;
import aplicacion.modelo.dominio.Factura;
import aplicacion.modelo.dominio.ModoPago;
import aplicacion.modelo.dominio.Producto;
import aplicacion.modelo.dominio.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author BRENDA
 */
@ManagedBean
@ViewScoped
public class DetalleFormBean implements Serializable{
    @ManagedProperty(value = "#{detalleBean}")
    private DetalleBean detalleBean;
    private Detalle unDetalle ;
    private List <Detalle> listadoDetalles ;
    private Producto producto;
    private List <Detalle> listadoDetalleCompra;
    private Factura unaFactura ;
    private ModoPago modoPago;
    private Usuario usuario;
    private double resultado;
    /**
     * Creates a new instance of DetalleFormBean
     */
public DetalleFormBean() {
        unDetalle = new Detalle();
        listadoDetalles=  new ArrayList<>();
        listadoDetalleCompra = new ArrayList<>();
        usuario= new Usuario();
        modoPago = new ModoPago();
        producto= new Producto();
        unaFactura = new Factura();
    }

    public DetalleFormBean(DetalleBean detalleBean, Detalle unDetalle, List<Detalle> listadoDetalles, Producto producto, List<Detalle> listadoDetalleCompra, Factura unaFactura, ModoPago modoPago, Usuario usuario) {
        this.detalleBean = detalleBean;
        this.unDetalle = unDetalle;
        this.listadoDetalles = listadoDetalles;
        this.producto = producto;
        this.listadoDetalleCompra = listadoDetalleCompra;
        this.unaFactura = unaFactura;
        this.modoPago = modoPago;
        this.usuario = usuario;
    }
    
    public void confirmarCompra (){
        Producto unProducto=new Producto();
        unProducto=getProducto();
        getUnDetalle().setProductos(unProducto);
        getUnDetalle().setCantidad(unDetalle.getCantidad());
        double setResultado= unDetalle.getCantidad()*producto.getPrecio();
        String resultadoString=String.valueOf(resultado);
        getUnDetalle().setPrecioProdCant(resultadoString);
        getListadoDetalleCompra().add(unDetalle);
    }
    
      public void agregarDetalle (Detalle unDetalle){
         getUnDetalle().setIddetalle(00);
        if(getListadoDetalles()!=null){
            getDetalleBean().agregarDetalle(getUnDetalle());
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO,"Se agrego correctamente ","Detalle"+getUnDetalle().getIddetalle());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        else {
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error", "No se puede agregar");
           FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        setUnDetalle(new Detalle());
    }
      
    public String confirmarPago(){
        String resultado = null;
        getModoPago().setInteres(5.0);
        getUnaFactura().setModopago(getModoPago());
        getUnaFactura().setFecha(new Date());
        getUnaFactura().setUsuarios((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValido"));
        resultado="pagoCompra";
        return resultado;
    }
    
    public String cargarFactura() {
       String pagina=null;
       Date fDate = new Date();
      modoPago.setInteres((double)(modoPago.getCuotas())+3.0);
      // unaFactura.setFecha(Date.from(Instant.now()));
        unaFactura.setUsuarios((Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariovalidado"));
         modoPago.setIdmodoPago((int) (Math.random()*1000000));
            unaFactura.setModopago(modoPago);
            unaFactura.setFecha(fDate);
            unaFactura.setNumFactura((int) (Math.random()*1000000));
        
         for (int i=0;i< listadoDetalleCompra.size();i++)
        {
           
          listadoDetalleCompra.get(i).setFactura(unaFactura);
        
        }
            for (int i=0;i< listadoDetalleCompra.size();i++)
        {
           
         agregarDetalle(listadoDetalleCompra.get(i));
        
        }
            pagina="listaCompras?faces-redirect=true";
            return pagina;
    }
    
    public Detalle obtenerDetalles(Detalle unDetalle){
        String resultado=null;
        unDetalle = detalleBean.obtenerDetalles(unDetalle);
        if(unDetalle == null){
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_ERROR," Invalido"," Invalido");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
        }
        else
        {
            FacesMessage facesmessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Valido"," Valido");
            if (unDetalle.getProductos().equals("unDetalle")){
                    resultado="paginaDetalles?faces-redirect=true"; 
            }
            else
                {
                resultado="detalles";
                }  
        }
        return unDetalle;
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
     * @return the listadoDetalles
     */
    public List <Detalle> getListadoDetalles() {
        return listadoDetalles;
    }

    /**
     * @param listadoDetalles the listadoDetalles to set
     */
    public void setListadoDetalles(List <Detalle> listadoDetalles) {
        this.listadoDetalles = listadoDetalles;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the unProducto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the listadoDetalleCompra
     */
    public List <Detalle> getListadoDetalleCompra() {
        return listadoDetalleCompra;
    }

    /**
     * @param listadoDetalleCompra the listadoDetalleCompra to set
     */
    public void setListadoDetalleCompra(List <Detalle> listadoDetalleCompra) {
        this.listadoDetalleCompra = listadoDetalleCompra;
    }

    /**
     * @return the unaFactura
     */
    public Factura getUnaFactura() {
        return unaFactura;
    }

    /**
     * @param unaFactura the unaFactura to set
     */
    public void setUnaFactura(Factura unaFactura) {
        this.unaFactura = unaFactura;
    }

    /**
     * @return the modoPago
     */
    public ModoPago getModoPago() {
        return modoPago;
    }

    /**
     * @param modoPago the modoPago to set
     */
    public void setModoPago(ModoPago modoPago) {
        this.modoPago = modoPago;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;}

    /**
     * @return the resultado
     */
    public double getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(double resultado) {
        this.resultado = resultado;

    }
}
