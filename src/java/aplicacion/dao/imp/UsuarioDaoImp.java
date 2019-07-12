package aplicacion.dao.imp;
//importaciones
import aplicacion.dao.IUsuarioDAO;
import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Implementacion de la interfaz del objeto usuario
 * COMO LO VA A HACER
 * @author Sofia Delgado
 */

public class UsuarioDaoImp implements IUsuarioDAO , Serializable{
    
    @Override
    public Usuario validarUsuario(String nombreUsuario, String password) {
        Usuario u =null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.like("nombreUsuario", nombreUsuario));
        criteria.add(Restrictions.like("password",password));
        criteria.add(Restrictions.eq("estado",true));
        if(!criteria.list().isEmpty())
            u=(Usuario)criteria.list().get(0);
        session.getTransaction().commit();
        
        session.close();
        return u;
    }

    @Override
    public Usuario obtenerUsuario(String nombreUsuario) {
        Usuario u = null;
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.like("nombreUsuario",nombreUsuario));
        if(!criteria.list().isEmpty())
           u=(Usuario)criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        System.out.println(u);
        return u;
    }

    @Override
    public void modificarUsuario(Usuario unUsuario) {
         Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.update(unUsuario);
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public void eliminarUsuario(Usuario unUsuario) {
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        session.delete(unUsuario);
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }

    @Override
    public List<Usuario> obtenerListaUsuariosActivos() {
         List <Usuario> listado= new ArrayList();
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
        Criteria criteria =session.createCriteria(Usuario.class);
        criteria.add(Restrictions.like("estado",true));
        listado=criteria.list();
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
         return listado;
    }

    @Override
    public void agregarUsuario(Usuario unUsuario) {
        
        Session session=NewHibernateUtil.getSessionFactory().openSession();//se abre la sesion
        session.beginTransaction();//se comienza la transaccion en la sesion
       
        session.save(unUsuario); //Para agregar un usuario
        session.getTransaction().commit();
        session.close(); //se cierra la sesion
    }
}
