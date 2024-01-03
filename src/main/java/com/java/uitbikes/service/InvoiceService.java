package com.java.uitbikes.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.*;
import com.java.uitbikes.model.*;
import com.java.uitbikes.repository.*;

@Service
public class InvoiceService {
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	InvoiceDetailRepository detailRepository;
	
	@Autowired
	TypeRepository typeRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	//create invoice
	public Invoice createInvoice(Invoice invoice) {
		Date purchasedDate= Date.from(Instant.now());
		invoice.setDate(purchasedDate);
//		invoice.setCustomer(customerRepository.findById(invoice.getCustomer().getId()).get());
		
		return invoiceRepository.save(invoice);
	}
	
	//create invoice at once
	public Invoice PostInvoice(InvoicePostDto order) {
		Invoice invoice = new Invoice();
		Date purchasedDate= Date.from(Instant.now());
		invoice.setDate(purchasedDate);
		invoice.setCustomer(order.getCustomer());
		invoice.setTotal(order.getTotal());
		for (InvoiceItemDto i: order.getDetails()) {
			Product product = i.getProduct();
			product.setQuantity(product.getQuantity() - i.getQuantity());
			productRepository.save(product);
			invoice.addProduct(i.getProduct(), i.getQuantity());
		}
		
		Customer cus = order.getCustomer();
		cus.setBalance(cus.getBalance() - order.getTotal());
		customerRepository.save(cus);
		
		return invoiceRepository.save(invoice);
	}
	
	//add product
	public Invoice addProduct(Long id, Long pId, int quantity) {
		Invoice invoice = invoiceRepository.findById(id).get();
		Product product = productRepository.findById(pId).get();
		product.setQuantity(product.getQuantity() - quantity);
		productRepository.save(product);
		invoice.addProduct(product, quantity);
		
		return invoiceRepository.save(invoice);
	}
	
	//get all invoices
	public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
	}
	
	//get all invoices by customer_id and status
	public List<InvoiceDto> getAllInvoicesByCustomerIdAndStatus(Long customer_id, int status) {
		Customer customer = customerRepository.findById(customer_id).get();
		List<Invoice> invoices = customer.getInvoices();
		List<InvoiceDto> customerInvoices = new ArrayList<InvoiceDto>();
        for (Invoice i: invoices) {
        	InvoiceDto invoice = new InvoiceDto(i, customer_id);
        	customerInvoices.add(invoice);
        }
        
        List<InvoiceDto> result = new ArrayList<InvoiceDto>();
        if (status == -1)
        	result = customerInvoices;
        else {
			for (InvoiceDto i: customerInvoices)
				if (i.getStatus() == status)
					result.add(i);
		}
        
        return result;
	}
	
	//get number of completed orders
	public int getNumberOfCompletedOrders() {
		List<Invoice> invoices = invoiceRepository.findByStatus(2);
        return invoices.size();
	}
	
	//get number of cancelled orders
	public int getNumberOfCancelledOrders() {
		List<Invoice> invoices = invoiceRepository.findByStatus(3);
        return invoices.size();
	}
	
	//get total number of orders
	public int getNumberOfOrders() {
		List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.size();
	}
	
	//get total revenue
	public Long getRevenue() {
		List<Invoice> invoices = invoiceRepository.findByStatus(2);
        Long result = 0L;
        for (Invoice i: invoices) {
        	result += i.getTotal();
        }
        return result;
	}
	
	//get revenue within the last 12 months
	public RevenueMonths getRevenueByMonth() {
		List<Invoice> allInvoices = invoiceRepository.findByStatus(2);
		
		List<String> allDates = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		SimpleDateFormat month_date = new SimpleDateFormat("MMM-yyyy");
		
		List<Long> allRevenues = new ArrayList<Long>();
		
		for (int i = 12; i > 0; i--) {
		    cal.add(Calendar.MONTH, -1);
		    String month_name = month_date.format(cal.getTime());
		    allDates.add(month_name);
		    
		    Long revenue = 0L;
		    for (Invoice in: allInvoices) {
		    	if (month_date.format(new Date(in.getDate().getTime())).equals(month_name)) {
		    		revenue += in.getTotal();
		    	}
		    }
		    allRevenues.add(revenue);
		}
		
		return new RevenueMonths(allDates, allRevenues);
	}
	
	public Object getBestSellingProduct() {
		List<Invoice> invoices = invoiceRepository.findByStatus(2);
		List<Product> products = productRepository.findAll();
		int maxCount = 0;
		Product product = new Product();
		int sum = 0;
		
		for (Product p: products) {
			int count = 0;
			for (Invoice i: invoices) {
				List<InvoiceDetail> details = i.getDetails();
				for (InvoiceDetail d: details) {
					if(d.getProduct().getId() == p.getId()) {
						count += d.getQuantity();
					}
				}
			}
			if (count > maxCount) {
				maxCount = count;
				product = p;
			}
		}
		
		for (Invoice i: invoices) {
			List<InvoiceDetail> details = i.getDetails();
			for (InvoiceDetail d: details) {
				sum += d.getQuantity();
			}
		}
		
		Map<String, Object> result = new HashMap<>();
	    result.put("name", product.getName());
	    result.put("count", maxCount);
	    result.put("percent", maxCount * 100 / sum);

	    return result;
		
	}
	
	//get best selling type
	public Object getBestSellingType() {
		List<Invoice> invoices = invoiceRepository.findByStatus(2);
		List<Type> types = typeRepository.findAll();
		
		int maxCount = 0;
		Type type = new Type();
		int sum = 0;
		
		for (Invoice i: invoices) {
			List<InvoiceDetail> details = i.getDetails();
			for (InvoiceDetail d: details) {
				sum += d.getQuantity();
			}
		}
		
		for (Type t: types) {
			int count = 0;
			for (Invoice i: invoices) {
				List<InvoiceDetail> details = i.getDetails();
				for (InvoiceDetail d: details) {
					if(d.getProduct().getType().getId() == t.getId()) {
						count += d.getQuantity();
					}
				}
			}
			
			if (count > maxCount) {
				maxCount = count;
				type = t;
			}
		}
		
		Map<String, Object> result = new HashMap<>();
	    result.put("name", type.getName());
	    result.put("count", maxCount);
	    result.put("percent", maxCount * 100 / sum);

	    return result;
	}
	
	//get best selling brand
	public Object getBestSellingBrand() {
		List<Invoice> invoices = invoiceRepository.findByStatus(2);
		List<Brand> brands = brandRepository.findAll();
		
		int maxCount = 0;
		Brand brand = new Brand();
		int sum = 0;
		
		for (Invoice i: invoices) {
			List<InvoiceDetail> details = i.getDetails();
			for (InvoiceDetail d: details) {
				sum += d.getQuantity();
			}
		}
		
		for (Brand b: brands) {
			int count = 0;
			for (Invoice i: invoices) {
				List<InvoiceDetail> details = i.getDetails();
				for (InvoiceDetail d: details) {
					if(d.getProduct().getBrand().getId() == b.getId()) {
						count += d.getQuantity();
					}
				}
			}
			
			if (count > maxCount) {
				maxCount = count;
				brand = b;
			}
		}
		
		Map<String, Object> result = new HashMap<>();
	    result.put("name", brand.getName());
	    result.put("count", maxCount);
	    result.put("percent", maxCount * 100 / sum);

	    return result;
	}
	
	//count Customer purchased today
	public double getCustomerPurchasedTodayRates() {
		return invoiceRepository.countCustomerPurchasedToday() / customerRepository.count() * 100;
	}
	
	//update invoice status
	public Invoice updateInvoice(Long id, int status) {
		Optional<Invoice> request = invoiceRepository.findById(id);
		if(request.isPresent()) {
			Invoice req = request.get();
			req.setStatus(status);
			if (status == 3) {
				Customer cus = customerRepository.findById(req.getCustomer().getId()).get();
				cus.setBalance(cus.getBalance()+req.getTotal());
				customerRepository.save(cus);
				List<InvoiceDetail> details = req.getDetails();
				for (InvoiceDetail invoiceDetail : details) {
					Product product = invoiceDetail.getProduct();
					product.setQuantity(product.getQuantity() + invoiceDetail.getQuantity());
					productRepository.save(product);
				}
			}
			return invoiceRepository.save(req);
		}
		return request.get();
	}
}
