package com.idex.cajero.modelo.dao;

import java.util.List;

import com.idex.cajero.modelo.beans.Cuenta;

/**
 * Interfaz de métodos para la clase Cuenta
 * 
 * @author pablofernandezsato
 *
 */
public interface IntCuentaDao {
	
	
	/**
	 * Método que devuelve un objeto List de tipo Cuenta con todos los objetos Cuenta que hay en la BBDD
	 * 
	 * @return List<Cuenta> o null
	 */
	List<Cuenta> findAll();
	
	/**
	 * Método que sirve para buscar un objeto Cuenta concreto a partir de su campo id. 
	 * Recimbe un parámetro de entrada que es un dato de tipo integer que representa la 
	 * idCuenta del objeto Cuenta a buscar. Si lo encuentra devuelve el objeto, si no lo
	 * encuentra, devuelve null
	 * @param idCuenta
	 * @return cuenta
	 */
	Cuenta findById(int idCuenta);
	/**
	 * Método que sirve para insertar/actualizar un nuevo registro de tipo Cuenta en la bbdd. Recibe
	 * por parámtro un objeto de tipo Cuenta que será insertado/actualizado en la bbdd, si todo va
	 * correcto devolverá un 1, en caso de que no haya podido insertar/actualizar el objeto en la 
	 * bbdd. devolverá un 0
	 * @param cuenta
	 * @return integer
	 */
	int insertOne(Cuenta cuenta);
	/**
	 * Método que sirve para borrar un registro de la bbdd. Recibe por parámetro un integer
	 * que representa el campo idCuenta del objeto cuenta a borrar. Primero comprueba si existe
	 * ese registro en la bbdd, en caso afirmativo lo elimina y devuelve un 1, en caso de que 
	 * no encuentre ningún registro con esa id, devuelve 0
	 * @param idCuenta
	 * @return integer
	 */
	int deleteOne(int idCuenta);

}
