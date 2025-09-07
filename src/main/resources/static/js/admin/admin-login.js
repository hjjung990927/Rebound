const auto_login_checkbox = document.getElementById("auto-login-checkbox");
const auto_login_icon = document.querySelector(".rebound-check-box i");
const modal = document.querySelector(".rebound-modal");
const modal_Inner = document.querySelector(".modal-inner");
const modalButton = document.getElementById("rebound-modal-success-button");
const loginButton = document.querySelector(".btn-login");
const email = document.querySelector('input[name="memberEmail"]');
const pw = document.querySelector('input[name="memberPassword"]');
const modalText = document.querySelector("div[class=body]");
const path =
    console.log(window.location.href.includes("error"));
if (window.location.href.includes("error")){
    modal.classList.add("active");
}

email.addEventListener("blur",(e)=>{
    if(e.target.value===""){
        modal.classList.add("active");
        modalText.innerText="이메일 형식에 맞게 입력해주세요."
    }
});
pw.addEventListener("blur",(e)=>{
    if(e.target.value===""){
        modal.classList.add("active");
        modalText.innerText="비밀번호를 입력해주세요."
    }
});

// 가입된 회원정보가 없거나 비밀번호가 잘못 입력 되었을 상황의 모달
loginButton.addEventListener("click", (e) => {
    if (!email.value || !pw.value) {
        modal.classList.add("active");
        modal_Inner.style.animation = "popUp 0.1s";
        e.preventDefault();
    }
});

modalButton.addEventListener("click", (e) => {
    modal.classList.remove("active");
    e.preventDefault();
});