package jmaster.la.bean;

import java.io.Serializable;

public class ItemBean implements Serializable {

	/**
	 * クラスフィールド
	 */
	private int code;			// 商品番号
	private String name;	// 商品名
	private int price;		// 価格

	/**
	 * デフォルトコンストラクタ
	 */
	public ItemBean() {}

	/**
	 * コンストラクタ
	 * @param code	商品番号
	 * @param name	商品名
	 * @param price	価格
	 */
	public ItemBean(int code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ItemBean [code=" + code + ", name=" + name + ", price=" + price + "]";
	}
}
