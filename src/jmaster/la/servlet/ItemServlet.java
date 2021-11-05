package jmaster.la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmaster.la.bean.ItemBean;
import jmaster.la.dao.DAOException;
import jmaster.la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータは送信されないので、リクエストの文字コードの設定はしない
		// モデルを利用して全商品を取得
		try {
			// 全商品を取得
			ItemDAO dao = new ItemDAO();
			List<ItemBean> items = dao.findAll();
			// 取得した商品リストをリクエストスコープに登録
			request.setAttribute("items", items);
			// 次画面に遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/showItem.jsp");
			dispatcher.forward(request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			// エラーメッセージをリクエストスコープに登録
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/errInternal.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
