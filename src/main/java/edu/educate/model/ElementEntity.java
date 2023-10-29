package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@jakarta.persistence.Table(name = "element", schema = "dbo", catalog = "educate")
public class ElementEntity  extends TitleEntity {

    @ManyToOne
    @Column(name = "element_grp_id", nullable = true)
    private ElementGrpEntity elementGrp;

    @OneToMany(mappedBy = "element")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "elementType")
    private List<PlansEntity> planTypes;

    @OneToMany(mappedBy = "elementStatus")
    private List<PlansEntity> planStatus;

}
