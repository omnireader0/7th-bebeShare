package bebeShare.domain.like;


import bebeShare.BaseEntity;
import bebeShare.domain.product.Product;
import bebeShare.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Dibs extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dibsId")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "memberId")
    private User user;


    @Builder
    public Dibs(User user , Product product) {
        this.user = user;
        this.product = product;
    }

    public void addUserAndProduct(User user , Product product){
        this.user =  user;
        this.product = product;
    }
}
