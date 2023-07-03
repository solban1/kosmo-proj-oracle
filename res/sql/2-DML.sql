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

-- DEPT
INSERT ALL
   INTO DEPT (DEPTNO, DNAME, DHEAD) VALUES (10, '기술팀', (select EMPNO from EMP where ENAME='배솔반'))
   INTO DEPT (DEPTNO, DNAME, DHEAD) VALUES (20, '인사팀', (select EMPNO from EMP where ENAME='김준영'))
SELECT 1 FROM DUAL;

--SCHEDULE
INSERT ALL
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (1, (select DEPTNO from DEPT where DEPTNO='20'), '부서 회의', to_date('2023-07-03,09:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-03,11:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (2, (select DEPTNO from DEPT where DEPTNO='20'), '저녁 회식', to_date('2023-07-04,17:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-04,20:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (3, (select DEPTNO from DEPT where DEPTNO='20'), '부서 회의', to_date('2023-07-10,09:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-10,11:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (4, (select DEPTNO from DEPT where DEPTNO='20'), '저녁 회식', to_date('2023-07-11,17:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-11,20:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (5, (select DEPTNO from DEPT where DEPTNO='20'), '저녁 회의', to_date('2023-07-03,16:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-03,18:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (6, (select DEPTNO from DEPT where DEPTNO='20'), '저녁 회식', to_date('2023-07-05,17:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-05,20:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (7, (select DEPTNO from DEPT where DEPTNO='20'), '저녁 회의', to_date('2023-07-10,16:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-10,18:00', 'YYYY-MM-DD,HH24:MI'))
   INTO SCHEDULE (S_NO, DEPTNO, S_BODY, S_START, S_END) VALUES (8, (select DEPTNO from DEPT where DEPTNO='20'), '저녁 회식', to_date('2023-07-15,17:00', 'YYYY-MM-DD,HH24:MI'), to_date('2023-07-15,20:00', 'YYYY-MM-DD,HH24:MI'))
SELECT 1 FROM DUAL;
COMMIT;
