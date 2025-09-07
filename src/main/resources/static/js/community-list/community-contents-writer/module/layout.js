const postActionLayout = (() => {
    const showPostActions = (post, memberId) => {
        const actionWrap = document.getElementById("postActionWrap");
        let text = '';

        if(post.postMemberId === memberId) {
            text += `
            <div class="post-actions">
                <div class="dropdown b-dropdown btn-group">
                    <button type="button" class="btn dropdown-toggle btn-secondary"></button>
                    <ul class="dropdown-menu dropdown-menu-right" style="position: absolute; transform: translate3d(-116px, 24px, 0px); top: 0px; left: 0px; will-change: transform;">
                        <li>
                            <a href="/community-list/failure-update/${post.id}" class="dropdown-item">게시글 수정</a>
                            <a href="/community-list/delete/${post.id}" class="dropdown-item" onclick="return confirm('게시글을 삭제하시겠습니까?')">게시글 삭제</a>
                        </li>
                    </ul>
                </div>
            </div>`;
        }

        actionWrap.innerHTML = text;
    };

    return { showPostActions:showPostActions };
})();

const commentLayout = (() => {
    const showList = (CommentsCriteria) => {
        const commentWrap = document.getElementById("commentWrap");
        let text = "";
        CommentsCriteria.comments.forEach((comment) => {
            const likedClass = comment.checkedLike ? "liked" : "";

            let displayGrade;
            if(comment.memberGrade === 'mentor') {
                displayGrade = '멘토회원';
            } else if(comment.memberGrade === 'subscribe') {
                displayGrade = '구독회원';
            } else {
                displayGrade = '일반회원';
            }

            text += `
            <li class="id${comment.id} post-comments-list-item">   
                <div class="post-comment-wrapper">
                    <div class="profile-image provider">
                        <img alt="${comment.memberName}" class="image" src="${`/api/display?filePath=${comment.filePath}&fileName=${comment.fileName}`}">
                    </div>
                    <div class="comment-information">
                        <div class="user-info provider">
                            <div class="user-info-wrapper pro-user">
                                <div class="user">
                                    <span class="user-name prisma-typography body14:semibold primary">${comment.memberName}</span>
                                </div>
                                <div class="pro-user-services">                            
                                    <span class="prisma-typography body12:regular tertiary service">${displayGrade}</span>
                                    <span class="prisma-typography body12:regular tertiary service">+ ${comment.likesCount}개의 추천 수</span>
                                </div>
                            </div>
                            <button class="send-request-button ${likedClass} prisma-typography body12:medium primary">${comment.checkedLike ? "추천됨" : "추천하기"}</button>
                        </div>
                        <div class="post-comment-contents">
                            <p class="text comment-text prisma-typography body14:regular primary">
                                <span class="id${comment.id} comment-span">${comment.commentContent}</span>                 
                                <textarea class="id${comment.id} comment-span" style="display: none;">${comment.commentContent}</textarea>
                            </p>
                        </div>
                        <div class="post-comment-actions">
                            <div class="comment-more-action prisma-typography body12:regular primary">
                                <span class="comment-create-at">${comment.relativeDate}</span>`;
            if(comment.memberId === memberId) {
                text +=`
                                    <div class="dropdown b-dropdown btn-group">
                                        <button type="button" class="btn dropdown-toggle btn-secondary"></button>
                                        <ul class="dropdown-menu dropdown-menu-right">
                                            <li>
                                                <button class="id${comment.id} dropdown-item update-button">댓글 수정</button>
                                                <button class="id${comment.id} dropdown-item update-ok-button" style="display: none">수정 완료</button>
                                                <button class="id${comment.id} dropdown-item cancel-button" style="display: none">수정 취소</button>               
                                                <button class="id${comment.id} dropdown-item delete-button">댓글 삭제</button>
                                            </li>
                                        </ul>
                                    </div> `;
            }
            text += `
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
        });
        commentWrap.innerHTML = text;
    };

    return {showList:showList};
})();




