-- 한 번에 여러 행 insert:
-- INSERT ALL
--    INTO t (col1, col2, col3) VALUES ('val1_1', 'val1_2', 'val1_3')
--    INTO t (col1, col2, col3) VALUES ('val2_1', 'val2_2', 'val2_3')
--    INTO t (col1, col2, col3) VALUES ('val3_1', 'val3_2', 'val3_3')
--    .
--    .
--    .
-- SELECT 1 FROM DUAL;

-- EMP
INSERT ALL
   INTO EMP (EMPNO, ENAME, PH, EMAIL, HIREDATE, SAL, PWD) VALUES (1001, '배솔반', '010-1234-1234', 'bsb@hello.ware', to_date('2022-01-01', 'YYYY-MM-DD'), 99999, 'bsb')
   INTO EMP (EMPNO, ENAME, PH, EMAIL, HIREDATE, SAL, PWD) VALUES (1002, '이현동', '010-2222-2222', 'lhd@hello.ware', to_date('2022-12-31', 'YYYY-MM-DD'), 88888, 'LHD')
   INTO EMP (EMPNO, ENAME, PH, EMAIL, HIREDATE, SAL, PWD) VALUES (1003, '김준영', '010-3333-3333', 'kjy@hello.ware', to_date('1998-11-10', 'YYYY-MM-DD'), 12345, 'qq1541')
SELECT 1 FROM DUAL;
COMMIT;
-- DEPT
INSERT ALL
   INTO DEPT (DEPTNO, DNAME, DHEAD) VALUES (10, '기술팀', (select EMPNO from EMP where ENAME='배솔반'))
   INTO DEPT (DEPTNO, DNAME, DHEAD) VALUES (20, '인사팀', (select EMPNO from EMP where ENAME='김준영'))
SELECT 1 FROM DUAL;
COMMIT;
--SCHEDULE
INSERT ALL
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (1, 20, '부서 회의', to_date('2023-07-03,09:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-03,11:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (8, 20, '저녁 회식', to_date('2023-07-15,17:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-15,20:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (2, 20, '저녁 회식', to_date('2023-07-04,17:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-04,20:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (3, 20, '부서 회의', to_date('2023-07-10,09:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-10,11:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (4, 20, '저녁 회식', to_date('2023-07-11,17:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-11,20:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (5, 20, '저녁 회의', to_date('2023-07-03,16:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-03,18:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (6, 20, '저녁 회식', to_date('2023-07-05,17:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-05,20:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (7, 20, '저녁 회의', to_date('2023-07-10,16:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-10,18:00', 'YYYY-MM-DD,HH24:MI'))
SELECT 1 FROM DUAL;
COMMIT;
--출근일시
INSERT ALL
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (1, 1002, to_date('2023-06-26,08:59', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-26,18:30', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (2, 1001, to_date('2023-06-26,09:05', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-26,18:35', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (3, 1003, to_date('2023-06-26,08:50', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-26,18:20', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (4, 1002, to_date('2023-06-27,08:59', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-27,18:30', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (5, 1001, to_date('2023-06-27,09:05', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-27,18:35', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (6, 1003, to_date('2023-06-27,08:50', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-27,18:20', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (7, 1002, to_date('2023-06-28,08:59', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-28,18:30', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (8, 1001, to_date('2023-06-28,09:05', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-28,18:35', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (9, 1003, to_date('2023-06-28,08:50', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-28,18:20', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (11, 1001, to_date('2023-06-29,09:05', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-29,18:35', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (10, 1002, to_date('2023-06-29,08:59', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-29,18:30', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (12, 1003, to_date('2023-06-29,08:50', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-29,18:20', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (13, 1002, to_date('2023-06-30,08:59', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-30,18:30', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (14, 1001, to_date('2023-06-30,09:05', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-30,18:35', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (15, 1003, to_date('2023-06-30,08:50', 'YYYY-MM-DD,HH24:MI'), to_date('2023-06-30,18:20', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (16, 1002, to_date('2023-07-03,08:59', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-03,18:30', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (17, 1001, to_date('2023-07-03,09:05', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-03,18:35', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (18, 1003, to_date('2023-07-03,08:50', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-03,18:20', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (19, 1002, to_date('2023-07-04,08:59', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-04,18:30', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (20, 1001, to_date('2023-07-04,09:05', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-04,18:35', 'YYYY-MM-DD,HH24:MI'))
   INTO ATTEND(ATTNO, EMPNO, A_START, A_END) VALUES (21, 1003, to_date('2023-07-04,08:50', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-04,18:20', 'YYYY-MM-DD,HH24:MI'))
SELECT 1 FROM DUAL;
COMMIT;
--게시글
INSERT ALL
   INTO BOARD(B_NO, EMPNO, B_BODY, WDATE) VALUES(1, 1001, '배솔반님의-게시글1', to_TIMESTAMP('2023-06-26, 09:30:19', 'YYYY-MM-DD, HH24:MI:SS'))
   INTO BOARD(B_NO, EMPNO, B_BODY, WDATE) VALUES(2, 1002, '점심 뭐먹지? 투표', to_TIMESTAMP('2023-06-26, 11:30:03', 'YYYY-MM-DD, HH24:MI:SS'))
   INTO BOARD(B_NO, EMPNO, B_BODY, WDATE) VALUES(3, 1003, '생일 축하^^', to_TIMESTAMP('2023-06-27, 13:30:21', 'YYYY-MM-DD, HH24:MI:SS'))
   INTO BOARD(B_NO, EMPNO, B_BODY, WDATE) VALUES(4, 1001, '퇴근하고싶다', to_TIMESTAMP('2023-06-28, 15:28:50', 'YYYY-MM-DD, HH24:MI:SS'))
   INTO BOARD(B_NO, EMPNO, B_BODY, WDATE) VALUES(5, 1001, '오늘 점심은 햄버거', to_TIMESTAMP('2023-06-26, 11:30:11', 'YYYY-MM-DD, HH24:MI:SS'))
   INTO BOARD(B_NO, EMPNO, B_BODY, WDATE) VALUES(6, 1002, '회사 매출보고', to_TIMESTAMP('2023-06-29, 11:30:03', 'YYYY-MM-DD, HH24:MI:SS'))
   INTO BOARD(B_NO, EMPNO, B_BODY, WDATE) VALUES(7, 1003, '생일 축하^^', to_TIMESTAMP('2023-07-04, 09:21:21', 'YYYY-MM-DD, HH24:MI:SS'))
   INTO BOARD(B_NO, EMPNO, B_BODY, WDATE) VALUES(8, 1001, '전사 오늘 저녁 회식있습니다', to_TIMESTAMP('2023-07-04, 09:28:20', 'YYYY-MM-DD, HH24:MI:SS'))
SELECT 1 FROM DUAL;
COMMIT;