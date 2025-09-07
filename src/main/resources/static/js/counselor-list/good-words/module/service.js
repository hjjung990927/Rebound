const service = (()=>{
    const getToday = async (callback)=>{
        const response = await fetch("/api/list/today");
        const todayMessageDTO = await response.json();
        if(callback){
            callback(todayMessageDTO);
        }
        return todayMessageDTO;

    }
    return{getToday:getToday}
})();