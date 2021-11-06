-- p.189 emp表から全てお行と列を表示する
SELECT * FROM emp;
SELECT code, name, age, tel FROM emp;

-- p.190 emp表から名前と年齢の列のみを表示する
SELECT name, age FROM emp;

-- p.190 年齢が40以上の社員を表示する：age >= 40の演算子がtrueとなる行のみを表示する。
SELECT * FROM emp WHERE age >= 40;

-- p.190 電話番号が07で始まる社員を表示する：WHERE句は「tel LIKE '07%'」となる。
SELECT * FROM emp WHERE tel LIKE '07%';

-- p.191 年齢の低い順に社員を並べる：ORDER BY句を利用する。
SELECT * FROM emp ORDER BY age;

-- p.191 年齢の高い順に社員を並べる。
SELECT * FROM emp ORDER BY age DESC;

-- p.192 「6, 吉田, 23, 0120-999-9999」を追加する。
INSERT INTO emp VALUES (6, '吉田', 23, '0120-999-9999');
SELECT * FROM emp;

-- p.192 「7, 坂本」を追加する。
INSERT INTO emp (code, name) VALUES (7, '坂本');
SELECT * FROM emp;

-- p.193 code列4番の社員の電話番号を06-8888-8888に変更する。
UPDATE emp SET tel = '06-8888-8888' WHERE code = 4;
SELECT * FROM emp;

-- p.194 code列が5番の社員を削除する。
DELETE FROM emp WHERE code = 5;
SELECT * FROM emp;