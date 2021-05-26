package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;

@Controller
public class ProductoController {
	private static final Log LOGGER = LogFactory.getLog(ProductoController.class);
	
	@Autowired
	@Qualifier("impproductomysql")
	IProductoService productoService;
	
	@GetMapping("/producto/mostrar")
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", productoService.crearProducto());
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}
	
	@GetMapping("/producto/editar/{codProducto}")
	public String editarProducto(Model model, @PathVariable(name="codProducto") int cod) throws Exception {
		try {
			Producto productoEncontrado = productoService.encontrarUnProducto(cod);
			model.addAttribute("unProducto", productoEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoService.crearProducto());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}

	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("unProducto", nuevoProducto);
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			return("producto");
		}
		else {
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			productoService.guardarProducto(nuevoProducto);
			LOGGER.info("Tamaño del Listado: "+ productoService.obtenerTodosProductos().size());
			return "redirect:/producto/mostrar";
		}
	}
	
	@PostMapping("/producto/modificar")
	public String modificarProducto(@ModelAttribute("unProducto") Producto productoModificado, Model model) {
		try {
			productoService.modificarProducto(productoModificado);
			model.addAttribute("unProducto", new Producto());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// pasar las excepciones al html
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoModificado);			
			model.addAttribute("productos", productoService.obtenerTodosProductos());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("productos", productoService.obtenerTodosProductos());
		return("producto");
	}
	
	@GetMapping("/producto/cancelar")
	public String cancelar() {
		return "redirect:/producto/mostrar";
	}
	
	@GetMapping("/producto/eliminarProducto/{id}")
	public String eliminarProducto(Model model, @PathVariable(name="id") int id) {
		try {
			productoService.eliminarProducto(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/producto/mostrar";
	}
}
