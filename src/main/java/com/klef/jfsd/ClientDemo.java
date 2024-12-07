package com.klef.jfsd;

import java.util.List;

	public class ClientDemo {
	    public static void main(String[] args) {
	        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
	        SessionFactory factory = cfg.buildSessionFactory();
	        Session session = factory.openSession();
	        Transaction tx = session.beginTransaction();

	        // Insert records
	        Customer customer1 = new Customer();
	        customer1.setName("Alice");
	        customer1.setEmail("alice@example.com");
	        customer1.setAge(30);
	        customer1.setLocation("New York");

	        Customer customer2 = new Customer();
	        customer2.setName("Bob");
	        customer2.setEmail("bob@example.com");
	        customer2.setAge(25);
	        customer2.setLocation("Los Angeles");

	        session.save(customer1);
	        session.save(customer2);

	        tx.commit();

	        // Apply HCQL Restrictions
	        Criteria criteria = session.createCriteria(Customer.class);

	        System.out.println("Customers with Age > 25:");
	        criteria.add(Restrictions.gt("age", 25));
	        List<Customer> customers = criteria.list();
	        for (Customer c : customers) {
	            System.out.println(c.getName() + " - " + c.getAge());
	        }

	        criteria = session.createCriteria(Customer.class);
	        System.out.println("\nCustomers located in 'New York':");
	        criteria.add(Restrictions.eq("location", "New York"));
	        customers = criteria.list();
	        for (Customer c : customers) {
	            System.out.println(c.getName() + " - " + c.getLocation());
	        }

	        criteria = session.createCriteria(Customer.class);
	        System.out.println("\nCustomers with email containing 'example':");
	        criteria.add(Restrictions.like("email", "%example%"));
	        customers = criteria.list();
	        for (Customer c : customers) {
	            System.out.println(c.getName() + " - " + c.getEmail());
	        }

	        session.close();
	        factory.close();
	    }
	}


