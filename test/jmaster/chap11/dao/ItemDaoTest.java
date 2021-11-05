package jmaster.chap11.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import jmaster.chap11.dao.matcher.EqualToItem;
import jmaster.la.bean.ItemBean;
import jmaster.la.dao.ItemDAO;

class ItemDaoTest {

	// テスト対象クラス：system under test
	private ItemDAO sut;

	@BeforeEach
	void setUp() throws Exception {
		this.sut = new ItemDAO();
	}

	@Test
	@DisplayName("データベースsampleに接続できる。")
	void testConstructor() {
		assertThat(sut, is(instanceOf(ItemDAO.class)));
	}

	@Nested
	@DisplayName("ItemDAO#findAllメソッドのテスト")
	class TestFindAll {
		@Test
		@DisplayName("商品テーブルの全レコードを取得できる。")
		void testFindAll() throws Exception {
			// setup
			List<ItemBean> expected = new ArrayList<ItemBean>();
			ItemBean bean = null;
			bean = new ItemBean(1, "Java基礎", 2500);
			expected.add(bean);
			bean = new ItemBean(2, "MLB Fun", 980);
			expected.add(bean);
			bean = new ItemBean(3, "料理BOOK！", 1200);
			expected.add(bean);
			bean = new ItemBean(4, "なつかしのアニメシリーズ", 2000);
			expected.add(bean);
			bean = new ItemBean(5, "The Racer", 1000);
			expected.add(bean);
			bean = new ItemBean(6, "Space Wars 3", 1000);
			expected.add(bean);
			bean = new ItemBean(7, "パズルゲーム", 780);
			expected.add(bean);
			bean = new ItemBean(8, "Invader Fighter", 3400);
			expected.add(bean);
			bean = new ItemBean(9, "Play the BasketBall", 2200);
			expected.add(bean);
			// execute
			List<ItemBean> actual = sut.findAll();
			// verify
			for (int i = 0; i < actual.size(); i++) {
				assertThat(actual.get(i), is(new EqualToItem(expected.get(i))));
			}
		}
	}
}
