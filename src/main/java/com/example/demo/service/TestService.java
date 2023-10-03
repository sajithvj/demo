package com.example.demo.service;


import com.example.demo.model.*;

import java.util.List;

public interface TestService {
 void showAllTestItems();
 void save(Test test);

 void showAllCompaniesItem();

 List<Companies> showCompaniesByName(String name);

 List<Trips> getTripsByBikeid(Integer bikeid);

 List<Books>getAllBooks();

 List<Magazines>getAllMagazines();
}
