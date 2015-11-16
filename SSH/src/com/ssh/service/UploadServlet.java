package com.ssh.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssh.util.Constants;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(UploadServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		String interfaceName = request.getParameter("cfInterface");
		request.setCharacterEncoding("utf-8");
		// 判断提交过来的表单是否为文件上传菜单
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String filePathName = System.getProperty(Constants.UPLOAD_PATH);
		String fileName = null;
		if (isMultipart) {
			// 构建一个文件上传对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			Iterator items;
			try {
				// 解析表单中提交的所有文件
				items = upload.parseRequest(request).iterator();
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();
					if (!item.isFormField()) {
						// 取出上传文件的文件名
						String name = item.getName();
//						String postprefix = name.substring(name.indexOf("."));
//
//						name = String.format(
//								"%1$tY-%1$tm-%1$td%1$tH-%1$tM-%1$tS%1$tL",
//								new Date())
//								+ postprefix;

						// 取得上传文件以后的存储路径
						fileName = name.substring(name.lastIndexOf('\\') + 1,
								name.length());

						// 上传文件以后的存储路径是否存在
						if (!(new File(filePathName).isDirectory())) {
							new File(filePathName).mkdir();
						}

						String path = filePathName + File.separator + name;

						// 上传文件
						File uploaderFile = new File(path);
						item.write(uploaderFile);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				logger.info("上传文件："+fileName+" 失败...");
				return;
			}
			response.setContentType("application/json");
			PrintWriter resp = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("state", true);
			result.put("name", fileName);
			resp.print(result);
			resp.flush();
			resp.close();
			logger.info("上传文件："+fileName+" 成功!");
		}
	}
}
