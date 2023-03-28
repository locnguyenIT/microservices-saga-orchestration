package com.ntloc.notification;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification toNotification(NotificationDTO notificationDTO);

    List<Notification> toListNotification(List<NotificationDTO> listNotificationDTO);

    NotificationDTO toNotificationDTO(Notification Notification);

    List<NotificationDTO> toListNotificationDTO(List<Notification> listNotification);
}
