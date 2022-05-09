import Entities.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;

public class Main {
    public static void main(String args[]) {


        //Continent eu = new Continent(1, "Europe");
        //Country ro = new Country(1, "Romania", "RO", eu);
        CitiesEntity city = new CitiesEntity();
        city.setCapital(true);
        city.setCountry("Romania");
        city.setLatitude(new BigDecimal(1));
        city.setLongitude(new BigDecimal(2));
        city.setName("AAAA");

        CountriesEntity country = new CountriesEntity();
        country.setCode("RO");
        country.setContinent("Europe");
        country.setName("Romania");

        ContinentsEntity continent = new ContinentsEntity();
        continent.setName("Europe");

        DAOclass dao = new DAOclass();
        Connection c = Database.getConnection();
        Statement stmt;
        SQLScripts script = new SQLScripts();
        try {
            stmt = c.createStatement();


            //stmt.executeUpdate(script.createCountries);
            //stmt.executeUpdate(script.createCities);
            //stmt.executeUpdate(script.createContinents);

            /*
            dao.addAllCities(c);
            dao.addCountry(c, ro);
            dao.addContinent(c, eu);

            Country result = dao.getCountry(c, "Romania");
            City resultCity = dao.getCity(c, "Bucharest");
            Continent resultCont = dao.getContinent(c, "Europe");

            System.out.println(result.toString());
            System.out.println(resultCity.toString());
            System.out.println(resultCont.toString());

            ShowDistance dist = new ShowDistance();
            System.out.println(dist.distance(c, "Bucharest", "Paris"));*/

            long startTime = System.currentTimeMillis();

            System.out.println(city.getCountry());
            EntityManager em = EntMan.entMan();
            em.getTransaction().begin();
            CitiesRepository cityRep = new CitiesRepository(em);
            CountriesRepository countryRep = new CountriesRepository(em);
            ContinentRepository continentRep = new ContinentRepository(em);
            cityRep.create(city);
            cityRep.createAllCities();
            countryRep.create(country);
            em.persist(continent);
            //continentRep.create(continent);
            CitiesEntity test = cityRep.findByName("Pari");
            System.out.println(test.toString());
            em.getTransaction().commit();
            EntMan.closeEm();
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime);
            System.out.println(duration);


/*
            stmt.executeUpdate("Drop table countries");
            stmt.executeUpdate("Drop table cities");
            stmt.executeUpdate("Drop table continents");
*/
            stmt.close();
            c.close();
        }catch ( Exception e ) {
            e.printStackTrace(System.out);
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }
}
