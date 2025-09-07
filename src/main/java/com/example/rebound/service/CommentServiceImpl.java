package com.example.rebound.service;

import com.example.rebound.common.enumeration.Provider;
import com.example.rebound.dto.CommentAlarmDTO;
import com.example.rebound.dto.CommentCriteriaDTO;
import com.example.rebound.dto.CommentDTO;
import com.example.rebound.repository.CommentDAO;
import com.example.rebound.repository.LikeDAO;
import com.example.rebound.util.PostCriteria;
import com.example.rebound.util.PostDateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;
    private final LikeDAO likeDAO;
    private final FileService fileService;
    private final MemberService memberService;


    //    댓글 작성
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void write(CommentDTO commentDTO) {
        commentDAO.save(commentDTO);
        commentDTO.setId(commentDTO.getId());

        Long memberId = commentDTO.getPostMemberId();
        Long commentId = commentDTO.getId();

        CommentAlarmDTO commentAlarmDTO = new CommentAlarmDTO();
        commentAlarmDTO.setMemberId(memberId);
        commentAlarmDTO.setCommentId(commentId);
        commentDAO.saveCommentAlarm(commentAlarmDTO);
    }

//    댓글 조회
    @Override
    public CommentCriteriaDTO getComments(Long postId, int page) {
        CommentCriteriaDTO commentCriteriaDTO = new CommentCriteriaDTO();
        PostCriteria criteria = new PostCriteria(page, commentDAO.getCommentsCountByPostId(postId));

        List<CommentDTO> comments = commentDAO.findAll(postId, criteria);

        comments.forEach(comment -> {
            comment.setRelativeDate(PostDateUtils.toRelativeTime(comment.getCreatedDate()));
            comment.setLikesCount(likeDAO.getLikeCount(comment.getId()));

            fileService.findFileByMemberId(comment.getMemberId())
                    .ifPresentOrElse(fileDTO -> {
                        comment.setFilePath(fileDTO.getFilePath());
                        comment.setFileName(fileDTO.getFileName());
                    }, () -> {
                        comment.setFilePath("");
                        comment.setFileName("no-profile.png");
                    });
        });


        criteria.setHasMore(comments.size() > criteria.getRowCount());
        if (criteria.isHasMore()) comments.remove(comments.size() - 1);

        commentCriteriaDTO.setComments(comments);
        commentCriteriaDTO.setCriteria(criteria);
        return commentCriteriaDTO;
    }

    @Override
    public void update(CommentDTO commentDTO) {
        commentDAO.update(toCommentVO(commentDTO));
    }

    @Override
    public void delete(Long id) {
        commentDAO.delete(id);
    }
}


