package com.example.therestaurant;

import com.example.therestaurant.entity.Cart;
import com.example.therestaurant.entity.Menu;
import com.example.therestaurant.entity.User;
import com.example.therestaurant.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CartTest {

    @Autowired
    private CartRepository cRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem() {
        Menu menu = entityManager.find(Menu.class, 3);
        User customer = entityManager.find(User.class, 1);

        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        newCart.setMenu_id(menu);
        newCart.setQty(2);

        Cart saveCart = cRepo.save(newCart);

        assertTrue(saveCart.getId() > 0);
    }

    @Test
    public void testGetCartByCustomer() {
        User customer = new User();

        List<Cart> cartItems = cRepo.findByCustomer(customer);

        assertEquals(1, cartItems.size());
    }
}
