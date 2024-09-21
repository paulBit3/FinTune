
import api from "../api/axiosConfig";

class StockService {

    //get all stocks
    getStockData() {    
       return api.get('/stocks');
    }
    
    
    
    //get stock by symbol
    getStockBySymbol(symbol) {
        return api.get(`/stock/${symbol}`);
    }
    
    
    //delete stock by Id
    delStockById(stock_id) {
        return api.get(`/stock/${stock_id}`);
    }
    
    
    //delete stock by symbol
    delStockBySymbol(symbol) {
        return api.get(`/stock/${symbol}`);
    }
    
    
    }
    export default new StockService();
    