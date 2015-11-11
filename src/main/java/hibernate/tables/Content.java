package hibernate.tables;

import builders.TagJSONBuilder;
import context.ContentList;
import context.ModeJSON;
import org.json.JSONArray;
import providers.TagProvider;
import context.ArticleStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "content")
public class Content implements Serializable, hibernate.tables.Table {

    private static final long serialVersionUID = -5284963386489685478L;
    private static final String IMAGE_STORE         = "http://46.101.140.19:81/imagine?imageName=";

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_img")
    private String image;

    @Column(name = "c_text", length = 4194304)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column (columnDefinition="enum('AUTHOR','CORRECTOR', 'EDITOR', 'DELETED', 'WEBSITE', 'CASE')", name="articleStatus", length = 2048)
    private ArticleStatus articleStatus;

    @Column(name = "c_created")
    private Timestamp created;

    @Column(name = "c_lastedit")
    private Timestamp lastEdit;

    @Column(name = "c_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "c_author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "c_owner")
    private User owner;

    @Column(name = "c_review_count")
    private Integer reviewCount;

    public Content(){

    }

    public Content(String name, String image, String text,
                   Timestamp created, String url, ArticleStatus articleStatus, User author, User owner){
        this.name = name;
        this.image = image;
        this.text = text;
        this.created = created;
        this.url = url;
        this.articleStatus = articleStatus;
        this.author = author;
        this.lastEdit = created;
        this.owner = owner;
    }

    public Content(Integer id, String name, String image,
                   String text, Timestamp created, String url, ArticleStatus articleStatus, User author, User owner){
        this.id = id;
        this.name = name;
        this.image = image;
        this.text = text;
        this.created = created;
        this.url = url;
        this.articleStatus = articleStatus;
        this.author = author;
        this.owner = owner;
    }

    public Content(Integer id, String name, String image,
                   String text, String url, ArticleStatus articleStatus, User author, Timestamp lastEdit, User owner){
        this.id = id;
        this.name = name;
        this.image = image;
        this.text = text;
        this.url = url;
        this.articleStatus = articleStatus;
        this.author = author;
        this.lastEdit = lastEdit;
        this.owner = owner;
    }


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Timestamp lastEdit) {
        this.lastEdit = lastEdit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public ArticleStatus getArticleStatus(){
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatus articleStatus){
        this.articleStatus = articleStatus;
    }
    @Override
    public String toString() {
        return new StringBuffer().append("Content [id=").append(id)
                .append(", name=").append(name)
                .append(", image=").append(image)
                .append(", text=").append(text)
                .append(", status=").append(articleStatus)
                .append(", created=").append(created)
                .append(", lastEdit=").append(lastEdit)
                .append(", url=").append(url)
                .append(", author=").append(author)
                .append(", reviewCount=").append(reviewCount)
                .append("]").toString();
    }

    public String getImagePath(){
        StringBuffer imagePath = new StringBuffer();
        imagePath.append(IMAGE_STORE);
        imagePath.append(image);
        return imagePath.toString();
    }

    public String getDateInString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(created);
        return date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result
                + ((lastEdit == null) ? 0 : lastEdit.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((reviewCount == null) ? 0 : reviewCount.hashCode());
        result = prime * result + ((articleStatus == null) ? 0 : articleStatus.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        Content other = (Content) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (created == null) {
            if (other.created != null)
                return false;
        } else if (!created.equals(other.created))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (lastEdit == null) {
            if (other.lastEdit != null)
                return false;
        } else if (!lastEdit.equals(other.lastEdit))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (reviewCount == null) {
            if (other.reviewCount != null)
                return false;
        } else if (!reviewCount.equals(other.reviewCount))
            return false;
        if (articleStatus == null) {
            if (other.articleStatus != null)
                return false;
        } else if (!articleStatus.equals(other.articleStatus))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

}
