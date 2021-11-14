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
public class ParttimeEmployee extends AbstractEmployee implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int hourworks;

    public int getHourworks() {
        return hourworks;
    }

    public void setHourworks(int hourworks) {
        this.hourworks = hourworks;
    }
    
        public static ParttimeEmployee findEmployeeById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee_EX7PU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee PTEmp = em.find(ParttimeEmployee.class, id);
        em.close();
        return PTEmp;
    }
    
    public static void updateEmployee(ParttimeEmployee PTEmp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee_EX7PU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee fromDb = em.find(ParttimeEmployee.class, PTEmp.getId());
        fromDb.setName(PTEmp.getName());
        fromDb.setHourworks(PTEmp.getHourworks());
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
        
    public static void removeEmployee(ParttimeEmployee PTEmp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee_EX7PU");
        EntityManager em = emf.createEntityManager();
        ParttimeEmployee fromDb = em.find(ParttimeEmployee.class, PTEmp.getId());
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
