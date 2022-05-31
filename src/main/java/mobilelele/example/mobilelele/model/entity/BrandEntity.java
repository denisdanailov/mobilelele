package mobilelele.example.mobilelele.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }
}
