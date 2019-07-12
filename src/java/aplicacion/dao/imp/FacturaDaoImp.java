package aplicacion.dao.imp;

import aplicacion.dao.IFacturaDAO;
import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Factura;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacion de la interfaz del objeto Factura 
 *   COMO LO VA A HACER
 * @author Sofia Delgado
 */

public class FacturaDaoImp implements IFacturaDAO, Serializable{
    
    public FacturaDaoImp() {
    }

    @Override
    public void agregarFactura(Factura unaFactura) {
       Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.save(unaFactura); 
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void eliminarFactura(Factura unaFactura) {
         Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.delete(unaFactura); 
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void modificarFactura(Factura unaFactura) {
         Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.update(unaFactura); 
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public Factura obtenerFactura(int numFactura) {
        Factura f = null;
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Factura.class);
        criteria.add(Restrictions.like("numFactura",numFactura));
        if(!criteria.list().isEmpty())
           f=(Factura)criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        return f;
    }
    
}
