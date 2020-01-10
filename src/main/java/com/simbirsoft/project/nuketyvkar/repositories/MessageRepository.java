package com.simbirsoft.project.nuketyvkar.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simbirsoft.project.nuketyvkar.model.Message;
import com.simbirsoft.project.nuketyvkar.model.Room;

/**
 * @author Koloyartsev V.A.
 * <p>
 * Message jpa repository.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
