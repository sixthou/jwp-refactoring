# 키친포스

## 요구 사항

### 상품 (Product)

- 상품을 등록할 수 있다.
- 상품의 가격이 올바르지 않으면 등록할 수 없다.
    - 상품의 가격은 0 원 이상이어야 한다.
- 상품의 목록을 조회할 수 있다.

### 메뉴 그룹 (MenuGroup)

- 메뉴 그룹을 등록할 수 있다.
- 메뉴 그룹의 목록을 조회할 수 있다.

### 메뉴 (Menu)

- 메뉴를 등록할 수 있다.
- 메뉴의 가격이 올바르지 않으면 등록할 수 없다.
    - 메뉴의 가격이 0 원 이상이어야 한다.
    - 메인그룹이 없다면 메뉴를 등록할 수 없다.
    - 메뉴그룹에 메뉴가 존재하지 않는다면 등록할 수 없다.
    - 메뉴의 가격이 메뉴 상품 가격의 합보다 크다면 등록할 수 없다.
- 메뉴의 목록을 조회 할 수 있다.
    - 메뉴 목록 조회 시 메뉴 상품도 함께 조회한다.

### 주문 (Order)

- 주문을 등록할 수 있다.
    - 주문 항목이 없으면 주문할 수 없다.
    - 주문 항목의 메뉴의 개수가 일치하지 않으면 주문 할 수 없다.
    - 주문 테이블의 값이 저장되어 있지 않으면 주문 할 수 없다.
    - 주문 테이블의 값이 비어있다면 주문 할 수 없다.
- 주문의 목록을 조회할 수 있다.
- 주문의 상태를 변경할 수 있다.
    - 주문이 저장되어 있지 않으면 주문의 상태를 변경할 수 없다.
    - 주문의 상태가 완료인 경우 주문의 상태를 변경할 수 없다.

### 단체 지정 (TableGroup)

- 테이블 그룹을 등록할 수 있다.
    - 주문 테이블 리스트가 없거나 테이블이 2개 미만이면 테이블 그룹을 등록할 수 없다.
    - 주문 테이블 리스트가 저장된 주문 테이블 리스트의 개수와 일치하지 않으면 테이블 그룹을 등록할 수 없다.
    - 주문 테이블이 비어있지 않거나 주문 테이블의 다른 그룹 테이블 정보가 있다면 테이블 그룹을 등록할 수 없다.
- 테이블 그룹 등록을 해제할 수 있다.
    - 테이블 그룹의 주문 테이블 리스트 중에 주문상태가 요리 식사 중인 경우 테이블 그룹 등록을 해제할 수 없다.

### 테이블 (Table)

- 테이블을 등록할 수 있다.
- 테이블 목록을 조회 할 수 있다.
- 테이블 상태를 빈 테이블로 변경할 수 있다.
    - 테이블이 값이 저장되어 있지 않으면 테이블의 상태를 변경할 수 없다.
    - 테이블 그룹이 등록되어 있다면 변경할 수 없다.
    - 테이블 주문의 상태가 조리 또는 식사일 경우 테이블의 상태를 변경할 수 없다.
- 테이블에 방문한 손님의 수를 변경할 수 있다.
    - 손님의 수가 0보다 작다면 변경할 수 없다.
    - 주문 테이블의 값이 저장되어 있지 않다면 손님의 수를 변경할 수 없다.
    - 주문 테이블이 빈테이블인 경우 손님의 수를 변경할 수 없다.

## 용어 사전

| 영문명              | 한글명      | 설명                            |
|------------------|----------|-------------------------------|
| product          | 상품       | 메뉴를 관리하는 기준이 되는 데이터           |
| menu group       | 메뉴 그룹    | 메뉴 묶음, 분류                     |
| menu             | 메뉴       | 메뉴 그룹에 속하는 실제 주문 가능 단위        |
| menu product     | 메뉴 상품    | 메뉴에 속하는 수량이 있는 상품             |
| amount           | 금액       | 가격 * 수량                       |
| order table      | 주문 테이블   | 매장에서 주문이 발생하는 영역              |
| empty table      | 빈 테이블    | 주문을 등록할 수 없는 주문 테이블           |
| order            | 주문       | 매장에서 발생하는 주문                  |
| order status     | 주문 상태    | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| number of guests | 방문한 손님 수 | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| table group      | 단체 지정    | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| order line item  | 주문 항목    | 주문에 속하는 수량이 있는 메뉴             |
| eat in           | 매장 식사    | 포장하지 않고 매장에서 식사하는 것           |

---

### 1단계 - 테스트를 통한 코드 보호

1. 요구사항
    - [x] `kitchenpos`패키지 코드를 보고 키친포스의 요구 사항을 README.md에 작성
    - [x] 키친포스의 요구 사항을 토대로 테스트 코드 작성
        - [x] 모든 Business Object에 대한 테스트 코드 작성
        - [x] SpringBootTest 이용한 통합 테스트 코드 또는 ExtendWith(mockitoExtension.class)를  
          이용한 단위 테스트 코드 작성
2. 프로그래밍 요구사항
    - 롬복 없이 미션 진행
