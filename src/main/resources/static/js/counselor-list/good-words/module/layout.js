const layout = (()=>{
    const showList=(todayMessageDTO)=>{
        const today = todayMessageDTO;
        const todayWrap = document.getElementById("todayWrap");
        const blind = document.getElementById("blind");
        let text = '';
        let count = 0;

        today.forEach((today)=>{
            text+=`
            <li data-testid="pro-knowhow-list-item" class="pro-knowhow-list-item">
                <section class="item-wrapper">
                    <a href="">
                        <figure class="image-wrapper">
                            <img alt="커버 이미지" class="content-image" src="/images/goodWords/memo0`
            if(count<4){
                text+=count
                count++
            }else{
                count=0;
                text+=count
                count++
            }

            text+=   `.jpg" lazy="loaded">
                                <div class="item-wrapper-text">
                                    <div class="text01">${today.todayMessageTitle}</div>
                                    <div class="text02">${today.todayMessageContent}</div>
                                </div>
                        </figure>
                        <p class="content-writer">${today.counselorName}</p>
                    </a>
                </section>
            </li>`
        })
        todayWrap.innerHTML=text;
    }
    return{showList:showList};
})();