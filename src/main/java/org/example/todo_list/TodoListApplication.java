package org.example.todo_list;

import org.example.todo_list.domain.Task;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.hibernate.SessionFactory;


@SpringBootApplication
public class TodoListApplication {

	@Bean
	public SessionFactory sessionFactory(){
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(Task.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());

		return factory;
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

}
