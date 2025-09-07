// 공지사항
const banner = document.querySelector("div.slick-track");
const firstBanner = document.createElement("div");
const lastBanner = document.createElement("div");
const bannerButtons = document.querySelectorAll("div.slide-button");
const totalBanners = 3;
let bannercount = 1;
const indicator = document.querySelector("div.indicator");


// 오늘의 좋은 말
let good1ButtonCheck = true;
let good1count = 0;
const good1slick = document.querySelector("div.good1-track");
const good1Buttons = document.querySelectorAll(".good1-button");
const good1PrevButton = good1Buttons[0];
const good1NextButton = good1Buttons[1];

const updateGood1ButtonVisibility = () => {
    if (good1count === 0) {
        good1PrevButton.classList.add("slick-disabled");
        good1NextButton.classList.remove("slick-disabled");
    } else if (good1count === 1) {
        good1PrevButton.classList.remove("slick-disabled");
        good1NextButton.classList.add("slick-disabled");
    }
};

good1Buttons.forEach((good1Button) => {
    good1Button.addEventListener("click", (e) => {
        if (
            good1ButtonCheck === false ||
            e.currentTarget.classList.contains("slick-disabled")
        ) {
            return;
        }

        good1ButtonCheck = false;

        const good1ButtonType = e.currentTarget.classList[1];
        if (good1ButtonType === "slick-prev") {
            // console.log("이전 버튼 눌림");
            good1count--;
            updateGood1ButtonVisibility();
            good1slick.style.transform = `translate(-${494 * good1count}px)`;
            good1slick.style.transition = `transform 0.5s`;
        } else {
            // console.log("다음 버튼 눌림");
            good1count++;
            updateGood1ButtonVisibility();
            good1slick.style.transform = `translate(-${494 * good1count}px)`;
            good1slick.style.transition = `transform 0.5s`;
        }
        setTimeout(() => {
            good1ButtonCheck = true;
        }, 500);
    });
});
updateGood1ButtonVisibility();

// 오늘의 좋은 말 2
let good2ButtonCheck = true;
let good2count = 0;
const good2slick = document.querySelector("div.good2-track");
const good2Buttons = document.querySelectorAll(".good2-button");
const good2PrevButton = good2Buttons[0];
const good2NextButton = good2Buttons[1];

const updateGood2ButtonVisibility = () => {
    if (good2count === 0) {
        good2PrevButton.classList.add("slick-disabled");
        good2NextButton.classList.remove("slick-disabled");
    } else if (good2count === 1) {
        good2PrevButton.classList.remove("slick-disabled");
        good2NextButton.classList.add("slick-disabled");
    }
};

good2Buttons.forEach((good2Button) => {
    good2Button.addEventListener("click", (e) => {
        if (
            good2ButtonCheck === false ||
            e.currentTarget.classList.contains("slick-disabled")
        ) {
            return;
        }

        good2ButtonCheck = false;

        const good2ButtonType = e.currentTarget.classList[1];
        if (good2ButtonType === "slick-prev") {
            // console.log("이전 버튼 눌림");
            good2count--;
            updateGood2ButtonVisibility();
            good2slick.style.transform = `translate(-${494 * good2count}px)`;
            good2slick.style.transition = `transform 0.5s`;
        } else {
            // console.log("다음 버튼 눌림");
            good2count++;
            updateGood2ButtonVisibility();
            good2slick.style.transform = `translate(-${494 * good2count}px)`;
            good2slick.style.transition = `transform 0.5s`;
        }
        setTimeout(() => {
            good2ButtonCheck = true;
        }, 500);
    });
});
updateGood2ButtonVisibility();

// 리바운드 이야기
let storyButtonCheck = true;
let storycount = 0;
const storyslick = document.querySelector("div.story-track");
const storyButtons = document.querySelectorAll(".story-button");
const storyPrevButton = storyButtons[0];
const storyNextButton = storyButtons[1];

const updateStoryButtonVisibility = () => {
    if (storycount === 0) {
        storyPrevButton.classList.add("slick-disabled");
        storyNextButton.classList.remove("slick-disabled");
    } else if (storycount < 4) {
        storyPrevButton.classList.remove("slick-disabled");
        storyNextButton.classList.remove("slick-disabled");
    } else {
        storyPrevButton.classList.remove("slick-disabled");
        storyNextButton.classList.add("slick-disabled");
    }
};

storyButtons.forEach((storyButton) => {
    storyButton.addEventListener("click", (e) => {
        if (
            storyButtonCheck === false ||
            e.currentTarget.classList.contains("slick-disabled")
        ) {
            return;
        }

        // console.log("storycount:", storycount);

        storyButtonCheck = false;

        const storyButtonType = e.currentTarget.classList[1];
        if (storyButtonType === "slick-prev") {
            // console.log("이전 버튼 눌림");
            storycount--;
            updateStoryButtonVisibility();
            storyslick.style.transform = `translate(-${329 * storycount}px)`;
            storyslick.style.transition = `transform 0.5s`;
        } else {
            // console.log("다음 버튼 눌림");
            storycount++;
            updateStoryButtonVisibility();
            storyslick.style.transform = `translate(-${329 * storycount}px)`;
            storyslick.style.transition = `transform 0.5s`;
            // console.log(storyNextButton.classList, storycount);
        }
        setTimeout(() => {
            storyButtonCheck = true;
        }, 500);
    });
});
updateStoryButtonVisibility();

// 상담사 가입 소개란
let joinButtonCheck = true;
let joincount = 0;
const joinslick = document.querySelector("div.join-counselor-track");
const joinButtons = document.querySelectorAll(".join-counselor-button");
const joinPrevButton = joinButtons[0];
const joinNextButton = joinButtons[1];

const updateJoinButtonVisibility = () => {
    if (joincount === 0) {
        joinPrevButton.classList.add("slick-disabled");
        joinNextButton.classList.remove("slick-disabled");
    } else if (joincount === 1) {
        joinPrevButton.classList.remove("slick-disabled");
        joinNextButton.classList.remove("slick-disabled");
    } else {
        joinPrevButton.classList.remove("slick-disabled");
        joinNextButton.classList.add("slick-disabled");
    }
};

joinButtons.forEach((joinButton) => {
    joinButton.addEventListener("click", (e) => {
        if (
            joinButtonCheck === false ||
            e.currentTarget.classList.contains("slick-disabled")
        ) {
            return;
        }

        // console.log("storycount:", storycount);

        joinButtonCheck = false;

        const joinButtonType = e.currentTarget.classList[1];
        if (joinButtonType === "slick-prev") {
            // console.log("이전 버튼 눌림");
            joincount--;
            updateJoinButtonVisibility();
            joinslick.style.transform = `translate(-${641 * joincount}px)`;
            joinslick.style.transition = `transform 0.5s`;
        } else {
            // console.log("다음 버튼 눌림");
            joincount++;
            updateJoinButtonVisibility();
            joinslick.style.transform = `translate(-${641 * joincount}px)`;
            joinslick.style.transition = `transform 0.5s`;
            // console.log(storyNextButton.classList, storycount);
        }
        setTimeout(() => {
            joinButtonCheck = true;
        }, 500);
    });
});

updateJoinButtonVisibility();

// 공지 오토 슬라이드
// 슬라이드 개수 가져오기
const slideCount = joinslick.querySelectorAll(".slick-slide").length;

// slick-track의 width를 슬라이드 수 × 641px로 설정
joinslick.style.width = `${slideCount * 641}px`;

firstBanner.innerHTML = `<div class="slick-slide slick-cloned" tabindex="-1" data-index="-1" area-hidden="true" style="width: 970px;">
                            <div>
                                <div class="banner-item" style="width: 100%; display: inline-block;">
                                    <div>
                                        <!-- 970px X 105px -->
                                        <img src="/images/main/banner1.png" alt="" class="banner-image">
                                    </div>
                                </div>
                            </div>
                        </div>`;
lastBanner.innerHTML = `<div class="slick-slide slick-cloned" tabindex="-1" data-index="3" area-hidden="true" style="width: 970px;">
                            <div>
                                <div class="banner-item" style="width: 100%; display: inline-block;">
                                    <div>
                                        <img src="/images/main/banner3.png" alt="" class="banner-image">
                                    </div>
                                </div>
                            </div>
                        </div>`;

firstBanner.classList.add("slick-cloned");
lastBanner.classList.add("slick-cloned");
banner.appendChild(firstBanner);
banner.prepend(lastBanner);

const updateBannerIndicator = () => {
    let currentDisplayCount = bannercount;
    if (bannercount === 0) {
        currentDisplayCount = totalBanners;
    } else if (bannercount === totalBanners + 1) {
        currentDisplayCount = 1;
    }
    indicator.innerHTML = `${currentDisplayCount}/${totalBanners}`;
};

banner.style.transform = `translate(-970px)`;

const autoSlide = () => {
    bannercount++;
    banner.style.transform = `translate(-${970 * bannercount}px)`;
    banner.style.transition = `transform 0.5s`;

    if (bannercount === 4) {
        setTimeout(() => {
            banner.style.transform = `translate(-970px)`;
            banner.style.transition = `transform 0s`;
        }, 500);
        bannercount = 1;
    }
    updateBannerIndicator();
};

let autoSlideInterval = setInterval(autoSlide, 2000);
let bannerButtonCheck = true;

// 공지 배너 좌/우 버튼
bannerButtons.forEach((bannerButton) => {
    const img = bannerButton.firstElementChild;
    img.addEventListener("click", (e) => {
        if (!bannerButtonCheck) {
            return;
        }
        bannerButtonCheck = false;
        clearInterval(autoSlideInterval);

        const bannerButtonType = e.target.parentElement.classList[1];
        if (bannerButtonType === "prev-button") {
            bannercount--;
            banner.style.transform = `translate(-${970 * bannercount}px)`;
            banner.style.transition = `transform 0.5s`;
            if (bannercount === 0) {
                setTimeout(() => {
                    banner.style.transform = `translate(-4850px)`;
                    banner.style.transition = `transform 0s`;
                }, 500);
                bannercount = 3;
            }
        } else {
            bannercount++;
            banner.style.transform = `translate(-${970 * bannercount}px)`;
            banner.style.transition = `transform 0.5s`;
            if (bannercount === 4) {
                setTimeout(() => {
                    banner.style.transform = `translate(-970px)`;
                    banner.style.transition = `transform 0s`;
                }, 500);
                bannercount = 1;
            }
        }

        autoSlideInterval = setInterval(autoSlide, 2000);
        updateBannerIndicator();
        setTimeout(() => {
            bannerButtonCheck = true;
        }, 500);
    });
});
updateBannerIndicator();


// 실패 게시글(커뮤니티) 목록
const firstCurationItem = document.getElementById("curation-item1");
const lastCurationItem = document.getElementById("curation-item2");
text = ``
const firstAreaPosts = posts.slice(0, 3);
const lastAreaPosts = posts.slice(3, 6);

firstAreaPosts.forEach((post) => {
    text += `
        <a href="/community-list/${post.id}" class="curation-list-wrapper">
            <div class="curation-contents has-thumbnail">
                <p class="topic-name">실패 경험담</p>
                    <span class="community-preview">
                        <p class="curation-title">${post.postTitle}</p>
                        <p class="curation-content"${post.postContent}</p>
                    </span>
                <div class="curation-reactions">
                    <span class="view-count"></span>
                </div>
            </div>
        </a>
    `
});
firstCurationItem.innerHTML = text;
text = ``;
lastAreaPosts.forEach((post) => {
    text += `
        <a href="/community-list/${post.id}" class="curation-list-wrapper">
            <div class="curation-contents has-thumbnail">
                <p class="topic-name">실패 경험담</p>
                    <span class="community-preview">
                        <p class="curation-title">${post.postTitle}</p>
                        <p class="curation-content"${post.postContent}</p>
                    </span>
                <div class="curation-reactions">
                    <span class="view-count"></span>
                </div>
            </div>
        </a>
    `
});
lastCurationItem.innerHTML = text;

// 오늘의 좋은 말
const goodTrack01 = document.getElementById("good01-track");
const goodTrack02 = document.getElementById("good02-track");
const goodTrack02Container = document.getElementById("exhibition-item");
text = ``;
const oneAreaGood = todayMessages.slice(0, 3);
const firstAreaGood = todayMessages.slice(0, 6);
const lastAreaGood = todayMessages.slice(6, 12);

// 오늘의 좋은 말(1)
if (!member || member.memberGrade == null || member.memberGrade === "none") {
    good1PrevButton.style.display = "none";
    good1NextButton.style.display = "none";
    goodTrack02Container.style.display = "none";

    oneAreaGood.forEach((todayMessage, i) => {
        let todayMessageSrc = `/images/goodWords/memo0${i % 4}.jpg`;

        text += `
            <div tabindex="-1" data-index="0" class="slick-slide slick-active slick-current" style="outline: none; width: 247px;">
                <div>
                    <a href="/counselor-list/good-words" style="width: 100%; display: inline-block; position: relative">
                        <img class="service-image" src="${todayMessageSrc}" alt="">
                        <span class="service-text">${todayMessage.todayMessageContent}</span>
                    </a>
                </div>
            </div>
        `;
    });

    text += `
        <div tabindex="-1" data-index="0" class="slick-slide slick-active slick-current" style="outline: none; width: 247px;">
                <div>
                    <a style="width: 100%; display: inline-block; position: relative">
                        <img class="service-image" src="/images/goodWords/memo04.jpg" style="border: none;">
                        <span class="service-text" style="font-size: 16px; font-weight: 500; text-align: left">리바운드를 구독하고 <br> 더 많은 서비스를 <br> 이용해 보세요!</span>
                    </a>
                </div>
            </div>
    `;
    goodTrack01.innerHTML = text;
}

// 오늘의 좋은 말(2)
else if (member.memberGrade === "mentor" || member.memberGrade === "subscribe") {
    // 오늘의 좋은 말1
    firstAreaGood.forEach((todayMessage, i) => {
        let todayMessageSrc = `/images/goodWords/memo0${i % 4}.jpg`;

        text += `
            <div tabindex="-1" data-index="0" class="slick-slide slick-active slick-current" style="outline: none; width: 247px;">
                <div>
                    <a href="/counselor-list/good-words-member" style="width: 100%; display: inline-block; position: relative">
                        <img class="service-image" src="${todayMessageSrc}" alt="">
                        <span class="service-text">${todayMessage.todayMessageContent}</span>
                    </a>
                </div>
            </div>
        `;
    });
    goodTrack01.innerHTML = text;

    // 오늘의 좋은 말2
    text = ``;

    lastAreaGood.forEach((todayMessage, i) => {
        let todayMessageSrc = `/images/goodWords/memo0${i % 4}.jpg`;

        text += `
            <div tabindex="-1" data-index="0" class="slick-slide slick-active slick-current" style="outline: none; width: 247px;">
                <div>
                    <a href="/counselor-list/good-words-member" style="width: 100%; display: inline-block; position: relative">
                        <img class="service-image" src="${todayMessageSrc}" alt="">
                        <span class="service-text">${todayMessage.todayMessageContent}</span>
                    </a>
                </div>
            </div>
        `;
    });
    goodTrack02.innerHTML = text;
}

// text = ``;
// lastAreaGood.forEach((todayMessage, i) => {
//     let todayMessageSrc = `/images/goodWords/memo0${i % 4}.jpg`;
//
//     text += `
//         <div tabindex="-1" data-index="0" class="slick-slide slick-active slick-current" style="outline: none; width: 247px;">
//             <div>
//                 <a href="/counselor-list/good-words-member" style="width: 100%; display: inline-block; position: relative">
//                     <img class="service-image" src="${todayMessageSrc}" alt="">
//                     <span class="service-text">${todayMessage.todayMessageContent}</span>
//                 </a>
//             </div>
//         </div>
//     `;
// });
// goodTrack02.innerHTML = text;


// 리바운드 이야기
const reboundStory = document.querySelector("div.soomgo-story-slide .story-track")
text =``;
notices.forEach((notice) => {
    text += `
        <div tabindex="-1" data-index="0" class="slick-slide slick-active slick-current" style="outline: none; width: 329px;">
            <div>
                <a href="/customer-center/notice-detail?id=${notice.id}" style="width: 100%; display: inline-block; position: relative">
                    <div class="soomgo-story-image" style="background-image: url(/images/main/공지임시이미지.jpg);"></div>
                    <div class="notice-text">${notice.noticeContent}</div>
                    <p class="soomgo-story-name">${notice.noticeTitle}</p>
                </a>
            </div>
        </div>
    `;
});
reboundStory.innerHTML = text;

// <div tabIndex="-1" data-index="0" className="slick-slide slick-active slick-current"
//      style="outline: none; width: 329px;">
//     <div>
//         <a href="" style="width: 100%; display: inline-block; position: relative">
//             <div className="soomgo-story-image" style="background-image: url(/images/main/공지임시이미지.jpg);"></div>
//             <div className="notice-text">공지 내용이 들어올 공간입니다. 이미지 위에 배치될 예정입니다.</div>
//             <p className="soomgo-story-name">공지 제목</p>
//         </a>
//     </div>
// </div>




