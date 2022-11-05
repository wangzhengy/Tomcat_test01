
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

//public class TestServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");
//        resp.setCharacterEncoding("utf-8");
//        String firstName = new String(req.getParameter("firstName"));
//        String lastName = new String(req.getParameter("lastName"));
//        //得到输出流
//        PrintWriter out = resp.getWriter();
//        out.println("<html>");
//        out.println("<head><meta charset=\"utf-8\"><title>Servlet</title></head>");
//        out.println("<body><center><center><h1>你的名字是:" + firstName + lastName + "</h1>"+
//                "<img src=\"G:\\JAVA\\Tomcat_test01\\img\\photo.jpg\">"+
//                "</center></body>");
//        out.println("</html>");
//        out.close();
//    }
//}


public class ShowImage extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public ShowImage() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      showImage(request, response);
//        downlodeImage(request, response);
    }

    public void showImage(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 读取方式
        // response.setContentType("application/zip");
        response.setContentType("image/jpeg");
        // 获取图片绝对路径
        String path = this.getServletContext().getRealPath("/");
        File file = new File(path + "/img/photo.jpg");
        // 创建文件输入流
        FileInputStream is = new FileInputStream(file);
        // 响应输出流
        ServletOutputStream out = response.getOutputStream();
        // 创建缓冲区
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        is.close();
        out.flush();
        out.close();

    }

    public void downlodeImage(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {

        // 获取图片绝对路径
        String path = this.getServletContext().getRealPath("/");
        File file = new File(path + "/img/photo.jpg");
        //设置头信息,内容处理的方式,attachment以附件的形式打开,就是进行下载,并设置下载文件的命名
        response.setHeader("Content-Disposition","attachment;filename="+file.getName());
        // 创建文件输入流
        FileInputStream is = new FileInputStream(file);
        // 响应输出流
        ServletOutputStream out = response.getOutputStream();
        // 创建缓冲区
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        is.close();
        out.flush();
        out.close();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }

}
