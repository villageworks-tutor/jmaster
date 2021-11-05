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

	@Nested
	@DisplayName("IteDAO#findByPriceメソッドのテスト")
	class TestFindByPrice {
		@Test
		@DisplayName("800円以下の商品は商品番号7の「パズルゲーム」である。")
		void testFindByPrice_01() throws Exception {
			// setup
			int target = 800;
			List<ItemBean> expected = new ArrayList<ItemBean>();
			expected.add(new ItemBean(7, "パズルゲーム", 780));
			// execute
			List<ItemBean> actual = sut.findByPrice(target);
			// verify
			for (int i = 0; i < actual.size(); i++) {
				assertThat(actual.get(i), is(new EqualToItem(expected.get(i))));
			}
		}
		@Test
		@DisplayName("500円以下の商品は登録されていない。")
		void testFindByPrice_02() throws Exception {
			// setup
			int target = 500;
			int expected = 0;
			// execute
			int actual = sut.findByPrice(target).size();
			// verify
			assertThat(actual, is(expected));
		}
		@Test
		@DisplayName("1000円以下の商品は")
		void testFindByPrice_03() throws Exception {
			// setup
			int target = 1000;
			List<ItemBean> expected = new ArrayList<ItemBean>();
			ItemBean bean = null;
			bean = new ItemBean(2, "MLB Fun", 980);
			expected.add(bean);
			bean = new ItemBean(5, "The Racer", 1000);
			expected.add(bean);
			bean = new ItemBean(6, "Space Wars 3", 1000);
			expected.add(bean);
			bean = new ItemBean(7, "パズルゲーム", 780);
			expected.add(bean);
			// execute
			List<ItemBean> actual = sut.findByPrice(target);
			// verify
			for (int i = 0; i < actual.size(); i++) {
				assertThat(actual.get(i), is(new EqualToItem(expected.get(i))));
			}
		}
	}

	@Nested
	@DisplayName("ItemDAO#sortByPriceメソッドのテスト")
	class sortByPrice {
		@Test
		@DisplayName("価格の低い順に並べ替える。")
		void testSortByPrice_01() throws Exception {
			// setup
			boolean target = true;
			List<ItemBean> expected = new ArrayList<ItemBean>();
			ItemBean bean = null;
			bean = new ItemBean(7, "パズルゲーム", 780);
			expected.add(bean);
			bean = new ItemBean(2, "MLB Fun", 980);
			expected.add(bean);
			bean = new ItemBean(5, "The Racer", 1000);
			expected.add(bean);
			bean = new ItemBean(6, "Space Wars 3", 1000);
			expected.add(bean);
			bean = new ItemBean(3, "料理BOOK！", 1200);
			expected.add(bean);
			bean = new ItemBean(4, "なつかしのアニメシリーズ", 2000);
			expected.add(bean);
			bean = new ItemBean(9, "Play the BasketBall", 2200);
			expected.add(bean);
			bean = new ItemBean(1, "Java基礎", 2500);
			expected.add(bean);
			bean = new ItemBean(8, "Invader Fighter", 3400);
			expected.add(bean);
			// execute
			List<ItemBean> actual = sut.sortByPrice(target);
			// verify
			for (int i = 0; i < actual.size(); i++) {
				assertThat(actual.get(i), is(new EqualToItem(expected.get(i))));
			}
		}
		@Test
		@DisplayName("価格の高い順に並べ替える。")
		void testSortByPrice_02() throws Exception {
			// setup
			boolean target = true;
			List<ItemBean> expected = new ArrayList<ItemBean>();
			ItemBean bean = null;
			bean = new ItemBean(8, "Invader Fighter", 3400);
			expected.add(bean);
			bean = new ItemBean(1, "Java基礎", 2500);
			expected.add(bean);
			bean = new ItemBean(9, "Play the BasketBall", 2200);
			expected.add(bean);
			bean = new ItemBean(4, "なつかしのアニメシリーズ", 2000);
			expected.add(bean);
			bean = new ItemBean(3, "料理BOOK！", 1200);
			expected.add(bean);
			bean = new ItemBean(5, "The Racer", 1000);
			expected.add(bean);
			bean = new ItemBean(6, "Space Wars 3", 1000);
			expected.add(bean);
			bean = new ItemBean(2, "MLB Fun", 980);
			expected.add(bean);
			bean = new ItemBean(7, "パズルゲーム", 780);
			expected.add(bean);
			// execute
			List<ItemBean> actual = sut.sortByPrice(!target);
			// verify
			for (int i = 0; i < actual.size(); i++) {
				assertThat(actual.get(i), is(new EqualToItem(expected.get(i))));
			}
		}
	}
}
