package hibernate.tables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "content_tag_linker")
public class ContentTagLinker implements Serializable, hibernate.tables.Table{
	private static final long serialVersionUID = 6859059228823369391L;
	@Id
	@Column (name = "ctl_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne												//(targetEntity = Content.class)
	@JoinColumn(name = "c_id")								//field name in table
	private Content content;
	
	@ManyToOne												//(targetEntity = Tag.class)
	@JoinColumn(name = "t_id")								//field name in table
	private Tag tag;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

    public ContentTagLinker(){

    }

    public ContentTagLinker(Content content, Tag tag){
        this.content = content;
        this.tag = tag;
    }
	
	@Override
	public String toString() {
		return new StringBuffer().append("ContentTagLinker [content=").append(content)
				.append(", tag=").append(tag)
				.append("]").toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
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
		ContentTagLinker other = (ContentTagLinker) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}
	
	
}
