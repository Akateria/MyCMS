package providers;


import hibernate.dao.ContentPositionHistoryDao;
import hibernate.daoImpl.ContentPositionHistoryDaoImpl;
import hibernate.tables.ContentPositionHistory;

import java.sql.SQLException;

public class ContentHistoryProvider {

    public void addContentHistory(ContentPositionHistory contentPositionHistory){
        try {
            ContentPositionHistoryDao contentPosition = new ContentPositionHistoryDaoImpl();
            contentPosition.addContentPositionHistory(contentPositionHistory);
        }
        catch (SQLException e){

        }
    }
}
