import models.Email;
import models.Passport;
import models.User;
import models.Aka;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

      Configuration configuration= new Configuration().configure();

        configuration
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Email.class)
                .addAnnotatedClass(Aka.class);
        SessionFactory sessionFactory= configuration.buildSessionFactory();
        Session manager = sessionFactory.openSession();

       // manager.getTransaction().begin();
        manager.beginTransaction();

        // this gives a passport without user_id because passport1 id initialized before user1:
//        Passport passport1 = new Passport("VK", "675887");
//        User user1 = new User("Vasia", passport1);
//        manager.save(user1);

        // this allows to set this passport to the certain user
//        User user = manager.find(User.class, 1);
//        Passport passport = manager.find(Passport.class, 1);
//        passport.setUser(user);

        // now we transfer mapped_by to the class Passport, and o the same:

//        Passport passport1 = new Passport("VK", "675887");
//        User user1 = new User("Vasia", passport1);
//        manager.save(user1);

//        Passport passport2 = new Passport("UA", "123456");
//        User user2 = new User("Petia", passport2);
//        manager.save(user2);

        // the following request will not give the info about passports if there are cascadetypes LAZY
        // however in most cases it is useless - put LAZY in 90%
//        for (User user : users) {
//            System.out.println(user.getPassport().getSeries()+", "+user.getPassport().getUser());
//        }

        // the following will work if in Passport cascadetype is MERGE - the possibility to update
        // the value through another object. With ALL in Passport it does not work
//        User user3= manager.find(User.class, 1);
//        user3.getPassport().setSeries("UT");

        // this will add list of emails, user MUST be defined or found for the first place
//        User user4= manager.find(User.class, 1);
//        List<Email> emails = new ArrayList<Email>();
//        emails.add(new Email("kokos", "123", user4));
//        emails.add(new Email("abricos", "234", user4));

            // the method save does not accepts collections, must iterate it
//        for (Email email : emails) {
//            manager.save(email);
//        }

        Aka aka1= new Aka();
        aka1.setValue("kefir");
        System.out.println(aka1);
        manager.save(aka1);

        Aka aka2= new Aka();
        aka2.setValue("zefir");
        System.out.println(aka2);
         manager.save(aka2);

        List<Aka> akas1 = new ArrayList<Aka>();
        akas1.add(aka1);
        akas1.add(aka2);

        List<User> users1 = manager.createQuery("select u from User u", User.class).list();
      for (User user : users1) {
        user.setAkas(akas1);
      }

        manager.getTransaction().commit();
        manager.close();
        sessionFactory.close();
    }
}
