package com.github.cocolollipop.mido_svg.university.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * This class correspond to a formation of any kind
 */
public abstract class Formation {

	// cocolollipop: I dont know if we need all of these...I let you comment
	// these ones
	protected char title;
	protected String fullName;
	protected String intitule;
	// this name corresponds to the name with a link ref to the program
	private String fullNameWithLink;
	// grade is corresponding to the year
	protected int grade;

	// Application type (for example: selection based on student records )
	protected String admisssion;
	// List of formations you could apply for after the current year
	protected List<Formation> availableFormations;
	// list of subjects that contain each formation
	protected List<Subject> subjects;
	// the main responsible of the formation
	protected Teacher teacher;

	protected String tagsList[];

	protected int posX;
	protected int posY;

	protected Formation child;

	protected String category;
	protected boolean shown;

	public Formation(String name, int grade, int x, int y) {
		this.title = ' ';
		this.intitule = " ";
		this.fullName = name;
		this.grade = grade;
		this.posX = x;
		this.posY = y;
		this.subjects = new ArrayList<Subject>();
		this.availableFormations = new ArrayList<Formation>();
		this.admisssion = "";
		this.teacher = new Teacher();
		this.shown = false;
		this.tagsList = new String[] { "", "", "", "", "" };
	}

	public String getFullNameWithLink() {
		if (this.fullNameWithLink == null) {
			return this.fullName;
		}
		return this.fullNameWithLink;
	}

	/**
	 * This is to create a link for a formation. It has to be used with the
	 * method drawString() from Graphics2D
	 * 
	 * @param link
	 */
	public void setFullNameWithLink(String link) {

		this.fullNameWithLink = "<a xlink:href=\"" + link + "\" target=\"_blank\">" + this.getFullName() + "</a>";

	}

	public char getTitle() {
		return title;
	}

	public void setTitle(char nomFormation) {

		this.title = nomFormation;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public String getAdmisssion() {
		return admisssion;
	}

	public void setAdmisssion(String admisssion) {
		this.admisssion = admisssion;
	}

	public List<Formation> getAvailableFormations() {
		return availableFormations;
	}

	public void setAvailableFormations(List<Formation> listOfAvailableFormations) {
		this.availableFormations = availableFormations;
	}

	public void addAvailableFormation(Formation formation) {
		this.availableFormations.add(formation);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getPosX() {

		return this.posX;
	}

	public int getPosY() {

		return this.posY;
	}

	public void setPosX(int i) {
		this.posX = i;

	}

	public void setPosY(int j) {
		this.posY = j;

	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void addSubjectsOfFormation(Subject s) {
		this.subjects.add(s);
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public boolean isShown() {
		return shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
	}

	public String[] getTagslist() {
		return tagsList;
	}

	public void setTagsList(String fileName) {
		String[] tagsList = this.readTagsList(fileName);
		this.tagsList = tagsList;
	}

	/**
	 * readTagList read a file entered as a paramater and return a table of
	 * String which contains each worlds of the file
	 * 
	 * @param fileName
	 * @return tagsList
	 */
	public String[] readTagsList(String fileName) {
		String chaine = "";

		try {
			InputStream ips = new FileInputStream(new File(fileName));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				// System.out.println(ligne);
				chaine += ligne + " ";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		String[] tagsList = chaine.split(",");
		return tagsList;
	}

	/**
	 * createTagList creates a file which contains each worlds of the list in
	 * parameters
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void createTagList(List<String> list) throws FileNotFoundException, IOException {

		String name = this.getFullName() + ".txt";
		String text = "";
		for (int i = 0; i < list.size(); i++) {
			text += list.get(i) + ",";
		}
		IOUtils.write(text, new FileOutputStream(name), "UTF-8");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(name))) {

			bw.write(text);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return category;
	}

	/**
	 * hasGotATeachert see if a "formation" has got a teacher (the
	 * firstname/lastname aren't null) or not
	 * 
	 * @param f
	 * @return true or false
	 */

	public boolean hasGotATeacher(Formation f) {
		if (f.getTeacher().getFirstName() == null && f.getTeacher().getLastName() == null)
			return false;
		else
			return true;

	}

}