package io.recruitment.assessment.api;

import io.recruitment.assessment.api.models.Product;
import io.recruitment.assessment.api.models.Role;
import io.recruitment.assessment.api.models.User;
import io.recruitment.assessment.api.repository.ProductRepository;
import io.recruitment.assessment.api.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Seeder implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger logger = Logger.getLogger(Seeder.class.getName());

    private boolean firstRun = true;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Seeder(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!firstRun) return;

        createUserIfNotFound("customer", Role.CUSTOMER);
        createUserIfNotFound("admin", Role.ADMIN);

        firstRun = false;
    }

    @Transactional
    void createUserIfNotFound(final String username, final Role role) {
        try {
            seedProducts();

            User user = userRepository.findOneByUsername(username);
            if (user == null) {
                user = new User(username, "$2y$10$BhldNzf1DeEVO8w4zJhXZ.CN52Xn6/rUuG/GLI.L9VyQLR0zhFUDy", role);
                userRepository.save(user);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Transactional
    void seedProducts() {
        try {
            if (productRepository.count() < 1) {
                productRepository.save(new Product("Rice", "Some rice product", BigDecimal.valueOf(3.99)));
                productRepository.save(new Product("Bean", "Some bean product", BigDecimal.valueOf(9.00)));
                productRepository.save(new Product("Garri", "Some garri product", BigDecimal.valueOf(30.00)));
                productRepository.save(new Product("Floor", "Some floor product", BigDecimal.valueOf(2.00)));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
