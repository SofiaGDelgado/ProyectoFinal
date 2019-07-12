
package aplicacion.dao.imp;

import aplicacion.dao.ICategoriaDAO;
import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Categoria;
import aplicacion.modelo.dominio.Cliente;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacion de la interfaz del objeto categoria
 * COMO LO VA A HACER
 * @author Sofia Delgado
 */
 
public class CategoriaDaoImp implements ICategoriaDAO,Serializable {

    @Override
    public void agregarCategoria(Categoria unaCategoria) {
       Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.save(unaCategoria);
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void eliminarCategoria(Categoria unaCategoria) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.delete(unaCategoria);
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void modificarCategoria(Categoria unaCategoria) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.update(unaCategoria);
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public Categoria obtenerCategoria(Integer idcategoria) {
        Categoria c = null;
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Cliente.class);
        criteria.add(Restrictions.like("idcategoria",idcategoria));
        if(!criteria.list().isEmpty())
           c=(Categoria)criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        return c;
    }
    
}
