package com.app.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.app.model.Employee;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
//		Employee e1 = new Employee("Ramesh", 30, "Male", 35000.00, "Developer");
//		session.save(e1);
//		Employee e2 = new Employee("Ramu", 29, "Male", 25000.00, "Software Testing");
//		session.save(e2);
//		Employee e3 = new Employee("Ramya", 25, "Female", 15000.00, "Data Entry");
//		session.save(e3);
//		Employee e4 = new Employee("Raja", 23, "Male", 35000.00, "Developer");
//		session.save(e4);
//		Employee e5 = new Employee("Ravi", 30, "Male", 50000.00, "Senior Developer");
//		session.save(e5);

		Employee empUpdate = (Employee) session.get(Employee.class, 4);
		empUpdate.setSalary(25000.00);
		session.update(empUpdate);

		// Employee empDelete=new Employee();
		// empDelete.setId(1);
		// session.delete(empDelete);

		Query query = session.createQuery("from com.app.model.Employee where designation=:designation order by id ");
		query.setString("designation", "Developer");
		List<Employee> devEmployeeList = query.list();
		System.out.println("Developers");
		for (Employee employeeList : devEmployeeList) {
			System.out.println(employeeList);
		}

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.between("age", 25, 30));
		List<Employee> empAgeBasedList = criteria.list();
		System.out.println("Employee list Between 25-30");

		for (Employee empList : empAgeBasedList) {
			System.out.println(empList);
		}

		transaction.commit();
		session.close();
		factory.close();

	}

}
