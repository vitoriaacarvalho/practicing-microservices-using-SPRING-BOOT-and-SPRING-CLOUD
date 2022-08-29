package com.devsuperior.hrpayment.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrpayment.entities.Payment;
import com.devsuperior.hrpayment.entities.Worker;
import com.devsuperior.hrpayment.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	@Autowired 
	private WorkerFeignClient workerFeignClient;
		
	public Payment getPayment(long workerId, int days) {
		Worker worker=workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
	
	
}
