package servlets.images;


import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/imagine")
public class ImagineServlet extends HttpServlet {

    private static final String IMAGE_PATH = "//var//www//img//";
    //private static final String IMAGE_PATH = "D:\\pngcms\\";
    private static final String IMAGE_EXTENSION = ".png";

    private String imageName;
    private String imagePass;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        response.setContentType("image/jpeg");
        getParametersFromRequest(request);
        getFullImagePath();
        uploadImage(response);
    }

    private void getFullImagePath(){
        StringBuffer imagePassBuffer = new StringBuffer();
        imagePassBuffer.append(IMAGE_PATH);
        imagePassBuffer.append(imageName);
        imagePassBuffer.append(IMAGE_EXTENSION);
        imagePass = imagePassBuffer.toString();
    }

    private void getParametersFromRequest(HttpServletRequest request){
        imageName = request.getParameter("imageName");
    }

    private void uploadImage(HttpServletResponse response){
        try {

            ServletOutputStream out = response.getOutputStream();
            FileInputStream fin = new FileInputStream(imagePass.toString());

            BufferedInputStream bin = new BufferedInputStream(fin);
            BufferedOutputStream bout = new BufferedOutputStream(out);
            int ch = 0;
            while ((ch = bin.read()) != -1) {
                bout.write(ch);
            }
            fin.close();
            bin.close();
            bout.close();
            out.close();
        }
        catch (IOException e){
            System.out.print(e);
        }
    }
}
