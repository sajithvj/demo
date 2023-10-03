package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.KafkaConsumerService;
import com.example.demo.service.KafkaService;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;
    @Autowired
    CompaniesRepository companiesRepository;

    @Autowired
    TripsRepository tripsRepository;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    MagazinesRepository magazinesRepository;


    @Override
    public void showAllTestItems() {
        testRepository.findAll().forEach(test -> System.out.println(test.toString()));

    }

    @Override
    public void save(Test test) {

        testRepository.save(test);
    }

    @Override
    public void showAllCompaniesItem() {
        companiesRepository.findAll().forEach(companies -> System.out.println(companies.toString()));

    }

    @Override
    public List<Companies> showCompaniesByName(String name) {
        return companiesRepository.getAllCompaniesByName(name);
    }

    @Override
    public List<Trips> getTripsByBikeid(Integer bikeid) {
        return tripsRepository.findByBikeId(bikeid);
    }

    @Override
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public List<Magazines> getAllMagazines() {
        return magazinesRepository.findAll();
    }
}
