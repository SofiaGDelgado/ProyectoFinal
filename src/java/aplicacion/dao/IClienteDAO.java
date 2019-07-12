package aplicacion.dao;

import aplicacion.modelo.dominio.Cliente;
import java.util.List;

/**
* Interfaz de la clase Cliente
* LO QUE VA A HACER
 * @author Sofia Delgado
 */
public interface IClienteDAO {
    Cliente obtenerCliente (int dni);
   void modificarCliente (Cliente unCliente);
    void eliminarCliente (Cliente unCliente);
    List <Cliente> obtenerListaClientesActivos();
    void agregarCliente (Cliente unCliente);
}
