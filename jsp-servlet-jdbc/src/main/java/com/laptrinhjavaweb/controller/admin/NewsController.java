package com.laptrinhjavaweb.controller.admin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/admin-news" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class NewsController extends HttpServlet {

	@Inject
	private INewsService newsService;

	@Inject
	private ICategoryService categoryService;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Điểm trung gian giao tiếp giữa Controller - View
		NewsModel model = FormUtil.toModel(NewsModel.class, request);
		String view = "";
		if (model.getType() != null && model.getType().equals(SystemConstant.LIST)) {
			view = "/views/admin/news/list.jsp";
			Pageable pageable = new PageRequest(model.getPage(), model.getMaxPageItems(),
					new Sorter(model.getSortName(), model.getSortBy()));

			//Hiển thị danh sách -> Lấy List ra 
			model.setListResult(newsService.findAll(pageable));	
			model.setTotalItems(newsService.getTotalItems());
			
			//totalPage = Tổng số item trên 1 page / maxPageItem
			//Toán tử lấy chẳn (int) Math.ceil((double).../ ...)
			model.setTotalPages((int) Math.ceil((double) model.getTotalItems() / (double) model.getMaxPageItems()));

		} else if (model.getType() != null && model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				// gửi dl lên services
				model = newsService.findOne(model.getId());
			}
			// trả về model
			request.setAttribute("categories", categoryService.findAll(null));
			view = "/views/admin/news/edit.jsp";
		}
		MessageUtil.showMessage(request);
		
		//Các controller trả về View đều cần SystemConstant.MODEL
		request.setAttribute(SystemConstant.MODEL, model);
		request.setAttribute("contextPath", request.getContextPath() + File.separator + "images");
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsModel model = new NewsModel();

		String fileName = "";
		// xử lý lưu file vào server
		if (ServletFileUpload.isMultipartContent(request)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);

			// upload path
			String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			List<FileItem> formItems;
			try {
				formItems = upload.parseRequest(request);

				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						// nếu item là file
						if (!item.isFormField()) {
							// nếu có upload file hình và đang thực hiện thao tác thêm
							// thì mới lưu file hình và lưu fileName để lưu vào db
							if (!item.getName().isEmpty()) {
								fileName = "news-" + new File(item.getName()).getName() + "-"
										+ System.currentTimeMillis();
								String filePath = uploadPath + File.separator + fileName;
								File storeFile = new File(filePath);
								item.write(storeFile);
							}

							// nếu item là form field
						} else {
							String fieldName = item.getFieldName();
							String fieldValue = item.getString(StandardCharsets.UTF_8.name());

							// nếu là trường id thì cần chuyển thành kiểu Long
							if (fieldName.equals("id") && !fieldValue.isEmpty()) {
								Field field = model.getClass().getSuperclass().getDeclaredField(fieldName);
								field.setAccessible(true);
								field.set(model, Long.parseLong(fieldValue));
								
							} else if (!fieldName.equals("id")) {
								Field field = model.getClass().getDeclaredField(fieldName);
								field.setAccessible(true);
								field.set(model, fieldValue);
							}
							
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// lưu dữ liệu
		UserModel currentUser = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		
		model.setThumbnail(fileName);
		// lưu mới
		if (model.getId() == null) {
			model.setCreatedBy(currentUser.getUserName());
			model = newsService.save(model);

			if (model != null) {
				response.sendRedirect(request.getContextPath() + "/admin-news?type=edit&id=" + model.getId()
						+ "&message=insert_success");
				return;
			}
			// cập nhật
		} else {
			model.setModifiedBy(currentUser.getUserName());
			model = newsService.update(model);

			if (model != null) {
				response.sendRedirect(request.getContextPath() + "/admin-news?type=edit&id=" + model.getId()
						+ "&message=update_success");
				return;
			}
		}

		response.sendRedirect(
				request.getContextPath() + "/admin-news?type=list&maxPageItems=5&page=1&message=error_system");
	}
}
