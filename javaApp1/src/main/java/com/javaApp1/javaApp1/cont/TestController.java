package com.javaApp1.javaApp1.cont;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaApp1.javaApp1.TestEntity;
import com.javaApp1.javaApp1.TestRepo;
import com.javaApp1.javaApp1.TestService;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.websocket.server.PathParam;
@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

	@Autowired
	private TestService testservice;
	//private TestRepo repo;
	
	@PostMapping("/create")
    public ResponseEntity<TestEntity> createTest(@RequestBody  TestEntity testEntity) {
        // Save the entity
     //   TestEntity savedEntity = repo.save(testEntity);
            
		try{  if(testEntity.getAmmount()==0.0 || testEntity.getName()==null) {return new ResponseEntity<>(HttpStatus.NO_CONTENT );  }
    	     
    	   TestEntity savedEntity=testservice.create(testEntity);
		    
    	   return new ResponseEntity<>(savedEntity,HttpStatus.CREATED);
       }
       catch(Exception e) {
    	   return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
//	@GetMapping("/t1")
//	public String createTest1() {
//	  //testservice.create(testEntity);
//		System.out.print("api");
//	    return "data saved";
//	}
	
  
	@GetMapping("/showall")
	public ResponseEntity <List<TestEntity>> showall(){
		List<TestEntity> testentity=testservice.showall();
		if(testentity==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(testentity, HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<TestEntity> updateMethod(@PathVariable("id") Long id,@RequestBody TestEntity testentity){
		if(id>=0) {
				System.out.println(" id is not given ");
		}
		Optional<TestEntity> data=testservice.findById(id);
		try {
			if(data.isPresent()) {
			TestEntity obj=data.get();
			 obj.setAmmount(testentity.getAmmount());
		     obj.setName(testentity.getName());
			testservice.create(obj);
			return new ResponseEntity("update ",HttpStatus.CREATED);
			 }
		}catch(Exception e) {
		return new ResponseEntity("internal serval error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		}
	}
