package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@NamedQuery(
        name="findCountryByName",
        query="SELECT c FROM CountriesEntity c WHERE c.name LIKE :name"
)
@Table(name = "countries", schema = "public", catalog = "postgres")
public class CountriesEntity extends AbstractEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "continent")
    private String continent;

    @OneToMany(cascade = CascadeType.ALL, targetEntity=CitiesEntity.class)
    private ArrayList<CitiesEntity>list = new ArrayList<>();


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountriesEntity that = (CountriesEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(continent, that.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, continent);
    }

    public ArrayList<CitiesEntity> getList() {
        return list;
    }
}
