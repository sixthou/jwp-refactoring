package kitchenpos.menu.application;

import static java.util.stream.Collectors.toList;

import java.util.List;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.menu.dto.MenuDto;
import kitchenpos.product.application.ProductService;
import kitchenpos.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final ProductService productService;

    public MenuService(
            final MenuRepository menuRepository,
            final ProductService productService
    ) {
        this.menuRepository = menuRepository;
        this.productService = productService;
    }

    @Transactional
    public MenuDto create(final MenuDto menuDto) {
        Menu menu = menuDto.toMenu();

        List<Product> products = menuDto.getMenuProductDtos().stream()
                .map(menuProductDto -> productService.findProductById(menuProductDto.getProductId()))
                .collect(toList());

        menu.checkSumPriceOfProducts(products);
        return MenuDto.of(menuRepository.save(menu));
    }

    public List<MenuDto> list() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().map(MenuDto::of).collect(toList());
    }
}
