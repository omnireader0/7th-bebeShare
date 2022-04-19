package bebeShare.domain.user;

import bebeShare.web.dto.userDto.*;
import bebeShare.web.dto.userDto.req.CommentRequest;
import bebeShare.web.dto.userDto.req.GiveRequest;
import bebeShare.web.dto.userDto.req.LikeRequest;
import bebeShare.web.dto.userDto.req.ShareRequest;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static bebeShare.domain.comment.QComment.comment;
import static bebeShare.domain.like.QDibs.dibs;
import static bebeShare.domain.product.QProduct.product;
import static bebeShare.domain.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;


public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<ShareInfoResponseDto> shareInfo(ShareRequest shareRequest) {


        return queryFactory                 //join 에서 양쪽 테이블 프로젝션을 가져오기때문에 MemberTeamDto를 따로빼서 원하는 값을 가져오게함, 셀렉트방식은 생성자로
                .select(new QShareInfoResponseDto(
                        product.id,
                        product.productName,
                        product.productImage1,
                        product.createdDate.as("insertDt")
                ))
                .from(product)
                .where(
                        productMemberIdEq(shareRequest.getMemberId()),
                        productStatus(shareRequest.getProductStatus())
                )
//                .offset(shareRequest.getPage())
//                .limit(shareRequest.getSize())
                .orderBy(product.createdDate.desc())
                .fetch();
    }


    @Override
    public List<GiveInfoResponseDto> giveInfo(GiveRequest giveRequest) {
        return queryFactory
                .select(new QGiveInfoResponseDto(
                        product.id,
                        product.productName,
                        product.productImage1,
                        product.createdDate.as("insertDt")

                ))
                .from(product)
                .where(
                        productShareIdEq(giveRequest.getShareId()),
                        productStatus(giveRequest.getProductStatus())
                )
//                .offset(giveRequest.getPage())
//                .limit(giveRequest.getSize())
                .orderBy(product.createdDate.desc())
                .fetch();
    }

    @Override
    public List<LikeInfoResponseDto> likeInfo(LikeRequest likeRequest) {

        return queryFactory
                .select(new QLikeInfoResponseDto(
                        product.id,
                        product.productName,
                        product.productImage1,
                        product.createdDate.as("insertDt")
                )).from(dibs)
                .join(dibs.product, product)
                .join(dibs.user, user)
                .where(
                        likeIdEq(likeRequest.getMemberId())
                )
//                .offset(likeRequest.getPage())
//                .limit(likeRequest.getSize())
                .fetch();
    }

    @Override
    public List<MemberCommentResponseDto> comments(CommentRequest commentRequest) {
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
                        commentMeberIdEq(commentRequest.getMemberId())
                )
//                .offset(commentRequest.getPage())
//                .limit(commentRequest.getSize())
                .fetch();
    }

    private BooleanExpression commentMeberIdEq(Long memberId) {
        return memberId == 0 ? comment.user.id.isNull() : comment.user.id.eq(memberId);
    }

    private BooleanExpression productMemberIdEq(long memberId) {
        return memberId == 0 ? product.user.id.isNull() : product.user.id.eq(memberId);
    }

    private BooleanExpression productShareIdEq(long shareId) {
        return shareId == 0 ? product.shareId.isNull() : product.shareId.eq(shareId);
    }

    private BooleanExpression likeIdEq(long likeId) {
        return likeId == 0 ? dibs.user.id.isNull() : dibs.user.id.eq(likeId);
    }

    private BooleanExpression memberIdEq(long memberId) {
        return user.id.eq(memberId);
    }

    private BooleanExpression productStatus(String productStatus) {
        return hasText(productStatus) ? product.productStatus.eq(productStatus) : null;
    }
}
