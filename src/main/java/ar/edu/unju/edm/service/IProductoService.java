package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Producto;

public interface IProductoService {
	public void guardarProducto(Producto unProducto);
	public Producto crearProducto();
	public List<Producto> obtenerTodosProductos();
	public Producto encontrarUnProducto(int cod) throws Exception;
	public void modificarProducto(Producto productoModificado);
	public void eliminarProducto(int cod) throws Exception;
	public Producto obtenerProductoCodigo(Integer codigo);
}
