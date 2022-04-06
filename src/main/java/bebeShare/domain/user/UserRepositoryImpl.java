package bebeShare.domain.user;

import bebeShare.web.dto.userDto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static bebeShare.domain.comment.QComment.comment;
import static bebeShare.domain.like.QDibs.dibs;
import static bebeShare.domain.product.QProduct.product;
import static bebeShare.domain.user.QUser.user;


public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<ShareInfoResponseDto> shareInfo(UserRequest userRequest) {


        return queryFactory                 //join 에서 양쪽 테이블 프로젝션을 가져오기때문에 MemberTeamDto를 따로빼서 원하는 값을 가져오게함, 셀렉트방식은 생성자로
                .select(new QShareInfoResponseDto(
                        product.id,
                        product.productName,
                        product.productImage1,
                        product.createdDate.as("insertDt")
                ))
                .from(product)
                .where(
                        productMemberIdEq(userRequest.getMemberId()),
                        productStatus(userRequest.getProductStatus())
                )
                .offset(userRequest.getPage())
                .limit(userRequest.getSize())
                .orderBy(product.createdDate.desc())
                .fetch();
    }


    @Override
    public List<GiveInfoResponseDto> giveInfo(UserRequest userRequest) {
        return queryFactory
                .select(new QGiveInfoResponseDto(
                        product.id,
                        product.productName,
                        product.productImage1,
                        product.createdDate.as("insertDt")

                ))
                .from(product)
                .where(
                        productMemberIdEq(userRequest.getMemberId()),
                        productStatus(userRequest.getProductStatus())
                )
                .offset(userRequest.getPage())
                .limit(userRequest.getSize())
                .orderBy(product.createdDate.desc())
                .fetch();
    }

    @Override
    public List<LikeInfoResponseDto> likeInfo(UserRequest userRequest) {

        return queryFactory
                .select(new QLikeInfoResponseDto(
                        product.id,
                        product.productName,
                        product.productImage1,
                        product.createdDate.as("insertDt")
                )).from(dibs)
                .join(dibs.product , product)
                .join(dibs.user , user)
                .where(
                        memberIdEq(userRequest.getMemberId())
                )
                .offset(userRequest.getPage())
                .limit(userRequest.getSize())
                .fetch();
    }

    @Override
    public List<MemberCommentResponseDto> comments(UserRequest userRequest) {
        return queryFactory
                .select(
                        new QMemberCommentResponseDto(
                                comment.id.as("commentId"),
                                product.productName,
                                comment.commentContent,
                                comment.createdDate.as("insertDt")
                        )
                ).from(comment)
                .join(comment.product, product)
                .join(comment.user, user)
                .where(
                        memberIdEq(userRequest.getMemberId())
                )
                .offset(userRequest.getPage())
                .limit(userRequest.getSize())
                .fetch();
    }


    private BooleanExpression productMemberIdEq(long memberId) {
        return product.user.id.eq(memberId);
    }

    private BooleanExpression memberIdEq(long memberId) {
        return user.id.eq(memberId);
    }

    private BooleanExpression productStatus(String productStatus) {
        return productStatus != null ? product.productStatus.eq(productStatus) : null;
    }


}
