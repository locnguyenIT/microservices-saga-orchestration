package com.ntloc.notification;

import com.ntloc.notification.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.notification.NotificationConstant.NOTIFICATION_WAS_NOT_FOUND;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationDTO> getAllNotification() {
        return notificationMapper.toListNotificationDTO(notificationRepository.findAll());
    }

    public NotificationDTO getNotification(Long id) {
        NotificationDTO notificationDTO = notificationRepository.findById(id).map(notificationMapper::toNotificationDTO).orElseThrow(() ->
                new ResourceNotFoundException(NOTIFICATION_WAS_NOT_FOUND));
        return notificationDTO;
    }
}
