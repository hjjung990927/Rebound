let page = 1;
const showList = async (page = 1) => {
    const loading = document.getElementById("loading");

    loading.style.display = "block";
    const postsCriteria = await postService.getPost(postLayout.showList, page);
    setTimeout(() => {
        loading.style.display = "none";
    }, 1000)

    return postsCriteria;
}
showList();

let checkScroll = true;
let postsCriteria;

window.addEventListener("scroll", async (e) => {
    // 현재 스크롤 위치
    const scrollTop = window.scrollY
    // 화면 높이
    const windowHeight = window.innerHeight;
    // 문서 전체 높이
    const documentHeight = document.documentElement.scrollHeight
    if(scrollTop + windowHeight >= documentHeight - 100) {
        //     바닥에 닿았을 때
        if(checkScroll){
            postsCriteria = await showList(++page);
            checkScroll = false;
        }
        setTimeout(() => {
            if(postsCriteria !== null && postsCriteria.criteria.hasMore){
                checkScroll = true
            }
        }, 1200);
    }
})

// 공지 autoSlide 이벤트
const slickSlide = document.querySelector("div.slick-track.slick-cloned");

const firstBanner = slickSlide.firstElementChild;
let count = 0;

const autoSlide = () => {
    count++;
    slickSlide.style.transform = `translate(-${632 * count}px)`;
    slickSlide.style.transition = `transform 0.8s`;

    if (count === 2) {
        setTimeout(() => {
            slickSlide.style.transform = `translate(0px)`;
            slickSlide.style.transition = `transform 0s`;
        }, 1000);
        count = 0;
    }
};

setInterval(autoSlide, 3000);

// 가장 많이 본 게시글
const hoverArea = document.querySelector("section.curation div.slick-slider");
const prevButton = document.querySelector(
    "section.curation button.slick-arrow.slick-prev"
);
const nextButton = document.querySelector(
    "section.curation button.slick-arrow.slick-next"
);
const curationSpan = document.querySelector(
    "section.curation div.curation-header span.curation-page"
);
const hotTopic = document.querySelector("div.slick-list.slick-pick");
let topicCount = 0;

hoverArea.addEventListener("mouseenter", (e) => {
    if (topicCount < 1) {
        prevButton.style.visibility = "hidden";
        nextButton.style.visibility = "visible";
    } else if (topicCount === 1) {
        prevButton.style.visibility = "visible";
        nextButton.style.visibility = "visible";
    } else if (topicCount > 1) {
        prevButton.style.visibility = "visible";
        nextButton.style.visibility = "hidden";
    }
});

prevButton.addEventListener("click", (e) => {
    if (topicCount > 0) {
        topicCount--;
        hotTopic.style.transform = `translate(-${610 * topicCount}px)`;
        hotTopic.style.transition = `transform 0.5s`;
        curationSpan.textContent = `${topicCount + 1}/3`;
    }

    if (topicCount > 0) {
        prevButton.style.visibility = "visible";
    } else {
        prevButton.style.visibility = "hidden";
    }
    if (topicCount < 2) {
        nextButton.style.visibility = "visible";
    } else {
        nextButton.style.visibility = "hidden";
    }
});

nextButton.addEventListener("click", (e) => {
    if (topicCount < 2) {
        topicCount++;
        hotTopic.style.transform = `translate(-${610 * topicCount}px)`;
        hotTopic.style.transition = `transform 0.5s`;
        curationSpan.textContent = `${topicCount + 1}/3`;
    }

    if (topicCount > 0) {
        prevButton.style.visibility = "visible";
    } else {
        prevButton.style.visibility = "hidden";
    }
    if (topicCount < 2) {
        nextButton.style.visibility = "visible";
    } else {
        nextButton.style.visibility = "hidden";
    }
    console.log(topicCount);
});

hoverArea.addEventListener("mouseleave", (e) => {
    prevButton.style.visibility = "hidden";
    nextButton.style.visibility = "hidden";
});

// 위로 가기 버튼 나타나기/숨기기
const topButton = document.getElementById("top-btn");

window.addEventListener("scroll", () => {
    if (window.scrollY > 100) {
        topButton.classList.add("show");
    } else {
        topButton.classList.remove("show");
    }
});

topButton.addEventListener("click", () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
});


