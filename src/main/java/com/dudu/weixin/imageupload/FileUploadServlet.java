package com.dudu.weixin.imageupload;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * 文件或者图片上传
 * Created by lizhen on 2017/7/29.
 */
@Controller
public class FileUploadServlet {
    /**
     * 日志打印
     */
    private static Logger log = LoggerFactory.getLogger(FileUploadServlet.class);

    /**
     * 真实的上传路径
     */
    private File uploadPath;

    /**
     * 缓存区的路径
     */
    private File tempPath;

    /**
     * @param request  请求
     * @param response 相应
     * @return 上传文件的路径
     */
    @ResponseBody
    @RequestMapping("/upload/UpLoad")
    public String ss(HttpServletRequest request, HttpServletResponse response) {
        String imagePath = "";
        /**
         * 获取上下文环境,添加自己的制定路径
         */
        uploadPath = new File(request.getSession().getServletContext().getRealPath("Views/images"));
        log.info("上传的路径uploadPath=====" + uploadPath);
        //如果目录不存在
        if (!uploadPath.exists()) {
            //创建目录
            uploadPath.mkdir();
        }
        //临时目录
        tempPath = new File(request.getSession().getServletContext().getRealPath("Views/images"));
        if (!tempPath.exists()) {
            //创建临时目录
            tempPath.mkdir();
        }

        /********************************使用 FileUpload 组件解析表单********************/

        //DiskFileItemFactory：创建 FileItem 对象的工厂，
        // 在这个工厂类中可以配置内存缓冲区大小和存放临时文件的目录。
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4096);
        factory.setRepository(tempPath);


        //ServletFileUpload：负责处理上传的文件数据，并将每部分的数据封装成一到 FileItem 对象中。
        //在接收上传文件数据时，会将内容保存到内存缓存区中，如果文件内容超过了 DiskFileItemFactory 指定的缓冲区的大小，
        //那么文件将被保存到磁盘上，存储为 DiskFileItemFactory 指定目录中的临时文件。
        //等文件数据都接收完毕后，ServletUpload再从文件中将数据写入到上传文件目录下的文件中


        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1000000 * 20);

        /*******************************解析表单传递过来的数据，返回List集合数据-类型:FileItem***********/

        try {
            //使用ServletFileUpload对象的parseRequest方法先把request对象中的数据解析
            List fileItems = upload.parseRequest(request);

            String itemNo = "";
            //Iterator iter = fileItems.iterator()取其迭代器
            //iter.hasNext()检查序列中是否还有元素
            for (Iterator iter = fileItems.iterator(); iter.hasNext(); ) {
                //获得序列中的下一个元素
                FileItem item = (FileItem) iter.next();
                //判断是文件还是文本信息
                //是普通的表单输入域
                if (item.isFormField()) {
                    if ("itemNo".equals(item.getFieldName())) {
                        itemNo = item.getString();
                    }
                }
                //是否为input="type"输入域
                if (!item.isFormField()) {
                    //上传文件的名称和完整路径
                    String fileName = item.getName();
                    log.info("上传文件的名称=" + fileName);
                    long size = item.getSize();
                    //判断是否选择了文件
                    if ((fileName == null || fileName.equals("")) && size == 0) {
                        continue;
                    }
                    //截取字符串 如：C:\WINDOWS\Debug\PASSWD.LOG
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());

                    // 保存文件在服务器的物理磁盘中：第一个参数是：完整路径（不包括文件名）第二个参数是：文件名称
                    //item.write(file);
                    //修改文件名和物料名一致，且强行修改了文件扩展名为gif
                    //item.write(new File(uploadPath, itemNo + ".gif"));
                    //将文件保存到目录下，不修改文件名
                    item.write(new File(uploadPath, fileName));
                    imagePath = uploadPath.toString() + "/" + fileName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return imagePath;

    }


}
