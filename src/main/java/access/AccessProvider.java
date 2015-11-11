package access;


import context.UserSession;
import hibernate.tables.userInfo.UserRole;

public class AccessProvider {

    public AccessLevel getUserAccess(UserSession userSession){
        UserRole userRole = userSession.getRole();
        AccessLevel accessLevel;
        switch (userRole){
            case UNCONFIRMED:
                accessLevel = AccessLevel.NONE;
                break;
            case ADMINISTRATOR:
            case EDITOR:
                accessLevel = AccessLevel.FULL_ACCESS;
                break;
            case CORRECTOR:
                accessLevel = AccessLevel.OWN_CORRECTORS;
                break;
            default:
                accessLevel = AccessLevel.NONE;
        }
        return accessLevel;
    }
}
