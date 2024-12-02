import http from "../http-common"; 

class MobileFirstWebsiteService {
  getAllMobileFirstWebsites(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/mobileFirstWebsite/mobileFirstWebsites`, searchDTO);
  }

  get(mobileFirstWebsiteId) {
    return this.getRequest(`/mobileFirstWebsite/${mobileFirstWebsiteId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/mobileFirstWebsite?field=${matchData}`, null);
  }

  addMobileFirstWebsite(data) {
    return http.post("/mobileFirstWebsite/addMobileFirstWebsite", data);
  }

  update(data) {
  	return http.post("/mobileFirstWebsite/updateMobileFirstWebsite", data);
  }
  
  uploadImage(data,mobileFirstWebsiteId) {
  	return http.postForm("/mobileFirstWebsite/uploadImage/"+mobileFirstWebsiteId, data);
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

export default new MobileFirstWebsiteService();
