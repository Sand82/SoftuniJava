const routeId = document.getElementById('routeId').value;
const commentsCtnr = document.getElementById('commentCtnr');

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

const commentForm = document.getElementById("commentForm");
commentForm.addEventListener("submit", handelCommentSubmit)

const allComments = [];

const displayComments = (comments) => {
    commentsCtnr.innerHTML = comments.map((c) => {
        return asCommand(c);
    }).join('');
}

function asCommand(c) {

    let stringCommentsId = "commentCtnr-" + c.comentId;

    let commentHtml = '<div id="stringCommentsId">'
    commentHtml += '<h4>' + c.user + ' ' + '('+ c.created + ')' + '</h4><br/>'
    commentHtml += '<p>' + c.message+ '</p>'
    commentHtml += '</div>'

    return commentHtml;
}

async function handelCommentSubmit(event) {
    event.preventDefault();

    const form = event.currentTarget;

    const url = form.action;

    const formData = new FormData(form);

    try{

        const responseData = await postFormDataAsJson({url, formData});

        commentsCtnr.insertAdjacentHTML("afterbegin", asCommand(responseData));

        form.reset();

    }catch (error) {

    }
}

async function postFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries())
    const formDataAsJSONString = JSON.stringify(plainFormData);

    const fetchOptions ={
        method: "POST",
        headers: {
            [csrfHeaderName] : csrfHeaderValue,
            "Content-Type" : "application/json",
            "Accept" : "application/json"
        },
        body: formDataAsJSONString
    }

    const response = await fetch(url, fetchOptions);

    if(!response.ok) {
        const errorMassage = await response.text();
        throw new Error(errorMassage);
    }

    return response.json();
}

fetch('http://localhost:8080/api/' + routeId + '/comments')
    .then(responce => responce.json())
    .then(data => {

        for (const comment of data) {
            console.log(comment);
            allComments.push(comment);
        }
        displayComments(allComments);
    });


