package ar.com.educacionit.resources;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.educacionit.domain.Cupones;
import ar.com.educacionit.domain.EstadosOrdenes;
import ar.com.educacionit.domain.Ordenes;
import ar.com.educacionit.domain.Socios;
import ar.com.educacionit.dto.OrdenesDto;
import ar.com.educacionit.services.OrdenService;
import lombok.extern.slf4j.Slf4j;

/*
 * tiene los metodos CRUD
 * 
 * REST
 * 
 * GET: obtener un recurso / lista de recursos 
 * POST: crear un recurso
 * PUT: actualizar un recurso
 * PATCH: actualizar un dato del recurso
 * DELETE: eliminar un recurso
 * */
@RestController
@Slf4j
public class OrdenesResources {

	@Autowired
	private OrdenService service;
	
	//http://localhost:8080/orden/1 > path params
	//http://localhost:8080/orden?k=v&v1=v1.... > query params
	
	//jax-rs > jersey:Response | spring:ResponseEntity 
	
	/*crear los metodos que van a atender las peticiones*/
	@GetMapping(value="/orden/{id}",produces = "application/json")
	public ResponseEntity<Ordenes> get(
			@PathVariable(name="id",required = true) Long id
			) {
		
		Optional<Ordenes> orden =  this.service.getById(id);
		
		//200
		if(orden.isPresent()) {
			return ResponseEntity.ok(orden.get());//jackson
		}
		//404
		return ResponseEntity.notFound().build();
		//50..
	}
	
	//idempotentes
	@PostMapping(value = "/orden")
	public ResponseEntity<Ordenes> post(
			@Valid @RequestBody OrdenesDto orden) {
		
		Ordenes ordenDb; 
		if(orden.getId() == null) {
			ordenDb = Ordenes.builder()
				.montoTotal(orden.getMontoTotal())
				.socio(new Socios(orden.getSocioId()))
				.estado(new EstadosOrdenes(orden.getEstadoId()))
				.cupon(orden.getCuponId() != null ? new Cupones(orden.getCuponId()) : null)
				.fechaCreacion(new Date())
				.build();
			
			this.service.save(ordenDb);
			orden.setId(ordenDb.getId());
		}
		
		ordenDb = this.service.getById(orden.getId()).get();
			
		return ResponseEntity.ok(ordenDb);
	}
	
	@DeleteMapping(value = "/orden/{id}")
	public ResponseEntity<Void> delete(
			@PathVariable(value = "id",required = true) Long id ) {

		try {
			this.service.delete(id);
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
}
