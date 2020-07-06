package persistence;

import java.io.Closeable;
import java.util.List;
import model.Patient;

public interface IPatientDao extends Closeable {

	// Auslesen von allen Patienten
	public List<Patient> allPatients();
	
	// Einf�gen eines neuen Patienten
	public void newPatient(Patient p);
	
	// �ndern eines Patienten
	public void editPatient(Patient p);
	
	// L�schen eines Patienten
	public void deletePatient(Patient p);

}
