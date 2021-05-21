package ar.edu.unju.edm.service;

import java.util.List;

@Service
import ar.edu.unju.edm.model.Venta;

public interface IVentaService {
	public void guardarVenta(Venta unaVenta);
	public Venta crearVenta();
	public List<Venta> obtenerTodasVentas();
	public Venta encontrarUnaVenta(int dni) throws Exception;
}
