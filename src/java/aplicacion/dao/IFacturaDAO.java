package aplicacion.dao;

import aplicacion.modelo.dominio.Factura;

/**
 * Interfaz del objeto Factura
 *   QUE ES LO QUE SE VA A HACER
 * @author Sofia Delgado
 */
public interface IFacturaDAO {
     void agregarFactura(Factura unaFactura);
    void eliminarFactura (Factura unaFactura);
     void modificarFactura(Factura unaFactura);
     Factura obtenerFactura(int numFactura);
}
