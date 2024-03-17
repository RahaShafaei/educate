package edu.educate.controller;

import edu.educate.controller.baseContrlller.BaseController;
import edu.educate.dto.MeetingDto;
import edu.educate.model.MeetingEntity;
import edu.educate.service.MeetingService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/meeting")
public class MeetingController extends BaseController<MeetingEntity, MeetingDto> {

    public MeetingController(MeetingService meetingService) {
        super(MeetingEntity.class,
                MeetingDto.class,
                meetingService,
                "meeting",
                "meetingDir/meeting",
                null,
                30,
                null);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer id) {
        Optional<MeetingEntity> meetingEntity = service.getEntityById(id);

        ByteArrayResource resource = new ByteArrayResource(meetingEntity.get().getFileValue());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(meetingEntity.get().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + meetingEntity.get().getTitle() + "\"")
                .body(resource);
    }

    @GetMapping("/deleted/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<MeetingEntity> meetingEntity = service.getEntityById(id);
        Integer planId = meetingEntity.get().getPlan().getId();

        service.deleteEntityEntirely(id);
        return "redirect:/plans/edit/" + planId;
    }
}