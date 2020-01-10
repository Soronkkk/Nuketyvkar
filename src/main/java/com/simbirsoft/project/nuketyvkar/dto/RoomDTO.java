package com.simbirsoft.project.nuketyvkar.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Room DTO.
 */
@Getter
@Setter
public class RoomDTO {

    private Long id;

    @NotBlank(message = "Write the room name")
    private String name;

    private boolean privateRoom;

    private boolean botRoom;

    private boolean deleted;

    private List<MessageDTO> messages;

}
