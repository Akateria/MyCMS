package hibernate.tables;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity
@Table(name = "tag")
public class Tag implements Serializable, hibernate.tables.Table{
	private static final long serialVersionUID = 1470698910064304556L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "t_id")
	private Integer id;
	
	@Column(name = "t_name")
	private String name;
	
	@Column(name = "t_keywords")
	private String keywords;
	
	@OneToMany(mappedBy="tag")		//reference on field in object ContentTagLinker
	private Set<ContentTagLinker> tags;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

    public String getKeywords(){
        return keywords;
    }

	public void setName(String name) {
		this.name = name;
	}

	public Set<ContentTagLinker> getTags() {
		return tags;
	}

    public void setKeywords(String keywords){
        this.keywords = keywords;
    }

	@Override
	public String toString() {
		return new StringBuffer().append("Tags [id=").append(id)
				.append(", name=").append(name)
				.append("]").toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
