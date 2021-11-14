/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_ex7;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;
/**
 *
 * @author naratcha.s
 */
public class Employee_EX7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int EmpType;
        int Menu = 1;
        Scanner sc = new Scanner(System.in);
        while (Menu == 1) {
            FulltimeEmployee FTEmp = new FulltimeEmployee();
            ParttimeEmployee PTEmp = new ParttimeEmployee();
            System.out.println("----- Please Select Menu -----");
            System.out.println("1 : Insert Mode");
            System.out.println("2 : Update Mode");
            System.out.println("3 : Delete Mode");
            System.out.println("0 : Exit Program");
            System.out.println("------------------------------");
            System.out.print("Please Input Number of Menu : ");
            Menu = sc.nextInt();
            if (Menu == 1) {
                System.out.println("----- Insert Mode -----");
                System.out.println("----- Please Select Employee Type -----");
                System.out.println("1 : Fulltime Employee");
                System.out.println("2 : Parttime Employee");
                System.out.println("---------------------------------------");
                System.out.print("Please Input Number of Employee Type : ");
                EmpType = sc.nextInt();
                if (EmpType == 1) {
                    System.out.println("---------------------------");
                    System.out.print("Please Input Name : ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Please Input Salary : ");
                    int salary = sc.nextInt();
                    FTEmp.setName(name);
                    FTEmp.setSalary(salary);
                    persist(FTEmp);
                    System.out.println("----- Insert Complete -----");
                }
                else {
                    if (EmpType == 2) {
                        System.out.println("---------------------------");
                        System.out.print("Please Input Name : ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        System.out.print("Please Input Salary per Hours : ");
                        int hw = sc.nextInt();
                        PTEmp.setName(name);
                        PTEmp.setHourworks(hw);
                        persist(PTEmp);
                        System.out.println("----- Insert Complete -----");
                    }
                }
            }
            else {
                if (Menu == 2) {
                    System.out.println("----- Update Mode -----");
                    System.out.println("----- Please Select Employee Type -----");
                    System.out.println("1 : Fulltime Employee");
                    System.out.println("2 : Parttime Employee");
                    System.out.println("---------------------------------------");
                    System.out.print("Please Input Number of Employee Type : ");
                    EmpType = sc.nextInt();
                    if (EmpType == 1) {
                        System.out.println("---------------------------");
                        System.out.print("Please Input ID : ");
                        int ID = sc.nextInt();
                        FTEmp = FulltimeEmployee.findEmployeeById(ID);
                        if (FTEmp != null) {
                            FulltimeEmployee.updateEmployee(FTEmp);
                            System.out.print("Please Input Name : ");
                            sc.nextLine();
                            String name = sc.nextLine();
                            System.out.print("Please Input Salary : ");
                            int salary = sc.nextInt();
                            FTEmp.setName(name);
                            FTEmp.setSalary(salary);
                            persist(FTEmp);
                        }
                    }
                    else {
                        if (EmpType == 2) {
                            System.out.println("---------------------------");
                            System.out.print("Please Input ID : ");
                            int ID = sc.nextInt();
                            PTEmp = ParttimeEmployee.findEmployeeById(ID);
                            if (PTEmp != null) {
                                ParttimeEmployee.updateEmployee(PTEmp);
                                System.out.print("Please Input Name : ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                System.out.print("Please Input Salary per Hours : ");
                                int hw = sc.nextInt();
                                PTEmp.setName(name);
                                PTEmp.setHourworks(hw);
                                persist(PTEmp);
                            }
                        }   
                    }
                    System.out.println("----- Update Complete -----");
                }
                else {
                    if (Menu == 3) {
                        System.out.println("----- Delete Mode -----");
                        System.out.println("----- Please Select Employee Type -----");
                        System.out.println("1 : Fulltime Employee");
                        System.out.println("2 : Parttime Employee");
                        System.out.println("---------------------------------------");
                        System.out.print("Please Input Number of Employee Type : ");
                        EmpType = sc.nextInt();
                        if (EmpType == 1) {
                            System.out.println("---------------------------");
                            System.out.print("Please Input ID : ");
                            int ID = sc.nextInt();
                            FTEmp = FulltimeEmployee.findEmployeeById(ID);
                            if (FTEmp != null) {
                                FulltimeEmployee.removeEmployee(FTEmp);
                            }   
                        }
                        else {
                            if (EmpType == 2) {
                                System.out.println("---------------------------");
                                System.out.print("Please Input ID : ");
                                int ID = sc.nextInt();
                                PTEmp = ParttimeEmployee.findEmployeeById(ID);
                                if (PTEmp != null) {
                                    ParttimeEmployee.removeEmployee(PTEmp);
                                }
                            }   
                        }      
                    }
                }
            }
        }
    }
    
    private static FulltimeEmployee TestInsertFullTimeObj(){
        FulltimeEmployee FTEmp = new FulltimeEmployee();
        
        FTEmp.setName("Fern");
        FTEmp.setSalary(1000000000);
        
        persist(FTEmp);
        return FTEmp;
    }
    
    private static ParttimeEmployee TestInsertPartTimeObj(){
        ParttimeEmployee PTEmp = new ParttimeEmployee();
        
        PTEmp.setName("Pond");
        PTEmp.setHourworks(20);
        
        persist(PTEmp);
        return PTEmp;
    }
    
    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employee_EX7PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
