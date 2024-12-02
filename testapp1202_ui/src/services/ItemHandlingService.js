import http from "../http-common"; 

class ItemHandlingService {
  getAllItemHandlings(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/itemHandling/itemHandlings`, searchDTO);
  }

  get(itemHandlingId) {
    return this.getRequest(`/itemHandling/${itemHandlingId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/itemHandling?field=${matchData}`, null);
  }

  addItemHandling(data) {
    return http.post("/itemHandling/addItemHandling", data);
  }

  update(data) {
  	return http.post("/itemHandling/updateItemHandling", data);
  }
  
  uploadImage(data,itemHandlingId) {
  	return http.postForm("/itemHandling/uploadImage/"+itemHandlingId, data);
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

export default new ItemHandlingService();
