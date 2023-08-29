package com.app.service;

import static org.apache.commons.io.FileUtils.readFileToByteArray;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.entity.Customer;
import com.app.entity.Vendor;
import com.app.exceptions.CustomerNotFoundException;
import com.app.exceptions.ServiceNotFoundException;
import com.app.exceptions.VendorNotFoundException;
import com.app.repository.CustomerRepositoryIF;
import com.app.repository.VendorRepositoryIF;

@Service
@Transactional
public class ImageHandlingImpl implements ImageHandlingIF {

	// injecting value of the field read from applicatoin.properties file
	@Value("${file.upload.location}") // field level DI , <property name n value />
	// ${file.upload.location} SpEL :Spring expr language
	private String uploadFolder;

	@Autowired
	private CustomerRepositoryIF customerRepo;
	@Autowired
	VendorRepositoryIF vendorRepo;

	@PostConstruct
	public void init() throws IOException {
		// chk if folder exists --yes --continue
		File folder = new File(uploadFolder);
		if (folder.exists()) {
			System.out.println("folder exists alrdy !");
		} else {
			// no --create a folder
			folder.mkdir();
			System.out.println("created a folder !");
		}
	}

	@Override
	public ApiResponse uploadImageCustomer(Long customerId, MultipartFile image) throws IOException {
		// get emp from emp id
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		// store the image on server side folder
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), image.getBytes());
		// set image path
		customer.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		return new ApiResponse("Image file uploaded successfully for Customer id " + customerId);
	}

	@Override
	public byte[] serveImageCustomer(Long customerId) throws IOException {
		// get emp by id
		Customer emp = customerRepo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		String path = emp.getImagePath();
		if (path != null) {
			// path ---> File --> byte[]
			return readFileToByteArray(new File(path));
			// OR from DB : return emp.getImage();
		} else
			throw new RuntimeException("Image not yet assigned !!!!");

	}
	
	//for vendor
	
	@Override
	public ApiResponse uploadImageVendor(Long vendorId, MultipartFile image) throws IOException {
		// get emp from emp id
		Vendor vendor = vendorRepo.findById(vendorId)
				.orElseThrow(() -> new VendorNotFoundException("Invalid Vendor ID!!!!"));
		// emp found --> PERSISTENT
		// store the image on server side folder
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), image.getBytes());
		// set image path
		vendor.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		return new ApiResponse("Image file uploaded successfully for vendor id for path " + vendorId+path);
	}

	@Override
	public byte[] serveImageVendor(Long vendorId) throws IOException {
		// get emp by id
		Customer emp = customerRepo.findById(vendorId).orElseThrow(() -> new CustomerNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		String path = emp.getImagePath();
		if (path != null) {
			// path ---> File --> byte[]
			return readFileToByteArray(new File(path));
			// OR from DB : return emp.getImage();
		} else
			throw new RuntimeException("Image not yet assigned !!!!");

	}

	//for service
	
	@Override
	public ApiResponse uploadImageService(Long serviceId, MultipartFile image) throws IOException {
		// get emp from emp id
		Customer customer = customerRepo.findById(serviceId)
				.orElseThrow(() -> new CustomerNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		// store the image on server side folder
		String path = uploadFolder.concat(image.getOriginalFilename());
		System.out.println(path);
		// Use FileUtils method : writeByte[] --> File
		writeByteArrayToFile(new File(path), image.getBytes());
		// set image path
		customer.setImagePath(path);
		// OR to store the img directly in DB as a BLOB
		// emp.setImage(image.getBytes());
		return new ApiResponse("Image file uploaded successfully for service id " + serviceId);
	}

	@Override
	public byte[] serveImageService(Long serviceId) throws IOException {
		// get emp by id
		Customer emp = customerRepo.findById(serviceId).orElseThrow(() -> new ServiceNotFoundException("Invalid emp ID!!!!"));
		// emp found --> PERSISTENT
		String path = emp.getImagePath();
		if (path != null) {
			// path ---> File --> byte[]
			return readFileToByteArray(new File(path));
			// OR from DB : return emp.getImage();
		} else
			throw new RuntimeException("Image not yet assigned !!!!");

	}

	
	
}
