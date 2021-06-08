package com.example.demo;

import com.example.demo.entity.Contact;
import com.example.demo.entity.dto.ContactDto;
import com.example.demo.entity.dto.FileDto;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.impl.ContactServiceImpl;
import com.example.demo.service.impl.UploadException;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.doReturn;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@MockBean
	private ContactServiceImpl contactServiceImpl;

	@MockBean
	private ContactRepository contactRepository;

	@MockBean
	private AddressRepository addressRepository;


	@Test
	void contextLoads() {
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.parse("128KB"));
		factory.setMaxRequestSize(DataSize.parse("128KB"));
		return factory.createMultipartConfig();
	}

//	@Test
//	void uploadCsvTest() throws IOException {
//		assert false;
//		Assert.(, contactServiceImpl.uploadCSV((MultipartFile) new File("/Users/derbisshyngys/IdeaProjects/test-project/src/main/java/com/example/demo/1.csv")));
//	}

	@Test
	void insertContactFailTest() {
		ContactDto contactDto = ContactDto.builder().phoneNumber("11111111111111").build();
		FileDto fileDto = FileDto.builder().phoneNumber(contactDto.getPhoneNumber()).build();
		Contact searchingContact = Contact.builder().phoneNumber(contactDto.getPhoneNumber()).build();

		Mockito.doReturn(contactDto).when(contactServiceImpl).findByPhoneNumber(searchingContact);

		Assert.assertEquals(false, contactServiceImpl.insertContact(fileDto));
	}

	@Test
	void insertContactTest() {
		FileDto fileDto = FileDto.builder().phoneNumber("22222222222222").build();
		Contact searchingContact = Contact.builder().phoneNumber(fileDto.getPhoneNumber()).build();

		Mockito.doReturn(null).when(contactServiceImpl).findByPhoneNumber(searchingContact);
		Mockito.doReturn(searchingContact).when(contactRepository).save(searchingContact);

		Assert.assertEquals(true, contactServiceImpl.insertContact(fileDto));

//		contactRepository.delete(searchingContact);
	}

	@Test
	void insertFromCsvTest() {
		FileDto contact = FileDto.builder().phoneNumber("333333333333333").build();
		FileDto contact2 = FileDto.builder().phoneNumber("4444444444444").build();

		List<FileDto> fileDtoList = new ArrayList<>();
		fileDtoList.add(contact);
		fileDtoList.add(contact2);

		Assert.assertEquals("Все загружено", contactServiceImpl.insertFromCsv(fileDtoList));

		fileDtoList.forEach(fileDto -> {
			Contact newContact = Contact.builder().phoneNumber(fileDto.getPhoneNumber()).build();
			contactRepository.delete(newContact);
		});
	}

//	@Test(expected = UploadException.class)
//	void insertFromCsvFailTest() throws UploadException {
//		FileDto contact = FileDto.builder().phoneNumber("5555555555555").build();
//		FileDto contact2 = FileDto.builder().phoneNumber("6666666666666").build();
//
//		List<FileDto> fileDtoList = new ArrayList<>();
//		fileDtoList.add(contact);
//		fileDtoList.add(contact2);
//
//		fileDtoList.forEach(fileDto -> {
//			Contact newContact = Contact.builder().phoneNumber(fileDto.getPhoneNumber()).build();
//			contactRepository.save(newContact);
//		});
//
//		Assert.assertSame(Exception.class, contactServiceImpl.insertFromCsv(fileDtoList));
//
//		fileDtoList.forEach(fileDto -> {
//			Contact newContact = Contact.builder().phoneNumber(fileDto.getPhoneNumber()).build();
//			contactRepository.delete(newContact);
//		});
//	}

}
