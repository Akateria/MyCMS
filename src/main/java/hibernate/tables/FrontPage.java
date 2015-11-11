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
@Table(name = "front_page")
public class FrontPage implements Serializable, hibernate.tables.Table{
	private static final long serialVersionUID = -2974433157849728801L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")  
	private Integer id;
	
	@ManyToOne 							//(targetEntity = Content.class)
	@JoinColumn(name = "f_content")		//field name in table
	private Content contentId;
	
	@Column(name="f_order")
	private Integer order;

    public FrontPage(){

    }

    public FrontPage(Content content, int order){
        this.contentId = content;
        this.order = order;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Content getContentId() {
		return contentId;
	}

	public void setContentId(Content contentId) {
		this.contentId = contentId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	
	@Override
	public String toString() {
		return new StringBuffer().append("FrontPage [id=").append(id)
				.append(", contentId=").append(contentId)
				.append(", order=").append(order)
				.append("]").toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentId == null) ? 0 : contentId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		FrontPage other = (FrontPage) obj;
		if (contentId == null) {
			if (other.contentId != null)
				return false;
		} else if (!contentId.equals(other.contentId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}

	
	

}
