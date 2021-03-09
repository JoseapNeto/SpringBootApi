package com.course.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.springboot.entities.Payment;
import com.course.springboot.repositories.PaymentRepository;


@Service
public class PaymentService {

	
	@Autowired
	private PaymentRepository repository;
	
	public List<Payment> findAll(){
		return repository.findAll();
	}
	
	public Payment findById(Integer id) {
		Optional<Payment> obj = repository.findById(id);
		return obj.get();
	}
	
	
	
}
