# Todo-application

### 프로젝트 ERD, API 명세 링크

https://jsbnote.notion.site/cbc77e08725a461e91ff76f0ebf43769?v=11e45a5ad5ac4b03bd804521ea43eb28&pvs=4

스파르타 코딩클럽 숙련주차 개인과제로 투두앱 백엔드 서버를 만드는 프로젝트이다.
API 명세서 작성, Entity와 ERD 설계, JWT와 Spring Security를 이용한 인증/인가 과정 구현, 예외 처리 등이 요구되는 작업이다.

- 논리 테이블은 유저 테이블, 할일 카드로 구성되어 있다.
    - 유저 테이블
        - PK 값인 user_id
        - 유저 필드
        - 비밀번호 필드
    - 할일 카드 테이블
        - PK 값인 todocard_id
        - FK 값인 user_id 를 참조한다. (username 을 사용하기 위함)
        - 제목 필드
        - 내용 필드
        - 작성일 필드
        - 수정일 필드
    - 댓글 테이블
        - PK 값인 comment_id
        - FK 값은 유저 테이블과 할일 카드 테이블에서 각각 참조한다.
        - 내용 필드