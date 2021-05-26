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
		return clienteDAO.findById(dni).orElseThrow(()->new Exception("El cliente NO existe"));
	}

	@Override
	public void modificarCliente(Cliente unClienteModificado) throws Exception {
		// TODO Auto-generated method stub
		Cliente clienteAModificar = clienteDAO.findByNroDocumento(unClienteModificado.getNroDocumento()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		cambiarCliente(unClienteModificado, clienteAModificar);
		clienteDAO.save(clienteAModificar);
	}
	
	private void cambiarCliente(Cliente desde, Cliente hacia) {
		hacia.setNroDocumento(desde.getNroDocumento());
		hacia.setTipoDocumento(desde.getTipoDocumento());
		hacia.setNombreApellido(desde.getNombreApellido());
		hacia.setEmail(desde.getEmail());
		hacia.setPassword(desde.getPassword());
		hacia.setFechaNacimiento(desde.getFechaNacimiento());
		hacia.setCodigoAreaTelefono(desde.getCodigoAreaTelefono());
		hacia.setNroTelefono(desde.getNroTelefono());
		hacia.setFechaUltimaCompra(desde.getFechaUltimaCompra());
	}

	@Override
	public void eliminarCliente(int dni) throws Exception{
		// TODO Auto-generated method stub
		Cliente clienteEliminar = clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("El cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}

	@Override
	public Cliente encontrarUnClienteId(int dni) throws Exception {
		// TODO Auto-generated method stub
		return clienteDAO.findById(dni).orElseThrow(()->new Exception("El cliente NO existe"));
	}
}
