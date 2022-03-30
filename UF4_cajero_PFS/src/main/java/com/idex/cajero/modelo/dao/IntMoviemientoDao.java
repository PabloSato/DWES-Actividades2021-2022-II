package com.idex.cajero.modelo.dao;

import java.util.List;

import com.idex.cajero.modelo.beans.Movimiento;

/**
 * Interfaz de métodos para la clase Movimiento
 * @author pablofernandezsato
 *
 */
public interface IntMoviemientoDao {

	/**
	 * Método que devuelve un objeto List de tipo Movimiento con todos los objetos 
	 * Movimiento que hay en la BBDD
	 * 
	 * @return List<Movimiento> o null
	 */
	List<Movimiento> findAll();
	
	/**
	 * Método que devuelve todos los objetos de tipo Movimiento que estén relacionados
	 * con la cuenta cuyo id se pasa por parámetro. Si no encuentra ningún movimiento
	 * devuelve null;
	 * @param idCuenta
	 * @return List<Movmineto>
	 */
	List<Movimiento> findByCuenta(int idCuenta);
	/**
	 * Método que sirve para buscar un objeto Movimiento concreto a partir de su campo id. 
	 * Recimbe un parámetro de entrada que es un dato de tipo integer que representa el 
	 * idMovimiento del objeto Movimiento a buscar. Si lo encuentra devuelve el objeto, si no lo
	 * encuentra, devuelve null
	 * @param idMovimiento
	 * @return Movimiento
	 */
	Movimiento findById(int idMovmiento);
	/**
	 * Método que sirve para insertar/actualizar un nuevo registro de tipo Movimiento en la bbdd. Recibe
	 * por parámtro un objeto de tipo Movmiento que será insertado/actualizado en la bbdd, si todo va
	 * correcto devolverá un 1, en caso de que no haya podido insertar/actualizar el objeto en la 
	 * bbdd. devolverá un 0
	 * @param movimiento
	 * @return integer
	 */
	int insertOne(Movimiento movimiento);
	/**
	 * Método que sirve para borrar un registro de la bbdd. Recibe por parámetro un integer
	 * que representa el campo idMovimiento del objeto Movimiento a borrar. Primero comprueba si existe
	 * ese registro en la bbdd, en caso afirmativo lo elimina y devuelve un 1, en caso de que 
	 * no encuentre ningún registro con esa id, devuelve 0
	 * @param idMovimiento
	 * @return integer
	 */
	int deleteOne(int idMovimiento);
}
