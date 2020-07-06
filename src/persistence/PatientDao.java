package persistence;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Patient;

public class PatientDao implements IPatientDao{

	private EntityManagerFactory ef;
	private EntityManager em;
	
	public PatientDao() {
		this.ef = Persistence.createEntityManagerFactory("jpaPatient");
		this.em = ef.createEntityManager();
	}
	
	@Override
	public void close() throws IOException {
		
	}

	@Override
	public List<Patient> allPatients() {
		TypedQuery<Patient> query = em.createNamedQuery("Patient.findAll", Patient.class);
		List<Patient> patients = query.getResultList();
		return patients;
	}

	@Override
	public void newPatient(Patient p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}

	@Override
	public void editPatient(Patient p) {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}

	@Override
	public void deletePatient(Patient p) {
		if(!em.getTransaction().isActive())
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}
}
