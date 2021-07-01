package com.example.demo;

import com.example.demo.api.ContactController;
import com.example.demo.initializer.Postgres;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.*;
import java.nio.file.Files;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Testcontainers
@Sql("src/main/resources/database/data.sql")
@TestExecutionListeners
@SpringBootTest
@ContextConfiguration(initializers = {
		Postgres.Initializer.class
})
@ExtendWith(SpringExtension.class)
@Transactional
class DemoApplicationTests {

	@Autowired
	private ContactController contactController;

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private AddressRepository addressRepository;

	File File = new File("src/main/resources/1.csv");

	FileItem fileItem = new DiskFileItem("file", Files.probeContentType(File.toPath()),
			false, File.getName(), (int) File.length(), File.getParentFile());

	@Container
	GenericContainer container = new GenericContainer(DockerImageName.parse("DemoApplicationTest.java"))
			.withClasspathResourceMapping("1.csv", "src/main/resources/1.csv", BindMode.READ_ONLY);


	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", Postgres.psqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", Postgres.psqlContainer::getUsername);
		registry.add("spring.datasource.password", Postgres.psqlContainer::getPassword);
	}

	DemoApplicationTests() throws IOException {
	}


	@Test
	void contextLoads() {
	}


	@Test
	void uploadCsvTest() throws Exception {
		try (InputStream in = new FileInputStream(File); OutputStream out = fileItem.getOutputStream()) {
			IOUtils.copy(in, out);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid file: " + e, e);
		}

		CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		Assert.hasText("true", contactController.uploadCSV(multipartFile).get(0));
	}

	@Test
	void uploadCsvFailTest() throws Exception {
		try (InputStream in = new FileInputStream(File); OutputStream out = fileItem.getOutputStream()) {
			IOUtils.copy(in, out);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid file: " + e, e);
		}

		CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		Assert.hasText("false", contactController.uploadCSV(multipartFile).get(0));
		contactRepository.deleteAll();
	}

//	@Container
//	private PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:42.2.20"))
//			.withDatabaseName("foo")
//			.withUsername("foo")
//			.withPassword("secret");
//
//	@Test
//	void test() {
//		assert (postgresqlContainer.isRunning());
//	}
}
