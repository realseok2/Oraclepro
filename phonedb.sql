--  전화번호부 sql 스크립트 ( person 테이블, 시퀀스 삭제)
drop table person;
drop SEQUENCE seq_person_id;

--  person 테이블 생성
CREATE TABLE person (
    person_id   number(5),
    name        varchar2(30)    not null,
    hp          varchar2(20),
    company     varchar2(20),
    PRIMARY KEY(person_id)
);

--  person 테이블 시퀀스 생성
CREATE SEQUENCE seq_person_id
INCREMENT by 1
start with 1;

--  person 데이터 입력
INSERT INTO person VALUES (seq_person_id.nextval, '이효리', '010-1111-1111', '02-1111-1111');
INSERT INTO person VALUES (seq_person_id.nextval, '정우성', '010-2222-2222', '02-2222-2222');
INSERT INTO person VALUES (seq_person_id.nextval, '유재석', '010-3333-3333', '02-3333-3333');
INSERT INTO person VALUES (seq_person_id.nextval, '이정재', '010-4444-4444', '02-4444-4444');
INSERT INTO person VALUES (seq_person_id.nextval, '서장훈', '010-5555-5555', '02-5555-5555');

--  커밋
commit;

--------------------------------------------------------------------------------------------
------------------------  여기까지 실행하면 데이터가 초기화 됩니다. -----------------------------
--------------------------------------------------------------------------------------------

--  전체 리스트 보기
select      person_id,
            name,
            hp,
            company
from        person;

--  이정재 데이터 수정
update      person
set         hp = '010-9999-9999',
            company = '02-9999-9999'
where       person_id = 4;

--  서장훈 데이터 삭제
delete from   person
where         person_id = 5;

--  유 or 2 검색
select
    *
from    person
where   name        like   '%유%'
or      hp          like   '%2%'
or      company     like   '%2%';