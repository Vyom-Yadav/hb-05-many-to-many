package com.luv2code.demo;

import com.luv2code.entity.Course;
import com.luv2code.entity.Instructor;
import com.luv2code.entity.InstructorDetail;
import com.luv2code.entity.Review;
import com.luv2code.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {

    public static void main(String[] args) {

        // Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Create session
        Session session = factory.getCurrentSession();

        try {

            session = factory.getCurrentSession();

            //start a transaction
            session.beginTransaction();

            //create a course
            Course tempCourse = new Course("Pacman - How to score 2 mil points");

            System.out.println("\nSave the course ... ");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            //add students to the course
            Student tempStudent1 = new Student("John", "Doe", "asdas@sad.com");
            Student tempStudent2 = new Student("Vyom", "Yadav", "dawd@sdoif.com");

           tempCourse.addStudent(tempStudent1, tempStudent2);
           //save the students
            System.out.println("\nSaving students ... ");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved the students: " + tempCourse.getStudents());

           // Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception a) {
            a.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }


}
