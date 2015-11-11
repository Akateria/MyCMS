package providers;

import builders.ContentJSONBuilder;
import context.ArticleStatus;
import context.ContentList;
import hibernate.tables.Content;

import java.util.ArrayList;

public class ContentListProvider {

    private static final int AMOUNT_ON_PAGE = 5;
    private static final int NUMBER_FIRST_PAGE = 1;

    public String getContentsByNumber(int pageNumber){
        ArrayList<Content> contents = getContents(pageNumber);
        ContentJSONBuilder contentJSONBuilder = new ContentJSONBuilder();
        String contentsInString = contentJSONBuilder.getJSONContentInString(contents);
        return contentsInString;
    }

    public String getContentsByNumber(int pageNumber, ArticleStatus articleStatus){
        ArrayList<Content> contents = getContents(pageNumber, articleStatus);
        ContentJSONBuilder contentJSONBuilder = new ContentJSONBuilder();
        String contentsInString = contentJSONBuilder.getJSONContentInString(contents);
        return contentsInString;
    }


    public ArrayList<Content> getContents(int pageNumber){
        ArrayList<Content> contents = new ArrayList<>();
        ContentList contentList = new ContentList();
        if(pageNumber > 1 ){
            contents = contentList.getContents(AMOUNT_ON_PAGE, pageNumber);
        }
        else {
            contents = contentList.getContents(AMOUNT_ON_PAGE, NUMBER_FIRST_PAGE);
        }
        return contents;
    }

    public ArrayList<Content> getContents(int pageNumber, ArticleStatus articleStatus){
        ArrayList<Content> contents = new ArrayList<>();
        ContentList contentList = new ContentList(articleStatus);
        if(pageNumber > 1 ){
            contents = contentList.getContents(AMOUNT_ON_PAGE, pageNumber);
        }
        else {
            contents = contentList.getContents(AMOUNT_ON_PAGE, NUMBER_FIRST_PAGE);
        }
        return contents;
    }

    public String getContentIdInString(ArrayList<Content> contents){
        StringBuffer stringBuffer = new StringBuffer();
        int listSize = contents.size();
        int currentItem = 1;

        for (Content content: contents){
            stringBuffer.append(content.getId().toString());
            if(currentItem < listSize){
                stringBuffer.append(",");
                currentItem++;
            }
        }

        return stringBuffer.toString();
    }


    private int getAmountOnPage(int amountPage, ContentList contentList){
        int residue = contentList.sizeList%AMOUNT_ON_PAGE;
        if(residue == 0){
            amountPage = contentList.sizeList/AMOUNT_ON_PAGE;
        }
        else {
            amountPage = contentList.sizeList/AMOUNT_ON_PAGE +1;
        }
        return amountPage;
    }

}
