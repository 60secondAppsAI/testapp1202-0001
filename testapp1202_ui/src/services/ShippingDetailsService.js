import http from "../http-common"; 

class ShippingDetailsService {
  getAllShippingDetailss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/shippingDetails/shippingDetailss`, searchDTO);
  }

  get(shippingDetailsId) {
    return this.getRequest(`/shippingDetails/${shippingDetailsId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/shippingDetails?field=${matchData}`, null);
  }

  addShippingDetails(data) {
    return http.post("/shippingDetails/addShippingDetails", data);
  }

  update(data) {
  	return http.post("/shippingDetails/updateShippingDetails", data);
  }
  
  uploadImage(data,shippingDetailsId) {
  	return http.postForm("/shippingDetails/uploadImage/"+shippingDetailsId, data);
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

export default new ShippingDetailsService();
