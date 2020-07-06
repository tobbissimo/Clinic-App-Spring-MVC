package persistence;

import java.io.Closeable;
import java.util.List;
import model.Patient;

public interface IPatientDao extends Closeable {

	// Auslesen von allen Patienten
	public List<Patient> allPatients();
	
	// Einfügen eines neuen Patienten
	public void newPatient(Patient p);
	
	// Ändern eines Patienten
	public void editPatient(Patient p);
	
	// Löschen eines Patienten
	public void deletePatient(Patient p);

}
