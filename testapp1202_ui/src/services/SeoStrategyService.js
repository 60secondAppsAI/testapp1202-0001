import http from "../http-common"; 

class SeoStrategyService {
  getAllSeoStrategys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/seoStrategy/seoStrategys`, searchDTO);
  }

  get(seoStrategyId) {
    return this.getRequest(`/seoStrategy/${seoStrategyId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/seoStrategy?field=${matchData}`, null);
  }

  addSeoStrategy(data) {
    return http.post("/seoStrategy/addSeoStrategy", data);
  }

  update(data) {
  	return http.post("/seoStrategy/updateSeoStrategy", data);
  }
  
  uploadImage(data,seoStrategyId) {
  	return http.postForm("/seoStrategy/uploadImage/"+seoStrategyId, data);
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

export default new SeoStrategyService();
