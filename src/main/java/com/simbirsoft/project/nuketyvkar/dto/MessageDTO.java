package com.simbirsoft.project.nuketyvkar.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Messages DTO.
 */
@Getter
@Setter
public class MessageDTO {

    private Long id;

    private UserDTO author;

    @JsonBackReference
    private RoomDTO room;

    @NotBlank(message = "Write the text")
    private String messageText;

    private ZonedDateTime createdDateTime;

}
