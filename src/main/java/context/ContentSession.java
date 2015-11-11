package context;


import hibernate.tables.User;

public class ContentSession {

    private static ContentSession contentSession;

    public String text;
    public String name;
    public String link;
    public String image;
    public Integer id;
    public ContentEditMode editMode;
    public User owner;

    private ContentSession(){

    }

    public static synchronized ContentSession getContentSession(){
        if(contentSession == null) {
            contentSession = new ContentSession();
        }

        return contentSession;
    }

    public void createContentSession(int id, String name,  String text,
                                      String link, String image, ContentEditMode contentEditMode){
        this.id = id;
        this.name = name;
        this.text = text;
        this.link = link;
        this.image = image;
        this.editMode = contentEditMode;
    }

    public void updateSession(String name,  String text,
                               String link, String image, User owner){
        this.owner = owner;
        createContentSession(id, name, text, link, image, ContentEditMode.EDIT_MODE);

    }

    public void updateSession(String name,  String text,
                              String link, String image){
        createContentSession(id, name, text, link, image, ContentEditMode.EDIT_MODE);

    }

    public void removeInstance(){
        contentSession = null;
    }

    public User getOwner(){
        return  owner;
    }
}
