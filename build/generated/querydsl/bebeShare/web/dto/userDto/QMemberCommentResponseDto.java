package bebeShare.web.dto.userDto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * bebeShare.web.dto.userDto.QMemberCommentResponseDto is a Querydsl Projection type for MemberCommentResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberCommentResponseDto extends ConstructorExpression<MemberCommentResponseDto> {

    private static final long serialVersionUID = 670342161L;

    public QMemberCommentResponseDto(com.querydsl.core.types.Expression<Long> commentId, com.querydsl.core.types.Expression<String> productName, com.querydsl.core.types.Expression<String> commentContent, com.querydsl.core.types.Expression<String> productImage, com.querydsl.core.types.Expression<java.time.LocalDateTime> insertDt) {
        super(MemberCommentResponseDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, commentId, productName, commentContent, productImage, insertDt);
    }

}

