import http from "../http-common"; 

class DealRepresentativeService {
  getAllDealRepresentatives(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/dealRepresentative/dealRepresentatives`, searchDTO);
  }

  get(dealRepresentativeId) {
    return this.getRequest(`/dealRepresentative/${dealRepresentativeId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/dealRepresentative?field=${matchData}`, null);
  }

  addDealRepresentative(data) {
    return http.post("/dealRepresentative/addDealRepresentative", data);
  }

  update(data) {
  	return http.post("/dealRepresentative/updateDealRepresentative", data);
  }
  
  uploadImage(data,dealRepresentativeId) {
  	return http.postForm("/dealRepresentative/uploadImage/"+dealRepresentativeId, data);
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

export default new DealRepresentativeService();
