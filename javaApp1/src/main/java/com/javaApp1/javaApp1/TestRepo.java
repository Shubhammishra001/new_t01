package com.javaApp1.javaApp1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends JpaRepository<TestEntity, Long>{

	
	
}
