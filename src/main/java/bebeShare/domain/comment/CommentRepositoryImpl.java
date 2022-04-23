package bebeShare.domain.comment;

import bebeShare.web.dto.commentDto.CommentInfoResponseDto;
import bebeShare.web.dto.commentDto.CommentsRequest;
import bebeShare.web.dto.commentDto.QCommentInfoResponseDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static bebeShare.domain.comment.QComment.comment;
import static bebeShare.domain.product.QProduct.product;


public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<CommentInfoResponseDto> findByProductId(CommentsRequest commentsRequest, Pageable pageable) {


        List<CommentInfoResponseDto> result = queryFactory
                .select(new QCommentInfoResponseDto(
                        comment.id,
                        comment.commentContent,
                        comment.deleteYn,
                        comment.commentStatus,
                        comment.user.picture,
                        comment.user.id,
                        comment.createdDate.as("insertDt")
                ))
                .from(comment)
                .where(
                        commentProductIdEq(commentsRequest.getProductId())
                )
                .offset(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .orderBy(comment.createdDate.asc())
                .fetch();


        JPAQuery<Comment> countQuery = queryFactory.select(comment)
                .from(comment)
                .where(
                        commentProductIdEq(commentsRequest.getProductId())
                );

        return PageableExecutionUtils.getPage(result, pageable, () -> countQuery.fetchCount());

    }

    private BooleanExpression commentProductIdEq(Long proudctId) {
        return proudctId == null ? comment.product.id.isNull() : product.product.id.eq(proudctId);
    }
}
