package ar.com.educacionit.services;

import java.util.Optional;

import ar.com.educacionit.domain.Ordenes;

public interface OrdenService {
	public Optional<Ordenes> getById(Long id);

	public void save(Ordenes ordenDb);

	public void delete(Long id);
}
