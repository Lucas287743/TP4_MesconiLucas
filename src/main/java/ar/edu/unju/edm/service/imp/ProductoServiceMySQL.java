package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IProductoDAO;
import ar.edu.unju.edm.service.IProductoService;

@Service
@Qualifier("impproductomysql")
public class ProductoServiceMySQL implements IProductoService{
	
	@Autowired
	Producto unProducto;
	
	@Autowired
	IProductoDAO productoDAO;

	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
	}

	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}
	
	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDAO.findAll();
	}
	
	@Override
	public Producto encontrarUnProducto(int cod) throws Exception {
		// TODO Auto-generated method stub
		return productoDAO.findById(cod).orElseThrow(()->new Exception("El producto NO existe"));
	}
	
	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void eliminarProducto(int cod) throws Exception{
		// TODO Auto-generated method stub
		Producto productoEliminar = productoDAO.findByCodProducto(cod).orElseThrow(()->new Exception("El producto no fue encontrado"));
		productoDAO.delete(productoEliminar);
	}

	@Override
	public Producto obtenerProductoCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}
}
