/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao;

import aplicacion.modelo.dominio.Categoria;

/**
 * Interfaz del objeto Categoria 
 *   QUE ES LO QUE SE VA A HACER
 * @author Sofia Delgado
 */
public interface ICategoriaDAO {
    void agregarCategoria(Categoria unaCategoria);
    void eliminarCategoria(Categoria unaCategoria);
    void modificarCategoria(Categoria unaCategoria);
    Categoria obtenerCategoria(Integer idcategoria);
    
}
