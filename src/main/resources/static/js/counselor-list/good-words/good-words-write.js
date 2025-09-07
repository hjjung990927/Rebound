// 본문 머리글
const headerInput = document.querySelector(".body-header-input");
const textNumber01 = document.querySelector(".body-header .text-number02");
const textInfo01 = document.querySelector(".input-text-check");

const minLength01 = 8;
textInfo01.style.visibility = "hidden";

headerInput.addEventListener("input", (e) => {
    const inputLength = e.target.value.length;

    if (inputLength < minLength01) {
        headerInput.style.borderColor = "rgb(234, 22, 35)";
        headerInput.style.boxShadow = "rgb(255, 219, 221) 0px 0px 0px 3px";
        textInfo01.style.visibility = "visible";
    } else {
        headerInput.style.borderColor = "rgb(105, 59, 242)";
        headerInput.style.boxShadow = "rgb(197, 182, 255) 0px 0px 0px 3px";
        textInfo01.style.visibility = "hidden";
    }

    textNumber01.textContent = inputLength;
    checkCondition();

    if (inputLength !== 0) {
        textNumber01.style.color = "#693bf2";
    } else if (inputLength === 0) {
        textNumber01.style.color = "rgb(170, 180, 191)";
    }
});

headerInput.addEventListener("blur", (e) => {
    const inputLength = e.target.value.length;

    if (inputLength >= minLength01) {
        headerInput.style.borderColor = "";
        headerInput.style.boxShadow = "";
    } else {
        textInfo01.style.visibility = "visible";
    }
});

headerInput.addEventListener("focus", (e) => {
    const inputLength = e.target.value.length;

    if (inputLength >= minLength01) {
        headerInput.style.borderColor = "rgb(105, 59, 242)";
        headerInput.style.boxShadow = "rgb(197, 182, 255) 0px 0px 0px 3px";
    }
});

// 본문 내용
const bodyInput = document.querySelector(".body-content-text");
const textNumber02 = document.querySelector(".body-content .text-number02");
const textInfo02 = document.querySelector(".body-content .input-text-check");

const minLength02 = 25;
textInfo02.style.visibility = "hidden";

bodyInput.addEventListener("input", (e) => {
    const inputLength = e.target.value.length;

    if (inputLength < minLength02) {
        bodyInput.style.borderColor = "rgb(234, 22, 35)";
        bodyInput.style.boxShadow = "rgb(255, 219, 221) 0px 0px 0px 3px";
        textInfo02.style.visibility = "visible";
    } else {
        bodyInput.style.borderColor = "rgb(105, 59, 242)";
        bodyInput.style.boxShadow = "rgb(197, 182, 255) 0px 0px 0px 3px";
        textInfo02.style.visibility = "hidden";
    }

    textNumber02.textContent = inputLength;
    checkCondition();

    if (inputLength !== 0) {
        textNumber02.style.color = "#693bf2";
    } else if (inputLength === 0) {
        textNumber02.style.color = "rgb(170, 180, 191)";
    }
});

bodyInput.addEventListener("blur", (e) => {
    const inputLength = e.target.value.length;

    if (inputLength >= minLength02) {
        bodyInput.style.borderColor = "";
        bodyInput.style.boxShadow = "";
    } else {
        textInfo02.style.visibility = "visible";
    }
});

bodyInput.addEventListener("focus", (e) => {
    const inputLength = e.target.value.length;

    if (inputLength >= minLength02) {
        bodyInput.style.borderColor = "rgb(105, 59, 242)";
        bodyInput.style.boxShadow = "rgb(197, 182, 255) 0px 0px 0px 3px";
    }
});

// 작성 완료 버튼 활성화
const completeButton = document.querySelector(".complete");

const checkCondition = () => {
    const number01 = Number(textNumber01.textContent);
    const number02 = Number(textNumber02.textContent);

    if (number01 >= minLength01 && number02 >= minLength02) {
        completeButton.style.cursor = "pointer";
        completeButton.style.backgroundColor = "rgb(84, 0, 215)";
    } else {
        completeButton.style.cursor = "not-allowed";
        completeButton.style.backgroundColor = "";
    }
};
