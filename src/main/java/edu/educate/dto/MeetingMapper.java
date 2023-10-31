package edu.educate.dto;

import edu.educate.model.MeetingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MeetingMapper {
    private final PlansMapper planMapper;
    public MeetingDto toDto(MeetingEntity meeting) {
        MeetingDto meetingDto = new MeetingDto();

        meetingDto.setId(meeting.getId());
        meetingDto.setTitle(meeting.getTitle());
        meetingDto.setDeleted(meeting.getDeleted());
        meetingDto.setDeletedAt(meeting.getDeletedAt());
        meetingDto.setInsertedAt(meeting.getInsertedAt());

        meetingDto.setPlan(
                meeting.getPlan() != null ?
                        this.planMapper.toDto(meeting.getPlan()) :
                        null
        );

        return meetingDto;
    }
}
