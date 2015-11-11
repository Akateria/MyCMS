package servlets;

import builders.ContentJSONBuilder;
import hibernate.tables.Content;
import providers.ContentListProvider;
import providers.ContentProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/rest/website/tagsearch")
public class ContentSearchServlet extends HttpServlet {

    private static final String KEY_PAGE = "page";
    private static final String KEY_WORD = "word";
    private static final String KEY_TAGS = "tags";

    private String pageID;
    private String word;
    private String tags;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getParametersFromRequest(req);
        String jsonString = getJSONObject();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void getParametersFromRequest(HttpServletRequest req){
        pageID = req.getParameter(KEY_PAGE);
        word = req.getParameter(KEY_WORD);
        tags = req.getParameter(KEY_TAGS);
    }

    private String getJSONObject(){

        ContentListProvider contentListProvider = new ContentListProvider();
        ContentProvider contentProvider = new ContentProvider();
        ContentJSONBuilder contentJSONBuilder = new ContentJSONBuilder();

        Integer page = new Integer(pageID);

        ArrayList<Content> contents = contentListProvider.getContents(page);
        String contentsIdArray = contentListProvider.getContentIdInString(contents);

        ArrayList<Content> contentsWord = contentProvider.getContentByString(word, contentsIdArray);
        ArrayList<Content> contentsTag = contentProvider.getContentByTags(tags, contentsIdArray);

        ArrayList<Content> general = contentsWord;

        label: for (Content contentWord: contentsWord){
            for (Content contentTag: contentsTag){
                if(contentWord.equals(contentTag)){
                    break label;
                }
                else {
                    general.add(contentTag);
                }
            }
        }

        String contentGeneral = contentJSONBuilder.getJSONContentInString(general);

        return contentGeneral;
    }

}
