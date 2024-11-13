package com.spring.jdbc.ecom;

import com.spring.jdbc.ecom.dao.CategoryDao;
import com.spring.jdbc.ecom.dao.ProductDao;
import com.spring.jdbc.ecom.dao.impl.CategoryDaoImpl;
import com.spring.jdbc.ecom.model.Category;
import com.spring.jdbc.ecom.model.Product;
import com.spring.jdbc.ecom.model.ProductWithCategory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcEcomApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJdbcEcomApplication.class, args);

		ProductDao productDao = context.getBean(ProductDao.class);
		CategoryDao categoryDao = context.getBean(CategoryDao.class);
		//Product product = productDao.create(product1);
//		List<Product> products = productDao.getAll();
//		products.forEach(items -> System.out.println(items.getTitle()));
//		Product product = productDao.get(101);
//		System.out.println("product " + product);

//		Category category = new Category();
//		category.setId(1001);
//		category.setDescription("Mobile phones USA");
//		category.setTitle("Mobiles");
//		categoryDao.create(category);
//
//		Category category1 = new Category();
//		category1.setId(1002);
//		category1.setDescription("Mobile phones Korea");
//		category1.setTitle("Mobiles");
//		categoryDao.create(category1);
//
//		Product product1 = new Product();
//		product1.setId(101);
//		product1.setTitle("PHONE");
//		product1.setDescription("Tim cook");
//		product1.setPrice(10000);
//		product1.setCatId(category.getId());
//		productDao.create(product1);
//
//		Product product2 = new Product();
//		product2.setId(102);
//		product2.setTitle("PHONE");
//		product2.setDescription("Doesn't Exist");
//		product2.setPrice(10001);
//		product2.setCatId(category1.getId());
//		productDao.create(product2);


		productDao.getAllWithCategory().forEach(System.out::println);

	}

}
