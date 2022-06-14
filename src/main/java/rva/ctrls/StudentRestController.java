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
import rva.jpa.Student;
import rva.repositories.GrupaRepository;
import rva.repositories.StudentRepository;
@CrossOrigin
@RestController
@Api(tags = {"Student CRUD operacije"})
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private GrupaRepository grupaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("student")
	@ApiOperation(value="Vraca kolekciju svih studenata iz baze podataka")
	public Collection<Student> getStudent()
	{
		
		return studentRepository.findAll();
	}
	
	@GetMapping("student/{id}")
	@ApiOperation(value="Vraca studenta na osnovu prosledjenog ID-ija")
	public Student getStudent(@PathVariable("id") Integer id) {
		return studentRepository.getOne(id);
		
	}
	@GetMapping("studentGrupa/{grupa}")
	@ApiOperation(value="Vraca studenta na osnovu prosledjene grupa")
	public Collection<Student> GetStudentByGrupa(@PathVariable("grupa") Integer grupa){
		Grupa g=grupaRepository.getOne(grupa);
	return studentRepository.findByGrupa(g);
	}
	
	@GetMapping("studentID/{id}")
	@ApiOperation(value="Vraca kolekciju studenta na osnovu id")
	public Collection<Student> GetStudentID(@PathVariable("id") Integer id){
		Grupa g=grupaRepository.getOne(id);
	return studentRepository.findByGrupa(g);
	}
	@PostMapping("student")
	@ApiOperation(value="Dodaje novog studenta u bazu podataka")
	public ResponseEntity<Student> insertStudent(@RequestBody Student student){
		
		if(!studentRepository.existsById(student.getId()))
		{
			
			studentRepository.save(student);
			return new ResponseEntity<Student>(HttpStatus.OK);
		}
		return new ResponseEntity<Student>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("student")
	@ApiOperation(value="Updatuje studenta u bazu podataka")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student){
		if (!studentRepository.existsById(student.getId()))
		{
			return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
			
		}
		studentRepository.save(student);
		return new ResponseEntity<Student>(HttpStatus.OK);
	}
	
	@DeleteMapping("student/{id}")
	@ApiOperation(value="Brise studenta iz baze podataka na osnovu ID-ija")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id){
		if (studentRepository.existsById(id))
		{
			studentRepository.deleteById(id);
			
			if (id == -100)
			{
				jdbcTemplate.execute("INSERT INTO \"student\" (\"id\", \"ime\", \"prezime\", \"broj_indeksa\","
						+ "\"grupa\", \"projekat\") values (-100,'test','test',1,1,1)");
			}
			return new ResponseEntity<Student>(HttpStatus.OK);
		}
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}
}
