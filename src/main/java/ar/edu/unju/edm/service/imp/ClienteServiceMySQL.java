package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.repository.IClienteDAO;
import ar.edu.unju.edm.service.IClienteService;

@Service
@Qualifier("impclientemysql")
public class ClienteServiceMySQL implements IClienteService{

	@Autowired
	Cliente unCliente;
	
	@Autowired
	IClienteDAO clienteDAO;
	
	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		
		clienteDAO.save(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
		
	}

	@Override
	public Cliente encontrarUnCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		return clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("El cliente NO existe"));
	}

	@Override
	public void modificarCliente(Cliente unClienteModificado) throws Exception {
		// TODO Auto-generated method stub
		//se busca el Cliente que se quiere modificar en la BD (por algún campo que no se permita modificar)
		//Vean que he utilizado el DNI pero sin embargo en mi app si puedo cambiar el DNI, entonces la sentencia siguiente no sería correcta
		//tal vez sería mejor buscar por ID, que es un campo que no se modifica (findById)
		//sin embargo aquí lo hice para que vean los posibles errores		
		Cliente clienteAModificar = clienteDAO.findByNroDocumento(unClienteModificado.getNroDocumento()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));

		//vean que si utilizan directamente save, lo que se hace es AGREGAR otro cliente a la BD, y lo que nosotros queremos hacer es SUSTITUIR
		// con lo que que clienteDAO.save(unClienteModificado); lo voy a dejar para el final del método
		
		//voy a realizar el intercambio entre el cliente que viene y el cliente que ya está en la BD
		//y guardar el cliente que está en la BD, pero lo voy a hacer en otro método
		cambiarCliente(unClienteModificado, clienteAModificar);
		
		//vuelve el cliente en la BD ya modificado y se guarda
		clienteDAO.save(clienteAModificar);
	}
	
	private void cambiarCliente(Cliente desde, Cliente hacia) {
		//observen que vamos a pasar todos los atributos del cliente que viene, hacia el cliente que ya está en la BD
		hacia.setNroDocumento(desde.getNroDocumento());
		hacia.setTipoDocumento(desde.getTipoDocumento());
		hacia.setNombreApellido(desde.getNombreApellido());
		hacia.setEmail(desde.getEmail());
		hacia.setPassword(desde.getPassword());
		hacia.setFechaNacimiento(desde.getFechaNacimiento());
		hacia.setCodigoAreaTelefono(desde.getCodigoAreaTelefono());
		hacia.setNroTelefono(desde.getNroTelefono());
		hacia.setFechaUltimaCompra(desde.getFechaUltimaCompra());
		//observen que NO se ha cambiado el id, ya que ese atributo no debería permitirse cambiar
	}

	@Override
	public void eliminarCliente(int dni) throws Exception{
		// TODO Auto-generated method stub
		Cliente clienteEliminar = clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("El cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}

	@Override
	public Cliente encontrarUnClienteId(int id) throws Exception {
		// TODO Auto-generated method stub
		return clienteDAO.findById(id).orElseThrow(()->new Exception("El cliente NO existe"));
	}
}
