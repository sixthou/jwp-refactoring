# 키친포스

## 요구 사항

### 메뉴

- 메뉴 그룹을 만든다
- 모든 메뉴 그룹을 조회한다
- 메뉴를 만든다
    - 메뉴의 가격은 0원 보다 커야한다
    - 메뉴에는 하나 이상의 상품이 포함된다
    - 메뉴의 가격은 포함된 상품 가격의 합보다 작거나 같아야 한다
- 모든 메뉴를 조회한다
- 상품을 만든다
    - 상품의 가격은 0원 이상 이어야 한다
- 모든 상품을 조회한다

### 주문

- 주문을 생성한다
    - 주문 항목이 있어야 한다
    - 주문 항목에 메뉴에 있는 메뉴만 있어야 한다
    - 주문 테이블이 있어야 한다
    - 주문 테이블이 비어있지 않아야 한다
    - 주문을 생성하면 조리상태가 된다
- 모든 주문을 조회한다
- 주문 상태를 변경한다
    - 생성된 주문이 있어야 한다
    - 계산완료 상태에선 변경할 수 없다
- 주문 테이블을 생성한다
- 모든 주문 테이블을 조회한다
- 주문 테이블을 비운다
    - 존재 하는 주문 테이블을 요청해야한다
    - 단체 지정이 되어있지 않아야 한다
    - 테이블에 조리, 식사 중인 주문이 없어야 한다
- 주문 테이블의 방문한 손님 수를 변경한다
    - 손님의 수는 0명 이상이어야 한다
    - 존재 하는 주문 테이블을 요청해야한다
    - 빈 테이블이 아니어야 한다
- 단체 지정을 생성한다
    - 주문 테이블을 2개 이상 지정해야 한다
    - 생성된 주문 테이블로 묶어야 한다
    - 빈 테이블이 포함되어선 안된다
- 단체 지정을 해제한다
    - 이미 주문이 생성되지 않아야 한다

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

## 1단계 - 테스트를 통한 코드 보호

### 기능목록

#### 메뉴

- [X] 메뉴 그룹 관리 기능 인수 테스트 작성
- [X] 메뉴 그룹 서비스 테스트 작성
  - [X] 메뉴 그룹을 저장한다
  - [X] 모든 메뉴 그룹을 조회한다
- [ ] 메뉴 관리 기능 인수 테스트 작성
- [ ] 메뉴 서비스 테스트 작성
  - [ ] 메뉴를 저장한다 
  - [ ] 메뉴의 가격은 0원 보다 커야한다
  - [ ] 메뉴에는 하나 이상의 상품이 포함된다
  - [ ] 메뉴의 가격은 포함된 상품 가격의 합보다 작거나 같아야 한다
  - [ ] 모든 메뉴를 조회한다
- [X] 상품 관리 기능 인수 테스트 작성
- [X] 상품 서비스 테스트 작성
  - [X] 상품을 만든다
  - [X] 상품의 가격은 0원 이상 이어야 한다
  - [X] 모든 상품을 조회한다
