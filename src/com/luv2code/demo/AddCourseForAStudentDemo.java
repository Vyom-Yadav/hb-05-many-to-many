package com.luv2code.demo;

import com.luv2code.entity.Course;
import com.luv2code.entity.Instructor;
import com.luv2code.entity.InstructorDetail;
import com.luv2code.entity.Review;
import com.luv2code.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCourseForAStudentDemo {

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

            int theId = 2;
            Student tempStudent = session.
                    get(Student.class, theId);

            System.out.println("\nLoaded student : " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());

           // Commit transaction

            Course course1 = new Course("How to speed a cube");
            Course course2 = new Course("Atari 2600-Game development");

            course1.addStudent(tempStudent);
            course2.addStudent(tempStudent);

            session.save(course1);
            session.save(course2);

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
