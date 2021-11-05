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
 * Servlet implementation class ItemServlet2
 */
@WebServlet("/ItemServlet2")
public class ItemServlet2 extends HttpServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// パラメータの解析
		String action = request.getParameter("action");
		try {
			// モデルのDAOのインスタンス化
			ItemDAO dao = new ItemDAO();
			// actionキーによる処理の分岐
			if (action == null || action.isEmpty()) {
				// パラメータなしの場合：商品の全件検索
				List<ItemBean> items = dao.findAll();
				// 検索結果をリクエストスコープに登録
				request.setAttribute("items", items);
				// 画面遷移
				this.gotoPage(request, response, "/showItem2.jsp");
			} else if (action.equals("search")) {
				// リクエストパラメータのactionキーが「検索（search）」の場合
				// リクエストパラメータを取得
				String priceString = request.getParameter("price");
				int price = Integer.parseInt(priceString);
				List<ItemBean> items = dao.findByPrice(price);
				// 検索結果をリクエストスコープに登録
				request.setAttribute("items", items);
				// 画面遷移
				this.gotoPage(request, response, "/showItem2.jsp");
			} else if (action.equals("sort")) {
				// リクエストパラメータのactionキーが「ソート（sort）」の場合
				// リクエストパラメータを取得
				String key = request.getParameter("key");
				List<ItemBean> items = null;
				if (key.equals("price_asc")) {
					items = dao.sortByPrice(true);
				} else {
					items = dao.sortByPrice(false);
				}
				// 並べ替え結果をリクエストスコープに登録
				request.setAttribute("items", items);
				// 画面遷移
				this.gotoPage(request, response, "/showItem2.jsp");
			} else if (action.equals("add")) {
				// リクエストパラメータのactionキーが「追加（add）」の場合
				// リクエストパラメータを取得
				String name = request.getParameter("name");
				String priceString = request.getParameter("price");
				int price = Integer.parseInt(priceString);
				// 新規追加するItemBeanクラスをインスタンス化
				ItemBean item = new ItemBean();
				item.setName(name);
				item.setPrice(price);
				// 商品を追加
				dao.add(item);
				// 全件検索を実行
				List<ItemBean> items = dao.findAll();
				// 検索結果をリクエストスコープに登録
				request.setAttribute("items", items);
				// 画面遷移
				this.gotoPage(request, response, "/showItem2.jsp");
			} else if (action.equals("delete")) {
				// リクエストパラメータのactionキーが「削除（delete）」の場合
				// リクエストパラメータを取得
				String codeString = request.getParameter("code");
				int code = Integer.parseInt(codeString);
				// 削除を実行
				dao.delete(code);
				// 全件検索を実行
				List<ItemBean> items = dao.findAll();
				// 検索結果をリクエストスコープに登録
				request.setAttribute("items", items);
				// 画面遷移
				this.gotoPage(request, response, "/showItem2.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message","内部エラーが発生しました。");
			this.gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
