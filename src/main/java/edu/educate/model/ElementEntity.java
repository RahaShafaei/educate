package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(
        name = "element",
        schema = "dbo",
        catalog = "educate",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "element_grp_id"})}
)
public class ElementEntity  extends TitleEntity {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "element_grp_id")
    private ElementGrpEntity elementGrp;

    @OneToMany(mappedBy = "element")
    private List<AttendanceEntity> attendances;

    @OneToMany(mappedBy = "elementType")
    private List<PlansEntity> planTypes;

    @OneToMany(mappedBy = "elementStatus")
    private List<PlansEntity> planStatus;

}
