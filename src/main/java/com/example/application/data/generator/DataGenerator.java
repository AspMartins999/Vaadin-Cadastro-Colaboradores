package com.example.application.data.generator;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Departamento;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.DepartamentoRepository;
import com.example.application.data.repository.StatusRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(ContactRepository contactRepository,
    		DepartamentoRepository departamentoRepository,
            StatusRepository statusRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (contactRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");
            /*ExampleDataGenerator<Company> companyGenerator = new ExampleDataGenerator<>(Company.class,
                    LocalDateTime.now());
            companyGenerator.setData(Company::setName, DataType.OCCUPATION);
            List<Company> companies = companyRepository.saveAll(companyGenerator.create(20, seed));*/
            /*List<Company> companies = companyRepository
                    .saveAll(Stream.of("Tecnologia", "Financeiro", "Marketing", "Vendas", "Operação")
                            .map(Company::).collect(Collectors.toList()));*/
            List<Departamento> departamentos = departamentoRepository
                    .saveAll(Stream.of("Tecnologia", "Financeiro", "Marketing", "Vendas", "Operação")
                            .map(Departamento::new).collect(Collectors.toList()));

            List<Status> statuses = statusRepository
                    .saveAll(Stream.of("Ativo", "Demitido", "Licença", "Em atendimento", "Treinamento")
                            .map(Status::new).collect(Collectors.toList()));

            logger.info("... generating 50 Contact entities...");
            ExampleDataGenerator<Contact> contactGenerator = new ExampleDataGenerator<>(Contact.class,
                    LocalDateTime.now());
            contactGenerator.setData(Contact::setNome, DataType.FIRST_NAME);
            contactGenerator.setData(Contact::setSobrenome, DataType.LAST_NAME);
            contactGenerator.setData(Contact::setEmail, DataType.EMAIL);

            Random r = new Random(seed);
            List<Contact> contacts = contactGenerator.create(50, seed).stream().map(contact -> {
                contact.setDepartamento(departamentos.get(r.nextInt(departamentos.size())));
                contact.setStatus(statuses.get(r.nextInt(statuses.size())));
                return contact;
            }).collect(Collectors.toList());

            contactRepository.saveAll(contacts);

            logger.info("Generated demo data");
        };
    }

}
