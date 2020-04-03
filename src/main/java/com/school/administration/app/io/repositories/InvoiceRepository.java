package com.school.administration.app.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.InvoiceEntity;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Long> {

	List<InvoiceEntity> findInvoiceByCreatedDate(String newStopTime);
	
}
