-- p.189 emp�\����S�Ă��s�Ɨ��\������
SELECT * FROM emp;
SELECT code, name, age, tel FROM emp;

-- p.190 emp�\���疼�O�ƔN��̗�݂̂�\������
SELECT name, age FROM emp;

-- p.190 �N�40�ȏ�̎Ј���\������Fage >= 40�̉��Z�q��true�ƂȂ�s�݂̂�\������B
SELECT * FROM emp WHERE age >= 40;

-- p.190 �d�b�ԍ���07�Ŏn�܂�Ј���\������FWHERE��́utel LIKE '07%'�v�ƂȂ�B
SELECT * FROM emp WHERE tel LIKE '07%';

-- p.191 �N��̒Ⴂ���ɎЈ�����ׂ�FORDER BY��𗘗p����B
SELECT * FROM emp ORDER BY age;

-- p.191 �N��̍������ɎЈ�����ׂ�B
SELECT * FROM emp ORDER BY age DESC;

-- p.192 �u6, �g�c, 23, 0120-999-9999�v��ǉ�����B
INSERT INTO emp VALUES (6, '�g�c', 23, '0120-999-9999');
SELECT * FROM emp;

-- p.192 �u7, ��{�v��ǉ�����B
INSERT INTO emp (code, name) VALUES (7, '��{');
SELECT * FROM emp;

-- p.193 code��4�Ԃ̎Ј��̓d�b�ԍ���06-8888-8888�ɕύX����B
UPDATE emp SET tel = '06-8888-8888' WHERE code = 4;
SELECT * FROM emp;

-- p.194 code��5�Ԃ̎Ј����폜����B
DELETE FROM emp WHERE code = 5;
SELECT * FROM emp;