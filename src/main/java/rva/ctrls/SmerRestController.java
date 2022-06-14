package rva.ctrls;

import java.util.Collection;

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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Grupa;
import rva.jpa.Smer;
import rva.repositories.SmerRepository;
@CrossOrigin
@RestController
@Api(tags = {"Smer CRUD operacije"})
public class SmerRestController {
	@Autowired
	private SmerRepository smerRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("smer")
	@ApiOperation(value="Vraca kolekciju svih smerova iz baze podataka")
	public Collection<Smer> getsmer() {
		return smerRepository.findAll();
	}
	
	@GetMapping("smer/{id}") 
	@ApiOperation(value="Vraca smer na osnovu prosledjenog ID-ija")
	public Smer getSmer(@PathVariable("id") Integer id) {
		return smerRepository.getOne(id);
				
	}
	@GetMapping("smerOznaka/{oznaka}")
	@ApiOperation(value="Vraca smer na osnovu prosledjene oznake")
	public Collection<Smer> getsmerOznakaBySmer(@PathVariable("oznaka")String oznaka){
		return smerRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	@PostMapping("smer")
	@ApiOperation(value="Dodaje novi smer u bazu podataka")
	public ResponseEntity<Smer> insertSmer(@RequestBody Smer smer){
		if (!smerRepository.existsById(smer.getId()))
		{
			smerRepository.save(smer);
			return new ResponseEntity<Smer>(HttpStatus.OK);
		}
		return new ResponseEntity<Smer>(HttpStatus.CONFLICT);

	}
	@PutMapping("smer")
	@ApiOperation(value="Updatuje smer u bazu podataka")
	public ResponseEntity<Smer> updateGrupa(@RequestBody Smer smer){
		if (!smerRepository.existsById(smer.getId()))
		{
			return new ResponseEntity<Smer>(HttpStatus.NO_CONTENT);
			
		}
		smerRepository.save(smer);
		return new ResponseEntity<Smer>(HttpStatus.OK);
	}
	@DeleteMapping("smer/{id}")
	@ApiOperation(value="Brise smer iz baze podataka na osnovu ID-ija")
	public ResponseEntity<Smer> deleteSmer(@PathVariable("id") Integer id){
		if (smerRepository.existsById(id))
		{
			smerRepository.deleteById(id);
			
			if (id == -100)
			{
				jdbcTemplate.execute("insert into smer values (-100,'test','test')");
			}
			return new ResponseEntity<Smer>(HttpStatus.OK);
		}
		return new ResponseEntity<Smer>(HttpStatus.NO_CONTENT);
	}
}