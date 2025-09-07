service.getToday(layout.showList);
// 위로 가기 버튼 나타나기/숨기기
const noLog = document.querySelector("button[name=noLog]");
const topButton = document.querySelector("a#top-btn.top-btn");

noLog.addEventListener("click",(e)=>{
    alert("상담사 로그인이 필요합니다.");
});

topButton.style.transition = "opacity 0.5s ease, transform 0.5s ease";
topButton.style.opacity = 0;
topButton.style.transform = "trnaslateY(20px)";

window.addEventListener("scroll", () => {
    if (window.scrollY === 0) {
        topButton.style.opacity = 0;
        topButton.style.transform = "translateY(30px)";
        setTimeout(() => {
            topButton.style.visibility = "hidden";
        }, 300);
    } else {
        topButton.style.visibility = "visible";
        topButton.style.opacity = 1;
        topButton.style.transform = "translateY(-20px)";
    }
});