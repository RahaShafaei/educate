package edu.educate.model;

import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@jakarta.persistence.Table(name = "meeting", schema = "dbo", catalog = "educate")
public class MeetingEntity extends TitleEntity {

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = true)
    private PlansEntity plan;

}
