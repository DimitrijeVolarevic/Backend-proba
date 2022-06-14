package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Grupa;
import rva.jpa.Student;

public interface GrupaRepository extends JpaRepository <Grupa, Integer> {
	
	Collection<Grupa> findByOznakaContainingIgnoreCase(String oznaka);

}
