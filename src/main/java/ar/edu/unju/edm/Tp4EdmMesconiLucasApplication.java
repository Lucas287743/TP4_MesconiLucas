package ar.edu.unju.edm;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@SpringBootApplication
public class Tp4EdmMesconiLucasApplication implements CommandLineRunner{

	@Autowired
	@Qualifier("impclientemysql")
	IClienteService clienteService;
	
	@Autowired
	Cliente cliente;
	
	public static void main(String[] args) {
		SpringApplication.run(Tp4EdmMesconiLucasApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
		//inicializo un cliente para pruebas
			cliente.setNroDocumento(100001);
			cliente.setNombreApellido("user");
			cliente.setTipoDocumento("DNI");			
			cliente.setEmail("correo@gmail.com");
			cliente.setPassword("123456");
			cliente.setFechaNacimiento(LocalDate.now());
			cliente.setFechaUltimaCompra(LocalDate.now());
			clienteService.guardarCliente(cliente);
			
	}
}
