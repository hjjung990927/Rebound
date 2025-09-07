const commentService = (() => {
    const write = async (comment) => {
        const response = await fetch("/api/comments/write", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(comment)
        });

        if(response.ok) {
            console.log("댓글 작성 성공");
        } else{
            const errorMessage = await response.text();
            console.log(errorMessage)
        }
    }

    const getList = async (postId, callback, page=1) => {
        const response = await fetch(`/api/comments/${page}?postId=${postId}`)
        const commentsCriteria = await response.json();

        if(callback){
            callback(commentsCriteria);
        }
    }

    const update = async (comment) => {
        await fetch(`/api/comments/${comment.id}`,{
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(comment)
        })
    }

    const remove = async (id) => {
        await fetch(`/api/comments/${id}`, {
            method: "DELETE",
        });
    }

    const like = async (commentId, memberId) => {
        const likeDTO = {
            memberId: memberId,
            commentId: commentId
        };

        const response = await fetch("/api/likes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(likeDTO)
        });

        if(response.ok) {
            console.log("추천 성공");
        } else{
            const errorMessage = await response.text();
            console.log(errorMessage);
            alert("이미 추천한 댓글입니다.");
        }
    };

    const removeLike = async (commentId, memberId) => {
        const response = await fetch(`/api/likes/${commentId}?memberId=${memberId}`, {
            method: "DELETE"
        });
        return response.ok;
    };

    return {write: write, getList : getList, update: update, remove: remove, like: like, removeLike: removeLike}
})();