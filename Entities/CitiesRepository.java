package Entities;

import com.opencsv.CSVReader;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CitiesRepository extends GenericRepository<CitiesEntity>{
    EntityManager em;
    public CitiesRepository(EntityManager em){
        this.em = em;
    }
    public boolean create(CitiesEntity c){
        if (c.getId() == null) {
            em.persist(c);
        } else {
            c = em.merge(c);
        }
        return true;
    }

    public boolean createAllCities(){
        try (CSVReader reader = new CSVReader(new FileReader("D:\\Desktop\\archive\\concap.csv"))) {
            String[] lineInArray;
            int id = 0;
            while ((lineInArray = reader.readNext()) != null) {
                id++;
                CitiesEntity city = new CitiesEntity();
                city.setCountry(lineInArray[0]);
                city.setName(lineInArray[1]);
                city.setCapital(true);
                city.setLatitude(new BigDecimal(Double.parseDouble(lineInArray[2])));
                city.setLongitude(new BigDecimal(Double.parseDouble(lineInArray[3])));

                create(city);
                //City city = new City(id, lineInArray[0], lineInArray[1], true, Double.parseDouble(lineInArray[2]), Double.parseDouble(lineInArray[3]));
                //addCity(c, city);
                //System.out.println(city.toString());
                //System.out.println(lineInArray[0] + " " + lineInArray[1] + " " +lineInArray[2] + " " +lineInArray[3] + " "+ lineInArray[4] + " "+ lineInArray[5]);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("AAAA");
            return false;
        }
        return true;
    }
    public CitiesEntity findById(int id){
        TypedQuery<CitiesEntity> q = em.createQuery("SELECT c FROM CitiesEntity c WHERE c.id = :id", CitiesEntity.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }


    public CitiesEntity findByName(String name){
        List list = em.createNamedQuery("findCityByName")
                .setParameter("name", name + "%")
                .getResultList();

        return (CitiesEntity) list.get(0);
    }
}
