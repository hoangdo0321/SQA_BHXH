//package com.example.socialinsurance;
//
//import com.example.socialinsurance.entity.Address;
//import com.example.socialinsurance.entity.InsuranceDetails;
//import com.example.socialinsurance.entity.Job;
//import com.example.socialinsurance.entity.User;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.*;
//
//@SpringBootTest
//public class TableRelationTests {
//    @Test
//    public void whenPersistIDS_thenIdAssigned() throws ParseException {
////        Account account = Account.newInstance("12345", "James Bone");
////        CreditCard creditCard = CreditCard.newInstance("123-456", LocalDate.now(), account);
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        Set<Job> jobSet = new HashSet<>();
//        Job job = Job.builder().name("Quan ly").workplace("Tech").salary(Double.valueOf(500000000)).isWorking(true).build();
//        Job job2 = Job.builder().name("Nhan vien").workplace("Product").salary(Double.valueOf(200000000)).isWorking(false).build();
//        jobSet.add(job);
//        jobSet.add(job2);
//        var user = User.builder()
//                .sinCode("012984320")
//                .idCard("891274981")
//                .fullName("Do Minh Hoang")
//                .tel("0916478372")
//                .dob(formatter.parse("03-03-2001"))
//                .email("hoang@gmail.com")
//                .address(
//                        Address.builder().city("HaNoi").district("Thanh Xuan").ward("Ha Dinh").build()
//                )
//                .detailAddress("to 2").job(jobSet).build();
//        InsuranceDetails insuranceDetails = InsuranceDetails.builder()
//                .startDate(formatter.parse("20-03-2023"))
//                .expireDate(formatter.parse("10-04-2023"))
//                .insuranceCost(Double.valueOf(150000))
//                .user(user)
//                .job(job)
//                .build();
//        InsuranceDetails insuranceDetails1 = InsuranceDetails.builder()
//                .startDate(formatter.parse("04-01-2023"))
//                .expireDate(formatter.parse("25-02-2023"))
//                .insuranceCost(Double.valueOf(350000))
//                .user(user)
//                .job(job)
//                .build();
//        InsuranceDetails insuranceDetails2 = InsuranceDetails.builder()
//                .startDate(formatter.parse("24-08-2023"))
//                .expireDate(formatter.parse("04-10-2023"))
//                .insuranceCost(Double.valueOf(950000))
//                .user(user)
//                .job(job)
//                .build();
//        em.persist(user);
//        User u = em.find(User.class, 1);
//        System.out.println(u.getFullName());
//        System.out.println(u.getEmail());
//        List<Job> jobList = new ArrayList<>(u.getJob());
//        for(Job j : jobList){
//            System.out.println(j.getId()+ " " + j.getName() +" "+ j.getWorkplace()+ " " + j.getSalary() + " " );
//        }
//
//        System.out.println();
//        System.out.println();
//
//
//    }
//}
