package bebeShare.domain.product;

import bebeShare.web.dto.productDto.ProductInfoResponseDto;
import bebeShare.web.dto.productDto.ProductRequest;
import bebeShare.web.dto.productDto.QProductInfoResponseDto;
import com.querydsl.core.types.Predicate;
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
                        productNameEq(productRequest.getProductName()),
                        productCategoryEq(productRequest.getCategoryCode())
                )
                .orderBy(product.createdDate.desc())
                .from(product)
                .fetch();
    }

    private Predicate productCategoryEq(String categoryCode) {
        return !categoryCode.isEmpty() ? product.productCategory.eq(categoryCode) : null;
    }

    private BooleanExpression productNameEq(String productName) {
        System.out.println("@@" + productName != null);
        System.out.println("@@" + productName == null);
        System.out.println("@@" + productName == "");
        System.out.println("@@" + productName.isEmpty());
        return !productName.isEmpty() ? product.productName.eq(productName) : null;
    }

}
