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
@Table(name = "SAMPLE", uniqueConstraints = @UniqueConstraint(columnNames = "ID", name = "PK_SAMPLE"))
@GeneratePojoBuilder
public class Sample {
  @Id
  @SequenceGenerator(name = "sampleSeqTask", sequenceName = "SEQ_SAMPLE_ID", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sampleSeqTask")
  @Column(name = "ID", updatable = false, nullable = false)
  private Integer id;
  @Column(name = "TITLE")
  private String title;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Sample [id=" + id + ", title=" + title + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
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
    Sample other = (Sample) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }
}
