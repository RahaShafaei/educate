package edu.educate.dto;

import edu.educate.dto.baseDto.DtoMapperUtils;
import edu.educate.model.MeetingEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MeetingMapper {
    private final DtoMapperUtils dtoMapperUtils;
    public MeetingDto toDto(MeetingEntity meeting) {
        MeetingDto meetingDto = new MeetingDto();

        dtoMapperUtils.populateCommonFields(meeting, meetingDto);
        meetingDto.setTitle(meeting.getTitle());

        return meetingDto;
    }
}
