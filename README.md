# 키친포스
서비스 설명
```
음식점에서 고객에게 식사 서비스를 위해 필요한 상품, 메뉴, 주문(포장, 매장 식사), 결제와 관련된 기능을 제공한다.
 - `상품, 메뉴, 메뉴 그룹`을 통해 고객에게 제공하는 음식 종류를 표현할 수 있다.
 - 손님은 `메뉴` 단위로 음식을 주문할 수 있으며, `매장 식사`인지 `포장`인지 여부를 주문 상태를 통해 표현할 수 있다.
 - 매장 식사의 경우 배정된 테이블 단위로 주문을 처리 할 수 있다.
 - 단체 손님의 경우 여러 테이블을 하나의 테이블 그룹 단위로 묶어 주문을 처리 할 수 있다.
```

## 요구 사항

### 상품
> 고객이 주문할 수 있도록 메뉴에 등록되는 주문 대상
* 상품을 `등록`할 수 있다.
* 상품의 `목록을 조회`할 수 있다.
* 속성 : `상품 번호`, `상품명`, `가격` 
* 제약 사항 :
    * `동일한 이름`을 가진 상품은 `등록할 수 없다`.
    * `상품의 가격`이 `0원 미만`이면 `등록할 수 없다`.

### 메뉴 그룹
> 메뉴를 표현하는 특정 주제(테마)
> e.g. 한마리메뉴, 신메뉴, 추천 메뉴
* 메뉴 그룹을 `등록`할 수 있다.
* 메뉴 그룹 `목록을 조회`할 수 있다.
* 속성 : `메뉴 그룹 번호`, `메뉴 그룹명`
* 제약 사항 :
    * `동일한 이름`을 가진 메뉴 그룹은 `등록할 수 없다`.
    
### 메뉴
> 고객이 상품을 주문하는 단위
* 메뉴를 `등록`할 수 있다.
* 메뉴 `목록을 조회`할 수 있다.
* 속성 : `메뉴 번호`, `메뉴명`, `메뉴 가격`, `메뉴그룹 번호`, `메뉴 상품 목록`
* 제약 사항 : 
    * `동일한 이름`을 가진 메뉴는 `등록 할 수 없다`.
    * 메뉴의 가격이 `0원 미만`이면 `등록할 수 없다`.
    * 메뉴의 가격이 `메뉴 상품 가격의 총 합보다 큰 경우` 메뉴를 `등록할 수 없다`.
    * `어떤 메뉴`(카테고리, 테마)인지를 표현하는 `메뉴 그룹`이 포함되어야 한다.
    * `메뉴의 주문 대상`을 표현하는 `메뉴 상품`이 포함되어야 한다.

### 메뉴 상품
> 메뉴에 구성된 상품 정보, 해당 메뉴에 어떤 상품이 몇개로 구성되었는지를 표현
* 속성 : `메뉴 순서(seq)`, `메뉴 번호`, `상품 번호`, `수량`
* 제약 사항 : 
    * 하나의 `메뉴 상품`은 `여러 메뉴`에 `포함될 수 없다.`.
        * e.g. `한 마리더!`메뉴 : `후라이드 + 간장`, `후라이드 + 양념`
        * e.g. `바삭 바삭!`메뉴 : `후라이드 + 닭똥집 튀김`
        * > `한 마리더!` 메뉴의 `후라이드`와 `바삭 바삭!` 메뉴의 `후라이드`는 같은 상품이지만 다른 메뉴에 포함된 `다른 메뉴 상품` 으로 구분할 수 있다. 

### 주문 테이블
> 식당에 위치한 식탁, 손님이 매장에서 식사하는 경우 할당한다.
* 매장 식사를 위한 주문 테이블을 `등록` 할 수 있다.
* 주문 테이블 `목록을 조회`할 수 있다.
* `매장 식사 주문` 시, `주문 테이블을 할당`할 수 있다.
* 특정 주문 테이블의 `사용 가능 여부를 변경`할 수 있다.
* 특정 주문 테이블의 `손님 수를 변경`할 수 있다.
* 속성 : `식탁 번호`, `테이블 그룹 번호`, `손님 수`, `식탁 사용 가능 여부(empty)`
* 제약 사항 :
    * `테이블 그룹`에 속한 주문 테이블은 사용 가능 여부를 `변경할 수 없다`.
    *  `조리중`이거나 `식사중`인 주문 테이블은 사용 가능 여부를 `변경할 수 없다`.
    * `비어 있는` 주문 테이블의 손님 수는 `변경할 수 없다`.

### 테이블 그룹
> 단체 손님의 경우, 여러 테이블의 주문 항목을 통합 하기 위한 단위
* `단체 손님`을 위해 `여러 주문 테이블`을 `하나의 테이블 그룹`으로 묶을 수 있다.
* `테이블 그룹`으로 묶인 여러 `주문 테이블`을 `해제` 할 수 있다.
* 속성 : `테이블 그룹 번호`, `생성일시`, `테이블 번호 목록`
* 제약 사항 :
    * `주문 테이블`이 `2개 미만`인 경우 테이블 그룹으로 `묶을 수 없다`.
    * `비어 있는` 주문 테이블은 테이블 그룹으로 `묶을 수 없다`.
    * `다른 테이블 그룹`에 포함된 주문 테이블은 테이블 그룹으로 `묶을 수 없다`. 
    
### 주문
> 하나의 주문 단위를 표현한다. 
* 고객은 `메뉴`를 `주문`할 수 있다.
* 고객은 `메뉴`를 `추가 주문`할 수 있다.
* 고객이 `주문한 메뉴`는 `주문 항목`으로 표현할 수 있다.
* 고객이 `주문`한 각 `메뉴의 순서`를 `구분`할 수 있다.
* 주문은 `조리`, `식사`, `계산 완료`의 `주문 상태`로 표현할 수 있다.
* 주문 상태는 `조리 -> 식사 -> 계산 완료` `순으로 변경`할 수 있다.
* 속성 : `주문 번호`, `주문 테이블 번호`, `주문 상태`, `주문 시간`, `주문 상품 목록`
* 제약 사항 :
    * `주문 항목`이 없는 경우 `주문 할 수 없다`.
    * `메뉴`가 없는 경우 `주문 할 수 없다`.
    * `비어 있는` 주문 테이블은 `주문 할 수 없다`.
    * 주문이 `완료 상태`인 경우 주문 상태를 `변경할 수 없다`.

### 주문 상태
> 주문을 진행 정도를 표현하는 상태 정보 
* `조리`, `식사`, `계산 완료`의 주문 상태로 표현할 수 있다.
* 주문 상태는 `조리 -> 식사 -> 계산 완료` 순으로 변경할 수 있다.
* 속성 : `조리(COOKING)`, `식사(MEAL)`, `계산완료(COMPLETION)`

### 주문 항목
> 고객이 주문한 각 메뉴를 표현한다. 
> e.g. 한 테이블에서 치킨과 양념치킨을 주문하는 경우 주문 항목은 치킨, 양념 치킨
* 속성 : `주문 순서(seq)`, `주문 번호`, `메뉴 번호`, `주문 수량`

---
## [Step.1] 테스트를 통한 코드 보호 TODO List
### 목표 테스트로 전체 로직 보호하기

상품 관련 테스트 작성
  - [x] 상품 생성
  - [x] 상품 목록 조회

메뉴 관련 테스트 작성
  - [x] 메뉴 생성
    - [x] 메뉴 가격이 [null, 음수]인 경우, 예외 발생 검증
    - [x] 존재하지 않는 메뉴 그룹 정보가 포함된 메뉴를 생성하는 경우, 예외 발생 검증
    - [x] 메뉴에 구성되는 메뉴 상품이 존재하지 않는 경우, 예외 발생 검증
    - [x] 메뉴가 가격이 메뉴에 구성된 각 메뉴 상품 가격의 총합보다 큰 경우, 예외 발생 검증
  - [x] 메뉴 목록 조회
  
메뉴 그룹 관련 테스트 작성
  - [x] 메뉴 그룹 생성
  - [x] 메뉴 그룹 목록 조회

메뉴 상품 관련 테스트 작성
  - [x] 메뉴 상품 요청 객체와 일치하는 상품을 조회하여 메뉴 상품 반환
  
주문 테이블 관련 테스트 작성
  - [x] 주문 테이블 생성 
  - [x] 주문 테이블 목록 조회
  - [x] 주문 테이블의 사용 가능 여부 변경
    - [x] 존재하지 않는 주문 테이블의 상태 변경 시, 예외 발생 검증 
    - [x] 단체 지정된 주문 테이블의 사용 가능 상태 변경 시, 예외 발생 검증
    - [x] 조리중이거나 식사중인 주문 테이블의 사용 가능 상태 변경 시, 예외 발생 검증
  - [x] 주문 테이블의 객수 변경
    - [x] 존재하지 않는 주문 테이블의 객수 변경 시, 예외 발생 검증
    - [x] 비어있는 주문 테이블의 객수 변경 시, 예외 발생 검증
  
주문 테이블 그룹 관련 테스트 작성
  - [x] 테이블 그룹 생성
    - [x] 그룹핑할 주문 테이블이 2개 미만인 경우 예외 발생 검증
    - [x] 존재하지 않는 주문 테이블이 포함된 경우 예외 발생 검증
    - [x] 비어있는 주문 테이블이 포함된 경우 예외 발생 검증
    - [x] 이미 그룹핑 된 주문 테이블이 포함된 경우 예외 발생 검증
  - [x] 테이블 그룹을 해제
    - [x] 조리중이거나 식사중인 주문 테이블이 포함된 경우 예외 발생 검증

주문 관련 테스트 작성
  - [x] 주문 생성
    - [x] 주문 항목이 없는 주문을 생성하는 경우 예외 발생 검증
    - [x] 존재하지 않는 메뉴를 주문한 경우 예외 발생 검증
    - [x] 주문 테이블이 존재하지 않는 주문을 생성하는 경우 예외 발생 검증
    - [x] 주문에 포함된 주문 테이블이 비어있는 경우(주문을 요청한 테이블이 `isEmpty() = true`인 경우) 예외가 발생 검증
  - [x] 주문 목록 조회
  - [x] 주문 상태를 변경
    - [x] 존재하지 않는 주문의 주문 상태를 수정하는 경우 예외 발생 검증
    - [x] 주문 상태 값이 없는 주문의 주문 상태를 수정하는 경우 예외 발생 검증
    - [x] 완료 상태인 주문의 주문 상태를 수정하는 경우 예외 발생 검증

## [Step.2] 서비스 리팩터링 TODO List
목표 : 서비스 계층의 비지니스 로직을 도메인 계층으로 이동 
  - 외부 의존성에 영향 없이 각 도메인이 담당하는 로직을 수행
  - 외부에서 도메인의 필드에 접근하여 로직을 처리 하지 않고, 도메인이 직접 필드의 상태 변경을 처리 
  - 도메인의 속성이 외부로 분산되어 유지보수성이 저하되는 현상을 방지하고, 도메인에 대한 응집도를 높여 표현력을 향상

진행 순서
1. Service 계층 리팩터링 
  - 각 서비스 계층에 작성된 기능 목록을 작성
  - 도메인 단위로 테스트를 작성, 서비스 계층의 기능을 도메인으로 이동

2. Repository 계층 리팩터링
  - 도메인을 영속성 단위로 표현하고 연관 도메인 간의 연관 관계를 정의
  - 도메인 필드를 대상으로 일급 컬렉션, 원시 타입 포장 객체 적용
  - 리팩터링 도메인을 대상으로 기존 DAO에 작성된 영속처리 부를 JPA로 이관

3. Controller 계층 리팩터링  - 엔티티로 명시된 요청/응답 부를 엔티티로 전달할 수 있는 요청/응답 DTO 객체로 변경
  
진행 시 주의 사항
- ORM 사용으로 발생할 수 있는 다양한 문제를 인지하고 방지
- 도메인 계층의 Getter/Setter 사용을 지양

---

상품
  - 기능 목록
    - [x] 상품 생성
    - [x] 상품 목록 조회
  - 리팩터링 목록
    - [x] 상품 도메인에 JPA 사용을 위한 Entity 설정 적용
      - [x] DB 연동 인터페이스 변경 : JDBC TemplateDao -> JPA Repository
      - [ ] 더 이상 참조가 없는 ProductDao 삭제
      - [ ] 원시 타입 포장 객체 적용
    - [ ] 요청/응답 객체 정의 후, 상품 엔티티로 명시된 파라미터 부 변경

메뉴
  - 메뉴 그룹
    - 기능 목록
      - [x] 메뉴 그룹 생성
      - [x] 메뉴 그룹 목록 조회
    - 리팩터링 목록
      - [x] 메뉴 그룹 도메인에 JPA 사용을 위한 Entity 설정 적용
        - [x] DB 연동 인터페이스 변경 : JDBC TemplateDao -> JPA Repository
        - [x] 더 이상 참조가 없는 MenuGroupDao 삭제
        - [ ] 원시 타입 포장 객체 적용 
      - [x] 메뉴그룹 생성 요청/응답 객체 정의 및 엔티티로 명시된 파라미터 부 변경
  
  - 메뉴
    - 기능 목록
      - [x] 메뉴 생성 : `메뉴` 등록 시, `메뉴 상품`을 함께 등록
      - [x] 메뉴 목록 조회 : `메뉴` 목록 조회 시, 메뉴에 포함된 `메뉴 상품`을 함께 조회
    - 리팩터링 목록
      - [x] 메뉴 도메인에 JPA 사용을 위한 Entity 설정 및 관련 도메인의 연관관계 매핑 설정
        - [x] 메뉴 : 메뉴 그룹 = N:1
        - [x] 메뉴 : 메뉴 상품 = 1:N
      - [x] 메뉴 생성 요청/응답 객체 정의 및 메뉴 엔티티로 명시된 파라미터 부 변경
        - 메뉴 엔티티는 메뉴 그룹과 메뉴 상품 엔티티와 연관관계를 가지고 있어 요청/응답객체로 사용 시 다음과 같은 문제 발생 
          - 응답 시, 트랜잭션 외부에서 FetchType.LAZY로 설정된 프록시 객체 초기화 과정에서 LazyInitializedException 발생
          - 요청 시, 메뉴 엔티티를 직렬화 하는 과정에서 메뉴상품의 메뉴 필드와 순환참조가 발생하여 StackOverFlow 발생
      - [x] 메뉴 상품 목록 필드를 일급 컬렉션으로 변경
      - [x] 메뉴 상품 도메인과 상품의 요구사항을 처리하는 중간 계층 정의
        - [x] 메뉴 상품 요청 객체로부터 상품을 조회하여 메뉴 상품 객체를 산출하는 기능 구현

테이블
  - 주문 테이블
    - [x] 주문 테이블 생성
    - [x] 주문 테이블 목록 조회
    - [x] 주문 테이블의 사용 가능 상태 변경
    - [x] 주문 테이블 객수 변경
  - 테이블 그룹 (단체 지정)
    - [x] 테이블 그룹 생성
      - `테이블 그룹` 생성 시, 각 `주문 테이블`의 테이블 그룹 매핑 정보 할당
    - [x] 테이블 그룹 해제
      - `테이블 그룹` 해제 시, 각 `주문 테이블`의 테이블 그룹 매핑 정보 해제

주문
  - [x] 주문 생성
    - `주문` 생성 시, `주문 항목`을 함께 등록
  - [x] 주문 목록 조회
    - `주문` 목록 조회 시, 주문에 포함된 `주문 항목`을 함께 조회
  - [x] 주문 상태 변경

### Step2 피드백 사항 정리
- [x] 주문 생성 시, 주문 요청 항목의 각 메뉴 조회를 위해 여러번 발생하는 쿼리 개선 
- [x] `주문` <-> `주문 테이블`간의 상호 의존 관계 개선 : 상호 참조 의존 관계를 단방향 의존관계로 변경
- [x] 주문 시간 항목을 주문 엔티티 생성 시점이 아닌 외부에서 주입
- [x] 상품 엔티티와 메뉴 엔티티가 공용으로 사용하는 가격 객체를 각 도메인의 요구사항만을 표현할 수 있도록 분리

## [Step.3] 의존성 리팩터링 TODO List
### 도메인 연관도에 따른 분류 : 패키지 분리 기준
#### 함께 생성되고 함께 삭제되는 객체들을 함께 묶어라
  - 메뉴 -> 메뉴 상품
  - 주문 -> 주문 항목
  
#### 도메인 제약 사항을 공유하는 객체들을 함께 묶어라
  - 메뉴 관련
    - `어떤 메뉴`(카테고리, 테마)인지를 표현하는 `메뉴 그룹`이 포함되어야 한다.
    - `메뉴의 주문 대상`을 표현하는 `메뉴 상품`이 포함되어야 한다.
    - 도메인 제약 사항에 따른 연관 도메인 : 메뉴, 메뉴 그룹, 메뉴 상품
    
  - 테이블 관련
    - `테이블 그룹`에 속한 주문 테이블은 사용 가능 여부를 `변경할 수 없다`.
    - `비어 있는` 주문 테이블은 테이블 그룹으로 `묶을 수 없다`. 
    - `주문 테이블`이 `2개 미만`인 경우 테이블 그룹으로 `묶을 수 없다`.
    - `조리중`이거나 `식사중`인 주문 테이블은 사용 가능 여부를 `변경할 수 없다`.
    - 도메인 제약 사항에 따른 연관 도메인 : 주문 테이블, 테이블 그룹, 주문
    
  - 주문 관련
    - `주문 항목`이 없는 경우 `주문 할 수 없다`.
    - `메뉴`가 없는 경우 `주문 할 수 없다`.
    - `비어 있는` 주문 테이블은 `주문 할 수 없다`.
    - 도메인 제약 사항에 따른 연관 도메인 : 주문 항목, 주문 상태, 메뉴, 주문 테이블

#### TODO List
- 위를 통해 분리한 각 도메인 패키지간 싸이클이 발생하는 경우를 살펴 보자
  - 테이블 관련 : 주문 테이블, 테이블 그룹, `주문`
  - 주문 관련 : 주문 항목, 주문 상태, 메뉴, `주문 테이블`
  - [ ] 주문과 주문 테이블의 객체 참조를 간접 참조로 변경

- 객체 참조로 인해 영향을 받으면 안되는 의존성을 분리하자
  - 객체 참조로 인해 트랜잭션이 길어지는 부분을 간접 참조로 변경하여 일시적인 협력 관계로 변경
  - [ ] 주문 항목과 메뉴의 객체 참조를 간접 참조로 변경

---
## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |

---
