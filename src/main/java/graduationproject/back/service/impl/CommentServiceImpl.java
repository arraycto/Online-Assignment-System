package graduationproject.back.service.impl;

import graduationproject.back.mapper.CommentMapper;
import graduationproject.back.model.entity.Comment;
import graduationproject.back.model.request.CommentAssignmentRequest;
import graduationproject.back.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author Pan
 * @Date 2020/12/29 20:46
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public int commentAssignment(String userId, CommentAssignmentRequest commentAssignmentRequest) {
        Comment comment = new Comment();
        comment.setAssignmentId(commentAssignmentRequest.getAssignmentId());
        comment.setAuthorId(userId);
        comment.setCourseId(commentAssignmentRequest.getCourseId());
        comment.setCreateTime(new Date());
        comment.setUserId(commentAssignmentRequest.getUserId());
        comment.setUserName(commentAssignmentRequest.getUserName());
        comment.setContent(commentAssignmentRequest.getContent());
        comment.setScore(commentAssignmentRequest.getScore());

        return commentMapper.commentAssignment(comment);
    }
}
