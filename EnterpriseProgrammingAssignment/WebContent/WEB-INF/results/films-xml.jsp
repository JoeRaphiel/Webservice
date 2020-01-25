<%@ page import ="javax.xml.bind.JAXBContext" %>
<%@ page import ="javax.xml.bind.JAXBException" %>
<%@ page import ="javax.xml.bind.Marshaller" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="model.FilmList" %>
<%@ page import ="model.Film" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
ArrayList<Film> films = (ArrayList<Film>) request.getAttribute("films");
FilmList fl = new FilmList();
fl.setFilmList(films);

try {
		// create JAXB context and instantiate marshaller
	    JAXBContext context = JAXBContext.newInstance(FilmList.class);
		Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // Send to client
	    m.marshal(fl, out);
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

%>