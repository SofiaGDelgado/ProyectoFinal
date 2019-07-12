package aplicacion.bean;

import aplicacion.dao.IDetalleDAO;
import aplicacion.dao.imp.DetalleDaoImp;
import aplicacion.modelo.dominio.Detalle;
import aplicacion.modelo.dominio.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author BRENDA
 */
@ManagedBean
@ViewScoped
public class DetalleBean implements Serializable{
    private IDetalleDAO detalleDao;
    private Detalle unDetalle ;
    private List <Detalle> listadoDetalles ;
    private Producto producto;
    private List <Detalle> listadoDetalleCompra;
    
    /**
     * Creates a new instance of DetalleBean
     */
    public DetalleBean() {
        detalleDao=new DetalleDaoImp();
        listadoDetalles= new ArrayList<>();
        listadoDetalleCompra=new ArrayList<>();
    }
   
    
    
    public void agregarDetalle (Detalle unDetalle){
        detalleDao.agregarDetalle(unDetalle);
    }
    
     public void eliminarDetalle(Detalle unDetalle){
        detalleDao.eliminarDetalle(unDetalle);
    }
    public void modificarDetalle(Detalle unDetalle){
        detalleDao.modificarDetalle(unDetalle);
    }
    public Detalle obtenerDetalles(Detalle unDetalle){
        return detalleDao.obtenerDetalles(unDetalle);
    }

    
    /**
     * @return the detalleDao
     */
    public IDetalleDAO getDetalleDao() {
        return detalleDao;
    }

    /**
     * @param detalleDao the detalleDao to set
     */
    public void setDetalleDao(IDetalleDAO detalleDao) {
        this.detalleDao = detalleDao;
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
     * @param producto the producto to set
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
    
}
