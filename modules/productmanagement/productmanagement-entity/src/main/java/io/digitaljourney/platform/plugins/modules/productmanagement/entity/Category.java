package io.digitaljourney.platform.plugins.modules.productmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@Entity
@Table(name = "PM_CATEGORY", uniqueConstraints = @UniqueConstraint(columnNames = "CATEGORYID", name = "PM_CATEGORY_PK"))
@GeneratePojoBuilder
public class Category {
	@Id
	@SequenceGenerator(name = "categorySeqTask", sequenceName = "PM_CATEGORY_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySeqTask")

	@Column(name = "CATEGORYID", updatable = false, nullable = false)
	private Integer categoryId;
	@Column(name = "CATEGORYNAME", nullable = false)
	private String categoryName;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer id) {
		this.categoryId = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}

}
