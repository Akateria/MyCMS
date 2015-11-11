package providers;


import context.ModeJSON;
import hibernate.dao.FrontPageDao;
import hibernate.daoImpl.FrontPageDaoImpl;
import hibernate.tables.Content;
import hibernate.tables.FrontPage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrontPageProvider {

    public ArrayList<Content> getFrontContent(){
        ArrayList<Content> contents = new ArrayList<>();
        FrontPageDao frontPageDao = new FrontPageDaoImpl();
        try {
            List<FrontPage> frontPageList = frontPageDao.getFrontPages();
            for (FrontPage frontPage: frontPageList){
                contents.add(frontPage.getContentId());
            }
        }
        catch (SQLException e){

        }

        return contents;
    }

}
