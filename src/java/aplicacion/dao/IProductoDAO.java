package aplicacion.dao;

import aplicacion.modelo.dominio.Producto;
import java.util.List;

/**
 * Interfaz DAO de Producto
 * LO QUE VA A HACER
 * @author Sofia Delgado
 */
public interface IProductoDAO {
    Producto obtenerProducto (String nombreProducto);
    void modificarProducto (Producto unProducto);
    void eliminarProducto (Producto unProducto);
    List <Producto> obtenerListaProductosCatalogo();
    void agregarProducto (Producto unProducto);
}
