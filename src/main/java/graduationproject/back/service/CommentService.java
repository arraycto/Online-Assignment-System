package graduationproject.back.service;

import graduationproject.back.model.request.CommentAssignmentRequest;

/**
 * @Author Pan
 * @Date 2020/12/29 20:46
 */
public interface CommentService {
    int commentAssignment(String userId, CommentAssignmentRequest commentAssignmentRequest);
}
