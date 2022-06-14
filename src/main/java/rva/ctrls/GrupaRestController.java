package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Grupa;
import rva.repositories.GrupaRepository;
import rva.repositories.StudentRepository;
@CrossOrigin
@RestController
@Api(tags = {"Grupa CRUD operacije"})
public class GrupaRestController {

		@Autowired
		private GrupaRepository grupaRepository;
		
		@Autowired
		private StudentRepository studentRepository;
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@GetMapping("grupa")
		@ApiOperation(value="Vraca kolekciju svih grupa iz baze podataka")
		public Collection<Grupa> getGrupe() {
			return grupaRepository.findAll();
		}
		
		@GetMapping("grupa/{id}")
		@ApiOperation(value="Vraca grupu na osnovu prosledjenog ID-ija")
		public Grupa getGrupa(@PathVariable("id") Integer id) {
			return grupaRepository.getOne(id);
					
		}
		@GetMapping("grupaOznaka/{oznaka}")
		@ApiOperation(value="Vraca grupu na osnovu prosledjene oznake")
		public Collection<Grupa> getGrupaByOznaka(@PathVariable("oznaka")String oznaka){
			return grupaRepository.findByOznakaContainingIgnoreCase(oznaka);
			
		}
		@PostMapping("grupa")
		@ApiOperation(value="Dodaje novu grupu u bazu podataka")
		public ResponseEntity<Grupa> insertGrupa(@RequestBody Grupa grupa){
			if (!grupaRepository.existsById(grupa.getId()))
			{
				grupaRepository.save(grupa);
				return new ResponseEntity<Grupa>(HttpStatus.OK);
			}
			return new ResponseEntity<Grupa>(HttpStatus.CONFLICT);
			
		}
		@PutMapping("grupa")
		@ApiOperation(value="Updatuje grupu u bazu podataka")
		public ResponseEntity<Grupa> updateGrupa(@RequestBody Grupa grupa){
			if (!grupaRepository.existsById(grupa.getId()))
			{
				return new ResponseEntity<Grupa>(HttpStatus.NO_CONTENT);
				
			}
			grupaRepository.save(grupa);
			return new ResponseEntity<Grupa>(HttpStatus.OK);
			
		}
		
		@Transactional
		@DeleteMapping("grupa/{id}")
		@ApiOperation(value="Brise grupu iz baze podataka na osnovu ID-ija")
		public ResponseEntity<Grupa> deleteGrupa(@PathVariable("id") Integer id){
			if (!grupaRepository.existsById(id))
				return new ResponseEntity<Grupa>(HttpStatus.NO_CONTENT);
			
			jdbcTemplate.execute("DELETE FROM student WHERE grupa=" + id);
			
				grupaRepository.deleteById(id); 
				
				if (id == -100)
				{
					jdbcTemplate.execute("insert into \"grupa\"(\"id\",\"oznaka\",\"smer\") "
							+ "values (-101,'test','test')");
				}
				return new ResponseEntity<Grupa>(HttpStatus.OK);
			
			
		}
}
