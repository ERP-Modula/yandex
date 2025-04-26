package modula.platform.yandex.tracker.user.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String self;
    private Long uid;
    private String login;
    private Long trackerUid;
    private Long passportUid;
    private String cloudUid;
    private String firstName;
    private String lastName;
    private String display;
    private String email;
    private boolean external;
    private boolean hasLicense;
    private boolean dismissed;
    private boolean useNewFilters;
    private boolean disableNotifications;
    private String firstLoginDate;
    private String lastLoginDate;
    private boolean welcomeMailSent;
}

