package kitchenpos.domain;

import kitchenpos.repository.MenuGroupRepository;
import kitchenpos.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@DisplayName("메뉴 유효성검사 관련")
@SpringBootTest
class MenuValidatorTest {
    @Autowired
    MenuValidator menuValidator;
    @MockBean
    MenuGroupRepository menuGroupRepository;
    @MockBean
    ProductRepository productRepository;

    Long menuGroupId;
    Long productId1;
    Long productId2;
    Product product1;
    Product product2;

    @BeforeEach
    void setUp() {
        setMenuGroup();
        setProduct();
    }

    void setMenuGroup() {
        menuGroupId = 1L;
        when(menuGroupRepository.existsById(menuGroupId)).thenReturn(true);
    }

    void setProduct() {
        productId1 = 1L;
        productId2 = 2L;
        product1 = new Product("짜장면", 6000);
        product2 = new Product("짬뽕", 7000);

        when(productRepository.findById(productId1)).thenReturn(Optional.of(product1));
        when(productRepository.findById(productId2)).thenReturn(Optional.of(product2));
    }

    @DisplayName("메뉴그룹에 속해야 한다")
    @Test
    void checkMenuGroup() {
        // given
        Long notExistsMenuGroupId = 1000L;
        Menu menu = new Menu("메뉴", 19000, notExistsMenuGroupId);

        // when then
        assertThatThrownBy(() -> menuValidator.checkMenuGroup(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("등록한 메뉴 상품은 존재해야 한다")
    @Test
    void checkProduct() {
        // given
        Long notExistsProductId = 1000L;
        Menu menu = new Menu("메뉴", 15000, menuGroupId);
        menu.addMenuProduct(new MenuProduct(notExistsProductId, 1));

        // when then
        assertThatThrownBy(() -> menuValidator.checkPrice(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 가격은 메뉴 상품의 금액의 합을 초과할 수 없다")
    @Test
    void checkPrice() {
        // given
        Menu menu = new Menu("메뉴", 19001, menuGroupId);
        menu.addMenuProduct(new MenuProduct(productId1, 2)); // 6000 * 2
        menu.addMenuProduct(new MenuProduct(productId2, 1)); // 7000 * 1

        // when then
        assertThatThrownBy(() -> menuValidator.checkPrice(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
