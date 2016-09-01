package com.petstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.petstore.utils.StatusEnum;

/**
 * PET class
 * 
 * @author dinesh.jaju
 *
 */
@Entity
public class Pet 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id ;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catid")
	private Category category ;

	private String name ;
	
	@ElementCollection
	@CollectionTable(name="PET_PHOTO_URLS", joinColumns=@JoinColumn(name="id"))
	@Column(name="photo_url")
	private List<String> photoUrls = new ArrayList<String>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "tagid")
	private List<Tag> tags = new ArrayList<Tag>();

	private StatusEnum status ;

	public Pet() {
	}

	public Pet(long id, Category category, String name, List<String> photoUrls,
			List<Tag> tags, StatusEnum status) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	public Pet(Category category, String name, List<String> photoUrls,
			List<Tag> tags, StatusEnum status) {
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	
	public Pet addPhotoUrlsItem(String photoUrlsItem) {
		this.photoUrls.add(photoUrlsItem);
		return this;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((photoUrls == null) ? 0 : photoUrls.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photoUrls == null) {
			if (other.photoUrls != null)
				return false;
		} else if (!photoUrls.equals(other.photoUrls))
			return false;
		if (status != other.status)
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pet [id=" + id + ", category=" + category + ", name=" + name
				+ ", photoUrls=" + photoUrls + ", tags=" + tags + ", status="
				+ status + "]";
	}
	
	
}
