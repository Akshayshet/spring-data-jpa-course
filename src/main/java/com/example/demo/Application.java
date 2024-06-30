package com.example.demo;

import com.example.demo.models.Student;
import com.example.demo.repository.StudeentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudeentRepository studeentRepository) {
        return args -> {
           generateRandomStudents(studeentRepository);
//           sorting(studeentRepository);
            PageRequest pageRequest = PageRequest.of(0,5, Sort.by("firstName").ascending());
            Page<Student> page = studeentRepository.findAll(pageRequest);
            System.out.println(page);
        };
    }

    private void sorting(StudeentRepository studeentRepository){
        Sort sort =Sort.by("firstName").ascending().and(Sort.by("age").descending());
        studeentRepository.findAll(sort).forEach(student -> System.out.println(student.getFirstName() +" "+ student.getAge()));
    }

    private void generateRandomStudents(StudeentRepository studeentRepository){
        Faker faker = new Faker();
        for (int i = 0; i <20 ; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            Student student = new Student(firstName, lastName, email, faker.number().numberBetween(17,55));
            studeentRepository.save(student);
        }
    }
}

