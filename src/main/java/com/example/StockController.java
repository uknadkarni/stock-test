package com.example;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

	@Autowired
	private StockRepository sr;
	
	private Logger logger = Logger.getLogger(StockController.class);
	
	public StockController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(value="/stock")
	public Stock stock(@RequestParam(value="ticker") String ticker, 
			@RequestParam(value="price") Double price){
		Stock s = new Stock(ticker, price);
		Stock savedStock = sr.save(s);
		if(savedStock == null){
			logger.error("Unable to save stock: " + s);
		}
		else{
			logger.info("Saved stock: " + savedStock);
		}
		return savedStock;
	}

	@GetMapping(value="/stock")
	public Stock stock(@RequestParam(value="ticker") String ticker){
		logger.info("Searching for: " + ticker);
		Stock s = sr.findOne(ticker);
		if(s == null){
			logger.error("Unable to find ticker: " + ticker);
		}
		else{
			logger.info("Found stock: " + s);
		}
		return s;
	}
}
