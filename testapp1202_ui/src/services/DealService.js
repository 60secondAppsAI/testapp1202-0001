import http from "../http-common"; 

class DealService {
  getAllDeals(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/deal/deals`, searchDTO);
  }

  get(dealId) {
    return this.getRequest(`/deal/${dealId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/deal?field=${matchData}`, null);
  }

  addDeal(data) {
    return http.post("/deal/addDeal", data);
  }

  update(data) {
  	return http.post("/deal/updateDeal", data);
  }
  
  uploadImage(data,dealId) {
  	return http.postForm("/deal/uploadImage/"+dealId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new DealService();
