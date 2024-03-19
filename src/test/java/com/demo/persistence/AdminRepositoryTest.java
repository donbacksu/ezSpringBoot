package com.demo.persistence;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.domain.Admin;

@SpringBootTest
public class AdminRepositoryTest {

	@Autowired
    private AdminRepository adminRepo;
	
	@Disabled
	@Test
	public void testInsertAdmin () {
		Admin account = Admin.builder()
				.id("admin")
				.pwd("admin")
				.name("안성생님")
				.phone("010-1234-1234")
				.build();
		adminRepo.save(account);
		
		Admin account2 = Admin.builder()
				.id("sslee")
				.pwd("1234")
				.name("이순신")
				.phone("010-1234-1234")
				.build();
		adminRepo.save(account2);
	}
}
