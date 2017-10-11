package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by jt on 12/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJapDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListMethod() throws Exception {

        List<Product> products = (List<Product>) productService.listAll();

        assert products.size() == 5;

    }

    @Test
    public void testGetById() throws Exception {
        Product product = productService.getById(2);

        assertEquals("Product 2", product.getDescription());
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        String description = "Test Description";
        BigDecimal price = new BigDecimal("12.00");
        String imageUrl = "example.com";

        Product returnProduct = new Product();
        returnProduct.setDescription(description);
        returnProduct.setPrice(price);
        returnProduct.setImageUrl(imageUrl);

        Product savedProduct = productService.saveOrUpdate(returnProduct);

        assertEquals(description, savedProduct.getDescription());
        assertEquals(price, savedProduct.getPrice());
        assertEquals(imageUrl, savedProduct.getImageUrl());
    }

    @Test
    public void testDelete() throws Exception {
        productService.delete(2);

        assertNull(productService.getById(2));
    }
}
