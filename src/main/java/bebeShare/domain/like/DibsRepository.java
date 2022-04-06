package bebeShare.domain.like;


import bebeShare.domain.product.Product;
import bebeShare.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DibsRepository extends JpaRepository<Dibs, Long> {
    Optional<Dibs> findByUserAndProduct(User user, Product product);

}
