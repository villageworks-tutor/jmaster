package jmaster.chap11.dao.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import jmaster.la.bean.ItemBean;

public class EqualToItem extends TypeSafeMatcher<ItemBean> {

	/** 期待値 */
	private ItemBean expected;

	/** 異なる値 */
	private String difference;

	/**
	 * コンストラクタ
	 * @param expected ItemBeanクラスのインスタンス
	 */
	public EqualToItem(ItemBean expected) {
		this.expected = expected;
	}

	@Override
	public void describeTo(Description description) {
		description.appendValue(this.expected);
		description.appendText(difference).appendText("が異なっています。");
	}

	@Override
	protected boolean matchesSafely(ItemBean actual) {
		// 商品番号の一致チェック
		if (actual.getCode() != expected.getCode()) {
			difference = "商品番号";
			return false;
		}
		// 商品名一致チェック
		if (!actual.getName().equals(expected.getName())) {
			difference = "商品名";
			return false;
		}
		// 価格一致チェック
		if (actual.getPrice() != expected.getPrice()) {
			difference = "価格";
			return false;
		}
		return true;
	}
}
