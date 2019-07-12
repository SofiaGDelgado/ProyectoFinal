package aplicacion.dao;

import aplicacion.modelo.dominio.Detalle;

/**
 *
 * @author Sofia Delgado
 */
public interface IDetalleDAO {
    void agregarDetalle (Detalle unDetalle);
    void eliminarDetalle (Detalle unDetalle);
    void modificarDetalle (Detalle unDetalle);
    Detalle obtenerDetalles (Detalle unDetalle);
    
}
