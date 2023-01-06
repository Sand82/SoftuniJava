const routeId = document.getElementById('routeId').value;
const commentsCtnr = document.getElementById('commentCtnr');

const allComments = [];

const displayComments = (comments) => {
    commentsCtnr.innerHTML = comments.map((c) => {
        return asCommand(c);
    }).join('');
}

function asCommand(c) {

    let stringCommentsId = "commentCtnr-" + c.commentId;

    let commentHtml = '<div id="stringCommentsId">'
    commentHtml += '<h4>' + c.user + ' ' + '('+ c.created + ')' + '</h4><br/>'
    commentHtml += '<p>' + c.massage+ '</p>'
    commentHtml += '</div>'

    return commentHtml;
}

fetch('http://localhost:8080/api/' + routeId + '/comments')
    .then(responce => responce.json())
    .then(data => {
        console.log(data)
        for (const comment of data) {
            allComments.push(comment);
        }
        displayComments(allComments);
    });


