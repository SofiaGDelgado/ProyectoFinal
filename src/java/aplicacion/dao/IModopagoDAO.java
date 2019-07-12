package aplicacion.dao;

import aplicacion.modelo.dominio.ModoPago;

/**
 * Interfaz del objeto ModoPago
 *   QUE ES LO QUE SE VA A HACER
 * @author Sofia Delgado
 */
public interface IModopagoDAO {
    void agregarModoPago(ModoPago unModo);
     void eliminarModoPago(ModoPago unModo);
     void modificarModoPago(ModoPago unModo);
     ModoPago obtenerModoPago (int idModoPago);
    
    
}
