package com.school.administration.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.school.administration.app.shared.dto.InvoiceDto;

public interface InvoiceService extends UserDetailsService {
	InvoiceDto createInvoice(String AudienceId, String ProductId, InvoiceDto invoice);
}
