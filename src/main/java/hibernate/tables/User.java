package hibernate.tables;

import hibernate.tables.userInfo.UserRole;
import hibernate.tables.userInfo.UserStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable, hibernate.tables.Table{

    private static final String KEY_NAME = "name";
    private static final String KEY_FIRST_NAME = "firstname";
    private static final String KEY_LAST_NAME = "lastname";

    private static final long serialVersionUID = -1698226571037727021L;
	@Id
	@Column(name = "u_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "u_name")
	private String login;
	@Column(name = "u_firstname")
	private String firstName;
	@Column(name = "u_lastname")
	private String lastName;
	@Column(name = "u_email")
	private String email;
	@Column(name = "u_password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column (columnDefinition="enum('ADMINISTRATOR', 'CORRECTOR', 'EDITOR', 'AUTHOR', 'UNCONFIRMED')",name="u_role")
	private UserRole role;
	
	@Enumerated(EnumType.STRING)
	@Column (columnDefinition="enum('UNAVAILABLE','AVAILABLE')", name="u_status") 
	private UserStatus status;

	@OneToMany(mappedBy="author")	//reference on field in object Content
	private Set<Content>content;

	@Column (name = "u_confirm_code")
	private String confirmCode;
	@Column (name = "u_restore_code")
	private String restoreCode;

	public Integer getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public Set<Content> getContent() {
		return content;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	public String getRestoreCode() {
		return restoreCode;
	}
	public void setRestoreCode(String restoreCode) {
		this.restoreCode = restoreCode;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("Users [id=").append(id)
				.append(", login=").append(login)
				.append(", firstName=").append(firstName)
				.append(", lastName=").append(lastName)
				.append(", email=").append(email)
				.append(", password=").append(password)
				.append(", role=").append(role)
				.append(", status=").append(status)
				.append(", confirmCode=").append(confirmCode)
				.append(", restoreCode=").append(restoreCode)
				.append("]").toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((confirmCode == null) ? 0 : confirmCode.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((restoreCode == null) ? 0 : restoreCode.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		User other = (User) obj;
		if (confirmCode == null) {
			if (other.confirmCode != null)
				return false;
		} else if (!confirmCode.equals(other.confirmCode))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (restoreCode == null) {
			if (other.restoreCode != null)
				return false;
		} else if (!restoreCode.equals(other.restoreCode))
			return false;
		if (role != other.role)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

    public JSONObject createJSON() {
        try {
            JSONObject userJSON = new JSONObject();
            userJSON.put(KEY_NAME, login);
            userJSON.put(KEY_FIRST_NAME, firstName);
            userJSON.put(KEY_LAST_NAME, lastName);
            return userJSON;
        }
        catch (JSONException e){
            return null;

        }
    }

    public String getUserInJSON(){
        JSONObject userJSON = createJSON();
        return userJSON.toString();
    }

}
