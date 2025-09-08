const kakaoButton=document.getElementById("kakaoLogin");

const id = document.getElementById("memberEmail");
const pw = document.getElementById("memberPassword");
const pwText = document.getElementById("pwText");
const show = document.querySelector(".main-13-3");
const cancel = document.getElementById("cancel");
// console.log(show);

id.addEventListener("focus", (e)=>{
    id.classList.add("active");
});

id.addEventListener("blur", (e)=>{
    id.classList.remove("active");
});

pw.addEventListener("focus", (e) => {
    pw.classList.add("active");
    show.classList.add("active");
});

pw.addEventListener("blur", (e)=>{
    pw.classList.remove("active");
});

pwText.addEventListener("blur", (e) => {
    pw.classList.remove("active");
    show.classList.remove("active");
});

pwText.addEventListener("keyup", (e) => {
    cancel.classList.toggle("active", e.target.value);
});

cancel.addEventListener("click", (e) => {
    pw.value = "";
    show.classList.add("active");
    cancel.classList.remove("active");
});

show.addEventListener("click", (e) => {
    console.log("fdsg");
    if (pw.type === "password") {
        pw.type = "text";
        // console.log("들어옴");
        e.target.innerText = "숨김";
        // console.log("fdnjgsji");
    } else if (pw.type === "text") {
        pw.type = "password";
        e.target.innerText = "표시";
    }
});

// 개인 별 client_id 입력
const KAKAO_CLIENT_ID = '';
// 할당 받은 IP 주소 입력
const REDIRECT_URI = 'http://할당 받은 IP 주소/kakao/login';

kakaoButton.addEventListener("click", () => {
    window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=${KAKAO_CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
});
