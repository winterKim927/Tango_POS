CREATE TABLE order_summary(
	order_summary_idx NUMBER PRIMARY KEY
	, payment_number NUMBER
	, price NUMBER
	, paydate date DEFAULT sysdate
)

CREATE SEQUENCE seq_order_summary_idx
INCREMENT BY 1
START WITH 1;

CREATE TABLE order_detail(
	order_detail_idx NUMBER PRIMARY KEY
	, order_summary_idx NUMBER
	, menu varchar2(20)
	, quantity NUMBER
	, CONSTRAINT fk_order_summary_detail FOREIGN KEY (order_summary_idx) 
	REFERENCES order_summary(order_summary_idx)
);

CREATE SEQUENCE seq_order_detail_idx
INCREMENT BY 1
START WITH 1;

SELECT sum(TOTALPRICE) AS DailyRevenue FROM ORDER_SUMMARY WHERE PAYDATE BETWEEN TO_DATE('20221228') AND TO_DATE('20221228'+1);

select to_date('20221228', 'yy/mm/dd') from dual;

SELECT sum(TOTALPRICE) AS DailyRevenue FROM ORDER_SUMMARY WHERE PAYDATE BETWEEN TO_DATE('20221231') AND TO_DATE('20221231')+1;

DROP TABLE POS_USER; 
CREATE TABLE pos_user(
	pos_user_idx NUMBER PRIMARY KEY
	, name varchar2(20)
	, id varchar2(20) UNIQUE NOT NULL
	, pass varchar2(20) NOT null 
	, regdate date DEFAULT sysdate
);

DROP SEQUENCE seq_pos_user_idx;
CREATE SEQUENCE seq_pos_user_idx
INCREMENT BY 1
START WITH 1;

ALTER TABLE JAVASE.POS_USER MODIFY PASS VARCHAR2(20);