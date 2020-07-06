package model;

import javax.persistence.*;

@Entity
@Table(name="patient")
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient extends Person
{
	private static final long serialVersionUID = 3310359118256555312L;
	
	@Id
	//@SequenceGenerator(name="MySeq", sequenceName="patient_seq", initialValue=1000, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String krankenkasse;
	
    public Patient() {
        super();
        this.krankenkasse = "n/a";
    }

    public Patient(long svnr, String geburtsdatum, String vorname, String nachname, Geschlecht geschlecht, 
    				String adresse, String krankenkasse) {
    	super(svnr, geburtsdatum, vorname, nachname, geschlecht, adresse);
    	this.krankenkasse = krankenkasse;
    }
	
    public int getPid() {
    	return pid;
    }
    
    public void setPid(int pid) {
    	this.pid = pid;
    }
    
	public String getKrankenkasse()
	{
		return krankenkasse;
	}

	public void setKrankenkasse(String krankenkasse)
	{
		this.krankenkasse = krankenkasse;
	}

	@Override
	public String toString() {
		return this.getNachname()+" "+this.getVorname();
	}
}
