package model;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//This statement means that class "FilmList.java" is the root-element of our example
@XmlRootElement(name = "filmList")
public class FilmList {

    // XmLElementWrapper generates a wrapper element around XML representation
    // @XmlElementWrapper(name = "filmCollection")
    // XmlElement sets the name of the entities
    @XmlElement(name = "film")
    private ArrayList<Film> films;

    public void setFilmList(ArrayList<Film> films) {
        this.films = films;
    }

    public ArrayList<Film> getFilmsList() {
        return films;
    }

	public FilmList() {
		super();
		// TODO Auto-generated constructor stub
	}    
}