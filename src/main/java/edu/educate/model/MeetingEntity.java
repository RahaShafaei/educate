package edu.educate.model;

import edu.educate.helper.MessageUtil;
import edu.educate.model.baseModel.TitleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "meeting", schema = "dbo", catalog = "educate")
public class MeetingEntity extends TitleEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "plan_id")
    private PlansEntity plan;

    @Lob
    @Column(name = "file_value")
    private byte[] fileValue;

    @Column(name = "file_type")
    private String fileType;

    @Override
    public List<String> getHeaderNames() {
        List<String> headers = new ArrayList<>();
        headers.add(MessageUtil.getMessage("plan.page.title"));
        headers.add(MessageUtil.getMessage("meeting.file"));
        headers.add(MessageUtil.getMessage("meeting.file.type"));
        return headers;
    }

    @Override
    public List<Object> getCellValues() {
        List<Object> objects = new ArrayList<>();
        objects.add(plan != null ? plan.getTitle() : null);
        objects.add(getTitle() != null ? getTitle() : null);
        objects.add(fileType != null ? fileType : null);
        return objects;
    }

    public PlansEntity getPlan() {
        return (PlansEntity)ifEntityIsDeleted(plan);
    }
}
