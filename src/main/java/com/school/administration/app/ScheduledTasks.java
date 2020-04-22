package com.school.administration.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.school.administration.app.io.repositories.InvoiceRepository;
import com.school.administration.app.io.repositories.ProductsRepository;
import com.school.administration.app.shared.dto.InvoiceDto;
import com.school.administration.app.shared.dto.ProductsDto;
import com.school.administration.app.ui.io.entity.InvoiceEntity;
import com.school.administration.app.ui.io.entity.ProductsEntity;

@Component
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    @Autowired
    ProductsRepository productRepository;
    
    @Autowired
    InvoiceRepository invoiceRepository;
    
    @Scheduled(cron = "0 0 6 * * *")
	public ProductsDto updateExpiredProduct() {
    	ProductsDto returnValue = new ProductsDto();
    	
    	final String DATE_FORMAT = "dd-MM-yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String date = formatter.format(currentTime.getTime());
		
		List<ProductsEntity> productEntity = new ArrayList<ProductsEntity>();
		productEntity = productRepository.findProductByExpiredDate(date);
		
		for (ProductsEntity product : productEntity)
		{
			if (product.getIsExpired() == false) 
			{
				product.setIsExpired(true);
				ProductsEntity updateProduct = productRepository.save(product);
				
				BeanUtils.copyProperties(updateProduct, returnValue);
			}
		}
		
    	logger.info(dateFormat.format(new Date()));
    	
		return returnValue;
	}
    
    
    @Scheduled(cron = "*/1 * * * * *")
    public InvoiceDto updateInvoice() {
    	InvoiceDto returnValue = new InvoiceDto();
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.HOUR_OF_DAY, -1);
    	Date date = calendar.getTime();
    	SimpleDateFormat sdfStopTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    	String newStopTime = sdfStopTime.format(date);
    	
		List<InvoiceEntity> invoiceEntity = new ArrayList<InvoiceEntity>();
		invoiceEntity = invoiceRepository.findInvoiceByCreatedDate(newStopTime);
    	
		for (InvoiceEntity invoice : invoiceEntity) 
		{
			if (invoice.getIsPayment() == false && invoice.getIsExpired() == false) 
			{
				invoice.setIsExpired(true);
				
				InvoiceEntity updateInvoice = invoiceRepository.save(invoice);
				
				BeanUtils.copyProperties(updateInvoice,  returnValue);
			}
		}
		
    	return returnValue;
    	
    }
}
