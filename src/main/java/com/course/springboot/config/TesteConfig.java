package com.course.springboot.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.course.springboot.entities.Category;
import com.course.springboot.entities.OrdemItem;
import com.course.springboot.entities.Order;
import com.course.springboot.entities.Payment;
import com.course.springboot.entities.Product;
import com.course.springboot.entities.User;
import com.course.springboot.enums.OrderStatus;
import com.course.springboot.repositories.CategoryRepository;
import com.course.springboot.repositories.OrdemItemRepository;
import com.course.springboot.repositories.OrderRepository;
import com.course.springboot.repositories.ProductRepository;
import com.course.springboot.repositories.UserRepository;
//import com.sun.tools.javac.code.Attribute.Array;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrdemItemRepository ordemItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers"); 
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100, ""); 
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		userRepository.saveAll(Arrays.asList(u1,u2));
		
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID,u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WATTING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WATTING_PAYMENT, u1); 
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
	
		
		OrdemItem oi1 = new OrdemItem(o1, p1, 2, p1.getPrice());
		OrdemItem oi2 = new OrdemItem(o1, p3, 1, p3.getPrice());
		OrdemItem oi3 = new OrdemItem(o2, p3, 2, p3.getPrice());
		OrdemItem oi4 = new OrdemItem(o3, p5, 2, p5.getPrice());
		ordemItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
	
		
		Payment pay1 = new Payment(null ,"2019-06-20T19:53:07Z",o1);
		o1.setPayment(pay1);
	
		orderRepository.save(o1);
	}
	
	
	//comentario commit
	
	
}
