package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@NamedQuery(
        name="findContinentByName",
        query="SELECT c FROM ContinentsEntity c WHERE c.name LIKE :name"
)
@Table(name = "continents", schema = "public", catalog = "postgres")
public class ContinentsEntity extends AbstractEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, targetEntity=CountriesEntity.class)
    private ArrayList<CountriesEntity> list = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContinentsEntity that = (ContinentsEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ContinentsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
