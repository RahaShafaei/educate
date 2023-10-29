package edu.educate.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "pr_course", schema = "dbo", catalog = "educate")
public class PrCourseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pr_course_id", nullable = false)
    private int prCourseId;
    @Basic
    @Column(name = "pr_course_grp_id", nullable = true)
    private Integer prCourseGrpId;
    @Basic
    @Column(name = "lt_title", nullable = true, length = 50)
    private String ltTitle;
    @Basic
    @Column(name = "pr_title", nullable = true, length = 50)
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
    @ManyToOne
    @JoinColumn(name = "pr_course_grp_id", referencedColumnName = "pr_course_grp_id")
    private PrCourseGrpEntity prCourseGrpByPrCourseGrpId;

    public int getPrCourseId() {
        return prCourseId;
    }

    public void setPrCourseId(int prCourseId) {
        this.prCourseId = prCourseId;
    }

    public Integer getPrCourseGrpId() {
        return prCourseGrpId;
    }

    public void setPrCourseGrpId(Integer prCourseGrpId) {
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

        PrCourseEntity that = (PrCourseEntity) o;

        if (prCourseId != that.prCourseId) return false;
        if (prCourseGrpId != null ? !prCourseGrpId.equals(that.prCourseGrpId) : that.prCourseGrpId != null)
            return false;
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
        int result = prCourseId;
        result = 31 * result + (prCourseGrpId != null ? prCourseGrpId.hashCode() : 0);
        result = 31 * result + (ltTitle != null ? ltTitle.hashCode() : 0);
        result = 31 * result + (prTitle != null ? prTitle.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        return result;
    }

    public PrCourseGrpEntity getPrCourseGrpByPrCourseGrpId() {
        return prCourseGrpByPrCourseGrpId;
    }

    public void setPrCourseGrpByPrCourseGrpId(PrCourseGrpEntity prCourseGrpByPrCourseGrpId) {
        this.prCourseGrpByPrCourseGrpId = prCourseGrpByPrCourseGrpId;
    }
}
