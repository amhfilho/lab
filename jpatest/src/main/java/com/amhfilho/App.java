package com.amhfilho;

import com.amhfilho.jpatest.Department;
import com.amhfilho.jpatest.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        System.out.println("Connecting to DB...");
        EntityManagerFactory emf  = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        System.out.println("Connected.");
        System.out.println("Running query...");
        Employee employee1 = new Employee(2L, "Miguel", new Department(1L, "IT"));
        em.getTransaction().begin();
        em.persist(employee1);
        em.getTransaction().commit();
        List<Employee> employeeList = em.createQuery("select e from Employee e").getResultList();
        employeeList.forEach(e -> System.out.println(e.toString()));
        System.out.println("Closing connection");
        em.close();
        emf.close();
    }
}
