import http from "../http-common"; 

class ValuationService {
  getAllValuations(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/valuation/valuations`, searchDTO);
  }

  get(valuationId) {
    return this.getRequest(`/valuation/${valuationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/valuation?field=${matchData}`, null);
  }

  addValuation(data) {
    return http.post("/valuation/addValuation", data);
  }

  update(data) {
  	return http.post("/valuation/updateValuation", data);
  }
  
  uploadImage(data,valuationId) {
  	return http.postForm("/valuation/uploadImage/"+valuationId, data);
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

export default new ValuationService();
