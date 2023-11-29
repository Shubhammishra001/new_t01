package com.javaApp1.javaApp1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@Autowired
	private TestRepo testrepo;
	
	public TestEntity create(TestEntity testentity) {
		
		
		return testrepo.save(testentity); 
	}

	public List<TestEntity> showall() {
		// TODO Auto-generated method stub
		return testrepo.findAll();
	}

	public Optional<TestEntity> findById(Long id) {
		// TODO Auto-generated method stub
		Optional<TestEntity> testentity= testrepo.findById(id);
	       return testentity;
	}

}
