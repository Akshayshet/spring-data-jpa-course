package com.example.demo;

import com.example.demo.models.Student;
import com.example.demo.repository.StudeentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudeentRepository studeentRepository) {
        return args -> {
            Student akshay = new Student("Akshay", "Kumar", "ak@gmail.com", 25);
            Student akshay1 = new Student("Akshay", "Kumar", "ak1@gmail.com", 26);
            Student abhi = new Student("Abhi", "Kumar", "ab@gmail.com", 25);
//          studeentRepository.save(akshay);
            studeentRepository.saveAll(List.of(akshay, abhi, akshay1));
            System.out.println(studeentRepository.count());
            studeentRepository.findById(2L).ifPresentOrElse(student -> {
                System.out.println(student);
            }, () -> {
                System.out.println("Studet with id not foubd");
            });
            studeentRepository.findById(3L).ifPresentOrElse(student -> {
                System.out.println(student);
            }, () -> {
                System.out.println("Not presnt with " + 3);
            });
            List<Student> students = studeentRepository.findAll();
            students.forEach(System.out::println);

            System.out.println("++++++++++++++++++++++++++");

            System.out.println(studeentRepository.findStudentByEmail("ak@gmail.com"));

            System.out.println(studeentRepository.findStudentByEmailAndFirstName("ak@gmail.com", "Akshay"));

            studeentRepository.findStudentsByFirstNameEqualsAndAgeEquals("Akshay", 25)
                    .forEach(System.out::println);

            studeentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThan("Akshay", 20)
                    .forEach(System.out::println);

            studeentRepository.deleteById(1L);
            System.out.println(studeentRepository.count());

            studeentRepository.deleteById(3L);

            studeentRepository.deleteAll();
        };
    }
}

