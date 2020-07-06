package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.Geschlecht;
import model.Patient;

@Controller
public class AppController {
	private final String driver = "org.sqlite.JDBC";
	private final String url = "jdbc:sqlite:C:\\workspace\\eclipse\\webApp6BAIF\\schule.db";
	private Connection con = null;

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String printHello(Model model) {
		model.addAttribute("message", "Hello World!");
		return "hello";
	}
	
	@RequestMapping(value="/schueler", method=RequestMethod.GET)
	public String listSchueler(Model model) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Patient> listP = new ArrayList<Patient>();
		try {
			if (con == null) {
				Class.forName(driver);
				con = DriverManager.getConnection(url);
			}
			String sql = "SELECT S_Schnr, S_Name, S_Vorname, S_Gebdat, S_Adresse FROM schueler";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Patient p = new Patient(rs.getLong("svnr"), rs.getString("geburtsdatum"), 
						rs.getString("vorname"), rs.getString("nachname"), Geschlecht.valueOf(rs.getString("geschlecht")),
						rs.getString("adresse"), rs.getString("krankenkasse"));
				listP.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("listSchueler", listP);
		return "schueler";
	}
	
	@RequestMapping(value="/schuelerForm", method = RequestMethod.POST)
	public ModelAndView getSchueler(ModelAndView model, @RequestParam("nr") int id) {
		Patient patient = new Patient();
		if (id == -1) {
			patient.setPid((int)(Math.random()*90000)+10000);
			patient.setGeburtsdatum(LocalDate.of(2000, 1, 1));
		} else {
			try {
				if (con == null) {
					Class.forName(driver);
					con = DriverManager.getConnection(url);
				}
				String sql = "SELECT S_Schnr, S_Name, S_Vorname, S_Gebdat, S_Adresse "
						+ "FROM schueler WHERE S_Schnr=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					patient = new Patient(rs.getLong("svnr"), rs.getString("geburtsdatum"), 
							rs.getString("vorname"), rs.getString("nachname"), Geschlecht.valueOf(rs.getString("geschlecht")),
							rs.getString("adresse"), rs.getString("krankenkasse"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addObject("schuelerObjekt", patient);
		model.addObject("genderList", Arrays.asList("weiblich","männlich"));
		model.setViewName("schuelerEdit");
		return model;
	}
	
	@RequestMapping(value="/schuelerEdit", method=RequestMethod.POST)
	public String editSchueler(@ModelAttribute("schuelerObjekt")Patient patient, Model model,
			 @RequestParam("nr") int id) {
		try {
			if (con == null) {
				Class.forName(driver);
				con = DriverManager.getConnection(url);
			}
			String sql = "SELECT S_Schnr FROM schueler WHERE S_Schnr=?";
			PreparedStatement pstmt0 = con.prepareStatement(sql);
			pstmt0.setInt(1, id);
			ResultSet rs = pstmt0.executeQuery();
			PreparedStatement pstmt = null;
			if (!rs.isBeforeFirst()) {
				sql = "INSERT INTO schueler (S_Schnr, S_Vorname, S_Name, S_Gebdat, S_Adresse) "
						+ "VALUES (?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
			} else {
				sql = "UPDATE schueler SET S_Schnr=?, S_Vorname=?, S_Name=?, S_Gebdat=?, S_Adresse=? "
						+ "WHERE S_Schnr=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(6, id);
			}
			pstmt.setInt(1, patient.getPid());
			pstmt.setString(2, patient.getVorname());
			pstmt.setString(3, patient.getNachname());
			pstmt.setString(4, patient.getGeburtsdatum().toString());
			pstmt.setString(5, patient.getAdresse());
			pstmt.executeUpdate();
			System.out.println(patient.getGeschlecht());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:schueler";
	}

	@RequestMapping(value="/schuelerDetails", method = RequestMethod.POST)
	public ModelAndView showSchueler(ModelAndView model, @RequestParam("nr") int id) {
		Patient patient = new Patient();
		try {
			if (con == null) {
				Class.forName(driver);
				con = DriverManager.getConnection(url);
			}
			String sql = "SELECT S_Schnr, S_Name, S_Vorname, S_Gebdat, S_Adresse "
					+ "FROM schueler WHERE S_Schnr=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				patient = new Patient(rs.getLong("svnr"), rs.getString("geburtsdatum"), 
						rs.getString("vorname"), rs.getString("nachname"), Geschlecht.valueOf(rs.getString("geschlecht")),
						rs.getString("adresse"), rs.getString("krankenkasse"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addObject("schuelerObjekt", patient);
		model.setViewName("schuelerDetails");
		return model;
	}
	
	@RequestMapping(value="/schuelerEntf", method=RequestMethod.POST)
	public String deleteSchueler (Model model, @RequestParam("nr") int id) {
		try {
			if (con == null) {
				Class.forName(driver);
				con = DriverManager.getConnection(url);
			}
			String sql = "DELETE FROM schueler WHERE S_Schnr=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:schueler";
	}
}
