package mapping.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Mainclass {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("monday");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

	------------------inserting---------------
		
		Student student1=new Student();
		student1.setSAge(23);
		student1.setSName("rahul");
		
		Student student2=new Student();
		student2.setSAge(25);
		student2.setSName("naveen");
		
		Student student3=new Student();
		student3.setSAge(23);
		student3.setSName("ram");
		
		
		Trainer trainer=new Trainer();
		trainer.setTName("Sridhar sir");
		trainer.setSubject("jdbc and hibernet");
		trainer.setSt(Arrays.asList(student1,student2,student3));
		
		
		entityTransaction.begin();
		entityManager.persist(trainer);
		entityTransaction.commit();

	-----------fetching student class-----------

		Student student=entityManager.find(Student.class, "rahul");
		System.out.println(student);

		------------------fetching Trainer class-----------
		
		Trainer trainer=entityManager.find(Trainer.class, "Shankar sir");
		System.out.println(trainer);

		---------updating Student class------------
		Student student=entityManager.find(Student.class, "rahul");
		student.setSAge(35);
		entityTransaction.begin();
		entityManager.merge(student);
		entityTransaction.commit();

		------------updating Trainer class-----------

		Trainer trainer=entityManager.find(Trainer.class, "Sridhar sir");
		trainer.setSubject("devops");

		entityTransaction.begin();
		entityManager.merge(trainer);
		entityTransaction.commit();

		------------deleting trainer class-----------
		
		Trainer trainer=entityManager.find(Trainer.class, "Sridhar sir");
		entityTransaction.begin();
		entityManager.remove(trainer);
		entityTransaction.commit();

		---------------deleting student class--------------

		Trainer trainer = entityManager.find(Trainer.class, "Shankar sir");
		List<Student> sl = trainer.getSt();
		Iterator<Student> i = sl.iterator();

		while (i.hasNext()) {
			Student s = i.next();
			if (s.getSName().equals("NTR"))
				i.remove();
		}
		trainer.setSt(sl);
		entityTransaction.begin();
		entityManager.persist(trainer);
		entityTransaction.commit();
		
		Student student=entityManager.find(Student.class, "NTR");
		entityTransaction.begin();
		entityManager.remove(student);
		entityTransaction.commit();

	}

}
