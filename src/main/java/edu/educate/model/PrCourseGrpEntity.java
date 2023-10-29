package edu.educate.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "pr_course_grp", schema = "dbo", catalog = "educate")
public class PrCourseGrpEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pr_course_grp_id", nullable = false)
    private int prCourseGrpId;
    @Basic
    @Column(name = "lt_title", nullable = true, length = 255)
    private String ltTitle;
    @Basic
    @Column(name = "pr_title", nullable = true, length = 255)
    private String prTitle;
    @Basic
    @Column(name = "descr", nullable = true, length = 255)
    private String descr;
    @Basic
    @Column(name = "deleted", nullable = true, length = 2)
    private String deleted;
    @Basic
    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt;
    @Basic
    @Column(name = "inserted_at", nullable = true)
    private Date insertedAt;

    public int getPrCourseGrpId() {
        return prCourseGrpId;
    }

    public void setPrCourseGrpId(int prCourseGrpId) {
        this.prCourseGrpId = prCourseGrpId;
    }

    public String getLtTitle() {
        return ltTitle;
    }

    public void setLtTitle(String ltTitle) {
        this.ltTitle = ltTitle;
    }

    public String getPrTitle() {
        return prTitle;
    }

    public void setPrTitle(String prTitle) {
        this.prTitle = prTitle;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrCourseGrpEntity that = (PrCourseGrpEntity) o;

        if (prCourseGrpId != that.prCourseGrpId) return false;
        if (ltTitle != null ? !ltTitle.equals(that.ltTitle) : that.ltTitle != null) return false;
        if (prTitle != null ? !prTitle.equals(that.prTitle) : that.prTitle != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;
        if (insertedAt != null ? !insertedAt.equals(that.insertedAt) : that.insertedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prCourseGrpId;
        result = 31 * result + (ltTitle != null ? ltTitle.hashCode() : 0);
        result = 31 * result + (prTitle != null ? prTitle.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        return result;
    }
}
