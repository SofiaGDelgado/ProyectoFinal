package aplicacion.dao.imp;

import aplicacion.dao.IModopagoDAO;
import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.ModoPago;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacion de la interfaz del objeto Modopago
 *   COMO LO VA A HACER
 * @author Sofia Delgado
 */

public class ModopagoDaoImp implements IModopagoDAO, Serializable{

    public ModopagoDaoImp() {
    }

    @Override
    public void agregarModoPago(ModoPago unModo) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.save(unModo); 
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void eliminarModoPago(ModoPago unModo) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.delete(unModo); 
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void modificarModoPago(ModoPago unModo) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.save(unModo); 
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public ModoPago obtenerModoPago(int idModoPago) {
        ModoPago m = null;
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ModoPago.class);
        criteria.add(Restrictions.like("idmodoPago",idModoPago));
        if(!criteria.list().isEmpty())
           m=(ModoPago)criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        return m;
    }
    
}
