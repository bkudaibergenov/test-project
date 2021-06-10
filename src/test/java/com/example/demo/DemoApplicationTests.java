package com.example.demo;

import com.example.demo.api.ContactController;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.io.*;
import java.nio.file.Files;


@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
	File File = new File("/Users/derbisshyngys/IdeaProjects/test-project/src/main/resources/1.csv");

	@Autowired
	private ContactController contactController;

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private AddressRepository addressRepository;


	@Test
	void contextLoads() {
	}


	@Test
	void uploadCsvTest() throws Exception {
		FileItem fileItem = new DiskFileItem("file", Files.probeContentType(File.toPath()),
				false, File.getName(), (int) File.length(), File.getParentFile());

		try (InputStream in = new FileInputStream(File); OutputStream out = fileItem.getOutputStream()) {
			IOUtils.copy(in, out);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid file: " + e, e);
		}

		CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		Assert.assertEquals("true", contactController.uploadCSV(multipartFile).get(0));
	}

	@Test
	void uploadCsvFailTest() throws Exception {
		FileItem fileItem = new DiskFileItem("file", Files.probeContentType(File.toPath()),
				false, File.getName(), (int) File.length(), File.getParentFile());

		try (InputStream in = new FileInputStream(File); OutputStream out = fileItem.getOutputStream()) {
			IOUtils.copy(in, out);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid file: " + e, e);
		}

		CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		Assert.assertEquals("false", contactController.uploadCSV(multipartFile).get(0));
		contactRepository.deleteAll();
	}
}
