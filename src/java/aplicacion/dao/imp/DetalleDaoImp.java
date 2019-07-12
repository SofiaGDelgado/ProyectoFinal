package aplicacion.dao.imp;

import aplicacion.dao.IDetalleDAO;
import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Detalle;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacion de la interfaz del objeto detalle
 * COMO LO VA A HACER
 * @author Sofia Delgado
 */

public class DetalleDaoImp implements IDetalleDAO, Serializable{

    public DetalleDaoImp() {
    }

    @Override
    public void agregarDetalle(Detalle unDetalle) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.save(unDetalle); 
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void eliminarDetalle(Detalle unDetalle) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.delete(unDetalle); 
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void modificarDetalle(Detalle unDetalle) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.update(unDetalle);
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }
    
    @Override
    public Detalle obtenerDetalles(Detalle unDetalle) {
        Detalle d= null;
        int cod= unDetalle.getIddetalle();
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Detalle.class);
        criteria.add(Restrictions.like("iddetalle",cod));
        if(!criteria.list().isEmpty())
           d=(Detalle)criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        return d;
    }
    
}
