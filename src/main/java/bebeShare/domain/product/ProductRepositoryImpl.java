package bebeShare.domain.product;

import bebeShare.web.dto.productDto.ProductInfoResponseDto;
import bebeShare.web.dto.productDto.ProductRequest;
import bebeShare.web.dto.productDto.QProductInfoResponseDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static bebeShare.domain.like.QDibs.dibs;
import static bebeShare.domain.product.QProduct.product;
import static org.springframework.util.StringUtils.hasText;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ProductInfoResponseDto> findAllProducts(ProductRequest productRequest, Pageable pageable) {

        List<ProductInfoResponseDto> result = queryFactory
                .select(new QProductInfoResponseDto(
                        product.id,
                        product.productName,
                        product.productImage1,
                        dibs.Id,
                        product.createdDate.as("insertDt")
                ))
                .from(product)
                .leftJoin(product.dibs, dibs)
                .where(
                        productNameEq(productRequest.getProductName()),
                        productCategoryEq(productRequest.getCategoryCode()),
                        productStatusEq(productRequest.getProductStatus()),
                        product.deleteYn.eq("N")
                )
                .orderBy(product.createdDate.desc())
                .orderBy(product.createdDate.asc())
                .from(product)
                .offset(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Product> countQuery = queryFactory.select(product)
                .from(product)
                .leftJoin(product.dibs, dibs)
                .where(
                        productNameEq(productRequest.getProductName()),
                        productCategoryEq(productRequest.getCategoryCode()),
                        productStatusEq(productRequest.getProductStatus())
                );

        return PageableExecutionUtils.getPage(result,pageable, () ->countQuery.fetchCount());
    }


    private BooleanExpression productCategoryEq(String categoryCode) {
        return hasText(categoryCode) ? product.productCategory.eq(categoryCode) : null;
    }

    private BooleanExpression productNameEq(String productName) {
        return hasText(productName) ? product.productName.eq(productName) : null;
    }

    private BooleanExpression productStatusEq(String productStatus) {
        return hasText(productStatus) ? product.productStatus.eq(productStatus) : null;
    }

    private BooleanExpression productMemberIdEq(Long id) {
        return id == null ? product.user.id.isNull() : product.user.id.eq(id);
    }


}
