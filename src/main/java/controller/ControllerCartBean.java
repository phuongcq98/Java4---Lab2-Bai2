package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.CartBean;
import cart.ProductDTO;
import model.Product;

/**
 * Servlet implementation class ControllerCartBean
 */
public class ControllerCartBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerCartBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String action = request.getParameter("action");
			if (action.equals("Add to Cart")) {
				HttpSession session = request.getSession(true);
				CartBean shop = (CartBean) session.getAttribute("SHOP");
				if (shop == null) {
					shop = new CartBean();
				}
				String code = request.getParameter("txtCode");
				String name = request.getParameter("txtName");
				String pri = request.getParameter("txtPrice");
				float fpri = Float.parseFloat(pri);
				Product s = new Product(code, name, fpri);
				ProductDTO sp = new ProductDTO(s);
				shop.addSanPham(sp);
				session.setAttribute("SHOP", shop);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else if (action.equals("View Cart")) {
				RequestDispatcher rd = request.getRequestDispatcher("showcart.jsp");
				rd.forward(request, response);
			} else if (action.equals("AddMore")) {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else if (action.equals("Remove")) {
				String[] list = request.getParameterValues("rmv");
				if (list != null) {
					HttpSession session = request.getSession();
					if (session != null) {
						CartBean shop = (CartBean) session.getAttribute("SHOP");
						if (shop != null) {
							for (int i = 0; i < list.length; i++) {
								shop.removeSanPham(list[i]);
							}
							session.setAttribute("SHOP", shop);
						}
					}
				}
				String url = "ControllerCartBean?action=View Cart";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
