<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

ソート：<a href="/jmaster/ItemServlet2?action=sort&key=price_asc">価格の低い順</a>、
<a href="/jmaster/ItemServlet2?action=sort&key=price_desc">価格の高い順</a><br />

<form action="/jmaster/ItemServlet2" method="post">
追加：商品名<input type="text" name="name" />
価格<input type="text" name="price" size="5" />を<input type="submit" value="追加" />
<input type="hidden" name="action" value="add" />
</form>

<form action="/jmaster/ItemServlet2" method="post">
検索：<input type="text" name="price" size="5" />円以下の商品を
<input type="submit" value="検索" />
<input type="hidden" name="action" value="search" />
</form>

<form action="/jmaster/ItemServlet2" method="post">
削除：商品番号<input type="text" name="code" size="5" />
番の商品を<input type="submit" value="削除" />
<input type="hidden" name="action" value="delete" />
</form>

<form action="/jmaster/ItemServlet2" method="post">
修正：商品番号<input type="text" name="code" size="5" />番の商品の価格を
<input type="text" name="price" size="5" />に<input type="submit" value="変更" />
<input type="hidden" name="action" value="update" />
</form>
