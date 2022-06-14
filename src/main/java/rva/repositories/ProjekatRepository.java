package rva.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Projekat;

public interface ProjekatRepository extends JpaRepository<Projekat, Integer> {

	Collection<Projekat> findByNazivContainingIgnoreCase(String naziv);

	List<Projekat> findAll();
	
}
