package aplicacion.dao.imp;

import aplicacion.dao.IProductoDAO;
import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacion de la interfaz del objeto producto
 * COMO LO VA A HACER
 * @author Sofia Delgado
 */

public class ProductoDaoImp implements IProductoDAO, Serializable{

    public ProductoDaoImp() {
    }

    

    @Override
    public Producto obtenerProducto(String nombreProducto) {
         Producto p = null;
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Producto.class);
        criteria.add(Restrictions.like("nombreProducto",nombreProducto));
        if(!criteria.list().isEmpty())
           p=(Producto)criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        return p;
    }

    @Override
    public void modificarProducto(Producto unProducto) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.update(unProducto);
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void eliminarProducto(Producto unProducto) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.delete(unProducto);
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public List<Producto> obtenerListaProductosCatalogo() {
        List <Producto> listado= new ArrayList();
        Session session =NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        Criteria criteria =session.createCriteria(Producto.class);
        criteria.add(Restrictions.like("estado",true));
        listado=criteria.list();
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
         return listado;
    }

    @Override
    public void agregarProducto(Producto unProducto) {
         Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.save(unProducto); //Para agregar un usuario
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }
    
}
