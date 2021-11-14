/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_ex7;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

/**
 *
 * @author naratcha.s
 */
@Entity
public class FulltimeEmployee extends AbstractEmployee implements Serializable {

    private static final long serialVersionUID = 1L;
    
        private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    public static FulltimeEmployee findEmployeeById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee_EX7PU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee FTEmp = em.find(FulltimeEmployee.class, id);
        em.close();
        return FTEmp;
    }
    
    public static void updateEmployee(FulltimeEmployee FTEmp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee_EX7PU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee fromDb = em.find(FulltimeEmployee.class, FTEmp.getId());
        fromDb.setName(FTEmp.getName());
        fromDb.setSalary(FTEmp.getSalary());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
        
    public static void removeEmployee(FulltimeEmployee FTEmp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee_EX7PU");
        EntityManager em = emf.createEntityManager();
        FulltimeEmployee fromDb = em.find(FulltimeEmployee.class, FTEmp.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
                
    }

    
}
