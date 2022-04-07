package bebeShare.domain.product;
import bebeShare.web.dto.productDto.ProductInfoResponseDto;
import bebeShare.web.dto.productDto.ProductRequest;
import bebeShare.web.dto.productDto.QProductInfoResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;


import javax.persistence.EntityManager;
import java.util.List;
import static bebeShare.domain.product.QProduct.product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ProductInfoResponseDto> findAllProducts(ProductRequest productRequest) {

        return queryFactory
                .select(new QProductInfoResponseDto(
                        product.id,
                        product.productName,
                        product.productImage1,
                        product.createdDate.as("insertDt")
                ))
                .where(
                        productNameEq(productRequest.getProductName())
                )
                .orderBy(product.createdDate.desc())
                .from(product)
                .fetch();
    }

    private BooleanExpression productNameEq(String productName) {
        return product.productName.eq(productName);
    }
}
