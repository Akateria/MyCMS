package hibernate.tables;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "similar_content")
public class SimilarContent implements Serializable, hibernate.tables.Table {

    @OneToMany
    @JoinColumn(name = "cs_content")
    private Content contentId;

    @ManyToOne
    @JoinColumn(name = "cs_content_similar")
    private Content contentIdSimilar;
}
